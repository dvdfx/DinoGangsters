
package Version1;
import java.util.*;
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
    float square2x1 = 50;
    float square2y1 = 50;
    float square2x2 = 100;
    float square2y2  = 100;
    static Vec2 grav = new Vec2(0f,-10f);
    private ArrayList<Object> wObjs = new ArrayList<Object>();

    
    public static void main(String[] args)
    {
        Main main = null;
        try
        {
          main = new Main();
          World world = new World(grav);
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
        //World world = new World(grav);
        
        initGL(1024, 600);
        
        Mouse.setGrabbed(false);
        Mouse.create();        
    }
    
     
    public void destroy()
    {
        Mouse.destroy();
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
        glRotatef(0,0.0f,0.0f,1.0f);
        glColor3f(0.0f,0.5f,0.5f);
        glBegin(GL_QUADS);
            glTexCoord2f(0.0f,0.0f); glVertex2f(60,60);
            glTexCoord2f(1.0f,0.0f); glVertex2f(960,60);
            glTexCoord2f(1.0f,1.0f); glVertex2f(960,500);
            glTexCoord2f(0.0f,1.0f); glVertex2f(60,500);
        glEnd();
        glColor3f(0.0f,0.0f,0.5f);
        glBegin(GL_QUADS);
            glTexCoord2f(0.0f,0.0f); glVertex2f(square2x1,square2y1);
            glTexCoord2f(1.0f,0.0f); glVertex2f(square2x2,square2y1);
            glTexCoord2f(1.0f,1.0f); glVertex2f(square2x2,square2y2);
            glTexCoord2f(0.0f,1.0f); glVertex2f(square2x1,square2y2);
        glEnd();
    }
    
    public void processMouse()
    {
        float XPos = Mouse.getX();
        float YPos = Mouse.getY();
             
    }
    
    public void processKeyboard()
    {
        if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
        {
            square2x1 += 0.5;
            square2x2 += 0.5;
        }
        
        if(Keyboard.isKeyDown(Keyboard.KEY_LEFT))
        {
            square2x1 -= 0.5;
            square2x2 -= 0.5;
        }
        
        if(Keyboard.isKeyDown(Keyboard.KEY_UP))
        {
            square2y1 += 1;
            square2y2 += 1;
        }
    }
    
    public void run()            
    {
      while(!Display.isCloseRequested())
      {
          if(Display.isVisible())
          {
              processKeyboard();
              processMouse();
              update();
              render();
          }
          Display.update();
      }
    }
    
    public void update()
    {
        world.step(1f/5f, 1,1);

    }
}
