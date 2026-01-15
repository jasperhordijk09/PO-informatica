import greenfoot.*;
public class loadAnimationManual  
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class loadAnimationManual
     */
    public loadAnimationManual()
    {
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public int sampleMethod(int y)
    {
        // put your code here
        return x + y;
    }
    public void loadAnimationManual(String name, String folderPath, String[] fileOrder) {
        GreenfootImage[] frames = new GreenfootImage[fileOrder.length];
    
        for (int i = 0; i < fileOrder.length; i++) {
            frames[i] = new GreenfootImage(folderPath + "/" + fileOrder[i]);
        }
    
        animations.put(name, new Animation(name, frames));
    }
}
