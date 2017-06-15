/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Task;

/**
 *
 * @author admin
 */
public class DaoSqlite {

    protected final static String DRIVER = "org.sqlite.JDBC";
    protected final static String JDBC = "jdbc:sqlite";

    public static void createTable(String dbPath) {
        String q = "create table task ("
                + "taskId integer not null primary key autoincrement, "
                + "name varchar(20) not null, "
                + "description varchar(60) not null); ";
        try (Connection conn = getConnectionDAO(dbPath);
                PreparedStatement ps = conn.prepareStatement(q)) {
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DaoSqlite.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void dropTable(String dbPath) {
        final String q = "drop table if exists task";
        try (Connection conn = getConnectionDAO(dbPath);
                PreparedStatement ps = conn.prepareStatement(q)) {
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DaoSqlite.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected static Connection getConnectionDAO(String dbPath) {
        Connection conn = null;
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(JDBC + ":" + dbPath);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DaoSqlite.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }

    public static void deleteRecord(int id, String dbPath) {
        String q = "delete from task where taskId = ?";
        try (Connection conn = getConnectionDAO(dbPath);
                PreparedStatement ps = conn.prepareStatement(q)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DaoSqlite.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void updateRecord(Task task, String dbPath) {
        String q = "update task set taskId=? , name=?, description=? where taskId= '"+task.getTaskId()+"'  ";
        try (Connection conn = getConnectionDAO(dbPath);
                PreparedStatement ps = conn.prepareStatement(q)) {
            ps.setInt(1, task.getTaskId());
            ps.setString(2, task.getName());
            ps.setString(3, task.getDescription());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DaoSqlite.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void createRecord(Task task, String dbPath) {
        String q = "insert into task (taskId, name, "
                + "description) values (null, ?, ?)";
        try (Connection conn = getConnectionDAO(dbPath);
                PreparedStatement ps = conn.prepareStatement(q)) {
            ps.setString(1, task.getName());
            ps.setString(2, task.getDescription());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DaoSqlite.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Task retrieveRecordById(int taskId, String dbPath) {
        String q = "select taskId, name, description from task where taskId=?";
        Task task = null;
        try (Connection conn = getConnectionDAO(dbPath);
                PreparedStatement ps = conn.prepareStatement(q)) {
            ps.setInt(1, taskId);
            task = myQuery(conn, ps).get(0);
        } catch (SQLException ex) {
            Logger.getLogger(DaoSqlite.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return task;
    }

    public static List<Task> retrieveAllRecordsByName(String dbPath) {
        String q = "select * from task";
        List<Task> list = null;
        try (Connection conn = getConnectionDAO(dbPath);
                PreparedStatement ps = conn.prepareStatement(q)) {
            list = myQuery(conn, ps);
        } catch (SQLException ex) {
            Logger.getLogger(DaoSqlite.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    protected static List<Task> myQuery(Connection conn, PreparedStatement ps) {
        List<Task> list = new ArrayList();
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int taskId = rs.getInt("taskId");
                String name = rs.getString("name");
                String desc = rs.getString("description");
                Task p = new Task(taskId, name, desc);
                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoSqlite.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

}
