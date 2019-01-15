package org.hm.demo.mcpreport.businessService;

import org.hm.demo.mcpreport.model.Communication;
import org.hm.demo.mcpreport.model.Kpis;
import org.hm.demo.mcpreport.model.Metrics;
import org.hm.demo.mcpreport.repository.IInternalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class InternalRepositoryService implements IInternalRepositoryService{

    private IInternalRepository internalRepository;

    @Autowired
    InternalRepositoryService(IInternalRepository internalRepository){
        this.internalRepository = internalRepository;
    }

    @Override
    public void persistData(Collection<Communication> communications, Metrics metrics) {
        internalRepository.persistData(communications);
        internalRepository.persistMetrics(metrics);
    }

    @Override
    public Collection<Communication> getData() {
        return internalRepository.getData();
    }

    @Override
    public Metrics getMetrics() {
        return internalRepository.getMetrics();
    }

    @Override
    public Kpis getKpis() {
        return internalRepository.getKpis();
    }

    @Override
    public void persistKpis(Kpis kpis) {
        internalRepository.persistKpis(kpis);
    }

}
