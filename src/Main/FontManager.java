package Main;
/**
 *
 * @author ShaheerZK
 */
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

public class FontManager 
{
    public static Font getFont()
    {
        Font mainFont = null;
        try
        {
            mainFont = Font.createFont(Font.PLAIN, new File("src/Fonts/MainFont.ttf"));
        }
        catch (FontFormatException | IOException e)
        {
            System.out.println("Exception occured while loading the font.");
        }
        return mainFont;
    }
}
