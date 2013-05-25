/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Version1;

import java.io.*;
import org.newdawn.slick.opengl.*;
import org.newdawn.slick.util.ResourceLoader;

/**
 *
 * @author Ramen
 */
public class Object
{
    private Texture texture;
    private String imgLoc;
    
    public void init() throws IOException
    {
        texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(imgLoc));
    }
}
