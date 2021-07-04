import greenfoot.*;
public class Snake extends Actor
{
    public SnakeSegment head, tail;
    private int vX, vY;
    
    public Snake(){
    }
    @Override
    protected void addedToWorld(World world){
        getImage().scale(Game.SCALE, Game.SCALE);
        
        head = new SnakeSegment(null);
        tail = head;
        getWorld().addObject(head, getX(), getY());
        
        vX = 1;
        vY = 0;
    }
    public void move(){
        String key = Game.getKey();
        
        if(key.equals("up")/*&&vY==0*/){
            vX = 0;
            vY = -1;
        }
        else if(key.equals("down")/*&&vY==0*/){
            vX = 0;
            vY = 1;
        }
        else if(key.equals("left")/*&&vX==0*/){
            vX = -1;
            vY = 0;
        }
        else if(key.equals("right")/*&&vX==0*/){
            vX = 1;
            vY = 0;
        }

        tail.move();
        head.setLocation(getX(), getY());
        setLocation(getX() + vX, getY() + vY);
    }
    public void extend(){
        //Erstelle neues Objekt mit sich selbst als Previous
        //Wird auf aktuellem tail aufgerufen
        SnakeSegment new_tail = new SnakeSegment(tail);
        getWorld().addObject(new_tail, tail.getX(), tail.getY());
        tail = new_tail;
    }
    public boolean collisionWith(Actor actor) {
        return (this.getX() == actor.getX() && this.getY() == actor.getY());
    }
    public boolean collisionWithSelf() {
        return (getWorld().getObjectsAt(getX(), getY(), SnakeSegment.class).size()>0);
    }
}