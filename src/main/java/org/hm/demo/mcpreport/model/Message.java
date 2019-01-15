package org.hm.demo.mcpreport.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Message communication
 */
public class Message extends Communication{

    @JsonProperty("message_content")
    String messageContent;

    @JsonProperty("message_status")
    MessageStatus messageStatus;

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public MessageStatus getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(MessageStatus messageStatus) {
        this.messageStatus = messageStatus;
    }
}
