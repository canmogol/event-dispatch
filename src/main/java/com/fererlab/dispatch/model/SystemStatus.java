package com.fererlab.dispatch.model;

public enum SystemStatus {

    E1_UNKNOWN(1), E2_RUNNING(2), E3_RESTARTING(3), E4_SHUTTING_DOWN(4);

    private final int index;

    SystemStatus(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
