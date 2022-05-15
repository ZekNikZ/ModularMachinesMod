package dev.mattrm.mc.modularmachines.common.old_multiblock;

/**
 * Marks a specified object as something that can check for the validity of a multiblock structure
 */
public interface IMultiblockProvider {
    IMultiblockStructure getStructure();
    void setStructure(IMultiblockStructure structure);
    boolean checkValidity();
}
