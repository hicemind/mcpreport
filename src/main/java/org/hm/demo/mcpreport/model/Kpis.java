package org.hm.demo.mcpreport.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Kpis {

    /**
     * processed JSON files
     */
    @JsonIgnore
    private Set<String> processedDates = new HashSet<>();

    /**
     * Total number of rows
     */
    private long totalNumberRows;

    /**
     * Total number of calls
     */
    private long totalNumberCalls;

    /**
     * Total number of messages
     */
    private long totalNumberMessages;

    /**
     * Total number of different origin country codes (https://en.wikipedia.org/wiki/MSISDN)
     */
    @JsonIgnore
    private Set<String> originCountries = new HashSet<>();

    /**
     * Total number of different destination country codes (https://en.wikipedia.org/wiki/MSISDN)
     */
    @JsonIgnore
    private Set<String> destinationCountries = new HashSet<>();

    /**
     * Duration of each JSON process
     */
    private Map<String,Long> durationByFile = new HashMap<>();

    public int getTotalProcessedFiles() {
        return processedDates.size();
    }

    public long getTotalNumberRows() {
        return totalNumberRows;
    }

    public void incrementTotalNumberRows() {
        this.totalNumberRows++;
    }

    public void incrementTotalNumberRows(int rowsNumber) {
        this.totalNumberRows+= rowsNumber;
    }

    public long getTotalNumberCalls() {
        return totalNumberCalls;
    }

    public void incrementTotalNumberCalls() {
        this.totalNumberCalls++;
    }

    public long getTotalNumberMessages() {
        return totalNumberMessages;
    }

    public void incrementTotalNumberMessages() {
        this.totalNumberMessages++;
    }

    public int getTotalOriginCountries(){
        return originCountries.size();
    }

    public Set<String> getOriginCountries() {
        return originCountries;
    }

    public void addOriginCountries(String originCountry) {
        this.originCountries.add(originCountry);
    }

    public int getTotalDestinationCountries(){
        return destinationCountries.size();
    }

    public Set<String> getDestinationCountries() {
        return destinationCountries;
    }

    public void addDestinationCountries(String destinationCountry) {
        this.destinationCountries.add(destinationCountry);
    }

    public Map<String, Long> getDurationByFile() {
        return durationByFile;
    }

    public void addDurationByFile(String date, Long durationByFile) {
        this.durationByFile.put(date, durationByFile);
    }

    public Set<String> getProcessedDates() {
        return processedDates;
    }

    public void addProcessedDate(String processedDate) {
        this.processedDates.add(processedDate);
    }

}
