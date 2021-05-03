package com.chuang.anarres.office.sys.controller;


import com.alibaba.fastjson.JSONAware;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chuang.anarres.office.sys.controller.basic.ICreateController;
import com.chuang.anarres.office.sys.controller.basic.IRetrieveController;
import com.chuang.anarres.office.sys.controller.basic.UnsafeCrudController;
import com.chuang.anarres.office.sys.entity.I18n;
import com.chuang.anarres.office.sys.model.vo.MenuVO;
import com.chuang.anarres.office.sys.service.II18nService;
import com.chuang.anarres.enums.I18nType;
import com.chuang.anarres.enums.Language;
import com.chuang.tauceti.support.Result;
import com.chuang.urras.rowquery.RowQuery;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 国际化  前端控制器
 * </p>
 *
 * @author chuang
 * @since 2020-12-21
 */
@RestController
@RequestMapping("/sys/i18n")
@Slf4j
public class I18nController extends UnsafeCrudController<I18n, II18nService> {

    @Resource private II18nService i18nService;


    @GetMapping("/json/{lang}")
    public JSONObject lang(@PathVariable("lang") String lang) {
        return Language.parse(lang)
                .map(language -> i18nService.lambdaQuery().eq(I18n::getLanguage, language).list())
                .map(this::toJSON)
                .orElse(new JSONObject());
    }


    @GetMapping("/client/json/{lang}")
    public String clientLang(@PathVariable("lang") String lang) {
        log.info(lang);

        String[] args = lang.split("\\.");

        Optional<List<I18n>> list = Language.parse(args[0])
                .map(language -> i18nService.lambdaQuery().eq(I18n::getTypeGroup, I18nType.CLIENT).eq(I18n::getLanguage, language).list());

        if(args.length > 1 && "properties".equals(args[1])) {
            return list.map(this::toProperties).orElse("");
        } else {
            return list.map(this::toJSON).map(JSONAware::toJSONString).orElse("");
        }
    }

    private String toProperties(List<I18n> i18ns) {
        StringBuilder builder = new StringBuilder();
        i18ns.forEach(i18n -> {
            builder.append(i18n.getI18n()).append("=").append(i18n.getMessage()).append("\n");
        });

        return builder.toString();
    }


    private JSONObject toJSON(List<I18n> list) {
        JSONObject json = new JSONObject();
        list.forEach(i18n -> json.put(i18n.getI18n(), i18n.getMessage()));
        return json;
    }

}

