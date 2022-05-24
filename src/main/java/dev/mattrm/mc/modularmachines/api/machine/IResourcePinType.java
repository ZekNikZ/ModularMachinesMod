package dev.mattrm.mc.modularmachines.api.machine;

public interface IResourcePinType<T> extends IPinType {
    Class<T> getType();
}
