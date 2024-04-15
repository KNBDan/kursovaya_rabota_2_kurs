package com.mycompany.jsontodb;

import static com.mycompany.jsontodb.unicDataQuery.connectToChosed;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONObject;

public class toJson {

    //общая глваная функция создания фишур для перевода
    public static void createFigListForJson(String url,String user,String password,String catalog,String schema,int id, String fileName,String way){
        //собираем их разных функций информацию о фигурах
         try {
      
            //подкл.чаеися к бд
            Connection connection = unicDataQuery.connectToChosed(url, user, password, catalog, schema);
            //получаем базовые параметры фигуры\
            List<figureTrans> toSave = null;
            toSave = getFromFigures(connection,schema,id);
            //пока так.
            
            //получаем доп параметры фигуры:
            //получаем входные и выходные переменные, связь
            for (figureTrans ft : toSave){
                getFromInVars(connection,schema,ft);
                getFromOutVars(connection,schema,ft);
                getLinks(connection,schema,ft);
            }
            //получаем связь фигур
            for (figureTrans ft : toSave){
                getVarLinks(connection,schema,ft);
            }
            //заносим данные в json
            createJson(toSave,fileName,way);
         } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Произошла ошибка при работе с базой данных", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }
    public static List<figureTrans> getFromFigures(Connection connection,String schema,int id){
        try {
            //подключаемся к бд          
            if (schema == null){
                schema = "";
            }else{
                schema = schema + ".";
            }
            String sql = "SELECT id_figure,name, description, x_pos, y_pos, size, shape, code FROM "+schema+"figures WHERE id_save = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            //перебор всех полученных фигур
            List<figureTrans> allFig = new ArrayList();
            while (rs.next()) {
                String name = rs.getString("name");
                String description = rs.getString("description");
                int x_pos = rs.getInt("x_pos");
                int y_pos = rs.getInt("y_pos");
                int size = rs.getInt("size");
                String shape = rs.getString("shape");
                String code = rs.getString("code");
                figureTrans ft = new figureTrans(name,description,size,shape,x_pos,y_pos,code,null,null,null,null,null);
                ft.setIndex(rs.getInt("id_figure"));
                allFig.add(ft);
            }      
            return allFig;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Произошла ошибка при получении основной информации\nфигуры", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
    //функция изымания входных переменных
    public static void getFromInVars(Connection connection,String schema,figureTrans ft){
        Map<Integer,String> allInVar = new HashMap<>();
        try {
            //подключаемся к бд          
            if (schema == null){
                schema = "";
            }else{
                schema = schema + ".";
            }
            String sql = "SELECT id_in,name FROM "+schema+"in_vars WHERE id_figure = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, ft.currentIndex);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                int id = rs.getInt("id_in");
                allInVar.put(id,name);
            }
            ft.inVar = allInVar;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Произошла ошибка при получении информаци\nо входных переменных", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }
    //функция изымания выходных переменных
    public static void getFromOutVars(Connection connection,String schema,figureTrans ft){
        Map<Integer,String> allOutVar = new HashMap<>();
        try {
            //подключаемся к бд          
            if (schema == null){
                schema = "";
            }else{
                schema = schema + ".";
            }
            String sql = "SELECT id_out,name FROM "+schema+"out_vars WHERE id_figure = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, ft.currentIndex);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                int id = rs.getInt("id_out");
                allOutVar.put(id,name);
            }
            ft.outVar = allOutVar;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Произошла ошибка при получении информаци\nо входных переменных", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }
    //функция изымания связей
    public static void getLinks(Connection connection,String schema,figureTrans ft){
        Map<String,Integer> links = new HashMap<>();
        try {
            //подключаемся к бд          
            if (schema == null){
                schema = "";
            }else{
                schema = schema + ".";
            }
            String sql = "SELECT to_figure,type FROM "+schema+"links WHERE from_figure = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, ft.currentIndex);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("to_figure");
                int type = rs.getInt("type");
                sql = "SELECT name FROM "+schema+"figures WHERE id_figure = ?";
                stmt = connection.prepareStatement(sql);
                stmt.setInt(1, id);
                ResultSet res = stmt.executeQuery();
                //берем название фигуры
                while (res.next()) {
                    String toFig = res.getString("name");
                    links.put(toFig,type);
                }
            }
            ft.links = links;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Произошла ошибка при получении информаци\nо входных переменных", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void getVarLinks(Connection connection,String schema,figureTrans ft){
        Map<String, Map<String, String>> varLink = new HashMap<>();
        try {
            //подключаемся к бд          
            if (schema == null){
                schema = "";
            }else{
                schema = schema + ".";
            }
            for (int inVar : ft.inVar.keySet()){
                String sql = "SELECT id_out_var FROM "+schema+"var_in_out_links WHERE id_in_var = ?";
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setInt(1, inVar);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()){//ищем id у выходных связанные с текущим входным
                    int outVar = rs.getInt("id_out_var");
                    sql = "SELECT f.name AS figure_name, ov.name AS out_var_name " +
                    "FROM "+schema+"figures f " +
                    "JOIN "+schema+"out_vars ov ON f.id_figure = ov.id_figure " +
                    "WHERE ov.id_out = ?";
                    stmt = connection.prepareStatement(sql);
                    stmt.setInt(1, outVar);
                    ResultSet res = stmt.executeQuery();
                    while (res.next()){ //ищем название фигуры у выходного
                        String name = res.getString("figure_name");
                        String oVN = res.getString("out_var_name");
                        //получаем значение из varLinks
                        Map<String, String> innerMap = varLink.get(name);
                        //проверяем, существует ли уже эта фигура
                        if (innerMap == null) {
                            //если фигура еще не создана, создаем новую
                            innerMap = new HashMap<>();
                            varLink.put(name, innerMap);
                        }
                        //добавляем данные
                        innerMap.put(oVN, ft.inVar.get(inVar));
                    }
                }
            }
            ft.varLinks = varLink;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Произошла ошибка при получении информаци\nо входных переменных", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    
    
    
    //сохранение в файл
    public static void createJson(List<figureTrans> figuresList,String fileName, String way){
        JSONArray jsonArray = new JSONArray();
        // Перебор всех элементов в списке figureTrans и внесение данных
        for (figureTrans figure : figuresList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("nameF", figure.name);
            jsonObject.put("descriptionF", figure.description);
            jsonObject.put("s", figure.s);
            jsonObject.put("shape", figure.shape);
            jsonObject.put("x", figure.x);
            jsonObject.put("y", figure.y);
            jsonObject.put("codeF", figure.code);
            //добавление массивов
            jsonObject.put("inVariable", figure.inVar.values());
            jsonObject.put("outVariable", figure.outVar.values());
            JSONObject jsonObjectLinks = new JSONObject();
            jsonObject.put("conToFig",figure.links);
            for (String key : figure.varLinks.keySet()){
                jsonObjectLinks.put(key, figure.varLinks.get(key));
            }
            jsonObject.put("varLinks",jsonObjectLinks);
            // Добавление объекта JSON в массив
            jsonArray.put(jsonObject);
        }
        File outputFile = new File(way+"\\", fileName +".json");
        try (FileWriter file = new FileWriter(outputFile)) {
            file.write(jsonArray.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
