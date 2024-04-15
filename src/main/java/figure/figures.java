package figure;

import java.awt.Shape;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.JComponent;

public class figures extends JComponent{
    int x, y;//x,y - координаты центра фигуры 
    Shape shape; //Тип фигуры
    int s; //s - size  
    String nameF; // Имя фигуры
    String descriptionF; // Подробное описание объекта
    ArrayList<String> inVariable = new ArrayList(); // Лист со входными переменными
    ArrayList<String> outVariable = new ArrayList(); // Лист с выходными переменными
    String codeF; // Код фигуры
    
    static AtomicInteger nextId = new AtomicInteger();
    static int id;
    static public boolean doubleCl = false;
    public figures() {
        id = nextId.incrementAndGet();   
    }
    
    public figures(int x, int y, Shape shape, int s, String nameF, String descriptionF,
                  ArrayList<String> inVariable, ArrayList<String> outVariable, String codeF) {
       this.x = x;
       this.y = y;
       this.shape = shape;
       this.s = s;
       this.nameF = nameF;
       this.descriptionF = descriptionF;
       this.inVariable = inVariable;
       this.outVariable = outVariable;
       this.codeF = codeF;
   }
    
    
    
      public double getS(){
       return s;        
    }
    
    public void setS(int s){
        this.s=s;
    }
    public Shape getShape(){
       return shape;       
    }
    public void setShape(Shape shape) {
        this.shape = shape;
    }
    
    public void setXX(int x){
        this.x=x;
    }
      public int getXX(){
       return x;       
    }
    public void setYY(int y){
        this.y=y;
    }
      public int getYY(){
       return y;       
    }
      
    public int getSises(){
        return s;
    }
    public int getId(){
        return id;
    }

    public void setSize(int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    //Новые переменные
    public void setNameF(String nameF) {
        this.nameF = nameF;
    }
    public String getNameF() {
        return nameF;
    }

    public void setDescriptionF(String descriptionF) {
        this.descriptionF = descriptionF;
    }
    public String getDescriptionF() {
        return descriptionF;
    }

    public void setInVariable(ArrayList<String> inVariable) {
        this.inVariable = inVariable;
    }
    public ArrayList<String> getInVariable() {
        return inVariable;
    }

    public void setOutVariable(ArrayList<String> outVariable) {
        this.outVariable = outVariable;
    }
     public ArrayList<String> getOutVariable() {
        return outVariable;
    }

    public void setCodeF(String codeF) {
        this.codeF = codeF;
    }
    public String getCodeF() {
        return codeF;
    }
    
    //Фунеция для сохранения. Чисто для удобства сделал в классе фигур, по факту можно вынести
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("x", x);
        map.put("y", y);    
        map.put("shape", this.getClass().toString()); // Вам нужно решить, как сохранять форму в Map
        map.put("s", s);
        map.put("nameF", nameF);
        map.put("descriptionF", descriptionF);
        map.put("inVariable", inVariable);
        map.put("outVariable", outVariable);
        map.put("codeF", codeF);
        return map;
    }
}
