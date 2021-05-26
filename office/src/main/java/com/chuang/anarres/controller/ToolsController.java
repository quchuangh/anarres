package com.chuang.anarres.controller;

import com.chuang.anarres.crud.SystemConfigPolicy;
import com.chuang.tauceti.httpclient.Request;
import com.chuang.tauceti.support.Result;
import com.chuang.tauceti.tools.basic.FileKit;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tools")
@Slf4j
public class ToolsController {

//    @Resource private ISeoDomainProposalService seoDomainProposalService;
//    @Resource private ISeoDomainService domainService;

    @Resource private SystemConfigPolicy systemConfigPolicy;

    @Value("${eden.fetch.save-path}") private String savePath;

    @PostMapping("/open/template")
    public String openTmp(boolean open) {
        systemConfigPolicy.set("TMP_OPEN", open + "", "^(false|true)$");
        return open + "";
    }

    @PostMapping("/open/js")
    public String openJS(boolean open) {
        systemConfigPolicy.set("JS_OPEN", open + "", "^(false|true)$");
        return open + "";
    }

//    @PostMapping("/repair/template")
//    public Result<?> repairTmp() {
//        String[] files = new File(savePath).list();
//
//        if(null == files) {
//            return Result.fail("找不到文件");
//        }
//        for(String file: files) {
//            addVar(savePath + "/" + file + "/index.ftl");
//            addVar(savePath + "/" + file + "/list.ftl");
//            addVar(savePath + "/" + file + "/article.ftl");
//        }
//
//        return Result.success();
//    }
//
//    @PostMapping("/repair/js")
//    public Result repairProposalReference() {
//
//        String[] files = new File(savePath).list();
//
//        if(null == files) {
//            return Result.fail("找不到文件");
//        }
//        for(String file: files) {
//            change(savePath + "/" + file + "/index.ftl");
//            change(savePath + "/" + file + "/list.ftl");
//            change(savePath + "/" + file + "/article.ftl");
//        }
//
//        return Result.success();
//
//    }

    private void addVar(String path) {
        log.info(path + " --> 修改");
        try {
            String ftl = FileKit.readUtf8String(path);
            ftl = ftl.replace("${headBegin!\"\"}", "")
                    .replace("${bodyBegin!\"\"}", "")
                    .replaceFirst("<head.*>", "<head>\n${headBegin!\"\"}")
                    .replace("</head>", "\n${headEnd!\"\"}</head>")
                    .replace("<body.*>", "<body>\n${bodyBegin!\"\"}")
                    .replace("</body>", "\n${bodyEnd!\"\"}</body>\n${bodyNext!\"\"}")
            ;
            FileKit.writeUtf8String(ftl, path);
            log.info(path  + " --> 修改成功");
        } catch (FileNotFoundException e) {

        } catch (Exception e) {
            log.error(path, e);
        }
    }

    private void change(String path) {
        log.info(path + " --> 修改");
        try {
            String ftl = FileKit.readUtf8String(path);

            if(!ftl.contains("da=\"da\"></script>")) {
                log.info(path + " --> 没有JS, 现在开始修复");
                ftl = ftl.replace("</head>", "<script src=\"/public/js/"+ RandomStringUtils.randomAlphabetic(3, 8) + ".js\" da=\"da\"></script></head>");
            }

           FileKit.writeUtf8String(ftl, path);
           log.info(path  + " --> 修改成功");
        } catch (Exception e) {
            log.error(path, e);
        }
    }



    @GetMapping("/feedKeywords")
    public Mono<Result<List<String>>> feedKeywords(String url, Integer times) {
        return Mono.create(sink -> {
            List<String> titles = new ArrayList<>();

            for(int i = 0; i < times; i++) {
                String random = RandomStringUtils.randomAlphabetic(10);
                String requestUrl = url.replace("${random}", random);
                Request.Get(requestUrl).header("user-agent", "Mozilla/5.0 (compatible; Baiduspider/2.0; +http://www.baidu.com/search/spider.html)").build().asyncExecuteAsString().thenAccept(s -> {
                    Document doc = Jsoup.parse(s);
                    titles.add(doc.title());
                    if(titles.size() == times) {
                        sink.success(Result.success(titles));
                    }
                });
            }
        });

    }


}
