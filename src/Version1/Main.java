
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


public class Main
{
    float square2x1 = 50;
    float square2y1 = 50;
    float square2x2 = 100;
    float square2y2  = 100;
    boolean shoot = false;
    private ArrayList<Object> wObjs = new ArrayList<Object>();
    private Object testObj;
    
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
        
        initGL(1024, 600);
        
        //wObjs.add(new Object());
        testObj = new Object();
        testObj.init("resource/rex.png", 0.0f, 0.0f, 64.0f, 64.0f);

        
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
        glOrtho(0, width, 0, height, 1, -1);
        //gluOrtho2D(0.0f,width,0.0f,height);
	glMatrixMode(GL_MODELVIEW);
        
        glDisable(GL_DEPTH_TEST);
        glDisable(GL_LIGHTING);
    }
    
    public void render()
    {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glLoadIdentity();
        
        testObj.render();
        for(int i = 0; i < wObjs.size(); i++)
        {
            wObjs.get(i).render();
        }
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
            testObj.xPos += 0.5;
        }
        
        if(Keyboard.isKeyDown(Keyboard.KEY_LEFT))
        {
            testObj.xPos -= 0.5;
        }
        
        if(Keyboard.isKeyDown(Keyboard.KEY_UP))
        {
            testObj.yPos += 1;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_DOWN))
        {
            testObj.yPos -= 1;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_SPACE))// && shoot == false)
        {
              shoot = true;
              Object shot = new Object();
              shot.init("resource/bullet.png",testObj.xPos +50 , testObj.yPos +40, 4, 4);
              wObjs.add(shot);
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
        constrainPlayer();
        bulletMove();
    }
    
    public void constrainPlayer()
    {
        if(testObj.xPos < 5)
        {
            testObj.xPos = 5;
        }
        if(testObj.xPos > 970)
        {
            testObj.xPos = 970;
        }
        if(testObj.yPos < 2)
        {
            testObj.yPos = 2;
        }
        if(testObj.yPos > 150)
        {
            testObj.yPos = 150;
        }
    }
    public void bulletMove()
    {
        for(int i = 0; i < wObjs.size(); i++)
        {
            wObjs.get(i).xPos += 5;
        }
    }
}
