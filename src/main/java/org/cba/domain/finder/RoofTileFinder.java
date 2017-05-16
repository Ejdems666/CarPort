package org.cba.domain.finder;

import io.ebean.Finder;
import org.cba.domain.RoofTile;
import org.cba.domain.query.QRoofTile;

public class RoofTileFinder extends Finder<Integer,RoofTile> {

  /**
   * Construct using the default EbeanServer.
   */
  public RoofTileFinder() {
    super(RoofTile.class);
  }

  /**
   * Construct with a given EbeanServer.
   */
  public RoofTileFinder(String serverName) {
    super(RoofTile.class, serverName);
  }

  /**
   * Start a new typed query.
   */
  public QRoofTile where() {
     return new QRoofTile(db());
  }

  /**
   * Start a new document store query.
   */
  public QRoofTile text() {
     return new QRoofTile(db()).text();
  }
}
