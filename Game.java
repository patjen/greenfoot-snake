import greenfoot.*;

public class Game extends World
{
    public static final int SCALE = 25;
    Snake snake;
    Food food;
    static String lastKey;
    
    public Game(){
        //Erstelle World mit folgenden Dimensionen und Scale
        super(25, 20, SCALE);
        //Färbe Hintergrund
        getBackground().setColor(Color.LIGHT_GRAY);
        getBackground().fill();

        snake = new Snake();
        addObject(snake,getWidth() / 2, getHeight() / 2);

        food = new Food();
        addObject(food, 0, 0);
        addFood();
        
        Greenfoot.setSpeed(35);
        Greenfoot.start();
    }
    public void act(){
        snake.move();
        if (snake.collisionWith(food)) {
            addFood();
            snake.extend();
        }
        if (snake.collisionWithSelf()) {
            //Endlosschleife in der alles gestoppt ist, bis wieder Enter gedrückt wird
            while(true){
                if(Greenfoot.getKey() == "enter"){
                    Greenfoot.setWorld(new Game());
                    break;
                }
            }
        }
    }
    
    private void addFood(){
        do{
            food.setLocation((int)(Math.random() * getWidth()), (int)(Math.random() * getHeight()));
        }while(getObjectsAt(food.getX(), food.getY(), SnakeSegment.class).size()>0);
    }
    
    public static String getKey(){
        String key;
        key = Greenfoot.getKey();
        if(key==null){
            if(!"up".equals(lastKey)&&Greenfoot.isKeyDown("up"))key = "up";
            if(!"down".equals(lastKey)&&Greenfoot.isKeyDown("down"))key = "down";
            if(!"left".equals(lastKey)&&Greenfoot.isKeyDown("left"))key = "left";
            if(!"right".equals(lastKey)&&Greenfoot.isKeyDown("right"))key = "right";
        }
        if(key==null)key = "";
        lastKey = key;
        return key;
    }
}