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
    public Bullet(float x, float y, boolean right)
    {
        super("resource/bullet.png", x, y, 8, 8, 0, 0, 4, 4);
        type = "Bullet";
    }
}
