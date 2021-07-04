import greenfoot.*;
public class SnakeSegment extends Actor
{
    SnakeSegment previous;
    
    public SnakeSegment(SnakeSegment previous){
        this.previous = previous;
        getImage().scale(Game.SCALE, Game.SCALE);
    }
    public void move(){
        if(previous!=null){
            setLocation(previous.getX(), previous.getY());
            previous.move();
        }
    }
}