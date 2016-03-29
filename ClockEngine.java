
/**
 * Write a description of class ClockEngine here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import javax.swing.JFrame;
import javax.swing.Timer;
public class ClockEngine
{
    public static void main(String[] args)
    {
        //Create a frame
        JFrame clockFrame = new JFrame("Tic-toc goes the clock.");
        //setSize of the Frame
        clockFrame.setSize(ClockComponent.frameH,ClockComponent.frameW);
        
        //construct graphical component of clock
        ClockComponent c = new ClockComponent();
        //add graphical component of clock to frame
        clockFrame.add(c);
        //make frame visible
        clockFrame.setVisible(true);
        
        //animate, make clock go tic-toc
        Timer t = new Timer(1000,c);
        t.start();
    }
    
}