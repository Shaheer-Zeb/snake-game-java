package Entity;
/**
 *
 * @author ShaheerZK
 */
import java.awt.Image;

public class SnakeBodyPart extends Entity
{
    enum Direction
    {
        horizontal, vertical, bottomLeft, bottomRight, topLeft, topRight;
    }
    Direction direction;
    SnakeBodyPart(int xPos, int yPos, Image image)
    {
        super.setXPos(xPos);
        super.setYPos(yPos);
        super.image = image;
    }
}
