
package Version1;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import org.jbox2d.dynamics.World;
import org.jbox2d.common.Vec2;

public class Main
{
    private World world;
    
    public static void main(String[] args)
    {
        Main main = null;
        try
        {
          main = new Main();
          main.create();
          main.run();
        }
        catch(Exception ex)
        {
            //LOGGER.log(Level.SEVERE,ex.toString(),ex);
        }
    }
    
    
    public void create() throws LWJGLException
    {
        Vec2 grav = new Vec2(0f,-10f);
        World world = new World(grav);
        
        initGL(1024, 600);
        
    }
    
     
    public void destroy()
    {
        Display.destroy();
    }
    
    public void initGL(int width, int height)
    {
        try
        {
            Display.setDisplayMode(new DisplayMode(width,height));
            Display.create();
            Display.setVSyncEnabled(true);
	}
        catch (LWJGLException e)
        {
            e.printStackTrace();
            System.exit(0);
	}
 
	glEnable(GL11.GL_TEXTURE_2D);
        glClearColor(0.0f,0.0f,0.0f,0.0f);
        
        glEnable(GL11.GL_BLEND);
        glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        
        glViewport(0,0,width,height);
	glMatrixMode(GL_MODELVIEW);
        
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        gluOrtho2D(0.0f,width,0.0f,height);
	glMatrixMode(GL_MODELVIEW);
        
        glDisable(GL_DEPTH_TEST);
        glDisable(GL_LIGHTING);
    }
    
    public void render()
    {
        glClear(GL_COLOR_BUFFER_BIT);
        glLoadIdentity();
 
        //Map background
        //glTranslatef(0,0,0.0f);
        glRotatef(0,0.0f,0.0f,1.0f);
        //glTranslatef(-(100 >> 1),-(100 >> 1),0.0f);
        glColor3f(0.0f,0.5f,0.5f);
        glBegin(GL_QUADS);
            glTexCoord2f(0.0f,0.0f); glVertex2f(60,60);
            glTexCoord2f(1.0f,0.0f); glVertex2f(960,60);
            glTexCoord2f(1.0f,1.0f); glVertex2f(960,500);
            glTexCoord2f(0.0f,1.0f); glVertex2f(60,500);
        glEnd();
        glColor3f(0.0f,0.0f,0.5f);
        glBegin(GL_QUADS);
            glTexCoord2f(0.0f,0.0f); glVertex2f(200,200);
            glTexCoord2f(1.0f,0.0f); glVertex2f(300,200);
            glTexCoord2f(1.0f,1.0f); glVertex2f(300,300);
            glTexCoord2f(0.0f,1.0f); glVertex2f(200,300);
        glEnd();
    }
    
    public void processMouse()
    {
        float XPos = Mouse.getX();
        float YPos = Mouse.getY();
        
        
    }
    
    public void run()            
    {
      while(!Display.isCloseRequested())
      {
          if(Display.isVisible())
          {
              processMouse();
              update();
              render();
          }
          Display.update();
      }
    }
    
    public void update()
    {
        //world.step(1f/5f, 1,1);
    }
}
