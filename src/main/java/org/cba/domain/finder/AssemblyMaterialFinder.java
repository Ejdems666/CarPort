package org.cba.domain.finder;

import io.ebean.Finder;
import org.cba.domain.AssemblyMaterial;
import org.cba.domain.query.QAssemblyMaterial;

public class AssemblyMaterialFinder extends Finder<Integer,AssemblyMaterial> {

  /**
   * Construct using the default EbeanServer.
   */
  public AssemblyMaterialFinder() {
    super(AssemblyMaterial.class);
  }

  /**
   * Construct with a given EbeanServer.
   */
  public AssemblyMaterialFinder(String serverName) {
    super(AssemblyMaterial.class, serverName);
  }

  /**
   * Start a new typed query.
   */
  public QAssemblyMaterial where() {
     return new QAssemblyMaterial(db());
  }

  /**
   * Start a new document store query.
   */
  public QAssemblyMaterial text() {
     return new QAssemblyMaterial(db()).text();
  }
}
