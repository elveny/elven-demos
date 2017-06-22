/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.groovy;

import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import groovy.lang.Script;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author qiusheng.wu
 * @Filename ScriptTest.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/5/5 17:17</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class ScriptTest {

    @Test
    public void quickstart() throws IllegalAccessException, InstantiationException {

        String groovyText = "\n" +
                "\t\treturn str1.concat(str2) \n" +
                "\t";

        Class<Script> classScript = (Class<Script>)new GroovyClassLoader().parseClass(groovyText);

        Script script = classScript.newInstance();

        Binding binding = new Binding();
        binding.setVariable("str1", "1111");
        binding.setVariable("str2", "2222");
        script.setBinding(binding);
        Object result = script.run();
        System.out.println(result);
    }

    @Test
    public void quickstart1() throws IllegalAccessException, InstantiationException {

        String groovyText = "def str1 = map.get(\"str1\")\n" +
                "def str2 = map.get(\"str2\")\n" +
                "return str1.concat(str2)";

        Class<Script> classScript = (Class<Script>)new GroovyClassLoader().parseClass(groovyText);

        Script script = classScript.newInstance();

        Binding binding = new Binding();
        Map<String, String> map = new HashMap<String, String>();
        map.put("str1", "1111");
        map.put("str2", "2222");
        binding.setVariable("map", map);
        script.setBinding(binding);
        Object result = script.run();
        System.out.println(result);
    }

    @Test
    public void parseClassTest() throws IOException, IllegalAccessException, InstantiationException {
        Class groovyClass = (Class<Script>)new GroovyClassLoader().parseClass(ResourceUtils.getFile("E:\\code\\idea\\elven\\elven-demos\\spring-boot-demo1\\src\\test\\java\\com\\elven\\demo\\springboot1\\test\\groovy\\XmlParserTest.groovy"));

        GroovyObject instance = (GroovyObject)groovyClass.newInstance();//proxy
        instance.invokeMethod("test1", null);
    }
}