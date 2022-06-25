package dev.mattrm.mc.modularmachines.common.graph.node;

import dev.mattrm.mc.modularmachines.common.graph.node.Node;

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
