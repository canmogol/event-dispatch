package com.fererlab.dispatch.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LogoutResponse extends AbstractResponse {

    private boolean loggedOut = false;

    public LogoutResponse() {
    }

    public LogoutResponse(boolean loggedOut) {
        this.loggedOut = loggedOut;
    }

    public boolean isLoggedOut() {
        return loggedOut;
    }

    public void setLoggedOut(boolean loggedOut) {
        this.loggedOut = loggedOut;
    }
}
