package org.hm.demo.mcpreport.repository;

import org.hm.demo.mcpreport.model.Communication;
import org.hm.demo.mcpreport.model.Metrics;

import java.time.LocalDate;
import java.util.AbstractMap;
import java.util.Collection;

public interface IExternalRepository {

    /**
     * Check if exists records in the external repository for the specified date
     * @param localDate
     * @return
     */
    Boolean checkIfDataExistsToThisDate(LocalDate localDate);

    /**
     * Gather the records from the external repository to be processed internally
     *
     * Only gather the correct entries.
     * @param localDate
     * @return
     */
    AbstractMap.SimpleEntry<Collection<Communication>, Metrics> getDataForThisDate(LocalDate localDate);



}
