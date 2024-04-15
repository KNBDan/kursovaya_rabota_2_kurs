//Подключение к базам данных
package com.mycompany.jsontodb;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class DataBaseConnection {
    //какойто фиктивный код
    public static Connection connectToMySQL() throws SQLException {
        String dbName = "figures";
        String url = "jdbc:mysql://localhost:3306";
        url = url+"/"+dbName;
        String user = "root";
        String password = "sqlPasword2023";
        return DriverManager.getConnection(url, user, password);
    }
     //подключение к mySql, желательно переписать под универмальность
    public static Connection addConnection( Connection connection,String url,String user,String password)throws SQLException, ClassNotFoundException{
        String dbDriver = ""; //Выбор драйвера для продолжения работы
        if (url.contains("mysql")){ 
            dbDriver= "com.mysql.cj.jdbc.Driver";
        }else if (url.contains("postgresql")){
            dbDriver= "org.postgresql.Driver";
        }else if (url.contains("mcsql")){
            dbDriver= "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        } 
        // Загрузка драйвера JDBC
        Class.forName(dbDriver);//"com.mysql.cj.jdbc.Driver"
        // Установка соединения с сервером баз данных
        connection = DriverManager.getConnection(url, user, password);  
        return connection;
    } 
    
}
