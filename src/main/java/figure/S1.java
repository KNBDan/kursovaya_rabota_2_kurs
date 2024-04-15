
package figure;

import static figure.figures.id;
import static figure.figures.nextId;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;

public class S1 extends figures{
    String str="S";
    public S1(int x, int y, int s) {
        this.x=x/2;
        this.y=y/2;
        this.s=s;
        id = nextId.incrementAndGet();
        this.nameF = "S" + this.id;
    }
    Font font = new Font("Arial", Font.BOLD, 24);
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        // s=(int) (zoom*s);
        //shape = new RoundRectangle2D.Double(x-s/2, y-s/4, s, s/2, 50, 100);
        shape = new Ellipse2D.Double(x-s, y-s/2, s/2, s/2);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
        g2.setColor(Color.WHITE);
        g2.fill(shape);
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(2));
        g2.draw(shape);
        g2.setFont(font);
        g2.drawString(str, (x-s)+17, (y-s/2)+34);// в дальнейшем кнопка
    }    
}   
