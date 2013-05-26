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
    int deathTimer = 1000;
    int shotLimit = 0;
    boolean flagDamage = false;
    
    public Police(float x, float y, float w, float h)
    {
        super("resource/popoSprite.png", x, y, w, h, 0, 0, 32, 64);
        type = "Police";
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
