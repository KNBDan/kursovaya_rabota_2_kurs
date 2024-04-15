package figure;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;

public class S extends figures{
    //id
//    private static int counter = 0;
//    public final int id;
//    double xx;
   String str="Start/End";
    public S(int x, int y, int s) {
        this.x=x;
        this.y=y;
        this.s=s;
        id = nextId.incrementAndGet();
    }
    Font font = new Font("Times new Roman", Font.PLAIN, 18);
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        shape = new RoundRectangle2D.Double(x/2-s/2, y/2-s/4, s, s/2, 50, 100);
        //shape = new Ellipse2D.Double(50, 50, x, y);
//RoundRectangle2D o = new RoundRectangle2D(x/2-s/2, y/2-s/4, s, s/2, 50, 100);
//        g2.setBackground(Color.yellow);
        g2.setRenderingHint ( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
       // выравниввание текста по середине каиртинки
//        FontMetrics fm = g2.getFontMetrics();
//        int nTitileWidth = fm.stringWidth(str);
//        int nTitleHeight = fm.getAscent() - fm.getLeading() - fm.getDescent();
//        int x0 = (s - nTitileWidth) / 2;
//        int y0 = ((s/2 - nTitleHeight) / 2) +nTitleHeight;
        // отрисовка
        g2.setColor(new Color(113,171, 226));
        g2.fill(shape);
       // g2.fillRoundRect(x/2-s/2, y/2-s/4, s, s/2, 50, 100);
        g2.setColor(Color.DARK_GRAY);
        g2.draw(shape);
       // g2.drawRoundRect(x/2-s/2, y/2-s/4, s, s/2, 50, 100);
        g2.setFont(font);
        g2.drawString(str, x/2, y/2);// в дальнейшем кнопка
//        if(shape.contains(1,2)==true){
//            str="true";}
//            else{str="false";}
//        g2.drawString(str, x/2, y/2);
//        }
//        g2.fill(shape);
        
    }
    // кнопка для дальнейших действий
//    стоит ли её соозадвать здесь или в событии при создании объекта
//JButton StartButton=  new JButton(str);
//StartButton.addActionListener(new ButtonAction());
//public boolean touch(Point2D p,Shape o){
//    if (o.contains(p)==true);
//    return true;
//}
    
//метод для вызова id или других данных о конкретной созданной фигуре
    
}   

