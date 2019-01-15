package org.hm.demo.mcpreport.businessAction;

import org.hm.demo.mcpreport.model.Metrics;

/**
 * Get Metrics
 */
public class GetMetricsAction extends ActionWithServiceProvider<Metrics> {

    public Metrics execute(){
        return getServiceProvider().getInternalRepositoryService().getMetrics();
    }

}
