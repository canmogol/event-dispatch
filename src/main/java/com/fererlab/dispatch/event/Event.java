package com.fererlab.dispatch.event;

import java.io.Serializable;

public interface Event extends Serializable {

    String getUuid();

    Event getParent();

    Event getRoot();

}
