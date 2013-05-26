package Version1;

/**
 *
 * @author David Fox
 */
public class Police extends Object
{     
    int health = 100;
    
    public void takeDamage()
    {
        this.health -= 10;
    }
}
