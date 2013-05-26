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
    int deathTimer = 0;
    int shotLimit = 0;
    boolean flagDamage = false;
    float yClip = 24;
    float yOff = 12;
    float yOffTex = 12;
    
    public Police(float x, float y)
    {
        super("resource/popoSprite2.png", x, y, 64, 104, 0, 12, 32, 52);
        type = "Police";
    }
        
    public void changeSprite(float tx, float ty)
    {
        ty += yOff;
        super.changeSprite(tx, ty);
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
