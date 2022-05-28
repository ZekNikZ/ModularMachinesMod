package dev.mattrm.mc.modularmachines.api.machine;

import java.util.List;
import java.util.function.Function;

public interface INodeProvider {
    /**
     * Returns a list of Node constructors. If this provider needs to keep
     * track of these nodes, return a function to create the node and store
     * a reference to it. However, the recommended way is to instead create
     * a subclass of Node in which you also pass a reference to the provider
     * and have the node be reactive to the state of the provider, not the
     * other way around.
     *
     * @return a list of Node constructors
     */
    List<Function<Integer, Node>> getNodeBuilders();
}
