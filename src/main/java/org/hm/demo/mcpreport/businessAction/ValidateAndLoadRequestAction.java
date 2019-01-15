package org.hm.demo.mcpreport.businessAction;

import org.hm.demo.mcpreport.businessService.IExternalRepositoryService;
import org.hm.demo.mcpreport.businessService.IInternalRepositoryService;
import org.hm.demo.mcpreport.model.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Validate if exist data for the received date
 */
public class ValidateAndLoadRequestAction extends ActionWithServiceProvider<Boolean> {

    private LocalDate dateToValidate;

    /**
     * Create a Validate Load Request Action
     * @param dateToValidate
     */
    public ValidateAndLoadRequestAction(LocalDate dateToValidate) {
        this.dateToValidate = dateToValidate;
    }

    public Boolean execute(){

        IExternalRepositoryService externalRepositoryService = getServiceProvider().getExternalRepositoryService();
        IInternalRepositoryService internalRepositoryService = getServiceProvider().getInternalRepositoryService();

        if (externalRepositoryService.checkIfDataExistsToThisDate(dateToValidate)){
            Calendar startTime =  Calendar.getInstance();
            AbstractMap.SimpleEntry<Collection<Communication>, Metrics> pair = externalRepositoryService.getDataForThisDate(dateToValidate);
            long processDuration = Calendar.getInstance().getTimeInMillis() - startTime.getTimeInMillis();

            //Calculate Metrics
            Metrics metrics = calculateMetrics(pair.getKey(), pair.getValue());
            internalRepositoryService.persistData(pair.getKey(), metrics);

            //Calculate Kpis
            Kpis oldKpis = internalRepositoryService.getKpis();
            String date = dateToValidate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            if (!oldKpis.getProcessedDates().contains(date)){
                Kpis newKpis = updateKpis(pair.getKey(), oldKpis, date, processDuration);
                internalRepositoryService.persistKpis(newKpis);
            }


            return true;
        }
        return false;
    }

    /**
     * Calculate Kpis
     * @param communications
     * @param kpis
     * @param date
     * @param processDuration
     * @return
     */
    private Kpis updateKpis(Collection<Communication> communications, Kpis kpis, String date,long processDuration) {
        kpis.addProcessedDate(date);
        kpis.incrementTotalNumberRows(communications.size());

        communications.forEach(communication -> {
            if (communication instanceof Call) {
                kpis.incrementTotalNumberCalls();
            }else if (communication instanceof Message){
                kpis.incrementTotalNumberMessages();
            }

            String originCountry = String.valueOf(communication.getOrigin()).substring(0, 2);
            String destinationCountry = String.valueOf(communication.getDestination()).substring(0, 2);

            kpis.addOriginCountries(originCountry);
            kpis.addDestinationCountries(destinationCountry);

            kpis.addDurationByFile(date, processDuration);
        });
        return kpis;
    }

    /**
     * Calculate metrics
     * @param communications
     * @param metrics
     * @return
     */
    private Metrics calculateMetrics(Collection<Communication> communications, Metrics metrics) {
        Map<String, Long> totalCallDuration = new HashMap<>();

        communications.forEach(communication -> {
            if (communication instanceof Call) {
                long duration = Long.valueOf(((Call) communication).getDuration());
                String originCountry = String.valueOf(communication.getOrigin()).substring(0, 2);
                metrics.addNumberOfCalls(originCountry);
                totalCallDuration.merge(originCountry, duration, Long::sum);

                String destinationCountry = String.valueOf(communication.getDestination()).substring(0, 2);
                metrics.addNumberOfCalls(destinationCountry);
                totalCallDuration.merge(destinationCountry, duration, Long::sum);
            }else if (communication instanceof Message){
                Message message = (Message) communication;
                Arrays.stream(message.getMessageContent().split(" ")).forEach(word -> {
                    metrics.addWordOccurrence(word);
                });
            }
        });
        totalCallDuration.forEach((country, duration) ->{
            long numberCalls = metrics.getNumberOfCalls().get(country);
            metrics.addAverageCallDuration(country,duration/numberCalls);
        });

        return metrics;
    }


}
