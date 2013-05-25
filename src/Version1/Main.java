
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
          main.create();
        }
        catch(Exception ex)
        {
            //LOGGER.log(Level.SEVERE,ex.toString(),ex);
        }
    }
    
    
    public void create() throws LWJGLException
    {
      Display.setDisplayMode(new DisplayMode(100,100));
      Display.create();
    }
}
