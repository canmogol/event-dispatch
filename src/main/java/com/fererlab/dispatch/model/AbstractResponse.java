package com.fererlab.dispatch.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AbstractResponse implements Response {

    private Status status= Status.STATUS_OK;;
    private String type = getClass().getSimpleName();

    public AbstractResponse() {
    }

    @Override
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
