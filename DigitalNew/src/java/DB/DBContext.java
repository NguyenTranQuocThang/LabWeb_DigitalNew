/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

//import GetValueFromConfigfile.GetValue;

import GetValueFromConfigfile.GetValue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;


/**
 *
 * @author thang
 */
public class DBContext {

    GetValueFromConfigfile.GetValue gv = new GetValue();
    String HOSTNAME = gv.getValue("HOSTNAME");
    //String HOSTNAME = "localhost";
    String PORT = gv.getValue("PORT");
    //String PORT = "1433";
    String DATABASENAME = gv.getValue("DATABASENAME");
    //String DATABASENAME = "digital";

    public Connection getConnection() {

        String url = "jdbc:sqlserver://" + HOSTNAME + ":" + PORT + ";" + "databaseName=" + DATABASENAME;
        System.out.println(url);
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(url, "sa", "123");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }
    public void closeConnection(ResultSet rs, PreparedStatement ps, Connection con) throws Exception {
        if (rs != null && !rs.isClosed()) {
            rs.close();
        }
        if (ps != null && !ps.isClosed()) {
            ps.close();
        }
        if (con != null && !con.isClosed()) {
            con.close();
        }
    }
    
}
