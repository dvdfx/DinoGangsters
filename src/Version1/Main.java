
package Version1;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;


public class Main
{
    public static final int D_HEIGHT = 650;
    public static final int D_WIDTH = 900;

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
      Display.setDisplayMode(new DisplayMode(D_WIDTH,D_HEIGHT));
      Display.create();
      
      initGL();
      resizeGL();
    }
    
     
    public void destroy()
    {
        Display.destroy();
    }
    
    public void initGL()
    {
        glClearColor(0.0f,0.0f,0.0f,0.0f);
        glDisable(GL_DEPTH_TEST);
        glDisable(GL_LIGHTING);
    }
    
    public void resizeGL()
    {
        glViewport(0,0,D_WIDTH,D_HEIGHT);
        
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        gluOrtho2D(0.0f,D_WIDTH,0.0f,D_HEIGHT);
        glPushMatrix();

        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
        glPushMatrix();
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
