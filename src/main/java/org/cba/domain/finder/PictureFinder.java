package org.cba.domain.finder;

import io.ebean.Finder;
import org.cba.domain.Picture;
import org.cba.domain.query.QPicture;

public class PictureFinder extends Finder<Integer,Picture> {

  /**
   * Construct using the default EbeanServer.
   */
  public PictureFinder() {
    super(Picture.class);
  }

  /**
   * Construct with a given EbeanServer.
   */
  public PictureFinder(String serverName) {
    super(Picture.class, serverName);
  }

  /**
   * Start a new typed query.
   */
  public QPicture where() {
     return new QPicture(db());
  }

  /**
   * Start a new document store query.
   */
  public QPicture text() {
     return new QPicture(db()).text();
  }
}
