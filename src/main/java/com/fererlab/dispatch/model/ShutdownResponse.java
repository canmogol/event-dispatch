package com.fererlab.dispatch.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ShutdownResponse extends AbstractResponse {

    private SystemStatus systemStatus = SystemStatus.E1_UNKNOWN;

    public ShutdownResponse() {
    }

    public ShutdownResponse(SystemStatus systemStatus) {
        this.systemStatus = systemStatus;
    }

    public SystemStatus getSystemStatus() {
        return systemStatus;
    }

    public void setSystemStatus(SystemStatus systemStatus) {
        this.systemStatus = systemStatus;
    }
}
