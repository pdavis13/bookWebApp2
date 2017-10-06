package edu.wctc.distjava.jgl.bookwebapp.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jlombardo
 */
public interface DataAccess {

    void closeConnection() throws SQLException;

    /**
     * Returns records from a table. Requires and open connection.
     * @param tableName
     * @param maxRecords
     * @return
     * @throws SQLException
     */
    List<Map<String, Object>> getAllRecords(String tableName, int maxRecords) throws SQLException, ClassNotFoundException;

    int deleteRecordByID(String tableName, String colName, Object keyValue) throws SQLException, ClassNotFoundException;

    int createRecord(String tableName, List<String> colNames, 
            List<Object> colValues)  throws SQLException;
    
    void openConnection(String driverClass, String url, String userName, String password) 
            throws ClassNotFoundException, SQLException;
    
}
