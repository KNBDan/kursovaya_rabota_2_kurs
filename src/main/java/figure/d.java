package figure;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.GeneralPath;

public class  d extends figures{//document
//int s, x, y;
    public d(int x, int y, int s) {
        this.x=x/2;
        this.y=y/2;
        this.s=s;
        this.nameF = "D(IF)" + this.id;
    }
    Font font = new Font("Arial", Font.BOLD, 20);
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setFont(font);
        g2.setRenderingHint ( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
        GeneralPath gp = new GeneralPath();
        gp.moveTo(x-s/4, y-s/4);//startpoint
        gp.lineTo(x, y);//nextpoint
        gp.lineTo(x-s/4, y+s/4);//nextpoint
        gp.lineTo(x-s/2, y);//nextpoint
        gp.lineTo(x-s/4, y-s/4);//nextpoint
        
        
               //g2.drawRect(x-s/2, y-s/4, s, s/2);
        
        //gp.curveTo( x-s/64, y-s/8,x+s/64, y+s/2, x-s/2, y+3*s/14);
        g2.setColor(Color.WHITE);
        g2.fill(gp);
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(2));
        g2.draw(gp);
        g2.drawString("IF", x-34, y+7);
        shape =gp;
        }
}