package com.cn.exception;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.type.ClassMetadata;
import org.springframework.util.ClassUtils;

public class CodeMsgEnumProcessor implements BeanFactoryPostProcessor {
    private final CachingMetadataReaderFactoryProvider metadataReaderFactoryProvider;

    public CodeMsgEnumProcessor(CachingMetadataReaderFactoryProvider metadataReaderFactoryProvider) {
        this.metadataReaderFactoryProvider = metadataReaderFactoryProvider;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        String codeMsgClassName = CodeMsg.class.getName();
        this.metadataReaderFactoryProvider.processMetadataReader(metadataReader -> {
            try {
                ClassMetadata classMetadata = metadataReader.getClassMetadata();
                String[] interfaceNames = classMetadata.getInterfaceNames();
                if(classMetadata.isFinal() && "java.lang.Enum".equals(classMetadata.getSuperClassName())){
                    boolean found = false;
                    for (String interfaceName : interfaceNames){
                        if(codeMsgClassName.equals(interfaceName)){
                            found = true;
                            break;
                        }
                    }
                    if(!found){
                        return;
                    }
                    Class<Enum> clazz = (Class<Enum>) ClassUtils.forName(classMetadata.getClassName(), ClassUtils.getDefaultClassLoader());
                    Enum[] enumConstants = clazz.getEnumConstants();
                    CodeMsg codeMsg;
                    for (Enum aEnum : enumConstants){
                        codeMsg = (CodeMsg) aEnum;
                        CodeMsgRegistry.addCodeMsg(codeMsg.getCode(),codeMsg.getMsg());
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }
}
