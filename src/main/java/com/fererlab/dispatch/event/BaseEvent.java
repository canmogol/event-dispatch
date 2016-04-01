package com.fererlab.dispatch.event;

import java.util.UUID;

public abstract class BaseEvent implements Event {

    private UUID uuid = UUID.randomUUID();
    private Event parent = null;

    public BaseEvent() {
        this(null);
    }

    public BaseEvent(Event parent) {
        this.parent = parent;
    }

    @Override
    public Event getParent() {
        return parent;
    }

    @Override
    public Event getRoot() {
        Event event = this;
        while (event.getParent() != null) {
            event = event.getParent();
        }
        return event;
    }

    public String getUuid() {
        return uuid.toString();
    }

    @Override
    public String toString() {
        return this.getClass().getName() + "{" +
                "uuid=" + uuid +
                ", is " + (parent == null ? "the root node" : "a child-node") +
                '}';
    }

}