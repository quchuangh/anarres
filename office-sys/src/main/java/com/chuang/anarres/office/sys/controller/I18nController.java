package com.chuang.anarres.office.sys.controller;


import com.alibaba.fastjson.JSONAware;
import com.alibaba.fastjson.JSONObject;
import com.chuang.anarres.office.sys.controller.basic.UnsafeCrudController;
import com.chuang.anarres.office.sys.entity.I18n;
import com.chuang.anarres.office.sys.service.II18nService;
import com.chuang.anarres.enums.I18nType;
import com.chuang.anarres.enums.Language;
import com.chuang.tauceti.support.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Comparator;
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

    @GetMapping("/all/client")
    @RequiresPermissions("i18n:read")
    public Result<JSONObject> client() {
        List<I18n> i18ns = i18nService.lambdaQuery().eq(I18n::getTypeGroup, I18nType.CLIENT).list();

        return Result.success();
    }

    @GetMapping("/all/server")
    @RequiresPermissions("i18n:read")
    public Result<List<I18n>> server() {
        return Result.success(i18nService.lambdaQuery().eq(I18n::getTypeGroup, I18nType.SERVER).list());
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
        list.stream().sorted(Comparator.comparing(I18n::getI18n)).forEach(i18n -> {
            putValue(json, i18n.getI18n(), i18n.getMessage());
        });
        return json;
    }

    private void putValue(JSONObject obj, String key, String value) {
//        ("\"" + key.replaceAll("\\.", "\".\"") + "\"")
        String[] props = key.split("\\.");
        JSONObject last = obj;
        for(int i = 0; i < props.length - 1; i++) {
            if(!last.containsKey(props[i]) ) {
                last.put(props[i], new JSONObject());
            } else if(!(last.get(props[i]) instanceof JSONObject)) {
                String v = last.getString(props[i]);
                last.put(props[i], new JSONObject());
                last.getJSONObject(props[i]).put("$", v);
            }
            last = last.getJSONObject(props[i]);
        }

        last.put(props[props.length - 1], value);
    }

    @Override
    public String basePermission() {
        return "i18n:";
    }
}

