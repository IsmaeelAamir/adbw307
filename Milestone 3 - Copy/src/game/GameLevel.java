package game;
import city.cs.engine.SoundClip;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

import java.awt.*;


public abstract class GameLevel extends World{

    private Watergirl watergirl;
    private Fireboy fireboy;
    private Door door;
    public SoundClip gameMusic;

    public GameLevel(Game game){
        door = new Door(this);
        door.setPosition(new Vec2(10, -34));




    }
    public void populate(Game game){


        DoorCollision doorCollision = new DoorCollision(this,game);
        fireboy = new Fireboy(this);

        FireCollision pickup1 = new FireCollision(fireboy,this,game);
        fireboy.addCollisionListener(pickup1);

        watergirl = new Watergirl(this);

        WaterCollision pickup2 = new WaterCollision(watergirl);
        watergirl.addCollisionListener(pickup2);


        watergirl.addCollisionListener(doorCollision);
        fireboy.addCollisionListener(doorCollision);

        watergirl.setPosition(new Vec2(-60, -35));
        fireboy.setPosition(new Vec2(60, -35));
    }
    public  void setWatergirl(Watergirl w){
        watergirl = w;
    };
    public  void setFireboy(Fireboy f){
        fireboy=f;
    };

    public Fireboy getFireboy() {
        return fireboy;
    }
    public Watergirl getWatergirl() {
        return watergirl;
    }
    public Door getDoor(){return door;}
    public abstract boolean isComplete();
    public abstract Image paintBackground();

    public SoundClip getGameMusic() {
        return gameMusic;
    }
    public abstract String getLevelName();


}


