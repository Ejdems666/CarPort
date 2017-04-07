package model;

/**
 * Created by adam on 26/02/2017.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector implements hyggedb.Connector {

    private Connection conn = null;

    private static final int PORT = 3306;

    public Connector(String ip, String database, String username, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://" + ip + ":" + PORT + "/" + database + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Copenhagen";
            this.conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connector(){
        this("localhost","cba_carport","root","root");
    }

    public Connection getConnection() {
        return this.conn;
    }


    public void close() {
        try {
            getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

