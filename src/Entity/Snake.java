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
import java.util.ArrayList;

public class Snake extends Entity implements KeyListener
{
    Random random = new Random();
    private int xPos = random.nextInt(Main.WIDTH - GamePanel.IMAGESIZE);
    private int yPos = random.nextInt(Main.HEIGHT - GamePanel.IMAGESIZE);
    
    private int size = 2;
    private final int speed = 1;
    
    Image snakeLeftImage = new ImageIcon("src/Images/head_left.png").getImage();
    Image snakeRightImage = new ImageIcon("src/Images/head_right.png").getImage();
    Image snakeUpImage = new ImageIcon("src/Images/head_up.png").getImage();
    Image snakeDownImage = new ImageIcon("src/Images/head_down.png").getImage();
    
    Image tailLeftImage = new ImageIcon("src/Images/tail_left.png").getImage();
    Image tailRightImage = new ImageIcon("src/Images/tail_right.png").getImage();
    Image tailUpImage = new ImageIcon("src/Images/tail_up.png").getImage();
    Image tailDownImage = new ImageIcon("src/Images/tail_down.png").getImage();
    
    Image bodyVerticalImage = new ImageIcon("src/Images/body_vertical.png").getImage();
    Image bodyHorizontalImage = new ImageIcon("src/Images/body_horizontal.png").getImage();
    
    ArrayList<SnakeBodyPart> snakeBody = new ArrayList<>();
    
    boolean movingLeft;
    boolean movingRight = true;
    boolean movingUp;
    boolean movingDown;
    
    GamePanel panel;
    Fruit fruit;
    
    int score = 0;
    
    public Snake(GamePanel panel, Fruit fruit)
    {
        super.setXPos(xPos);
        super.setYPos(yPos);
        super.image = snakeRightImage;
        
        snakeBody.add(score, new SnakeBodyPart(xPos - GamePanel.IMAGESIZE, yPos, tailLeftImage));

        this.panel = panel;
        this.fruit = fruit;
    }
    public void draw(Graphics2D g2)
    {
        g2.drawImage(image, getXPos(), getYPos(), getWidth(), getHeight(), null); // The head
        for (SnakeBodyPart part : snakeBody) // The body
            g2.drawImage(part.getImage(), part.getXPos(), part.getYPos(), part.getWidth(), part.getHeight(), null);
    }
    public void update()
    {
        checkForCollision();
        checkFruitConsumption();
        move();
    }
    private void move()
    {
        // Moving the head
        if (movingLeft)
            changeXPos(-speed);
        else if (movingRight)
            changeXPos(speed);
        else if (movingUp)
            changeYPos(-speed);
        else if (movingDown)
            changeYPos(speed);
        // Moving the body
        moveBody();
    }
    void moveBody()
    {
        int size = snakeBody.size();
        for (int i = 0; i < size; i++)
        {
            if (i == size - 1)
            {
                SnakeBodyPart part = snakeBody.get(i);
                part.setXPos(getXPos() - GamePanel.IMAGESIZE);
                part.setYPos((getYPos()));
                break;
            }
            SnakeBodyPart previousPart = snakeBody.get(i);
            SnakeBodyPart nextPart = snakeBody.get(i + 1);
            
            previousPart.setXPos(nextPart.getXPos() - GamePanel.IMAGESIZE);
            previousPart.setYPos(nextPart.getYPos());
        }
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
        
        int fruitXPos = fruit.getXPos();
        int fruitYPos = fruit.getYPos();
        int fruitWidth = fruit.getWidth();
        int fruitHeight = fruit.getHeight();
        
        if ((xPos >= fruitXPos && xPos <= fruitXPos + fruitWidth) 
                && (yPos >= fruitYPos && yPos < fruitYPos + fruitHeight))
        {
            growSnakeBody();
            size++;
            fruit.respawn();
        }
    }
    void growSnakeBody()
    {
         SnakeBodyPart tail = snakeBody.get(0);
         tail.setXPos(tail.getXPos() - GamePanel.IMAGESIZE);
         snakeBody.add(++score, new SnakeBodyPart(tail.getXPos() + GamePanel.IMAGESIZE, tail.getYPos(), bodyHorizontalImage));   
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
        char key = ke.getKeyChar();

        if (key == 'w' && !movingDown) 
        {
            movingUp = true;
            movingDown = false;
            movingLeft = false;
            movingRight = false;
    
            image = snakeUpImage;
        } 
        else if (key == 'a' && !movingRight) 
        {
            movingUp = false;
            movingDown = false;
            movingLeft = true;
            movingRight = false;
    
            image = snakeLeftImage;
        } 
        else if (key == 's' && !movingUp) 
        {
            movingUp = false;
            movingDown = true;
            movingLeft = false;
            movingRight = false;
    
            image = snakeDownImage;
        } 
        else if (key == 'd' && !movingLeft) 
        {
            movingUp = false;
            movingDown = false;
            movingLeft = false;
            movingRight = true;
    
            image = snakeRightImage;
        }
    }
    @Override
    public void keyReleased(KeyEvent ke) 
    {
        
    }
}
