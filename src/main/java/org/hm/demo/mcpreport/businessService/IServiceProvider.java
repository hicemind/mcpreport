package org.hm.demo.mcpreport.businessService;

/**
 * Provide services to access and produce business data
 */
public interface IServiceProvider {

    IExternalRepositoryService getExternalRepositoryService();

    IInternalRepositoryService getInternalRepositoryService();
}
