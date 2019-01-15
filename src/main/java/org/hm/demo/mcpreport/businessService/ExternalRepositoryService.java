package org.hm.demo.mcpreport.businessService;

import org.hm.demo.mcpreport.model.Communication;
import org.hm.demo.mcpreport.model.Metrics;
import org.hm.demo.mcpreport.repository.IExternalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.AbstractMap;
import java.util.Collection;

@Service
public class ExternalRepositoryService implements IExternalRepositoryService {

    private IExternalRepository externalRepository;

    @Autowired
    ExternalRepositoryService(IExternalRepository externalRepository){
        this.externalRepository = externalRepository;
    }

    @Override
    public Boolean checkIfDataExistsToThisDate(LocalDate localDate) {
        return externalRepository.checkIfDataExistsToThisDate(localDate);
    }

    @Override
    public AbstractMap.SimpleEntry<Collection<Communication>, Metrics> getDataForThisDate(LocalDate localDate) {
        return externalRepository.getDataForThisDate(localDate);
    }
}
