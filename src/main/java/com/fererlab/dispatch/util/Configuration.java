package com.fererlab.dispatch.util;


import com.fererlab.dispatch.service.Service;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

@XmlRootElement
public class Configuration {

    private Set<Class<? extends Service>> services = new HashSet<>();
    private Map<String, String> properties = new HashMap<>();

    public Set<Class<? extends Service>> getServices() {
        return services;
    }

    public void setServices(Set<Class<? extends Service>> services) {
        this.services = services;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

}
