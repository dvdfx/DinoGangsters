/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Version1;
import org.lwjgl.openal.AL;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.util.ResourceLoader;

/**
 *
 * @author Ramen
 */
public class Bullet extends Object
{
    private String source;
    
    public Bullet(float x, float y, boolean right, String src)
    {
        super("src/resource/bullet.png", x, y, 8, 8, 0, 0, 4, 4);
        type = "Bullet";
        source = src;
        if(right)
        {
            velocity[0] = 8;
        }
        else
        {
            velocity[0] = -8;
        }
    }
    
    public String getSource()
    {
        return source;
    }
}
