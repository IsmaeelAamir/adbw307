package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import org.jbox2d.common.Vec2;

public class FireCollision implements CollisionListener {
    private GameLevel level;
    private Game game;


    private Fireboy fireboy;
    public FireCollision(Fireboy f, GameLevel level, Game game){
        this.fireboy = f;
        this.level = level;
        this.game = game;
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof FirePickup) {
            System.out.println("Collected Fire");
            fireboy.addFireCount();
            e.getOtherBody().destroy();
        }

        else if (e.getOtherBody() instanceof WaterPickup){
            System.out.println("Fireboy touched water, life lost");
            fireboy.DecrementLiveCount();
            e.getOtherBody().destroy();
        }

        else if (e.getOtherBody() instanceof Watergirl){
            System.out.println("Fireboy touched watergirl, life lost");
            fireboy.DecrementLiveCount();
        }

        else if (e.getOtherBody() instanceof Heart) {

            System.out.println("Fireboy found a heart");
            fireboy.incrementLiveCount();
            e.getOtherBody().destroy();
        }





        if (fireboy.getLiveCount() == 0){
            System.out.println("All lives lost, fireboy has died.");
            fireboy.resetLiveCount();
            fireboy.resetFireCount();
            fireboy.setPosition(new Vec2(60, -35));
        }
    }
}