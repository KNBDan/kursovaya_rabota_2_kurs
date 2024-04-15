package com.mycompany.jsontodb;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

//Класс для работы с файлом сохранния для возможности выбора любой субд и бд в программе. Будет сохранять и считывать данные для подключения к бд
public class localUserInfo {
    static String fileName = "database_info.txt";

    //функция возвращает данные о подключении user password url
    public static ArrayList<String[]> getInfo(Connection connection){
        String user;
        String url;
        String password;
        //попытка подключения к файлу
        isFileExist(fileName);
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            ArrayList<String[]> data = new ArrayList<>(); //масств с тгформацией о подключениях
            while ((line = br.readLine()) != null) {
                // Разбиваем строку на элементы
                String[] parts = line.split(",");
                // Если строка имеет необходимую структуру, то извлекаем данные
                if (parts.length >= 3) {
                    url = parts[0].trim();
                    user = parts[1].trim();
                    password = parts[2].trim();
                    //обновляем массив с данными о подключениях
                    data.add(new String[]{url,user,password});
                }
            }
            return data;
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла " + fileName);
            e.printStackTrace();
        }
        return null;
    }
    // Функция принимает данные на хранение
    public static boolean addConnectInfo(String user, String url, String password){
        if (!isFileExist(fileName)){//если ошибка в работе с файлом, просто выходим
            return false;
        }
        if (userAdd(user,url,password)){
            System.out.println("ok");
            return true;
        }
        else{
             System.out.println("er");
             return false;
        }
    }

    //Проверка наличия записи о пользователе в фале
    private static boolean userAdd(String user, String url, String password){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName)); //чтаем файл
            try{
                String line;
                //ПРоверка на наличие данной записи в файле
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(","); //разбираем запись на части для проверки
                    if (parts.length >= 3 && parts[0].equals(url) && parts[1].equals(user) && parts[2].equals(password)) {
                        System.err.println("Такая запись уже существует: " + line);
                        return false;
                    }
                    else{
                    }
                }
                //создание новой записи в файл
                BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true)); //открываем писатель
                writer.write(url + "," + user+ "," + password);
                writer.newLine();
                writer.close(); //закрываем писатель
                System.out.println("Новая запись успешно добавлена в файл " + fileName);
                return true;
            }
             finally {
                reader.close();//закрытие читателя
            }
        } catch (IOException ex) {
            Logger.getLogger(localUserInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    //Проверяем существование файла сохранения и при необходимости создаем его
    private static boolean isFileExist(String filename){
        try {
            File file = new File(filename);
            if (file.createNewFile()) {// Файл существует
                System.out.println("Файл " + filename + " создан");
            } else {// Файл не существует
                System.out.println("Файл " + filename + " уже существует");
            }
            return true;//файл готов к работе
        } catch (IOException e) {
            System.err.println("Ошибка при создании файла");
            e.printStackTrace();
        }
        return false; //файл не готов к работе
    }
    public static String[] getConnectionInfoByTypeUser(String user, String typeDB){
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(","); // Разбиваем строку по запятой
                if (parts.length >= 3 && parts[0].trim().contains(typeDB) && parts[1].trim().equals(user)) {
                    // Нашли строку с нужным типом базы данных и пользователем
                    String url = parts[0].trim();
                    String password = parts[2].trim();
                    return new String[]{url, user, password};
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
