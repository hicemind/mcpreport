package org.hm.demo.mcpreport.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Call communication
 */
public class Call extends Communication {

    int duration;

    @JsonProperty("status_code")
    StatusCode statusCode;

    @JsonProperty("status_description")
    String statusDescription;


    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(StatusCode statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

}
