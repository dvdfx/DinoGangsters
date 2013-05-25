
package Version1;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;


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
      Display.setDisplayMode(new DisplayMode(100,100));
      Display.setTitle("DinoGangsters");
      Display.create();
    }
    
    public void destroy()
    {
        Display.destroy();
    }
    
    public void run()
    {
      while(!Display.isCloseRequested())
      {
       Display.update();   
      }
    }
    
    public void update()
    {
        
    }
}
