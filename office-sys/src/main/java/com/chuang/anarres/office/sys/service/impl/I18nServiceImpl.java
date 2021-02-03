package com.chuang.anarres.office.sys.service.impl;

import com.chuang.anarres.office.sys.event.I18nUpdatedEvent;
import com.chuang.anarres.office.sys.service.II18nService;
import com.chuang.anarres.office.sys.CurrentUser;
import com.chuang.anarres.office.sys.entity.I18n;
import com.chuang.anarres.enums.I18nType;
import com.chuang.anarres.enums.Language;
import com.chuang.anarres.office.sys.mapper.I18nMapper;
import com.chuang.tauceti.tools.basic.HashKit;
import com.chuang.urras.rowquery.RowQueryService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 国际化  服务实现类
 * </p>
 *
 * @author chuang
 * @since 2020-12-21
 */
@Service
public class I18nServiceImpl extends RowQueryService<I18nMapper, I18n> implements II18nService {

    @Resource private ApplicationContext applicationContext;

    @Override
    public boolean save(I18nType type, String i18n, String message, Language language) {
        boolean success =  findOne(type, i18n, language)
                .map(n -> updateById(n.setMessage(message)))
                .orElseGet(() -> {
                    String md5 = HashKit.md5(type + ":" + i18n + ":" + language);
                    I18n o = new I18n().setI18n(i18n).setTypeGroup(type).setLanguage(language).setMessage(message).setMd5(md5).setCreator("admin");
                    return save(o);
                });
        applicationContext.publishEvent(new I18nUpdatedEvent(this));
        return success;
    }

    @Override
    public boolean delete(I18nType type, String i18n) {
        boolean deleted = lambdaUpdate()
                .eq(I18n::getTypeGroup, type)
                .eq(I18n::getI18n, i18n)
                .remove();
        if(deleted) {
            applicationContext.publishEvent(new I18nUpdatedEvent(this));
        }
        return deleted;
    }

    @Override
    public boolean delete(I18nType type, String i18n, Language language) {
        boolean deleted = lambdaUpdate()
                .eq(I18n::getTypeGroup, type)
                .eq(I18n::getI18n, i18n)
                .eq(I18n::getLanguage, language)
                .remove();
        if(deleted) {
            applicationContext.publishEvent(new I18nUpdatedEvent(this));
        }
        return deleted;
    }


    @Resource private CurrentUser currentUser;

//    @Override
//    public void onApplicationEvent(ApplicationStartedEvent event) {
//
//        currentUser.temp("admin", () -> {
//            String parent = "D:\\workspace\\IdeaProjects_new\\anarres-admin-client\\src\\assets\\i18n\\";
//
//            save(parent + "el-GR.json", Language.el_GR);
//            save(parent + "en-US.json", Language.en_US);
//            save(parent + "ko-KR.json", Language.ko_KR);
//            save(parent + "pl-PL.json", Language.pl_PL);
//            save(parent + "tr-TR.json", Language.tr_TR);
//            save(parent + "zh-CN.json", Language.zh_CN);
//            save(parent + "zh-TW.json", Language.zh_TW);
//        });
//
//    }
//
//    private void save(String file, Language language) {
//        try {
//            String str = FileKit.readString(file, "utf-8");
//            JSONObject json = JSONObject.parseObject(str);
//            json.keySet().forEach(s -> {
//                save(I18nType.CLIENT, s, json.getString(s), language);
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
}
