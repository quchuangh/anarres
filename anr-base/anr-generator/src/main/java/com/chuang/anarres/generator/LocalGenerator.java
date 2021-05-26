package com.chuang.anarres.generator;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.chuang.tauceti.generator.GenType;
import com.chuang.tauceti.generator.Generator;
import com.chuang.tauceti.generator.config.GenConfig;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 本机生成代码
 * 自定义的Generator重写 initGlobalMap(GenConfig config) 产生的变量通过 cfg.global 访问
 * 自定义的Generator重写 initTableMap(GenConfig config, TableInfo tableInfo) 产生的变量通过 cfg.table 访问
 * 自定义的Generator重写 initContext(GenConfig config, TableInfo info) 产生的变量通过 cfg.tableGen 访问
 *
 * 假设有 a,b,c 3张表
 * cfg.global 全局，3张表的所有生成器都能使用 比如 a 表的controller 生成器创建的变量能在 所有表的所有模板中使用
 * cfg.table  是同一张表的生成器之间共享。  比如 a 表的 controller 生成器创建的变量能在 a表的 controller，service，等所有模板中使用
 * cfg.tableGen 同一张表的同一个生成器使用。  比如 a 表的 controller 生成器创建的变量只能在 controller模板中使用
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
                .rootPackage("com.chuang.anarres.gen")
                .nameConvert(GenType.ENTITY, "%s")// 相加XXXXEntity后缀,填入 %sEntity

                .tablePrefix("t_", "sys_") //表前缀，生成的对象都会去掉前缀
                .includeTables(tables)
                // 添加枚举字段的类型转化，如果生成的表格字段正好在下面的所有配置中，则会将类型改为相应的枚举类型
//                .enums(Gender.class, "sys_user:gender", "sys_user_info:gender")
//                .enums(Language.class, "sys_user:language", "sys_user_info:language", "sys_i18n:language")
//                .enums(RelationType.class, "sys_relation:type")
//                .enums("sys_user", "state", UserStatus.class)
                .global(globalConfig -> {
                    globalConfig.setFileOverride(true);
                })
                .strategy(strategyConfig -> {
                    strategyConfig.setTableFillList(Arrays.asList(
                            new TableFill("updater", FieldFill.INSERT_UPDATE),
                            new TableFill("updated_time", FieldFill.INSERT_UPDATE),
                            new TableFill("creator", FieldFill.INSERT),
                            new TableFill("created_time", FieldFill.INSERT),
                            new TableFill("deleted", FieldFill.INSERT),
                            new TableFill("revision", FieldFill.INSERT)
                    ));
                })
                .mvn(false) // 是否为单maven工程。
                .debug() // 是否打印debug日志
                .lookup("com.chuang.anarres.generator.impl", Generator.class)   //寻找Generator实现类
//                .addImpl(new AngularComponentTs()) // 直接扫描实现，不用加。

                .templateEngine(new VelocityTemplateEngine())
                .gen();                                         // 生成代码

    }

}
