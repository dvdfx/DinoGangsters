/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Version1;

import java.io.*;
import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.Color;
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
    public float xPos, yPos, width, height;
    
    public Object()
    {
        ;
    }
    
    public void init(String iLoc, float x, float y, float w, float h) throws IOException
    {
        xPos = x;
        yPos = y;
        width = w;
        height = h;
        imgLoc = iLoc;
        System.out.println(imgLoc);
        texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(imgLoc));
    }
    
    public void render()
    {
        Color.white.bind();
        texture.bind();
        glBegin(GL_QUADS);
            glTexCoord2f(0.0f, 1.0f); glVertex2f(xPos, yPos);
            glTexCoord2f(1.0f,1.0f); glVertex2f(xPos + width, yPos);
            glTexCoord2f(1.0f,0.0f); glVertex2f(xPos + width, yPos + height);
            glTexCoord2f(0.0f,0.0f); glVertex2f(xPos, yPos + height);
        glEnd();
    }
}
