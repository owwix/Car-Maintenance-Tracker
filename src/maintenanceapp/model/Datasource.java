/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package maintenanceapp.model;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

/**
 *
 * @author alexanderokonkwo
 */
public class Datasource {
    
    public static final String DB_NAME = "maintenance.db";
    
    public static final String CONNECTION_STRING = "jdbc:sqlite:/Users/alexanderokonkwo/Desktop/Projects/MaintenanceApp/" + DB_NAME;
    
    public static final String TABLE_MAINTENANCELOG = "MaintenanceLog";
    public static final String COLUMN_MAIN_ID = "MainId";
    public static final String COLUMN_DATE = "Date";
    public static final String COLUMN_MILES = "Miles";
    
    public static final String TABLE_MAINTENANCETYPE = "MaintenanceType";
    public static final String COLUMN_TYPE = "Type";
    
    private Connection conn;
    
    public boolean open(){
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
            System.out.println("Connection successful");
            return true;
        } catch (SQLException e) {
            System.out.println("Couldn't connect to database: " + e.getMessage());
            return false;
        }
            
    }
    
    public void close() {
        try {
            if (conn!=null) {
                conn.close();
            }
        } catch(SQLException e) {
            System.out.println("Couldn't close connection: " + e.getMessage());
        }
    }
    
    public String[] getMaintenanceTypesAsArray() {
        
        try (Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM " + TABLE_MAINTENANCETYPE);){
                    
            List<MaintenanceType> types = new ArrayList<>();
            while(results.next()){
                MaintenanceType type = new MaintenanceType();
                type.setType(results.getString(COLUMN_TYPE));
                types.add(type);
                
            }
            
            String typesArray[];
            typesArray = new String[types.size()];
            for (int i = 0; i < types.size(); i++){
                typesArray[i] = types.get(i).getType();
            }            
            
        return typesArray;
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        } 
    }
    
    public void updateTypes(String addedType) {
        try (Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = conn.createStatement();) {
            
            statement.executeQuery(String.format("INSERT INTO %s (Type) VALUES ('%s')", TABLE_MAINTENANCETYPE, addedType));
            System.out.println("Add successful");
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());

        } 
 
    }
    
    public void removeTypes(String removedType) {
        try (Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = conn.createStatement();) {
            
            statement.executeQuery(String.format("DELETE FROM %s WHERE Type='%s'", TABLE_MAINTENANCETYPE, removedType));
            System.out.println("Remove successful");
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());

        } 
    }
    
    public int getNumOfTypes() throws SQLException{
        Statement statement = conn.createStatement();
        String query = "SELECT COUNT(*) FROM MaintenanceType";
        ResultSet rs = statement.executeQuery(query);
        rs.next();
        int count = rs.getInt(1);
        return count;
    }
    
}
