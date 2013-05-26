package Version1;

import java.io.IOException;
import java.io.*;
import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.*;
import org.newdawn.slick.util.ResourceLoader;;

/**
 *
 * @author David Fox
 */
public class Police extends Object
{     
    int health = 100;
    int shotLimit = 3;
    
    public Police()
    {
        type = "Police";
    }
    
    public void init(float x, float y, float w, float h)
    {
        xPos = x;
        yPos = y;
        width = w;
        height = h;
        tOffX = 0;
        tOffY = 0;
        tOffW = 32;
        tOffH = 64;
        try
        {
            texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("resource/popoSprite.png"));
        }
        catch (IOException e)
        {
            texture = null;
        }
        texW = texture.getTextureWidth();
        texH = texture.getTextureHeight();
    }
    
    public void takeDamage()
    {
        this.health -= 20;
    }
    
    public int getHealth()
    {
        return this.health;
    }
        
}
