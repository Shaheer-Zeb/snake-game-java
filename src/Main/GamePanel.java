package Main;
/**
 *
 * @author ShaheerZK
 */
import Entity.Fruit;
import Entity.Snake;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable
{
    public static final int TILESIZE = 40;
    final int FPS = 60;
    
    Thread gameThread;
    
    private final Snake snake;
    public static Fruit fruit;
    
    Image backgroundImage;
    
    private boolean gameOver;    
    GamePanel()
    {
        snake = new Snake(this);
        fruit = new Fruit();
        
        this.addKeyListener(snake);
        this.setFocusable(true);
        this.requestFocusInWindow();
        
        backgroundImage = new ImageIcon("src/Images/background.jpg").getImage();
        
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        draw((Graphics2D)g);
    }
    void draw(Graphics2D g2)
    {
        g2.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
        
        g2.setColor(Color.WHITE);
        g2.drawString("Score " + snake.getScore(), 20, 20);
        
        fruit.draw(g2);
        snake.draw(g2);
    }
    void update()
    {
        snake.update();
        checkGameOver();
    }
    void checkGameOver()
    {
        if (gameOver)
        {
            System.out.println("Game Over");
            gameThread.stop();
        }
    }
    @Override
    public void run() 
    {
        while (gameThread != null)
        {
            long lastUpdateTime = System.currentTimeMillis();
            while (true)
            {
                long deltaTime = System.currentTimeMillis() - lastUpdateTime;
                if (deltaTime >= 1000 / FPS)
                {
                  update();
                  repaint();
                  lastUpdateTime = System.currentTimeMillis();;
                }
                else
                {
                    try 
                    {
                        Thread.sleep(1000 / FPS - deltaTime);
                    } catch (InterruptedException ex)
                    {
                        System.getLogger(GamePanel.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                    }
                }
            }
        }
    } 
    public void setGameOver(boolean b)
    {
        gameOver = b;
    }
}
