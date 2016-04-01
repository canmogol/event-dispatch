package com.fererlab.dispatch.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LoginResponse extends AbstractResponse {

    private boolean logged = false;
    private String name = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }
}
