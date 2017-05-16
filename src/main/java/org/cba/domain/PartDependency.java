package org.cba.domain;

/**
 * Created by adam on 16/05/2017.
 */
public interface PartDependency {
    AssemblyMaterial getAssemblyMaterial();

    int getAmountPerUnit();
}
