package org.hm.demo.mcpreport.businessAction;

import org.hm.demo.mcpreport.businessService.IServiceProvider;
import org.hm.demo.mcpreport.businessService.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

abstract class ActionWithServiceProvider<T> implements IAction<T> {

    Logger logger = LoggerFactory.getLogger(ActionWithServiceProvider.class);

    private static IServiceProvider serviceProvider;

    public IServiceProvider getServiceProvider(){
        if (serviceProvider == null) {
            AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
            appContext.scan("org.hm.demo.mcpreport.businessService");
            appContext.scan("org.hm.demo.mcpreport.repository");
            appContext.refresh();
            logger.info("Service Provider loaded");

            this.serviceProvider = appContext.getBean(ServiceProvider.class);
        }
        return serviceProvider;
    }
}
