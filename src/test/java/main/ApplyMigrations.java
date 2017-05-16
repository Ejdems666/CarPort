package main;

import io.ebean.Ebean;

/**
 * Created by adam on 16/05/2017.
 */
public class ApplyMigrations {
    public static void main(String[] args) {
        System.setProperty("disableTestProperties","true");
        Ebean.getDefaultServer();
        System.out.println("migration applied");
    }
}
