/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Version1;

import java.io.*;
import static org.lwjgl.opengl.GL11.*;
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
    
    public Object()
    {
        ;
    }
    
    public void init(String iLoc, int w, int h) throws IOException
    {
        imgLoc = iLoc;
        System.out.println(imgLoc);
        texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(imgLoc));
    }
    
    public void render()
    {
        //glTranslatef(0,0,0.0f);
        glRotatef(0,0.0f,0.0f,1.0f);
        //glTranslatef(-(100 >> 1),-(100 >> 1),0.0f);
        texture.bind();
        glBegin(GL_QUADS);
            glTexCoord2f(0.0f,0.0f); glVertex2f(60,60);
            glTexCoord2f(1.0f,0.0f); glVertex2f(960,60);
            glTexCoord2f(1.0f,1.0f); glVertex2f(960,500);
            glTexCoord2f(0.0f,1.0f); glVertex2f(60,500);
        glEnd();
    }
}
