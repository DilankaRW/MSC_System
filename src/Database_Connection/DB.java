/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Database_Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;



/**
 *
 * @author User
 */
public class DB {
    private String url = "jdbc:mysql://localhost:3307/viruvo";
    private String username = "root";
    private String password = "123";

    Connection con = null;

    public Connection myCon() throws Exception {
        if (con == null) {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            return con;
        } else {
            return con;
        }
    }

    public void putData(String sql) throws Exception {
        myCon().createStatement().executeUpdate(sql);
    }

    public ResultSet getData(String sql) throws Exception {
        return myCon().createStatement().executeQuery(sql);
    }
}