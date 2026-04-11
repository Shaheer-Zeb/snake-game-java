package Main;
/**
 *
 * @author ShaheerZK
 */
import javax.swing.JFrame;

public class Main 
{
    public final static int WIDTH = 800;
    public final static int HEIGHT = 600;
    static JFrame frame;
    
    public static void main(String[] args) 
    {
        frame = new JFrame("Snake Mate");
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        
        frame.add(new GamePanel());
        
        frame.setVisible(true);
    }
}