package Entity;
/**
 *
 * @author ShaheerZK
 */
import java.awt.Image;
public class Entity 
{
    private int xPos, yPos;
    private final int width = 40, height = 40;
    Image image;
    
    public int getXPos()
    {
        return xPos;
    }
    public int getYPos()
    {
        return yPos;
    }
    public int getWidth()
    {
        return width;
    }
    public int getHeight()
    {
        return height;
    }
    public Image getImage()
    {
        return image;
    }
    public void setXPos(int i)
    {
        xPos = i;
    }
    public void setYPos(int i)
    {
        yPos = i;
    }
    public void changeXPos(int i)
    {
        xPos += i;
    }
    public void changeYPos(int i)
    {
        yPos += i;
    }
}
