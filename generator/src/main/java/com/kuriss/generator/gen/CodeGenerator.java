package com.kuriss.generator.gen;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CodeGenerator {

    public static void main(String[] args) {
        // 从命令行参数获取模块名称、数据库名称和单个表名
        String moduleName = args.length > 0 ? args[0] : "business"; // 默认模块名为 "member"
        String dbName = args.length > 1 ? args[1] : "train_business"; // 默认数据库名为 "train_member"
        String tableName = args.length > 2 ? args[2] : "Station"; // 单个表名，默认是 "example_table"

        // 设置数据库连接信息
        String dbUrl = "jdbc:mysql://localhost:3306/" + dbName + "?useUnicode=true&useSSL=false&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
        String dbUsername = "train_business"; // 替换为你的数据库用户名
        String dbPassword = "123456"; // 替换为你的数据库密码

        // 获取当前项目的根目录路径
        String projectRoot = System.getProperty("user.dir");

        FastAutoGenerator.create(dbUrl, dbUsername, dbPassword)
                // 全局配置
                .globalConfig(builder -> {
                    builder.author("Kuriss")
                            .outputDir(projectRoot + "/" + moduleName + "/src/main/java") // 设置Java文件的输出目录
                            .disableOpenDir();
                })
                // 包配置
                .packageConfig(builder -> {
                    builder.parent("com.kuriss.train") // 设置父包名
                            .moduleName(moduleName) // 设置模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, projectRoot + "/" + moduleName + "/src/main/resources/mapper"));
                })
                // 自定义配置
                .injectionConfig(builder -> {
                    builder.beforeOutputFile((tableInfo, objectMap) -> {
                        Map<String, String> customFile = new HashMap<>();
                        customFile.put("Query.java", projectRoot + "/" + moduleName + "/src/main/java/com/kuriss/train/" + moduleName + "/query/" + tableInfo.getEntityName() + "Query.java");
                        objectMap.put("customFile", customFile);
                    });
                })
                // 策略配置
                .strategyConfig(builder -> {
                    builder.addInclude(tableName) // 设置单个表名
                            .addTablePrefix("t_", "sys_") // 表前缀过滤
                            .entityBuilder()
                            .enableLombok() // 使用 Lombok 简化实体类
                            .naming(NamingStrategy.underline_to_camel) // 下划线转驼峰命名策略
                            .enableTableFieldAnnotation() // 添加 @TableField 注解，以支持关键字字段
                            .controllerBuilder()
                            .enableRestStyle() // 生成 @RestController 控制器
                            .formatFileName("%sController"); // 设置Controller类名格式
                })
                // 使用自定义模板
                .templateConfig(builder -> {
                    builder.controller("/templates/mapper.java.vm") // 设置Controller的自定义模板路径
                            .service("/templates/service.java.vm") // 设置Service接口的自定义模板路径
                            .serviceImpl("/templates/serviceImpl.java.vm");// 设置Service实现类的自定义模板路径
                })
                // 设置模板引擎
                .templateEngine(new VelocityTemplateEngine())
                // 执行代码生成
                .execute();
    }
}
