package com.fererlab.dispatch.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public interface Response extends Serializable {

    Status getStatus();

    String getType();

}
