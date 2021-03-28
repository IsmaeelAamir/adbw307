package game;

import city.cs.engine.*;

public class Fireboy extends Walker {
    private static final Shape fireboyShape = new PolygonShape(
            -1.13f,1.39f, -0.18f,-1.85f, 0.34f,-1.87f, 1.09f,1.47f, -0.03f,2.41f
    );

    private static final BodyImage fireboyImage = new BodyImage("data/fireboy.png",5);

    private int fireCount = 0;
    private int liveCount = 3;

    public Fireboy(World world) {
        super(world, fireboyShape);
        addImage(fireboyImage);
        fireCount=0;
    }

    public int getFireCount() {
        return fireCount;
    }

    public void setFireCount(int fireCount) {
        this.fireCount = fireCount;
    }

    public void addFireCount() {
        fireCount++;
        System.out.println("Fireboy Points: " + fireCount);
    }

    public int getLiveCount() {
        return liveCount;
    }
    public void resetLiveCount() {
        this.liveCount = 3;
        System.out.println("Fireboy Lives: " + liveCount);
    }

    public void setLiveCount(int liveCount) {
        this.liveCount = liveCount;
    }

    public void DecrementLiveCount(){
        liveCount--;
        System.out.println("Fireboy Lives: " + liveCount);
    }

    public void incrementLiveCount(){
        liveCount++;
        System.out.println("Fireboy Lives: " + liveCount);
    }

    public void resetFireCount() {
        this.fireCount = 0;
        System.out.println("Fireboy Points: " + fireCount);
    }

}
