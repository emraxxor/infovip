package com.github.infovip.core.basic.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Basic connection for the database
 *
 * @author Barna Attila
 *
 */
public class SqlConnection {

    private Connection conn;
    private Statement stmt;

    public void init(String dburl, String user, String password) {
        try {
            Class.forName("org.postgresql.Driver");
            setConn(DriverManager.getConnection(dburl, user, password));
            stmt = conn.createStatement();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(SqlConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public java.sql.ResultSet selectQuery(String query) {
        java.sql.ResultSet rs = null;
        try {
            rs = stmt.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(SqlConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public void updateQuery(String query) {
        try {
            stmt.execute(query);
        } catch (SQLException ex) {
            Logger.getLogger(SqlConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public Connection getConn() {
        return conn;
    }

    public void setStmt(Statement stmt) {
        this.stmt = stmt;
    }

    public Statement getStmt() {
        return stmt;
    }

    public void destroyConnect() {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(SqlConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(SqlConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
