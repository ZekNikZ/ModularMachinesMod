package dev.mattrm.mc.modularmachines.api.machine;

public interface INodeManager {
    void activateConnections(Node node);
    /*
     * Psuedocode:
     *
     * if node has output control flow pin,
     *   activate connected control flow node(s)
     *
     * if any connected nodes via output pins are set to "automatic", activate them
     */
}
