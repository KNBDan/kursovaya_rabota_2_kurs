package com.mycompany.jsontodb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class unicDataQuery {
    JOptionPane optionPane = new JOptionPane(); //информативное окно для оповещения пользователя

    //Функция добавления данных в бд просто по количеству столбцов. СТАРАЯ
    public static void insertData(Connection connection, String tableName, int num ,String... values) throws SQLException {
       StringBuilder sql = new StringBuilder("INSERT INTO ").append(tableName).append(" VALUES (");
       sql.append("?");
       sql.append(", ");
       for (int i = 0; i < values.length; i++) {
           sql.append("?");
           if (i < values.length - 1) {
               sql.append(", ");
           }
       }
       sql.append(")");

       try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {
           int parameterIndex = 1; // Начинаем счетчик параметров с 1
           statement.setInt(parameterIndex++, num); //фейковое добавление каунтера, нужн орешить эту проблему. НЕобходимо заносить данные по определенным столбцам, а не все в кучу.
           //как раз этой функцией и пытался эт орешить, но в итоге названия столбцов идут как данные, вообще все входыне данные заносятся в поля таблицы.
           for (int i = 0; i < values.length; i++) {
               System.out.println(values[i]);
               statement.setString(parameterIndex++, values[i]); // Инкрементируем счетчик параметров
           }
           statement.executeUpdate();
       }
    }
    
    
    //Функция добавления в бд
    public static void getFigFromJson(String url, String user, String password, String catalog, String schema,File file,String selectedFileName){
        //Массив фигур
        ArrayList<figureTrans> allFig = new ArrayList();
        //попытка получения всех фигур из файла
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            reader.close();
            // Преобразование содержимого файла в массив объектов JSON
            JSONArray jsonArray = new JSONArray(stringBuilder.toString());
            for (int i = 0; i < jsonArray.length(); i++) {// Перебираем все объекты JSON в массиве
                //собираем данные из объектов файла
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.getString("nameF");
                String description = jsonObject.getString("descriptionF");
                int s = jsonObject.getInt("s");
                String shape = jsonObject.getString("shape");
                int x = jsonObject.getInt("x");
                int y = jsonObject.getInt("y");
                String code = jsonObject.getString("codeF");
                //массив входных аргументов
                JSONArray jVar = jsonObject.getJSONArray("inVariable");
                List<String> inVar =  new ArrayList<>();
                for (int j = 0; j < jVar.length(); j++){
                    inVar.add(jVar.getString(j));
                }
                jVar = jsonObject.getJSONArray("outVariable");//массив выходных аргументов
                List<String> outVar =  new ArrayList<>();
                for (int j = 0; j < jVar.length(); j++){
                    outVar.add(jVar.getString(j));
                }
                JSONObject objVar = jsonObject.getJSONObject("conToFig");//словарь со связями фигуры 
                Map<String, Integer> links = new HashMap<>();
                // Получение ключей из объекта objVar
                Iterator<String> keys = objVar.keys();
                while (keys.hasNext()) {
                    String key = keys.next();
                    int value = objVar.getInt(key);
                    links.put(key, value);
                }
                //словарь со словарем связи входных и выходных данных
                JSONObject varLinksObject = jsonObject.getJSONObject("varLinks");
                Map<String, Map<String, String>> varLinks = new HashMap<>();

                // Проверяем, существует ли ключ "firstFigure"
                if (varLinksObject.has("firstFigure")) {
                    // Получаем объект "firstFigure"
                    JSONObject firstFigureObject = varLinksObject.getJSONObject("firstFigure"); //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

                    // Создаем словарь для "firstFigure"
                    Map<String, String> linksDictionary = new HashMap<>();

                    // Итерируемся по ключам в "firstFigure"
                    Iterator<String> keyss = firstFigureObject.keys();
                    while (keyss.hasNext()) {
                        String key = keyss.next();
                        String value = firstFigureObject.getString(key);
                        linksDictionary.put(key, value);
                    }

                    // Добавляем словарь в varLinks
                    varLinks.put("firstFigure", linksDictionary);
                }       
                //Создаем фигуру на основе сохранения
                figureTrans ft = new figureTrans(name,description,s,shape,x,y,code,inVar,outVar,links,varLinks,selectedFileName);
                //Сохраняем её в массив
                allFig.add(ft);
            }
            ////добавление в бд
            //Добавление в таблицу "сохранения"
            Connection connection = connectToChosed(url, user, password, catalog, schema);
            //  String fileName = insertSaveTableInData(connection,catalog,schema,allFig.get(0));
            String fileName =  insertSaving(connection,catalog,schema,selectedFileName);
            //Добавления в остальные таблицы
            for (figureTrans fig : allFig){ //Добавление в "фигуры"
                fig.saveName = fileName;
                insertData(connection,catalog,schema,fig);
            }
            for (figureTrans fig : allFig){ //Добавление в "ссылки"
                Map<String,Integer> link_f = fig.links;
                for (Map.Entry<String, Integer> entry : link_f.entrySet()) {
                    String from_to = entry.getKey();
                    int type_link = entry.getValue();
                    insertLinks(connection,catalog,schema,fig,from_to,type_link);
                }
            }
            for (figureTrans fig : allFig){ //добавление выходных данных
                Map<Integer,String> outVar = fig.outVar;
                for (String elem : outVar.values()){                
                    insertOutVar(connection, catalog, schema, fig, elem);
                }
            }
            for (figureTrans fig : allFig){ //добавление выходных данных
                Map<Integer,String> inVar = fig.inVar;
                for (String elem : inVar.values()){                
                    insertInVar(connection, catalog, schema, fig, elem);
                }
            }
            for (figureTrans fig : allFig){ // добавление связей переменных
                Map<String,Map<String,String>> links_var = fig.varLinks;
                for (String key_fig_name : links_var.keySet()) { //проходим по всем связанным фигурам
                    // Получение значения типа Map<String, String> для текущего ключа
                    Map<String, String> innerMap = links_var.get(key_fig_name); //внутреннии словари с связью именно переменных
                    // Перебор элементов внутренней Map<String, String>
                    for (Map.Entry<String, String> entry : innerMap.entrySet()) {
                        String lilKey = entry.getKey(); //outVar
                        String lilValue = entry.getValue(); //inVar
                        insertVarLinks(connection, catalog, schema, fig, lilValue, lilKey, key_fig_name);
                    }
                }
            }
            JOptionPane optionPane = new JOptionPane(); //информативное окно для оповещения пользователя

            optionPane.setMessage("Сохранение "+selectedFileName+" было создано");
            optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
            
            JDialog dialog = optionPane.createDialog(null, "");
            dialog.setVisible(true);
            
            
            //reader.close();//ненужен уже ведбь
        } catch (IOException | JSONException | SQLException e) {
            e.printStackTrace();
            
        }
    }
    public static boolean isSaveNameExist(Connection connection, String catalog, String schema,String fileName){
        //подключение к бд
        try{
            if (schema == null){
                schema = "";
            }
            else{
                schema= schema+".";
            }
            String svn = fileName; //для замены
            //проврека на наличие данного названия в таблице

            String quer = "SELECT saving_name FROM "+schema+"savings WHERE saving_name = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(quer)) {
            preparedStatement.setString(1, svn);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return true;
                } else {
                    return false;
                }
            }
        } 
        } catch (SQLException e) {
            // Обработка ошибок при выполнении запроса
            e.printStackTrace();
        }
        return false;
    }
    
    public static String insertSaving(Connection connection, String catalog, String schema,String fileName){
        try {
            if (schema == null){
                schema = "";
            }
            else{
                schema= schema+".";
            }
            String sql = "INSERT INTO "+schema+"savings (saving_name) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, fileName);
            statement.executeUpdate();  
        } catch (SQLException ex) {
            Logger.getLogger(unicDataQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fileName;      
    }
    //Добавление данных в базу данных
    public static String insertSaveTableInData2(Connection connection, String catalog, String schema,figureTrans ft) throws SQLException, SQLException{
        //подключение к бд
        String base = ft.saveName; //Название файла сохранения
        try{
            if (schema == null){
                schema = "";
            }
            else{
                schema= schema+".";
            }
            String svn = base; //для замены
            //проврека на наличие данного названия в таблице
            for (int i=1;i<1000;i++){
                String quer = "SELECT saving_name FROM "+schema+"savings WHERE saving_name = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(quer)) {
                preparedStatement.setString(1, svn);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        System.out.println("Элемент с названием " + svn + " существует в таблице savings.");
                        svn = base+"("+ Integer.toString(i) +")";
                    } else {
                        System.out.println("Элемент с названием " + svn + " не существует в таблице savings.");
                        base = svn;
                        break;
                    }
                 }
                }
            }
            
            //Заполнение savings
            String sql = "INSERT INTO "+schema+"savings (saving_name) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, base);
            statement.executeUpdate();        
        } catch (SQLException e) {
            // Обработка ошибок при выполнении запроса
            e.printStackTrace();
            return base;
        }
        return base;
    }
    //Добавление данных в базу данных
    public static void insertData(Connection connection, String catalog, String schema,figureTrans ft) throws SQLException, SQLException{
        //подключение к бд
        try{
            if (schema == null){ //если не было схемы, ничего не вставляем, работаем с mysql
                schema = "";
            }
            else{ //иначе вставляем название схемы и работаем с postgre
                schema= schema+".";
            }
            //заполнение figures
            //String sql = "INSERT INTO figures (name, description, x_pos, y_pos, size, shape, code) VALUES (?, ?, ?, ?, ?, ?, ?)";
            String sql = "INSERT INTO "+schema+"figures (name, description, x_pos, y_pos, size, shape, code, id_save) " +
             "SELECT ?, ?, ?, ?, ?, ?, ?, id_saving " +
             "FROM "+schema+"savings " +
             "WHERE saving_name = ?"; //вот тут ищем определенные названия в тбплице сохранений и подставляем соот id
            PreparedStatement statement = connection.prepareStatement(sql);
            // Установка значений в подготовленный запрос
            statement.setString(1, ft.name);
            statement.setString(2, ft.description);
            statement.setInt(3, ft.x);
            statement.setInt(4, ft.y);
            statement.setInt(5, ft.s);
            statement.setString(6, ft.shape);
            statement.setString(7, ft.code);
            statement.setString(8, ft.saveName);
            // Выполнение запроса
            statement.executeUpdate();
            
        } catch (SQLException e) {
            // Обработка ошибок при выполнении запроса
            e.printStackTrace();
        }
        
    }
    //добавление ссылок на фигуры в бд
    public static void insertLinks(Connection connection, String catalog, String schema,figureTrans ft,String fig_to,int type_l) throws SQLException, SQLException{
        //подключение к бд
        try{
            if (schema == null){ //если не было схемы, ничего не вставляем, работаем с mysql
                schema = "";
            }
            else{ //иначе вставляем название схемы и работаем с postgre
                schema= schema+".";
            }
            String sql = "INSERT INTO " + schema + "links (from_figure, to_figure, type) " +
             "SELECT f1.id_figure, f2.id_figure, ? " +
             "FROM " + schema + "figures f1 " +
             "JOIN " + schema + "savings s1 ON f1.id_save = s1.id_saving AND s1.saving_name = ? " +
             "JOIN " + schema + "figures f2 ON f2.id_save = s1.id_saving AND f2.name = ? " +
             "WHERE f1.name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, type_l);
            statement.setString(2, ft.saveName);
            statement.setString(3, fig_to);
            statement.setString(4, ft.name);
            // Выполнение запроса
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //добавление выходных данных
    public static void insertOutVar(Connection connection, String catalog, String schema,figureTrans ft,String varName){
        try{
            if (schema == null){ //если не было схемы, ничего не вставляем, работаем с mysql
                schema = "";
            }
            else{ //иначе вставляем название схемы и работаем с postgre
                schema= schema+".";
            }
            //запрос заключает в себя выбор название переменной и id фиугры, которая совпадает с id в таблице фигур с названием текущей фигуры и с id текущего сохранения
            String sql = "INSERT INTO " + schema + "out_vars (name, id_figure) " +
                         "SELECT ?, f1.id_figure " +
                         "FROM " + schema + "figures f1 " +
                         "JOIN " + schema + "savings s1 ON f1.id_save = s1.id_saving AND s1.saving_name = ? " +
                         "WHERE f1.name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, varName);
            statement.setString(2, ft.saveName);
            statement.setString(3, ft.name);
            // Выполнение запроса
            statement.executeUpdate();
        } catch (SQLException e) {
            // Обработка ошибок при выполнении запроса
            e.printStackTrace();
        }
    }
    //добавление входных данных
    public static void insertInVar(Connection connection, String catalog, String schema,figureTrans ft,String varName){
        try{
            if (schema == null){ //если не было схемы, ничего не вставляем, работаем с mysql
                schema = "";
            }
            else{ //иначе вставляем название схемы и работаем с postgre
                schema= schema+".";
            }
            //запрос заключает в себя выбор название переменной и id фиугры, которая совпадает с id в таблице фигур с названием текущей фигуры и с id текущего сохранения
            String sql = "INSERT INTO " + schema + "in_vars (name, id_figure) " +
                         "SELECT ?, f1.id_figure " +
                         "FROM " + schema + "figures f1 " +
                         "JOIN " + schema + "savings s1 ON f1.id_save = s1.id_saving AND s1.saving_name = ? " +
                         "WHERE f1.name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, varName);
            statement.setString(2, ft.saveName);
            statement.setString(3, ft.name);
            // Выполнение запроса
            statement.executeUpdate();
        } catch (SQLException e) {
            // Обработка ошибок при выполнении запроса
            e.printStackTrace();
        }
    }
    // добавление связей у переменных
    public static void insertVarLinks(Connection connection, String catalog, String schema,figureTrans ft,String inVar,String outVar, String fromFig){
        try{
            if (schema == null){ //если не было схемы, ничего не вставляем, работаем с mysql
                schema = "";
            }
            else{ //иначе вставляем название схемы и работаем с postgre
                schema= schema+".";
            }           
            String sql = "INSERT INTO " + schema + "var_in_out_links (id_out_var, id_in_var) " +
             "SELECT (SELECT ov.id_out FROM " + schema + "out_vars ov " +
             "        JOIN " + schema + "figures f1 ON ov.id_figure = f1.id_figure " +
             "        JOIN " + schema + "savings s1 ON f1.id_save = s1.id_saving " +
             "        WHERE ov.name = ? AND f1.name = ? AND s1.saving_name = ?) AS id_out_var, " +
             "(SELECT iv.id_in FROM " + schema + "in_vars iv " +
             "        JOIN " + schema + "figures f2 ON iv.id_figure = f2.id_figure " +
             "        JOIN " + schema + "savings s2 ON f2.id_save = s2.id_saving " +
             "        WHERE iv.name = ? AND f2.name = ? AND s2.saving_name = ?) AS id_in_var";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, outVar);      // Подставьте ваше значение для имени outVar
            statement.setString(2, fromFig);  // Подставьте ваше значение для имени фигуры
            statement.setString(3, ft.saveName);  // Подставьте ваше значение для имени сохранения

            statement.setString(4, inVar);       // Подставьте ваше значение для имени inVar
            statement.setString(5, ft.name);  // Подставьте ваше значение для имени фигуры
            statement.setString(6, ft.saveName);  // Повторно используйте имя сохранения для соответствия имени сохранения из таблицы in_vars
            
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } 
    //Создание схемы
    public static boolean createShema(String url, String user, String password, String catalog, String schema){
        try {
            //Получение подключения
            Connection connection = connectToChosed(url, user, password, catalog,null);
            //Попытка создания каталога в бд
            Statement stmnt = connection.createStatement();
            stmnt.executeUpdate("CREATE SCHEMA " + schema);
            connection.close();
            return true; //ДА если получислось
        } catch (SQLException e) {
            System.out.println(e); 
        } 
        return false;//Нет если не получилось
    }
    //Создание таблиц внутри схемы
    public static void createTables(String url, String user, String password, String catalog, String schema){
       try {
            //создаем подключение к созданной схеме (каталогу)
            Connection connection = connectToChosed(url, user, password, catalog, schema);
            Statement stmt = connection.createStatement();
            
            if (catalog==null){ //если указана схемв добавим точку для создания в ней таблицы
                String useRe ="USE "+schema;
                stmt.executeUpdate(useRe);
                schema = "";            
            }else{
               schema = schema+".";
            }
            
            if (url.contains("postgre")){
                String[] queries = {
                    "CREATE TABLE " + schema + "savings (" +
                            "id_saving SERIAL PRIMARY KEY, " +
                            "saving_name VARCHAR(255) NOT NULL)",
                    "CREATE TABLE " + schema + "figures (" +
                            "id_figure SERIAL PRIMARY KEY, " +
                            "name VARCHAR(255) NOT NULL, " +
                            "description TEXT NOT NULL, " +
                            "x_pos INT NOT NULL, " +
                            "y_pos INT NOT NULL, " +
                            "size INT NOT NULL, " +
                            "shape CHAR(30) NOT NULL, " +
                            "code TEXT NOT NULL, " +
                            "id_save SERIAL NOT NULL, " +
                            "FOREIGN KEY (id_save) REFERENCES " + schema + "savings(id_saving) ON DELETE CASCADE ON UPDATE CASCADE)",
                    "CREATE TABLE " + schema + "links (" +
                            "id_link SERIAL PRIMARY KEY, " +
                            "from_figure SERIAL NOT NULL, " +
                            "to_figure SERIAL NOT NULL, " +
                            "type INT NOT NULL, " +
                            "FOREIGN KEY (from_figure) REFERENCES " + schema + "figures(id_figure) ON DELETE CASCADE ON UPDATE CASCADE, " +
                            "FOREIGN KEY (to_figure) REFERENCES " + schema + "figures(id_figure) ON DELETE CASCADE ON UPDATE CASCADE)",
                    "CREATE TABLE " + schema + "out_vars (" +
                            "id_out SERIAL PRIMARY KEY, " +
                            "name VARCHAR(255) NOT NULL, " +
                            "id_figure SERIAL NOT NULL, " +
                            "FOREIGN KEY (id_figure) REFERENCES " + schema + "figures(id_figure) ON DELETE CASCADE ON UPDATE CASCADE)",
                    "CREATE TABLE " + schema + "in_vars (" +
                            "id_in SERIAL PRIMARY KEY, " +
                            "name VARCHAR(255) NOT NULL, " +
                            "id_figure SERIAL NOT NULL, " +
                            "FOREIGN KEY (id_figure) REFERENCES " + schema + "figures(id_figure) ON DELETE CASCADE ON UPDATE CASCADE)",
                    "CREATE TABLE " + schema + "var_in_out_links (" +
                            "id_var_link SERIAL PRIMARY KEY, " +
                            "id_out_var SERIAL NOT NULL, " +
                            "id_in_var SERIAL NOT NULL, " +
                            "FOREIGN KEY (id_out_var) REFERENCES " + schema + "out_vars(id_out) ON DELETE CASCADE ON UPDATE CASCADE, " +
                            "FOREIGN KEY (id_in_var) REFERENCES " + schema + "in_vars(id_in) ON DELETE CASCADE ON UPDATE CASCADE)",
                };
                // Выполнение запросов
                for (String query : queries) {
                    stmt.executeUpdate(query);
                }
            }   
            else{if(url.contains("mysql")){
                String[] queries = {
                    "CREATE TABLE IF NOT EXISTS in_vars (\n" +
"	id_in int AUTO_INCREMENT NOT NULL UNIQUE,\n" +
"	name char(255) NOT NULL,\n" +
"	id_figure int NOT NULL,\n" +
"	PRIMARY KEY (id_in)\n" +
")",
                    "CREATE TABLE IF NOT EXISTS savings (\n" +
"	id_saving int AUTO_INCREMENT NOT NULL UNIQUE,\n" +
"	saving_name char(255) NOT NULL,\n" +
"	PRIMARY KEY (id_saving)\n" +
")",
                    "CREATE TABLE IF NOT EXISTS figures (\n" +
"	id_figure int AUTO_INCREMENT NOT NULL UNIQUE,\n" +
"	name char(255) NOT NULL,\n" +
"	description text NOT NULL,\n" +
"	x_pos double NOT NULL,\n" +
"	y_pos double NOT NULL,\n" +
"	size int NOT NULL,\n" +
"	shape char(30) NOT NULL,\n" +
"	code text NOT NULL,\n" +
"	id_save int NOT NULL,\n" +
"	PRIMARY KEY (id_figure)\n" +
")",
                    "CREATE TABLE IF NOT EXISTS links (\n" +
"	id_link int AUTO_INCREMENT NOT NULL UNIQUE,\n" +
"	from_figure int NOT NULL,\n" +
"	to_figure int NOT NULL,\n" +
"	type tinyint NOT NULL,\n" +
"	PRIMARY KEY (id_link)\n" +
")",
                    "CREATE TABLE IF NOT EXISTS out_vars (\n" +
"	id_out int AUTO_INCREMENT NOT NULL UNIQUE,\n" +
"	name char(255) NOT NULL,\n" +
"	id_figure int NOT NULL,\n" +
"	PRIMARY KEY (id_out)\n" +
")",
                    "CREATE TABLE IF NOT EXISTS var_in_out_links (\n" +
"	id_var_link int AUTO_INCREMENT NOT NULL UNIQUE,\n" +
"	id_out_var int NOT NULL,\n" +
"	id_in_var int NOT NULL,\n" +
"	PRIMARY KEY (id_var_link)\n" +
")",
                    "ALTER TABLE in_vars ADD CONSTRAINT in_vars_fk2 FOREIGN KEY (id_figure) REFERENCES figures(id_figure) ON DELETE CASCADE ON UPDATE CASCADE",
                    "ALTER TABLE figures ADD CONSTRAINT figures_fk8 FOREIGN KEY (id_save) REFERENCES savings(id_saving) ON DELETE CASCADE ON UPDATE CASCADE",
                    "ALTER TABLE links ADD CONSTRAINT links_fk1 FOREIGN KEY (from_figure) REFERENCES figures(id_figure) ON DELETE CASCADE ON UPDATE CASCADE",
                    "ALTER TABLE links ADD CONSTRAINT links_fk2 FOREIGN KEY (to_figure) REFERENCES figures(id_figure) ON DELETE CASCADE ON UPDATE CASCADE",
                    "ALTER TABLE out_vars ADD CONSTRAINT out_vars_fk2 FOREIGN KEY (id_figure) REFERENCES figures(id_figure) ON DELETE CASCADE ON UPDATE CASCADE",
                    "ALTER TABLE var_in_out_links ADD CONSTRAINT vio_links_fk1 FOREIGN KEY (id_out_var) REFERENCES out_vars(id_out) ON DELETE CASCADE ON UPDATE CASCADE",
                    "ALTER TABLE var_in_out_links ADD CONSTRAINT vio_links_fk2 FOREIGN KEY (id_in_var) REFERENCES in_vars(id_in) ON DELETE CASCADE ON UPDATE CASCADE"
                };
                for (String query : queries) {
                    stmt.executeUpdate(query);
                }
            }}
            System.out.println("done");
        } catch (SQLException e) {
            System.out.println(e); 
        }    
    }
    // Метод для получения списка таблиц из базы данных по её URL
    public static ArrayList<String> getTables(String url,String user, String password,String catalog,String shema) {
        ArrayList<String> tables = new ArrayList<>();
        try {
            Connection connection = connectToChosed(url,user,password,catalog,shema); //получения подключения к бд
            DatabaseMetaData metaData = connection.getMetaData();
            // Получение списка таблиц определенной схемы и определенного каталога 
            ResultSet resultSet = metaData.getTables(catalog,shema,"%", new String[]{"TABLE"});
            while (resultSet.next()) {
                tables.add(resultSet.getString("TABLE_NAME"));
            } 
            // Закрытие ресурсов
            resultSet.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tables;
    }
    
    //Подсчет таблиц внутри схемы
    public static int countTables(String url,String user, String password,String catalog,String shema){
        try {
            Connection connection = connectToChosed(url,user,password,catalog,shema); //получения подключения к бд
            DatabaseMetaData metaData = connection.getMetaData();
            // Получение списка таблиц определенной схемы и определенного каталога 
            ResultSet resultSet = metaData.getTables(catalog,shema,"%", new String[]{"TABLE"});
            int count = 0;
            while (resultSet.next()) {
                count+=1;
            } 
            // Закрытие ресурсов
            resultSet.close();
            connection.close();
            return count;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    //получение имени таблиц для определения подходящей бд
    public static List<String> getTableNames(String url, String user, String password, String catalog, String schema) {
        List<String> tableNames = new ArrayList<>();
        try {
            // Подключение
            Connection connection = connectToChosed(url, user, password, catalog, schema);
            // Получение метаданных
            DatabaseMetaData metaData = connection.getMetaData();
            // Получение всех таблиц
            ResultSet resultSet = metaData.getTables(catalog, schema, "%", new String[]{"TABLE"});
            while (resultSet.next()) { // Извлечение названий таблиц
                String tableName = resultSet.getString("TABLE_NAME");
                tableNames.add(tableName);
            }
            resultSet.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tableNames;
    }
    //получение названия всех сохранений для выбора нужного (создать в конструкторе соот окно с выбором
    public static Map getSaveMap(String url,String user, String password,String catalog,String schema){
        try {
            //подключение к бд
            Connection connection = connectToChosed(url, user, password, catalog, schema);
            //создание словаря id-name
            if (schema==null){ //если указана схемв добавим точку для создания в ней таблицы
                schema = "";            
            }else{
               schema = schema+".";
            }
            Map<Integer, String> savingsMap = new HashMap<>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id_saving, saving_name FROM "+schema+"savings");
            //Перебор всех сохранений и занесение в словарь
            while (resultSet.next()) {
                int id = resultSet.getInt("id_saving");
                String name = resultSet.getString("saving_name");
                savingsMap.put(id, name);
            }
            resultSet.close();
            statement.close();
            connection.close();     
            return savingsMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //функция удаления сохранения из базы данных
    public static void deleteSave(Connection connection, String catalog, String schema, String saveName){
        try{
            if (schema == null){ //если не было схемы, ничего не вставляем, работаем с mysql
                schema = "";
            }
            else{ //иначе вставляем название схемы и работаем с postgre
                schema= schema+".";
            }
            String sql = "DELETE FROM "+schema+"savings WHERE saving_name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, saveName);
            // Выполнение запроса
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //подключение к указанной бд для дальнейшей работы
    public static Connection connectToChosed(String url,String user, String password,String catalog,String schema) throws SQLException{
        if (catalog!=null){
            if (url.charAt(url.length()-1) == '/'){
                url+=catalog;
            }
            else{
                url+="/"+catalog;
            }
        }
        Connection connection = DriverManager.getConnection(url,user,password);
        return connection;
    }
    
}
