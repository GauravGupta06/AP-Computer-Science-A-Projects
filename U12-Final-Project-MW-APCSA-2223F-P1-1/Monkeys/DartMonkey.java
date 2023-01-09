import java.util.*;
import java.lang.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.net.*;
import java.io.*;
public class DartMonkey extends Monkey {
  private Timer timer = new Timer();
  private Image image;
  private String name = "DART MONKEY";
  private int width = 40;
  private int height = 40;
  private int price = 200;
  private int range = 175;

  private int targetX;
  private int targetY;
  private boolean isAttacking;

  //milliseconds
  private int attackFrequency = 600;
  //balloon units
  private int damage = 1;
  //dart speed
  private int dartSpeed = 5;

  
  public DartMonkey(int xPos, int yPos){
    super("DART MONKEY", xPos, yPos, 40, 40, 200, 175, 650, 1, 5);
    try {
      URL url = getClass().getResource("JPGs/swagdartmonkey.png");
      image = ImageIO.read(url);
    } catch (Exception e){
      System.out.println("Dart monkey exception");
    }
    schedule();
  }

  @Override
  public void draw(Graphics window){
    window.drawImage(image, super.getX(), super.getY(), super.getWidth(), super.getHeight() ,null);
  }

  public void setTarget(int x, int y){
    targetX = x;
    targetY = y;
  }

  public int getTargetX(){
    return targetX;
  }

  public int getTargetY(){
    return targetY;
  }

  public void setAttackingStatus(boolean b){
    isAttacking = b;
  }
 
  public void defaultAttack(){
    int mx = this.getCenterX();
    int my = this.getCenterY();
    int xd = Math.abs(mx-targetX);
    int yd = Math.abs(my-targetY);
    boolean lowX = false;
    boolean lowY = false;
    if (isAttacking){
      if (mx >= targetX)
        lowX = true;
      if (my >= targetY)
        lowY = true;
      if (lowX && lowY){
        xd = -xd;
        yd = -yd;
      }
      else if (lowX && !lowY){
        xd = -xd;
      }
      else if (!lowX && lowY){
        yd = -yd;
      }
      Dart dart = new Dart(super.getCenterX(), super.getCenterY(), xd/5, yd/5, 1, 15, 15);
      super.updateDartHorde(dart);
      //call sound method here
    }
  }

  
  public void schedule()
  {
    try {
      timer.schedule(new TimerHandler(), attackFrequency);
    }
    catch (Exception e)
    {
      
    }
  }
  
  private class TimerHandler extends TimerTask{
    @Override
    public void run(){
      //shoot dart
      defaultAttack();
      schedule();
    }
  }

  
  
}