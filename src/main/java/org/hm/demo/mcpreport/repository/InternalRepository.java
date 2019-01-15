package org.hm.demo.mcpreport.repository;

import org.hm.demo.mcpreport.model.Communication;
import org.hm.demo.mcpreport.model.Kpis;
import org.hm.demo.mcpreport.model.Metrics;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class InternalRepository implements IInternalRepository{

    //Simple solution only to simulate a persistence.
    //As I believe this not the scope from the challenge,
    //I just will put the information in this static field.
    //Not pretty.

    private static Collection<Communication> communications;

    private static Metrics metrics;

    private static Kpis kpis = new Kpis();


    @Override
    public void persistData(Collection<Communication> communications) {
        this.communications = communications;
    }

    @Override
    public Collection<Communication> getData() {
        return communications;
    }

    @Override
    public void persistMetrics(Metrics metrics) {
        this.metrics = metrics;
    }

    @Override
    public Metrics getMetrics() {
        return metrics;
    }

    @Override
    public Kpis getKpis() {
        return kpis;
    }

    @Override
    public void persistKpis(Kpis kpis) {
        this.kpis = kpis;
    }

}
