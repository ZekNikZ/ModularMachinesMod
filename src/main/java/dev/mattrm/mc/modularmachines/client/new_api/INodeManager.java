package dev.mattrm.mc.modularmachines.client.new_api;

import dev.mattrm.mc.modularmachines.client.new_api.common.node.Node;

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

    default void addToActivationQueue(Node node) {
        this.addToQueue(node.type(), node);
    }

    void addToQueue(Node.Type type, Node node);
}
