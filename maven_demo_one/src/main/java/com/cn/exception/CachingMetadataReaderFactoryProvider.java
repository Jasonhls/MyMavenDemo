package com.cn.exception;

import com.cn.exception.utils.PropertySourceUtils;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

@Slf4j
@Data
@Component
public class CachingMetadataReaderFactoryProvider implements ApplicationListener<ContextRefreshedEvent>, ApplicationContextAware {

    private CachingMetadataReaderFactory metadataReaderFactory;
    private volatile boolean cleared = false;
    private ApplicationContext applicationContext;
    private ResourcePatternResolver resourcePatternResolver;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if(metadataReaderFactory != null && !cleared &&
                contextRefreshedEvent.getApplicationContext() == this.applicationContext){
            metadataReaderFactory.clearCache();
            cleared = true;
        }
    }

    public void processMetadataReader(Consumer<MetadataReader> consumer){
        List<String> backpackages = PropertySourceUtils.getBasePackages();
        processMetadataReader(consumer,backpackages);
    }

    @SneakyThrows
    public void processMetadataReader(Consumer<MetadataReader> consumer, List<String> packages){
        try {
            List<Resource> resources = getResourceFromPackages(packages);
            distinctProcessResources(consumer,resources);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Resource> getResourceFromPackages(List<String> packages) throws IOException {
        List<Resource> resources = new LinkedList<>();
        for (String pk : packages){
            String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
                    resolveBasePackage(pk) + "/**/*.classAndInterface";
            resources.addAll(Arrays.asList(resourcePatternResolver.getResources(packageSearchPath)));
        }
        return resources;
    }

    protected String resolveBasePackage(String basePackage){
        return ClassUtils.convertClassNameToResourcePath(this.applicationContext.getEnvironment().
                resolveRequiredPlaceholders(basePackage));
    }

    public void distinctProcessResources(Consumer<MetadataReader> consumer, List<Resource> resources) throws IOException {
        HashSet<String> scannedRes = new HashSet<>();
        for (Resource resource : resources){
            String uriStr = getUriStr(resource);
            try {
                if(!scannedRes.add(uriStr)){
                    continue;
                }
                doProcessResource(consumer,resource);
            } catch (IOException e) {
                log.warn("process metadata reader on resource {} failed ",uriStr,e);
            }
        }
    }

    public void doProcessResource(Consumer<MetadataReader> consumer, Resource resource) throws IOException {
        if(resource.isReadable()){
            MetadataReader metadataReader = this.metadataReaderFactory.getMetadataReader(resource);
            consumer.accept(metadataReader);
        }
    }

    public String getUriStr(Resource resource) throws IOException {
        return resource.getURL().toString();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        this.metadataReaderFactory = new CachingMetadataReaderFactory(applicationContext);
        this.resourcePatternResolver = ResourcePatternUtils.getResourcePatternResolver(applicationContext);
    }
}
