//package com.chuang.anarres;
//
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import com.chuang.anarres.office.sys.CurrentUser;
//import com.chuang.anarres.office.sys.entity.Menu;
//import com.chuang.anarres.office.sys.service.IMenuService;
//import com.chuang.urras.rowquery.handlers.ValueGetter;
//import org.springframework.boot.context.event.ApplicationStartedEvent;
//import org.springframework.context.ApplicationListener;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//public class Init implements ApplicationListener<ApplicationStartedEvent> {
//
//    private static final String MENU_JSON = "[{\"text\":\"主导航\",\"i18n\":\"menu.main\",\"key\":\"main\",\"parentId\":0,\"parents\":\"0/\",\"sortRank\":0,\"link\":\"#\",\"externalLink\":\"#\",\"target\":\"_self\",\"hideInBreadcrumb\":true,\"reuse\":false,\"icon\":\"\",\"acl\":null,\"disabled\":false,\"shortcut\":false,\"children\":[{\"text\":\"分析页\",\"i18n\":\"menu.analysis\",\"key\":\"analysis\",\"parentId\":1,\"parents\":\"0/1/2/\",\"sortRank\":1,\"link\":\"/dashboard/analysis\",\"externalLink\":\"/dashboard/analysis\",\"target\":\"_self\",\"hideInBreadcrumb\":true,\"reuse\":false,\"icon\":\"\",\"acl\":null,\"disabled\":false,\"shortcut\":false,\"children\":[]},{\"text\":\"监控页\",\"i18n\":\"menu.monitor\",\"key\":\"monitor\",\"parentId\":1,\"parents\":\"0/1/2/\",\"sortRank\":1,\"link\":\"/dashboard/monitor\",\"externalLink\":\"/dashboard/monitor\",\"target\":\"_self\",\"hideInBreadcrumb\":true,\"reuse\":false,\"icon\":\"\",\"acl\":null,\"disabled\":false,\"shortcut\":false,\"children\":[]},{\"text\":\"工作台\",\"i18n\":\"menu.workplace\",\"key\":\"workplace\",\"parentId\":1,\"parents\":\"0/1/2/\",\"sortRank\":1,\"link\":\"/dashboard/workplace\",\"externalLink\":\"/dashboard/workplace\",\"target\":\"_self\",\"hideInBreadcrumb\":true,\"reuse\":false,\"icon\":\"\",\"acl\":null,\"disabled\":false,\"shortcut\":false,\"children\":[]}]},{\"text\":\"仪表盘\",\"i18n\":\"menu.dashboard\",\"key\":\"dashboard\",\"parentId\":0,\"parents\":\"0/1/\",\"sortRank\":0,\"link\":\"#\",\"externalLink\":\"#\",\"target\":\"_self\",\"hideInBreadcrumb\":true,\"reuse\":false,\"icon\":\"dashboard\",\"acl\":null,\"disabled\":false,\"shortcut\":false,\"children\":[]},{\"text\":\"快捷菜单\",\"i18n\":\"menu.fast\",\"key\":\"fast\",\"parentId\":0,\"parents\":\"0/1/\",\"sortRank\":1,\"link\":\"#\",\"externalLink\":\"#\",\"target\":\"_self\",\"hideInBreadcrumb\":true,\"reuse\":false,\"icon\":\"anticon-rocket\",\"acl\":null,\"disabled\":false,\"shortcut\":false,\"children\":[{\"text\":\"国际化\",\"i18n\":\"menu.i18n\",\"key\":\"i18n\",\"parentId\":6,\"parents\":\"0/1/7/\",\"sortRank\":1,\"link\":\"/sys/i18n\",\"externalLink\":\"/syst/i18n\",\"target\":\"_self\",\"hideInBreadcrumb\":true,\"reuse\":false,\"icon\":\"\",\"acl\":null,\"disabled\":false,\"shortcut\":false,\"children\":[]},{\"text\":\"菜单\",\"i18n\":\"menu.menu\",\"key\":\"menu\",\"parentId\":6,\"parents\":\"0/1/7/\",\"sortRank\":2,\"link\":\"/sys/menu\",\"externalLink\":\"/sys/menu\",\"target\":\"_self\",\"hideInBreadcrumb\":true,\"reuse\":false,\"icon\":\"\",\"acl\":null,\"disabled\":false,\"shortcut\":false,\"children\":[]},{\"text\":\"权限\",\"i18n\":\"menu.permission\",\"key\":\"permission\",\"parentId\":6,\"parents\":\"0/1/7/\",\"sortRank\":3,\"link\":\"/sys/permission\",\"externalLink\":\"/sys/permission\",\"target\":\"_self\",\"hideInBreadcrumb\":true,\"reuse\":false,\"icon\":\"\",\"acl\":null,\"disabled\":false,\"shortcut\":false,\"children\":[]},{\"text\":\"角色\",\"i18n\":\"menu.role\",\"key\":\"role\",\"parentId\":6,\"parents\":\"0/1/7/\",\"sortRank\":4,\"link\":\"/sys/role\",\"externalLink\":\"/sys/role\",\"target\":\"_self\",\"hideInBreadcrumb\":true,\"reuse\":false,\"icon\":\"\",\"acl\":null,\"disabled\":false,\"shortcut\":false,\"children\":[]},{\"text\":\"组织架构\",\"i18n\":\"menu.organization\",\"key\":\"organization\",\"parentId\":6,\"parents\":\"0/1/7/\",\"sortRank\":5,\"link\":\"/sys/organization\",\"externalLink\":\"/sys/organization\",\"target\":\"_self\",\"hideInBreadcrumb\":true,\"reuse\":false,\"icon\":\"\",\"acl\":null,\"disabled\":false,\"shortcut\":false,\"children\":[]},{\"text\":\"职位\",\"i18n\":\"menu.position\",\"key\":\"position\",\"parentId\":6,\"parents\":\"0/1/7/\",\"sortRank\":6,\"link\":\"/sys/position\",\"externalLink\":\"/sys/position\",\"target\":\"_self\",\"hideInBreadcrumb\":true,\"reuse\":false,\"icon\":\"\",\"acl\":null,\"disabled\":false,\"shortcut\":false,\"children\":[]},{\"text\":\"日志\",\"i18n\":\"menu.log\",\"key\":\"log\",\"parentId\":6,\"parents\":\"0/1/7/\",\"sortRank\":7,\"link\":\"/sys/log\",\"externalLink\":\"/sys/log\",\"target\":\"_self\",\"hideInBreadcrumb\":true,\"reuse\":false,\"icon\":\"\",\"acl\":null,\"disabled\":false,\"shortcut\":false,\"children\":[]}]},{\"text\":\"系统\",\"i18n\":\"menu.system\",\"key\":\"system\",\"parentId\":0,\"parents\":\"0/1/\",\"sortRank\":2,\"link\":\"#\",\"externalLink\":\"#\",\"target\":\"_self\",\"hideInBreadcrumb\":true,\"reuse\":false,\"icon\":\"anticon-setting\",\"acl\":null,\"disabled\":false,\"shortcut\":false,\"children\":[]},{\"text\":\"报表\",\"i18n\":\"menu.report\",\"key\":\"report\",\"parentId\":0,\"parents\":\"0/1/\",\"sortRank\":3,\"link\":\"#\",\"externalLink\":\"#\",\"target\":\"_self\",\"hideInBreadcrumb\":true,\"reuse\":false,\"icon\":\"anticon-pie-chart\",\"acl\":null,\"disabled\":false,\"shortcut\":false,\"children\":[]}]";
//
//    @Resource private IMenuService menuService;
//    @Resource private CurrentUser currentUser;
//
//    @Override
//    public void onApplicationEvent(ApplicationStartedEvent event) {
//        currentUser.set("system");
//        if(0 == menuService.count()) {
//            JSONArray array = JSONArray.parseArray(MENU_JSON);
//            List<Menu> menu = toList(array);
//            menuService.saveBatch(menu);
//        }
//    }
//
//
//    private List<Menu> toList(JSONArray ary) {
//
//        List<Menu> list = new ArrayList<>();
//        for (int i = 0; i < ary.size(); i++) {
//            JSONObject obj = ary.getJSONObject(i);
//            Menu menu = obj.toJavaObject(Menu.class);
//            menu.setCanShortcut(false)
//                    .setIcon("")
//                    .setAcl("")
//                    .setEnabled(!obj.getBoolean("disabled"))
//                    .setCode(obj.getString("key"));
//            list.add(menu);
//        }
//        for (int i = 0; i < ary.size(); i++) {
//            JSONObject obj = ary.getJSONObject(i);
//            JSONArray children = obj.getJSONArray("children");
//            if(children.size() > 0) {
//                list.addAll(toList(children));
//            }
//        }
//
//        return list;
//    }
//}
