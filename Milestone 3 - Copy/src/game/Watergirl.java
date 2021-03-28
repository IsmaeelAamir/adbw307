package game;

import city.cs.engine.*;

public class Watergirl extends Walker {
    private static final Shape watergirlShape = new PolygonShape(
            -0.22f,2.02f, 1.22f,0.54f, 0.3f,-1.87f, -0.22f,-1.87f, -1.0f,-1.08f, -1.28f,0.76f

    );

    private static final BodyImage watergirlImage = new BodyImage("data/watergirl.png",5);




    private int waterCount = 0;
    private int liveCount = 3;

    public Watergirl(World world) {
        super(world, watergirlShape);
        addImage(watergirlImage);
        waterCount=0;
    }

    public int getWaterCount() {
        return waterCount;
    }

    public void addWaterCount() {
        waterCount++;
        System.out.println("Watergirl Points: " + waterCount);
    }
    public void setWaterCount(int waterCount) {
        this.waterCount = waterCount;}

    public int getLiveCount() {
        return liveCount;
    }

    public void setLiveCount(int liveCount) {
        this.liveCount = liveCount;
    }

    public void addLiveCount(){
        liveCount++;
        System.out.println("Watergirl Lives: " + liveCount);
    }

    public void DecrementLiveCount(){
        liveCount--;
        System.out.println("Watergirl Lives: " + liveCount);
    }
    public void incrementLiveCount(){
        liveCount++;
        System.out.println("Watergirl Lives: " + liveCount);
    }

    public void resetLiveCount() {
        this.liveCount = 3;
        System.out.println("Watergirl Lives: " + liveCount);
    }
    public void resetWaterCount() {
        this.waterCount = 0;
        System.out.println("Watergirl Points: " + waterCount);
    }
}
