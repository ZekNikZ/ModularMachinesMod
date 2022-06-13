package dev.mattrm.mc.modularmachines.client.gui.graph;

import java.util.UUID;

public class Connection {
    private final UUID nodeFrom;
    private final String pinFrom;
    private final UUID nodeTo;
    private final String pinTo;

    public Connection(UUID nodeFrom, String pinFrom, UUID nodeTo, String pinTo) {
        this.nodeFrom = nodeFrom;
        this.pinFrom = pinFrom;
        this.nodeTo = nodeTo;
        this.pinTo = pinTo;
    }

    public UUID getNodeFrom() {
        return nodeFrom;
    }

    public String getPinFrom() {
        return pinFrom;
    }

    public UUID getNodeTo() {
        return nodeTo;
    }

    public String getPinTo() {
        return pinTo;
    }
}
