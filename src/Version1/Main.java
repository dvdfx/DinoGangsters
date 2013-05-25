
package Version1;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;


public class Main
{
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
 
	GL11.glEnable(GL11.GL_TEXTURE_2D);
        glClearColor(0.0f,0.0f,0.0f,0.0f);
        
        GL11.glViewport(0,0,width,height);
	GL11.glMatrixMode(GL11.GL_MODELVIEW);
        
        glMatrixMode(GL11.GL_PROJECTION);
        glLoadIdentity();
        gluOrtho2D(0.0f,width,0.0f,height);
        glPushMatrix();
        
        glDisable(GL_DEPTH_TEST);
        glDisable(GL_LIGHTING);
    }
    
    public void render()
    {
        glClear(GL_COLOR_BUFFER_BIT);
        glLoadIdentity();
 
        //Map background
        glTranslatef(0,0,0.0f);
        glRotatef(0,0.0f,0.0f,1.0f);
        glTranslatef(-(100 >> 1),-(100 >> 1),0.0f);
        glColor3f(0.0f,0.5f,0.5f);
        glBegin(GL_QUADS);
            glTexCoord2f(0.0f,0.0f); glVertex2f(60,60);
            glTexCoord2f(1.0f,0.0f); glVertex2f(800,60);
            glTexCoord2f(1.0f,1.0f); glVertex2f(800,650);
            glTexCoord2f(0.0f,1.0f); glVertex2f(60,650);
        glEnd();
    }
    
    public void run()
            
    {
      while(!Display.isCloseRequested())
      {
          if(Display.isVisible())
          {
              update();
              render();
          }
          Display.update();
      }
    }
    
    public void update()
    {
        
    }
}
