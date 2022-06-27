package dev.mattrm.mc.modularmachines.common.graph;

import java.util.UUID;

public record Connection(UUID fromNode, String fromPin, UUID toNode, String toPin) {
}
