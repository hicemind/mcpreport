package org.hm.demo.mcpreport.businessService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceProvider implements IServiceProvider{

    private IExternalRepositoryService externalRepositoryService;
    private IInternalRepositoryService internalRepositoryService;

    @Autowired
    ServiceProvider(IExternalRepositoryService externalRepositoryService, IInternalRepositoryService internalRepositoryService){
        this.externalRepositoryService = externalRepositoryService;
        this.internalRepositoryService = internalRepositoryService;
    }

    public IExternalRepositoryService getExternalRepositoryService(){
        return externalRepositoryService;
    }

    public IInternalRepositoryService getInternalRepositoryService(){
        return internalRepositoryService;
    }
}
