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
    public Bullet(String iLoc, float x, float y, float w, float h, float tx, float ty, float tw, float th)
    {
        super(iLoc, x, y, w, h, tx, ty, tw, th);
        type = "Bullet";
    }
}
