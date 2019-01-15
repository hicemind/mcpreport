package org.hm.demo.mcpreport.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Communication structure
 */
@JsonIgnoreProperties(value = { "message_type" })
public abstract class Communication {

    long timestamp;

    long origin;

    long destination;

    /**
     * Get timestamp
     * @return
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * Set timestamp
     * @param timestamp
     */
    public void setTimestamp(long timestamp) {
        if (timestamp == 0){
            throw new IllegalArgumentException("The timestamp must be a valid number");
        }
        this.timestamp = timestamp;
    }

    /**
     * Get origin
     * @return
     */
    public long getOrigin() {
        return origin;
    }

    /**
     * Set origin
     * @param origin
     */
    public void setOrigin(long origin) {
        this.origin = origin;
    }

    /**
     * Get destination
     * @return
     */
    public long getDestination() {
        return destination;
    }

    /**
     * Set destination
     * @param destination
     */
    public void setDestination(long destination) {
        this.destination = destination;
    }

}
