
package Version1;
import java.util.*;
import java.util.Random;
import java.awt.Font;
import java.io.IOException;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.Color;
import org.newdawn.slick.util.ResourceLoader;
import org.lwjgl.openal.AL;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.openal.SoundStore;

public class Main
{
    float square2x1 = 50;
    float square2y1 = 50;
    float square2x2 = 100;
    float square2y2  = 100;
    float clickedXPos, clickedYPos =0;
    boolean shoot = false;
    boolean menu = true;
    int score =0;
    TrueTypeFont font;
    private ArrayList<Object> bulletObjs = new ArrayList<Object>();
    private ArrayList<Object> policeObjs = new ArrayList<Object>();
    private Object testObj;
    private Object menuObj;
    private Object bkgd;
    
    private int SCREEN_WIDTH = 1024;
    private int SCREEN_HEIGHT = 600;
    
    private Audio wavEffect;
    
    private long lastFrame;
    private int fps;
    private long lastFPS;
    
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
    
    
    public void create() throws LWJGLException, IOException
    {
        initGL(SCREEN_WIDTH, SCREEN_HEIGHT);
        
        getDelta();
        lastFPS = getTime();
        
        menuObj = new Object();
        menuObj.init("resource/ammo.png", 50.0f, 80.0f, 916f, 520f, 0.0f, 0.0f, 16f, 16f);
        
        testObj = new Object();
        testObj.init("resource/rexWithTGun2.png", 0.0f, 0.0f, 64.0f, 128.0f, 0.0f, 0.0f, 32.0f, 64.0f);
        
        bkgd = new Object();
        bkgd.init("resource/street.png", 0.0f, 0.0f, SCREEN_WIDTH, SCREEN_HEIGHT, 220.0f, 40.0f, 380.0f, 200.0f);
        
        Font awtFont = new Font("Times New Roman", Font.BOLD, 24);
        font = new TrueTypeFont(awtFont, false);
                    
        wavEffect = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("resource/menu.wav"));
        
        Mouse.setGrabbed(false);
        Mouse.create();        
    }
    
     
    public void destroy()
    {
        AL.destroy();
        Mouse.destroy();
        Display.destroy();
    }
    
    public void initGL(int width, int height)
    {
        try
        {
            Display.setDisplayMode(new DisplayMode(width,height));
            Display.create();
            //Display.setVSyncEnabled(true);
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
        glOrtho(0, width, height, 0, 1, -1);
        //gluOrtho2D(0.0f,width,0.0f,height);
	glMatrixMode(GL_MODELVIEW);
        
        glDisable(GL_DEPTH_TEST);
        glDisable(GL_LIGHTING);
    }
    
    public void render()
    {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glLoadIdentity();
        
        if(menu == true)
        {
            menuObj.render();
        }
        else
        {
            bkgd.render();
            testObj.render();
            for(int i = 0; i < bulletObjs.size(); i++)
            {
                bulletObjs.get(i).render();
            }
            for(int j =0; j < policeObjs.size(); j++)
            {
                policeObjs.get(j).render();
            }
        
            displayScore();
        }
    }
    
    public void processMouse()
    {
        clickedXPos = Mouse.getX();
        clickedYPos = Mouse.getY();
             
    }
    
    public void processKeyboard()
    {
        if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
        {
            testObj.xPos += 4;
        }
        
        if(Keyboard.isKeyDown(Keyboard.KEY_LEFT))
        {
            testObj.xPos -= 4;
        }
        
        if(Keyboard.isKeyDown(Keyboard.KEY_UP))
        {
            testObj.yPos -= 4;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_DOWN))
        {
            testObj.yPos += 4;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_SPACE))
        {
              shoot = true;
              if(bulletObjs.size() > 0)
              {
                if(bulletObjs.get(bulletObjs.size()-1).xPos > (testObj.xPos + 80))
                {
                  Object shot = new Object();
                  shot.init("resource/bullet.png",testObj.xPos +50 , testObj.yPos +75, 8, 8, 0, 0, 4, 4);
                  bulletObjs.add(shot);
                }
              }
              else
              {
                  Object shot = new Object();
                  shot.init("resource/bullet.png",testObj.xPos +50 , testObj.yPos +75, 8, 8, 0, 0, 4, 4);
                  bulletObjs.add(shot);
              }
        }
    }
    
    public void run()            
    {
      wavEffect.playAsSoundEffect(1.0f, 1.0f, true);
      while(!Display.isCloseRequested())
      {
          if(Display.isVisible())
          {
              if(menu == true)
              {
                  processMouse();
                  startClicked();
                  playMusic();
                  render();
                  SoundStore.get().poll(0);
              }
              else
              {
                  AL.destroy();
                  int delta = getDelta();
                  processKeyboard();
                  processMouse();
                  update(delta);
                  render();
              }
          }
          Display.update();
          Display.sync(60);
      }
      Display.destroy();
    }
    
    public void update(int delta)
    {
        constrainPlayer();
        bulletMove();
        addPoPo();
        bulletCol();
    }
    
    public void startClicked()
    {
        if((Mouse.isButtonDown(0))&&(clickedXPos > 350)&&(clickedXPos < 650 ))
        {
            menu = false;
        }
        else if(Mouse.isButtonDown(0))
        {
            System.out.println(clickedXPos);
        }
    }
    
    public void playMusic()
    {
        
    }
    
    public void constrainPlayer()
    {
        if(testObj.xPos < 5)
        {
            testObj.xPos = 5;
        }
        if(testObj.xPos > 960)
        {
            testObj.xPos = 960;
        }
        if(testObj.yPos > 465)
        {
            testObj.yPos = 465;
        }
        if(testObj.yPos < 350)
        {
            testObj.yPos = 350;
        }
    }
    public void bulletMove()
    {
        for(int i = 0; i < bulletObjs.size(); i++)
        {
            bulletObjs.get(i).xPos += 8;
        }
    }
    
    public void addPoPo()
    {
        Random randomGenerator = new Random();
        int chance = randomGenerator.nextInt(150);
                
        if(chance == 75)
        {
            int randX = randomGenerator.nextInt(600);
            int randY = randomGenerator.nextInt(120);
            
            Object police = new Object();
            police.init("resource/police.png", testObj.xPos+50+randX, 350+randY, 64, 128, 0, 0, 32, 64);
            policeObjs.add(police);
        }
    }
    
    public void bulletCol()
    {
        for(int i=0; i<bulletObjs.size(); i++)
        {
            for(int j=0; j<policeObjs.size(); j++)
            {
                if((bulletObjs.get(i).xPos > policeObjs.get(j).xPos)&&(bulletObjs.get(i).xPos < policeObjs.get(j).xPos+32)&&(bulletObjs.get(i).yPos > policeObjs.get(j).yPos) &&(bulletObjs.get(i).yPos < policeObjs.get(j).yPos+128))
                {
                    policeObjs.remove(j);
                    bulletObjs.remove(i);
                    score++;
                    break;
                }
            }
        }
        
        for(int i=0; i<bulletObjs.size(); i++)
        {
          if(bulletObjs.get(i).xPos > 930)
          {
              bulletObjs.remove(i);
          }
        }
    }
    
    public void displayScore()
    {
        font.drawString(50, 20, "Score: "+score, Color.green);
    }
    
    public int getDelta()
    {
        long time = getTime();
        int delta = (int) (time - lastFrame);
        lastFrame = time;
        
        return delta;
    }
    
    public long getTime()
    {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }
}
