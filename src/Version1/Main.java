
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
    private ArrayList<Object> wObjs = new ArrayList<Object>();
    private Object testObj;
    private Object menuObj;
    private Object bkgd;
    
    private int SCREEN_WIDTH = 1024;
    private int SCREEN_HEIGHT = 600;
    
    private Audio menuMusic;
    private Audio fireSound;
    private Audio inGameMusic;
    
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
        
        menuObj = new Object("resource/start.png", 0.0f, 0.0f, 1024f, 600f, 0.0f, 0.0f, 1024f, 600f);
        
        testObj = new Object("resource/rexSprite.png", 0.0f, 0.0f, 64.0f, 128.0f, 0.0f, 0.0f, 32.0f, 64.0f);
        wObjs.add(testObj);
        
        bkgd = new Object("resource/street2.png", 0.0f, 0.0f, SCREEN_WIDTH, SCREEN_HEIGHT, 220.0f, 0.0f, 420.0f, 280.0f);
        
        Font awtFont = new Font("Times New Roman", Font.BOLD, 24);
        font = new TrueTypeFont(awtFont, false);
                    
        menuMusic = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("resource/menu3.wav"));
        inGameMusic = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("resource/ingame1.wav"));
        fireSound = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("resource/shoot.wav"));
        
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
            testObj.changeSprite(testObj.getSpriteX() + 32, 0);
            testObj.render();
            for(int i = 0; i < wObjs.size(); i++)
            {
                if(wObjs.get(i).type.equals("Police"))
                {
                    if(((Police)wObjs.get(i)).flagDamage)
                    {
                        wObjs.get(i).changeSprite(0, 64);
                        ((Police)wObjs.get(i)).flagDamage = false;
                    }
                    else
                    {
                        //wObjs.get(i).changeSprite(0, 0);
                    }
                }
                wObjs.get(i).render();
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
              if(wObjs.size() > 0)
              {
                if(wObjs.get(wObjs.size()-1).xPos > (testObj.xPos + 80))
                {
                  fireSound.playAsSoundEffect(1.0f, 1.0f, false);
                  wObjs.add(new Bullet("resource/bullet.png",testObj.xPos +50 , testObj.yPos +75, 8, 8, 0, 0, 4, 4));
                }
              }
              else
              {
                  fireSound.playAsSoundEffect(1.0f, 1.0f, false);
                  wObjs.add(new Bullet("resource/bullet.png",testObj.xPos +50 , testObj.yPos +75, 8, 8, 0, 0, 4, 4));
              }
        }
    }
    
    public void run()            
    {
        boolean inMenu = false;
      while(!Display.isCloseRequested())
      {
          if(Display.isVisible())
          {
              if(menu == true)
              {
                  if(inMenu == false)
                  {
                    inGameMusic.stop();
                    menuMusic.playAsSoundEffect(1.0f, 1.0f, true);
                    inMenu = true;
                  }
                  processMouse();
                  startClicked();
                  render();
                  SoundStore.get().poll(0);
              }
              else
              {
                  if(inMenu == true)
                  {
                    menuMusic.stop();
                    inGameMusic.playAsMusic(1.0f, 1.0f, true);
                    inMenu = false;
                  }
                  int delta = getDelta();
                  processKeyboard();
                  processMouse();
                  update(delta);
                  render();
                  SoundStore.get().poll(0);
              }
          }
          Display.update();
          Display.sync(60);
      }
      Display.destroy();
      AL.destroy();
    }
    
    public void update(int delta)
    {
        constrainPlayer();
        bulletMove();
        addPoPo();
        policeMove();
        bulletCol();
        playDeath();
        poShootYou();
    }
    
    public void startClicked()
    {
        if((Mouse.isButtonDown(0))&&(clickedXPos > 270)&&(clickedXPos < 765)&&(clickedYPos < 225)&&(clickedYPos > 150))
        {
            menu = false;
        }
        else if(Mouse.isButtonDown(0))
        {
           // System.out.println(clickedYPos);
        }
    }
    
    public void poShootYou()
    {
        for(int i=0; i < wObjs.size(); i++)
        {
            if(wObjs.get(i).type.equals("Police"))
            {
                if(((Police)wObjs.get(i)).shotLimit < 3)
                {
                    Object pshot = new Object();
                    pshot.init("resource/bullet.png",wObjs.get(i).xPos -15 , wObjs.get(i).yPos +75, 8, 8, 0, 0, 4, 4);
                    wObjs.add(pshot);
                    ((Police)wObjs.get(i)).shotLimit++;           
                }
            }
        }
    }
    
    public void policeMove()
    {
        for(int i=0; i < wObjs.size(); i++)
        {
            if(wObjs.get(i).type.equals("Police"))
            {
                if(wObjs.get(i).xPos - testObj.xPos > 0)    
                {
                    wObjs.get(i).xPos -= 1;
                }
                else
                {
                    wObjs.get(i).xPos += 1;
                }
            }
        }
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
        for(int i = 0; i < wObjs.size(); i++)
        {
            if(wObjs.get(i).type.equals("Bullet"))
            {
                wObjs.get(i).xPos += 8;
            }
            //if(wObjs.get(i).type.equals("Bullet"))
            //{
            //}
        }
    }
    
    public void playDeath()
    {
        for(int i=0;i<wObjs.size(); i++)
        {
            if(wObjs.get(i).type.equals("Police"))
            {
                if(((Police)wObjs.get(i)).health < 10)
                {
                    ((Police)wObjs.get(i)).changeSprite(64, 64);
                    wObjs.get(i).flip = true;
                    ((Police)wObjs.get(i)).deathTimer -= 10;
                    if(((Police)wObjs.get(i)).deathTimer <0)
                    {
                        ((Police)wObjs.get(i)).changeSprite(0, 0);
                    }
                }
            }
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
            
            wObjs.add(new Police(testObj.xPos+80+randX, 350+randY, 64, 128));
        }
    }
    
    public void bulletCol()
    {
        ListIterator firstIter = wObjs.listIterator();
        while(firstIter.hasNext())
        {
            Object o1 = (Object) firstIter.next();
            
            ListIterator secondIter = wObjs.listIterator();
            
            while(secondIter.hasNext())
            {
                Object o2 = (Object) secondIter.next();
                if(o1 != o2)
                {
                    if((o1.xPos > o2.xPos)&&(o1.xPos < o2.xPos + o2.width)&&(o1.yPos > o2.yPos) &&(o1.yPos < o2.yPos + o2.height))
                    {
                        System.err.println("COLLISION!");
                        if((o1.type.equals("Bullet") ^ o2.type.equals("Bullet")) && (o1 != testObj && o2 != testObj))
                        {
                            if(o1.type.equals("Police"))
                            {
                                ((Police)o1).takeDamage();
                                ((Police)o1).flagDamage = true;
                            }
                            else
                            {
                                o1.setToRemove(true);
                            }
                            
                            if(o2.type.equals("Police"))
                            {
                                ((Police)o2).takeDamage();
                                ((Police)o2).flagDamage =true;
                            }
                            else
                            {
                                o2.setToRemove(true);
                            }
                    
                            break;
                        }
                    }
                }
            }
        }
        
        checkIfDead();
        
        ListIterator wObjsIter = wObjs.listIterator();
        while(wObjsIter.hasNext())
        {
            Object obj = (Object) wObjsIter.next();
            if(obj.getToRemove() || (obj.type.equals("Bullet") && obj.xPos > SCREEN_WIDTH + 20))
            {
                wObjsIter.remove();
            }
        }
    }
    
    public void checkIfDead()
    {
        ListIterator policeIter = wObjs.listIterator();
        while(policeIter.hasNext())
        {
            Object obj = (Object) policeIter.next();
            if(obj.type.equals("Police"))
            {
                if(((Police)obj).getHealth() < 10)
                {
                    if(((Police)obj).deathTimer < 0)
                    {
                        obj.setToRemove(true);
                        score++;
                    }
                }
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
