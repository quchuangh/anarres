package com.chuang.anarres.service.polymer.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chuang.anarres.Utils;
import com.chuang.anarres.enums.ProposalStatus;
import com.chuang.anarres.enums.TaskStatus;
import com.chuang.anarres.model.*;
import com.chuang.anarres.service.platform.IPushService;
import com.chuang.anarres.service.platform.PlatformManager;
import com.chuang.anarres.service.polymer.IWebBuildService;
import com.chuang.anarres.service.single.*;
import com.chuang.tauceti.support.exception.BusinessException;
import com.chuang.tauceti.tools.basic.collection.CollectionKit;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Stream;

@Service
@Slf4j
public class WebBuildServiceImpl implements IWebBuildService {

    private static final String[] suffix = new String[] {
            "", ".html", ".php", ".asp", ".aspx", ".shtml", ".htm", ".shtm"
    };

    @Resource private ISeoDomainProposalService proposalService;
    @Resource private ISeoDomainService domainService;
    @Resource private ISeoTemplateService templateService;
    @Resource private IArticleService articleService;
    @Resource private IPushService pushService;
    @Resource private IPlatformCheckContentService platformCheckContentService;
    @Resource private IDownloadTaskService downloadTaskService;

    public synchronized boolean audit(Integer id, String remark, Boolean pass, String username) {
        SeoDomainProposal proposal = proposalService.findById(id)
                .orElseThrow(() -> new BusinessException(-1, "找不到这条记录"));
        if(proposal.getState() != ProposalStatus.RECEIPT) {
            throw new BusinessException(-1, "该记录没有锁定，无法操作");
        }
        if(!username.equals(proposal.getAuditor())) {
            throw new BusinessException(-1, "这个记录已经被别人锁定，你不能操作");
        }

        proposal.setAuditedTime(LocalDateTime.now())
                .setState(pass ? ProposalStatus.PASS : ProposalStatus.REJECT)
                .setRemark(remark);
        proposalService.updateById(proposal);

        if(pass) {
            // 建站
            domainService.findByDomain(proposal.getDomain()).ifPresent(seoDomain -> {
                throw new BusinessException(-1, "已经有一个相同域名的站被创建，本次创建中断");
            });

            //分配文章
            SeoTemplate template = templateService.findByWebsite(proposal.getTemplateWebsite())
                    .orElseThrow(() -> new BusinessException(-1, "模板不存在"));

            int max = Stream.of(
                    template.getIndexATagSize(),
                    template.getListATagSize(),
                    template.getArticleATagSize(),
                    template.getInterlinkSize()
            ).max(Integer::compareTo).orElse(0);


            String dir = Utils.converterToFirstSpell(proposal.getListPageTitle());
            String[] others = proposal.getOtherMenus().split(","); //其他菜单
            max = max + others.length;


            List<Article> articleList =  articleService.findNotUsed(proposal.getType(), max);

            articleList.forEach(article -> {
                article.setUsed(true)
                        .setOwner(proposal.getDomain());
            });
            log.info("网站[{}] -> 模板[{}] -> 文章[{}] -> 查询 -> [{}]", proposal.getDomain(), template.getWebsite(), max, articleList.size());
            JSONArray menus = new JSONArray();

            // 添加list菜单
            JSONObject listJson = new JSONObject();
            listJson.put("title", proposal.getListPageTitle());
            listJson.put("pinyin", dir);
            menus.add(listJson);

            //添加其他菜单
            for (int i = 0; i < others.length; i++) {
                Article article = articleList.get(i);
                article.setUsed(true)
                        .setMenu(true)
                        .setOwner(proposal.getDomain());

                JSONObject json = new JSONObject();
                json.put("title", others[i]);
                json.put("pinyin", Utils.converterToFirstSpell(others[i]));
                json.put("id", article.getId());
                menus.add(json);
            }
            // 分配文章到 Atag
            for (int i = others.length; i < articleList.size(); i++) {
                Article article = articleList.get(i);
                article.setUsed(true)
                        .setMenu(false)
                        .setOwner(proposal.getDomain());
            }
            articleService.updateBatchById(articleList);

            SeoDomain domain = new SeoDomain()
                    .setDomain(proposal.getDomain())
                    .setDescription(proposal.getDescription())
                    .setKeywords(proposal.getKeywords())
                    .setMainKey(proposal.getMainKey())
                    .setServerIp(proposal.getServerIp())
                    .setSuffix(CollectionKit.randomOne(suffix))
                    .setServerTag(proposal.getServerTag())
                    .setDir(dir)
                    .setDailyArticle(3)
                    .setNewSite(true)
                    .setProposalReference(proposal.getReference())
                    .setJsGroup("default")
                    .setArticleTime(LocalTime.of(RandomUtils.nextInt(8,13), RandomUtils.nextInt(0, 59), RandomUtils.nextInt(0, 59)))
                    .setLastArticleTime(LocalDateTime.now())
                    .setMenus(menus.toJSONString())
                    .setType(proposal.getType())
                    .setTemplateWebsite(proposal.getTemplateWebsite())
                    .setTitle(proposal.getTitle());
            domainService.save(domain);

            (((PlatformManager)pushService)).supports().forEach(platformType -> {
                platformCheckContentService.getOrCreate(platformType, domain.getDomain());
            });


        } else {
            // 如果审核拒绝，要减去模板使用次数
            String temp = proposal.getTemplateWebsite();
            templateService.findByWebsite(temp).ifPresent(seoTemplate -> {
                seoTemplate.setUseTimes(seoTemplate.getUseTimes() - 1);
                templateService.updateById(seoTemplate);
            });
            downloadTaskService.lambdaUpdate()
                    .set(DownloadTask::getState, TaskStatus.ENDED)
                    .set(DownloadTask::getFailTimes, 2000)
                    .eq(DownloadTask::getWebsite, proposal.getTemplateWebsite())
                    .eq(DownloadTask::getState, TaskStatus.ERROR)
                    .update();

        }
        return true;
    }

    public void offline(Integer id) {
        SeoDomainProposal proposal = proposalService.findById(id).orElseThrow(() -> new BusinessException(-1, "记录不存在"));

        String reference = proposal.getReference();
        SeoDomain domain = domainService.findByReference(reference).orElseThrow(() -> new BusinessException(-1, "网站不存在"));

        // 删掉站点
        domainService.removeById(domain.getId());

        // 删掉网站使用的文章
        List<Article> articles = articleService.findByOwner(domain.getDomain());
        articles.forEach(article -> article.setUsed(false).setOwner(domain.getDomain()));
        articleService.updateBatchById(articles);


        // 审核状态变更
        proposal.setState(ProposalStatus.CREATED);
        proposalService.updateById(proposal);



    }

}
