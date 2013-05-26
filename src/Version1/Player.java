/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Version1;

/**
 *
 * @author Ramen
 */
public class Player extends Object
{
    int health = 100;
    int shotLimit = 12;
    boolean flagDamage = false;
    private boolean isShooting = false;
    private int totalAmmo = 36;
    private int burstRate = 3;
    private int burstShots = 0;
    private int shotsFired = 0;
    private long lastFired = 0;
    
    public Player(float x, float y)
    {
        super("resource/rexSprite.png", x, y, 64, 128, 0, 0, 32, 64);
        type = "Player";
    }
    
    public boolean isShooting()
    {
        return isShooting;
    }
    
    public void setShooting(boolean isShoot)
    {
        isShooting = isShoot;
    }
    
    public long getLastFired()
    {
        return lastFired;
    }
    
    public void setLastFired(long t)
    {
        lastFired = t;
    }
    
    public int getShotsFired()
    {
        return shotsFired;
    }
    
    public void setShotsFired(int s)
    {
        shotsFired = s;
    }
    
    public void incShotsFired(int s)
    {
        shotsFired += s;
    }
    
    public int getBurstShots()
    {
        return burstShots;
    }
    
    public void setBurstShots(int s)
    {
        burstShots = s;
    }
    
    public void incBurstShots(int s)
    {
        burstShots += s;
    }
    
    public int getBurstRate()
    {
        return burstRate;
    }
    public void takeDamage()
    {
        this.health -= 5;
    }
    public int getTotalAmmo()
    {
        return totalAmmo;
    }
    public void setTotalAmmo(int s)
    {
        totalAmmo += s;
    }
}
