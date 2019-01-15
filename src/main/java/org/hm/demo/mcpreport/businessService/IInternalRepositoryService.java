package org.hm.demo.mcpreport.businessService;

import org.hm.demo.mcpreport.model.Communication;
import org.hm.demo.mcpreport.model.Kpis;
import org.hm.demo.mcpreport.model.Metrics;

import java.util.Collection;

/**
 * Service to access the internal repository
 */
public interface IInternalRepositoryService {

    /**
     * Persist data gather from json
     * @param communications
     * @param metrics
     */
    void persistData(Collection<Communication> communications, Metrics metrics);

    /**
     * Get Communication data
     * @return
     */
    Collection<Communication> getData();

    /**
     * Get Metrics
     * @return
     */
    Metrics getMetrics();

    /**
     * Get Kpis
     * @return
     */
    Kpis getKpis();

    /**
     * Persist kpis
     * @param kpis
     */
    void persistKpis(Kpis kpis);

}
