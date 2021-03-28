package game;

import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.SoundClip;
import city.cs.engine.StaticBody;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class Level1 extends GameLevel{

    public Level1(Game game) {
        super(game);

        try {
            gameMusic = new SoundClip("data/main.wav");   // Open an audio input stream
            gameMusic.loop();  // Set it to continous playback (looping)
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }


        // Ground and ceiling
        Shape groundShape = new BoxShape(100, 0.35f);
        StaticBody ground = new StaticBody(this, groundShape);
        ground.setPosition(new Vec2(0f, -35.25f));
        StaticBody ceiling = new StaticBody(this, groundShape);
        ceiling.setPosition(new Vec2(0f, 35.29f));

        //Walls
        Shape wallShape = new BoxShape(0.35f, 100f);
        StaticBody wall1 = new StaticBody(this, wallShape);
        wall1.setPosition(new Vec2(-63, 0));
        StaticBody wall2 = new StaticBody(this, wallShape);
        wall2.setPosition(new Vec2(63f, 0));


        // make a platform
        makePlatform(-35,-22,4);
        makePlatform(-50,-10,6);
        makePlatform(-30,5,3);
        makePlatform(20,14,2.5f);
        makePlatform(20,48,3);
        makePlatform(10,23,7);
        makePlatform(54,5,9);
        makePlatform(0,5,5);
        makePlatform(28,-13,1.5f);
        makePlatform(19,-20,2);
















    }

    public void makePlatform(float x, float y, float w){
        Shape platformShape = new BoxShape(w, 0.35f);
        StaticBody platform = new StaticBody(this, platformShape);
        platform.setPosition(new Vec2(x, y));

        Random rand = new Random();
        int randint = rand.nextInt(3);
        if (randint == 1){
            FirePickup firePickup = new FirePickup(this);
            firePickup.setPosition(new Vec2(x, y+1));}
        else if (randint == 0){
            WaterPickup waterPickup = new WaterPickup(this);
            waterPickup.setPosition(new Vec2(x, y+1));
        }
        else if(randint == 2){
            Heart heart = new Heart(this);
            heart.setPosition(new Vec2(x, y+1));
        }
    }

    @Override
    public void populate(Game game){
        super.populate(game);
        FirePickup firePickup = new FirePickup(this);
        firePickup.setPosition(new Vec2(3, -10));


    }

    @Override
    public boolean isComplete() {
        if (getWatergirl().getWaterCount() >= 3  || getFireboy().getFireCount()>=3)
            return true;
        else
            return false;
    }

    @Override
    public Image paintBackground(){
        Image background = new ImageIcon("data/black.jpg").getImage();
        return background;
    }

    @Override
    public String getLevelName(){
        return "Level1";
    }


}
