package Entity;
/**
 *
 * @author ShaheerZK
 */
import java.util.Random;
import Main.Main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import Main.GamePanel;

public class Snake extends Entity implements KeyListener
{
    Random random = new Random();
    private int xPos = random.nextInt(Main.WIDTH - GamePanel.TILESIZE);
    private int yPos = random.nextInt(Main.HEIGHT - GamePanel.TILESIZE);
    
    private int size = 1;
    private final int speed = 1;
    
    Image snakeLeftImage = new ImageIcon("src/Images/head_left.png").getImage();
    Image snakeRightImage = new ImageIcon("src/Images/head_right.png").getImage();
    Image snakeUpImage = new ImageIcon("src/Images/head_up.png").getImage();
    Image snakeDownImage = new ImageIcon("src/Images/head_down.png").getImage();
    
    boolean movingLeft;
    boolean movingRight = true;
    boolean movingUp;
    boolean movingDown;
    
    GamePanel panel;
    
    int score = 0;
    
    public Snake(GamePanel panel)
    {
        super.setXPos(xPos);
        super.setYPos(yPos);
        this.image = snakeRightImage;

        this.panel = panel;
    }
    public void draw(Graphics2D g2)
    {
        g2.drawImage(image, getXPos(), getYPos(), getWidth(), getHeight(), null);
    }
    public void update()
    {
        checkForCollision();
        checkFruitConsumption();
        move();
    }
    private void move()
    {
        if (movingLeft)
            changeXPos(-speed);
        else if (movingRight)
            changeXPos(speed);
        else if (movingUp)
            changeYPos(-speed);
        else if (movingDown)
            changeYPos(speed);
    }
    private void checkForCollision()
    {
        if (super.getXPos() <= 0)
            panel.setGameOver(true);
        else if (super.getXPos() >= Main.WIDTH - getWidth())
            panel.setGameOver(true);
        else if (super.getYPos() <= 0)
            panel.setGameOver(true);
        else if (super.getYPos() >= Main.HEIGHT - getHeight())
            panel.setGameOver(true);
    }
    private void checkFruitConsumption()
    {
        int xPos = getXPos();
        int yPos = getYPos();
        
        int fruitXPos = GamePanel.fruit.getXPos();
        int fruitYPos = GamePanel.fruit.getYPos();
        int fruitWidth = GamePanel.fruit.getWidth();
        int fruitHeight = GamePanel.fruit.getHeight();
        
        if ((xPos >= fruitXPos && xPos <= fruitXPos + fruitWidth) 
                && (yPos >= fruitYPos && yPos < fruitYPos + fruitHeight))
        {
            score++;
            size++;
            GamePanel.fruit.setXPos(random.nextInt(Main.WIDTH - fruitWidth));
            GamePanel.fruit.setYPos(random.nextInt(Main.HEIGHT - fruitHeight));
            System.out.println("Consumed");
        }
    }
    public int getScore()
    {
        return score;
    }
    @Override
    public void keyTyped(KeyEvent ke) 
    {
        
    }
    @Override
    public void keyPressed(KeyEvent ke) 
    {
        switch (ke.getKeyChar()) 
        {
            case 'w' ->
            {
                movingUp = true;
                movingDown = false;
                movingLeft = false;
                movingRight = false;
                
                image = snakeUpImage;
            }
            case 'a' -> 
            {
                movingUp = false;
                movingDown = false;
                movingLeft = true;
                movingRight = false;
                
                image = snakeLeftImage;
            }
            case 's' -> 
            {
                movingUp = false;
                movingDown = true;
                movingLeft = false;
                movingRight = false;
                
                image = snakeDownImage;
            }
            case 'd' -> 
            {
                movingUp = false;
                movingDown = false;
                movingLeft = false;
                movingRight = true;
                
                image = snakeRightImage;
            }
        }
    }
    @Override
    public void keyReleased(KeyEvent ke) 
    {
        
    }
}
