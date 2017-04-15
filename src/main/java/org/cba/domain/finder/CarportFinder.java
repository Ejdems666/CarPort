package org.cba.domain.finder;

import io.ebean.Finder;
import org.cba.domain.Carport;
import org.cba.domain.query.QCarport;

public class CarportFinder extends Finder<Integer,Carport> {

  /**
   * Construct using the default EbeanServer.
   */
  public CarportFinder() {
    super(Carport.class);
  }

  /**
   * Construct with a given EbeanServer.
   */
  public CarportFinder(String serverName) {
    super(Carport.class, serverName);
  }

  /**
   * Start a new typed query.
   */
  public QCarport where() {
     return new QCarport(db());
  }

  /**
   * Start a new document store query.
   */
  public QCarport text() {
     return new QCarport(db()).text();
  }
}
