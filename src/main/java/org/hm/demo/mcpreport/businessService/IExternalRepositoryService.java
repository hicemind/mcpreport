package org.hm.demo.mcpreport.businessService;

import org.hm.demo.mcpreport.model.Communication;
import org.hm.demo.mcpreport.model.Metrics;

import java.time.LocalDate;
import java.util.AbstractMap;
import java.util.Collection;

/**
 * Service to access the external repository
 */
public interface IExternalRepositoryService {

    /**
     * Check if exists records in the external repository for the specified date
     * @param localDate
     * @return
     */
    Boolean checkIfDataExistsToThisDate(LocalDate localDate);

    /**
     * Gather the records from the externa repository to be processed internally
     * @param localDate
     * @return
     */
    AbstractMap.SimpleEntry<Collection<Communication>, Metrics> getDataForThisDate(LocalDate localDate);

}
