package dev.mattrm.mc.modularmachines.api.machine;

public interface IDataPinType<T> extends IPinType {
    Class<T> getType();
}
