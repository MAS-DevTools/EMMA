/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mas.emma.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Column;
import javax.persistence.Entity;
import mas.emma.data.statictypes.enums.ApplicationType;
import mas.emma.data.statictypes.enums.Environment;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.ibatis.jdbc.ScriptRunner;

/**
 *
 * @author nlmsc
 * @param <T>
 */
public class DatabaseService<T> extends BaseService {

    //Getting the connection
    private static String DbURL;
    private static String Username;
    private static String Pw;
    private static Environment Env;
    private static ApplicationType AppType;

    /**
     *
     * @param env
     * @param url
     * @param appType
     * @param username
     * @param pw
     */
    @SuppressWarnings("unchecked")
    public DatabaseService(Environment env, String url, ApplicationType appType, String username, String pw) {
        runWithErrorHandeling(() -> {
            try {
                switch (env) {
                    case DEVELOPMENT -> //Registering the Driver
                        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                    case CTE, QA, PRODUCTION -> //Registering the Driver
                        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                    default -> {
                    }
                }
            } finally {
                DatabaseService.DbURL = url;
                DatabaseService.AppType = appType;
                DatabaseService.Username = username;
                DatabaseService.Pw = pw;
                DatabaseService.Env = env;
                DatabaseService.AppType = appType;
            }
        });
    }

    @SuppressWarnings("unchecked")
    public List<T> getObject(String query, Class<T> classType) {
        return returnGenericListWithErrorHandeling(() -> {
            try ( Connection con = DriverManager.getConnection(DbURL, Username, Pw)) {
                try ( Statement stmt = con.createStatement()) {
                    ResultSet resultSet = stmt.executeQuery(query);
                    return mapResultSetToObject(resultSet, classType);
                } catch (Exception ex) {
                    Logger.getLogger(DatabaseService.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseService.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        });
    }

    @SuppressWarnings("unchecked")
    public List<T> mapResultSetToObject(ResultSet rs, Class outputClass) throws Exception {
        List<T> outputList = new ArrayList<T>();
        try {
            // make sure resultset is not null
            if (rs != null) {
                // check if outputClass has 'Entity' annotation
                if (outputClass.isAnnotationPresent(Entity.class)) {
                    // get the resultset metadata
                    ResultSetMetaData rsmd = rs.getMetaData();
                    // get all the attributes of outputClass
                    Field[] fields = outputClass.getDeclaredFields();
                    while (rs.next()) {
                        T bean = (T) outputClass.getDeclaredConstructor().newInstance();
                        for (int _iterator = 0; _iterator < rsmd
                                .getColumnCount(); _iterator++) {
                            // getting the SQL column name
                            String columnName = rsmd
                                    .getColumnName(_iterator + 1);
                            // reading the value of the SQL column
                            Object columnValue = rs.getObject(_iterator + 1);
                            // iterating over outputClass attributes to check if any attribute has 'Column' annotation with matching 'name' value
                            for (Field field : fields) {
                                if (field.isAnnotationPresent(Column.class)) {
                                    Column column = field
                                            .getAnnotation(Column.class);
                                    if (column.name().equalsIgnoreCase(
                                            columnName)
                                            && columnValue != null) {
                                        BeanUtils.setProperty(bean, field
                                                .getName(), columnValue);
                                        break;
                                    }
                                }
                            }
                        }
                        // outputList.add(bean);
                    }
                } else {
                    throw new Exception("No annotation present!");
                }
            } else {
                return null;
            }
        } catch (IllegalAccessException | SQLException | InstantiationException | InvocationTargetException e) {
            throw new Exception(e.getMessage());
        }
        return outputList;
    }

    public void runScript(File scriptFile) {
        runWithErrorHandeling(() -> {
            try ( Connection con = DriverManager.getConnection(DbURL, Username, Pw)) {
                ScriptRunner sr = new ScriptRunner(con);
                Reader reader = new BufferedReader(new FileReader(scriptFile));
                sr.runScript(reader);
            }
        });
    }
}
