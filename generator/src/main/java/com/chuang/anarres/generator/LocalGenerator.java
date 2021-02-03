package com.chuang.anarres.generator;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.chuang.anarres.enums.Language;
import com.chuang.anarres.enums.RelationType;
import com.chuang.anarres.enums.PermissionType;
import com.chuang.anarres.enums.UserStatus;
import com.chuang.tauceti.generator.GenType;
import com.chuang.tauceti.generator.Generator;
import com.chuang.tauceti.generator.config.GenConfig;
import com.chuang.tauceti.support.enums.Bank;
import com.chuang.tauceti.support.enums.Gender;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 本机生成代码
 */
public class LocalGenerator {

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入" + tip + "：");
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {

        String[] tables = scanner("表名，多个英文逗号分割").split(",");

        GenConfig.create()
                .author("chuang")
                .jdbcDriver("com.mysql.cj.jdbc.Driver")
                .jdbcUrl("jdbc:mysql://127.0.0.1:3306/anarres?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=CONVERT_TO_NULL&useSSL=false&serverTimezone=CTT")
                .jdbcUsername("root")
                .jdbcPassword("123123")

                .rootPackage("com.chuang.anarres")
                .nameConvert(GenType.ENTITY, "%s")

                .tablePrefix("t_", "sys_")
                .includeTables(tables)

                // 添加枚举字段的类型转化，如果生成的表格字段正好在下面的所有配置中，则会将类型改为相应的枚举类型
                .enums(Bank.class, "t_payment_deal:bank", "t_bank_card:bank", "t_withdraw_deal:bank")
                .enums(Gender.class, "sys_user:gender", "sys_user_info:gender")
                .enums(Language.class, "sys_user:language", "sys_user_info:language", "sys_i18n:language")
                .enums(RelationType.class, "sys_relation:type")
                .enums(PermissionType.class, "sys_role_permission:permission_type")
                .enums("sys_user", "state", UserStatus.class)
                .global(globalConfig -> globalConfig.setFileOverride(true))
                .strategy(strategyConfig -> strategyConfig.setTableFillList(Arrays.asList(
                        new TableFill("updater", FieldFill.INSERT_UPDATE),
                        new TableFill("creator", FieldFill.INSERT)
                )))
                .mvn(false)
                .debug()
                .lookup("com.chuang.anarres.generator.impl", Generator.class)
                .templateEngine(new VelocityTemplateEngine())
                .gen();                                         // 生成代码

    }

}
