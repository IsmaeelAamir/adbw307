package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import org.jbox2d.common.Vec2;

public class WaterCollision implements CollisionListener {

    private Watergirl watergirl;
    public WaterCollision(Watergirl w){
        this.watergirl = w;
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof WaterPickup) {
            System.out.println("Collected Water");
            watergirl.addWaterCount();
            e.getOtherBody().destroy();
        }
        else if (e.getOtherBody() instanceof FirePickup){
            System.out.println("Watergirl tocuhed fire, life lost");
            watergirl.DecrementLiveCount();
            e.getOtherBody().destroy();
        }

        else if (e.getOtherBody() instanceof Fireboy){
            System.out.println("Watergirl touched fireboy, life lost");
            watergirl.DecrementLiveCount();
        }

        else if (e.getOtherBody() instanceof Heart){
            System.out.println("Watergirl found a heart");
            watergirl.incrementLiveCount();
            e.getOtherBody().destroy();
        }


        if (watergirl.getLiveCount() == 0){
            System.out.println("All lives lost, watergirl has died.");
            watergirl.resetLiveCount();
            watergirl.resetWaterCount();
            watergirl.setPosition(new Vec2(-60, -35));
        }
    }
}