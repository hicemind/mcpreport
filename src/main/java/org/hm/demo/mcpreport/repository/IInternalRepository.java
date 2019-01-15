package org.hm.demo.mcpreport.repository;

import org.hm.demo.mcpreport.model.Communication;
import org.hm.demo.mcpreport.model.Kpis;
import org.hm.demo.mcpreport.model.Metrics;

import java.util.Collection;

public interface IInternalRepository {

    /**
     * Persist the data in data model application to be query
     * @param communications
     */
    void persistData(Collection<Communication> communications);

    /**
     * Get Communication data
     * @return
     */
    Collection<Communication> getData();

    /**
     * Persist metrics from last processed date
     * @param metrics
     */
    void persistMetrics(Metrics metrics);

    /**
     * Get metrics
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
