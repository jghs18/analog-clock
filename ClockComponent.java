
/**
 * Write a description of class ClockComponent here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import java.awt.geom.*;
import java.awt.BasicStroke;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class ClockComponent extends JComponent implements ActionListener
{
    public static final int DIAMETER = 400;
    public static final int frameH =(int) Math.round(1.55*DIAMETER);
    public static final int frameW =(int) Math.round(1.55*DIAMETER);

    public void actionPerformed(ActionEvent e)
    {
        
       repaint();
        
    }
    
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;//type casting g into a Graphics2D object referemced by g2
        //translate graphical context to center of clock (make origin the center of the clock)
        g2.translate(frameW/2,frameH/2);
        //henceforth, 0,0 will be the center of the clock
        //retrieve data from internal clock of machine
        GregorianCalendar gC = new GregorianCalendar();
        int hr = gC.get(Calendar.HOUR);
        int min = gC.get(Calendar.MINUTE);
        int sec = gC.get(Calendar.SECOND);
        System.out.println(sec);
        //draw clock face
        drawClockFace(g2);
        //draw clock hands
        drawClockHands(g2,hr,min,sec);
    }

    private void drawClockFace(Graphics2D g2)
    {
        //draw outer circle for perimeter of clock
        Ellipse2D.Double face = new Ellipse2D.Double(-DIAMETER/2,-DIAMETER/2,DIAMETER,DIAMETER);
        g2.setStroke(new BasicStroke(15));
        g2.draw(face);
        //draw tick marks along inner perimeter
        double r1 = DIAMETER * 0.315;
        double r2 = DIAMETER * 0.368;
        //difference between DIAMETER coefficients represents length of tick mark
        //coefficient for r1 represents distance from center of tick mark

        for(int theta = 0; theta <360; theta += 6)
        {
            if(theta%30==0) g2.setStroke(new BasicStroke(7));
            else g2.setStroke(new BasicStroke(2));
            double thetaRadians = Math.toRadians(theta);
            //r1 represents the hypotenuse of the triangle formed from the center of the clock...
            double x1 = r1 * Math.cos(thetaRadians);//static method cos invoked with explicit parameter radian value of angle
            double y1 = r1 * Math.sin(thetaRadians);
            double x2 = r2 * Math.cos(thetaRadians);
            double y2 = r2 * Math.sin(thetaRadians);
            Line2D.Double tick = new Line2D.Double(x1,y1,x2,y2);
            g2.draw(tick);
        }
        //"draw" numbers
        double rNum = DIAMETER * 0.43;
        int fontSize =(int) Math.round(DIAMETER * 0.08);
        g2.setFont(new Font("Arial", Font.BOLD+Font.ITALIC,fontSize));
        double yOffSet = fontSize * 0.44;
        for(int h = 1; h<=12; h++)
        {
            String hrStr = Integer.toString(h);
            double thetaHr = getHrRadians(h,0);
            
            double xOffSet = hrStr.length() * DIAMETER * 0.025;
            
            double x = rNum*Math.cos(thetaHr)-xOffSet;
            double y = rNum*Math.sin(thetaHr)+yOffSet;
            
            g2.drawString(hrStr,(float) x,(float) y);
        }
        //anything else you wish to add to the face
    }
    
    public double getHrRadians(int hr, int min)
    {
        return Math.toRadians(30*hr-90+(min/2));
    }
    
    public double getMinRadians(int min, int sec)
    {
        return Math.toRadians(6*min-90+(sec/10));
    }
    
    public double getSecRadians(int sec)
    {
        return Math.toRadians(6*sec-90);
    }
    
    public void drawClockHands(Graphics2D g2, int hr, int min, int sec)
    {
        double hrRad = DIAMETER * 0.218;
        double thetaHr = getHrRadians(hr,min);
        
        double xHr = hrRad * Math.cos(thetaHr);
        double yHr = hrRad * Math.sin(thetaHr);
        g2.setStroke(new BasicStroke(8));
        Line2D.Double hrHand = new Line2D.Double(0,0,xHr,yHr);
        g2.draw(hrHand);
        
        double minRad = DIAMETER * 0.335;
        double thetaMin = getMinRadians(min,sec);
        
        double xMin = minRad * Math.cos(thetaMin);
        double yMin = minRad * Math.sin(thetaMin);
        g2.setStroke(new BasicStroke(3));
        Line2D.Double minHand = new Line2D.Double(0,0,xMin,yMin);
        g2.draw(minHand);
        
        double secRad = DIAMETER * 0.368;
        double thetaSec = getSecRadians(sec);
        
        double xSec = secRad * Math.cos(thetaSec);
        double ySec = secRad * Math.sin(thetaSec);
        g2.setStroke(new BasicStroke(1));
        g2.setColor(Color.RED);
        Line2D.Double secHand = new Line2D.Double(0,0,xSec,ySec);
        g2.draw(secHand);
        
    }


}
