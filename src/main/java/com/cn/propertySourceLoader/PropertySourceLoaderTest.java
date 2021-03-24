package com.cn.propertySourceLoader;

import org.junit.Test;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.reader.UnicodeReader;

import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

/**
 * @description:
 * @author: helisen
 * @create: 2021-03-24 11:21
 **/
public class PropertySourceLoaderTest {
    /**
     * 测试读取yml后缀的配置文件中的配置
     * @throws IOException
     */
    @Test
    public void test() throws IOException {
        DefaultResourceLoader defaultResourceLoader = new DefaultResourceLoader();
        Resource resource = defaultResourceLoader.getResource("classpath:./application.yml");
        Reader reader = new UnicodeReader(resource.getInputStream());


        LoaderOptions options = new LoaderOptions();
        options.setAllowDuplicateKeys(false);
        Yaml yaml = new Yaml(options);
        Iterator<Object> iterator = yaml.loadAll(reader).iterator();
        while(iterator.hasNext()) {
            Object next = iterator.next();
            System.out.println(next);
        }
    }
}
