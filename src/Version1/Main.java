
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
import org.jbox2d.dynamics.*;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.collision.shapes.*;

public class Main
{
    private World world;
    float square2x1 = 50;
    float square2y1 = 50;
    float square2x2 = 100;
    float square2y2  = 100;
    static Vec2 grav = new Vec2(0f,-10f);
    boolean sleep = false;
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
        World world = new World(grav);   
        
        initGL(1024, 600);
        
        //wObjs.add(new Object());
        testObj = new Object();
        testObj.init("resource/ammo.png", 0.0f, 0.0f, 64.0f, 64.0f);
        
        BodyDef player = new BodyDef();
        player.position.set(testObj.xPos, testObj.yPos);
        player.type = BodyType.DYNAMIC; 
        
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(5, 2,new Vec2(0,0),0);
        
        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        fd.density =1f;
        
        Body p1 = world.createBody(player);
        p1.createFixture(fd);
        
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
        if(Keyboard.isKeyDown(Keyboard.KEY_UP))
        {
            testObj.yPos -= 1;
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
          physUpdate();
      }
    }
    
    public void update()
    {
        ;
    }
    
    public void physUpdate()
    {
       //world.step(1f/60f, 6,2);
       //p1.getPosition();
        //world.step(1f/5f, 1,1);
    }
}
