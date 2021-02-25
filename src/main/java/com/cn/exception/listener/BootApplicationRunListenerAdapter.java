package com.cn.exception.listener;

import com.cn.exception.utils.SpringApplicationUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class BootApplicationRunListenerAdapter extends ApplicationRunListenerAdapter{
    private boolean bootApp;
    public BootApplicationRunListenerAdapter(SpringApplication application, String[] args){
        super(application,args);
    }

    public void init(){
        bootApp = SpringApplicationUtils.isBootApplication(getApplication());
    }

    @Override
    public void starting() {
        init();
        if(bootApp){
            doStarting();
        }
    }

    protected void doStarting(){

    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
        super.environmentPrepared(environment);
        if(bootApp){
            doEnviromentPrepared(environment);
        }
    }

    protected void doEnviromentPrepared(ConfigurableEnvironment configurableEnvironment){}

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        super.contextPrepared(context);
        if(bootApp){
            doContextPrepared(context);
        }
    }

    protected void doContextPrepared(ConfigurableApplicationContext context){}

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        super.contextLoaded(context);
        if(bootApp){
            doContextLoaded(context);
        }
    }

    protected void doContextLoaded(ConfigurableApplicationContext context){}

    @Override
    public void started(ConfigurableApplicationContext context) {
        super.started(context);
        if(bootApp){
            doStarted(context);
        }
    }

    protected void doStarted(ConfigurableApplicationContext context){}

    @Override
    public void running(ConfigurableApplicationContext context) {
        super.running(context);
        if(bootApp){
            doRunning(context);
        }
    }

    protected void doRunning(ConfigurableApplicationContext context){}

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
        super.failed(context, exception);
        if(bootApp){
            doFailed(context,exception);
        }
    }

    protected void doFailed(ConfigurableApplicationContext context, Throwable exception){}
}
