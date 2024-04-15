package figure;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.GeneralPath;
import javax.swing.*;

public class V extends figures{
//    int s, x, y;
    public V(int x, int y, int s) {
        this.x=x/2;
        this.y=y/2;
        this.s=s;
        this.nameF = "V" + this.id;
    }
    Font font = new Font("Arial", Font.BOLD, 24);
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setFont(font);        
        g2.setRenderingHint ( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
        
        GeneralPath gp = new GeneralPath();
        gp.moveTo(x-s/4, y-s/4);//startpoint
        gp.lineTo(x+s/4, y-s/4);//nextpoint
        gp.lineTo(x+s/2, y);//nextpoint
        gp.lineTo(x+s/4, y+s/4);//nextpoint
        gp.lineTo(x-s/4, y+s/4);//nextpoint
        gp.lineTo(x-s/2, y);//nextpoint 
        gp.lineTo(x-s/4, y-s/4);//nextpoint
        
        
        g2.setColor(Color.WHITE);
        g2.fill(gp);
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(2));
        g2.draw(gp);

        g2.drawString("V", x-10, y+9);
        shape =gp;
    }
}
