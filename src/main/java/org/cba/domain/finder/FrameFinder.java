package org.cba.domain.finder;

import io.ebean.Finder;
import org.cba.domain.Frame;
import org.cba.domain.query.QFrame;

public class FrameFinder extends Finder<Integer,Frame> {

  /**
   * Construct using the default EbeanServer.
   */
  public FrameFinder() {
    super(Frame.class);
  }

  /**
   * Construct with a given EbeanServer.
   */
  public FrameFinder(String serverName) {
    super(Frame.class, serverName);
  }

  /**
   * Start a new typed query.
   */
  public QFrame where() {
     return new QFrame(db());
  }

  /**
   * Start a new document store query.
   */
  public QFrame text() {
     return new QFrame(db()).text();
  }
}
