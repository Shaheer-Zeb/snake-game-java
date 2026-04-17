package Entity;
/**
 *
 * @author ShaheerZK
 */
import java.util.Random;
import Main.Main;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Fruit extends Entity
{
    Random random = new Random();
    int xPos = random.nextInt(Main.WIDTH - getWidth());
    int yPos = random.nextInt(Main.HEIGHT - getHeight());
    Image image = new ImageIcon("src/Images/apple.png").getImage();
    
    public Fruit()
    {
        super.setXPos(xPos);
        super.setYPos(yPos);
        super.image = image;
    }
    public void draw(Graphics2D g2)
    {
        g2.drawImage(image, getXPos(), getYPos(), getWidth(), getHeight(), null);
    }
    public void respawn()
    {
        setXPos(random.nextInt(Main.WIDTH - getWidth()));
        setYPos(random.nextInt(Main.HEIGHT - getHeight()));
    }
}
