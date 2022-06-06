package dev.mattrm.mc.modularmachines.api.machine;

import java.util.Map;
import java.util.function.Function;

public interface INodeProvider {
    /**
     * Returns a map of Node names to constructors. If this provider needs to keep
     * track of these nodes, return a function to create the node and store
     * a reference to it. However, the recommended way is to instead create
     * a subclass of Node in which you also pass a reference to the provider
     * and have the node be reactive to the state of the provider, not the
     * other way around.
     *
     * @return a map of Node constructors
     */
    Map<String, Function<Integer, Node>> getNodeBuilders();
}
