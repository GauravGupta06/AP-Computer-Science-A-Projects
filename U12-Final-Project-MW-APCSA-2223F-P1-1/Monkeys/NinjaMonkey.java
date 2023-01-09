import java.util.*;
import java.lang.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.net.*;
import java.io.*;
public class NinjaMonkey extends Monkey {
  private Timer timer = new Timer();
  private Image image;
  private String name = "NINJA MONKEY";
  private int width = 40;
  private int height = 40;
  private int price = 500;
  private int range = 225;

  private int targetX;
  private int targetY;
  private boolean isAttacking;

  //milliseconds
  private int attackFrequency = 350;
  //balloon units
  private int damage = 2;
  //dart speed
  private int dartSpeed = 7;
  
  public NinjaMonkey(int xPos, int yPos){
    super("NINJA MONKEY", xPos, yPos, 40, 40, 500, 225, 300, 2, 7);
    try {
      URL url = getClass().getResource("JPGs/NinjaMonkey.png");
      image = ImageIO.read(url);
    } catch (Exception e){
      System.out.println("Ninja monkey exception");
    }
    schedule();
  }

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

  @Override
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
      Dart dart = new Dart(super.getCenterX(), super.getCenterY(), xd/10, yd/10, 2, 30, 30);
      dart.setNinja();
      super.updateDartHorde(dart);
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