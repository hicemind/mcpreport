package org.hm.demo.mcpreport.model;

import java.util.HashMap;
import java.util.Map;

public class Metrics {

    /**
     * Number of rows with missing fields
     */
    private long numberMissingFields;

    /**
     * Number of messages with blank content
     */
    private long numberBlankContent;

    /**
     * Number of rows with fields errors
     */
    private long numberRowFieldsErrors;

    /**
     * Number of calls origin/destination grouped by country code (https://en.wikipedia.org/wiki/MSISDN)
     */
    private Map<String, Long> numberOfCalls = new HashMap<>();

    /**
     * Relationship between OK/KO calls
     */
    //TODO ??

    /**
     * Average call duration grouped by country code (https://en.wikipedia.org/wiki/MSISDN)
     */
    private Map<String, Double> averageCallDuration = new HashMap<>();

    /**
     * Word occurrence ranking for the given words in message_content field.
     */
    private Map<String, Long> wordOccurrence = new HashMap<>();


    public long getNumberMissingFields() {
        return numberMissingFields;
    }

    public void incrementNumberMissingFields() {
        this.numberMissingFields++;
    }

    public long getNumberBlankContent() {
        return numberBlankContent;
    }

    public void incrementNumberBlankContent() {
        this.numberBlankContent++;
    }

    public long getNumberRowFieldsErrors() {
        return numberRowFieldsErrors;
    }

    public void incrementNumberRowFieldsErrors() {
        this.numberRowFieldsErrors++;
    }

    public Map<String, Long> getNumberOfCalls() {
        return numberOfCalls;
    }

    public void addNumberOfCalls(String country) {
        numberOfCalls.merge(country, 1L, Long::sum);
    }

    public Map<String, Double> getAverageCallDuration() {
        return averageCallDuration;
    }

    public void addAverageCallDuration(String country, double duration) {
        averageCallDuration.merge(country, duration, Double::sum);
    }

    public Map<String, Long> getWordOccurrence() {
        return wordOccurrence;
    }

    public void addWordOccurrence(String word) {
        wordOccurrence.merge(word, 1L, Long::sum);
    }
}
