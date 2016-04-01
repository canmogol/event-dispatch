package com.fererlab.dispatch.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ShutdownRequest extends AbstractResponse {

    private boolean immediately = false;

    public ShutdownRequest() {
    }

    public ShutdownRequest(boolean immediately) {
        this.immediately = immediately;
    }

    public boolean isImmediately() {
        return immediately;
    }

    public void setImmediately(boolean immediately) {
        this.immediately = immediately;
    }
}
