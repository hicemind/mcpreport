package org.hm.demo.mcpreport.businessAction;

import org.hm.demo.mcpreport.model.Kpis;

/**
 * Get Kpis
 */
public class GetKpisAction extends ActionWithServiceProvider<Kpis> {

    public Kpis execute(){
        return getServiceProvider().getInternalRepositoryService().getKpis();
    }

}
