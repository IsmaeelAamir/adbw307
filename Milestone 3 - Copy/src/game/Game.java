package game;

import city.cs.engine.DebugViewer;
import city.cs.engine.SoundClip;
import city.cs.engine.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.text.View;
import java.io.IOException;

/**
 * A level with some bodies.
 */
public class Game {

    /** The World in which the bodies move and interact. */
    private GameLevel level;

    /** A graphical display of the level (a specialised JPanel). */
    private GameView view;
    private SoundClip gameMusic;

    private WatergirlController controllerW;
    private FireboyController controllerF;
    /** Initialise a new Game. */
    public Game() {

        // make the level
        level = new Level1(this);
        level.populate(this);


        view = new GameView(level, 1250, 700,this);
        view.setZoom(10);
        view.setBack(level.paintBackground());


        // uncomment this to draw a 1-metre grid over the view
        // view.setGridResolution(1);

        // add some mouse actions
        // add this to the view, so coordinates are relative to the view

         controllerF = new FireboyController(level.getFireboy(), this);
         controllerW = new WatergirlController(level.getWatergirl());
        view.addKeyListener(controllerF);
        view.addKeyListener(controllerW);

        //Tracker tracker = new Tracker(view,level.getZombie());
        //level.addStepListener(tracker);
        view.addMouseListener(new GiveFocus(view));

        //level.addStepListener(new Tracker(view, level.getStudent()));
        // add the view to a frame (Java top level window)
        final JFrame frame = new JFrame("Basic level");
        frame.add(view);


        ControlPanel buttons = new ControlPanel(this);
        frame.add(buttons.getMainPanel(), BorderLayout.WEST);



        // enable the frame to quit the application
        // when the x button is pressed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        // don't let the frame be resized
        frame.setResizable(false);
        // size the frame to fit the level view
        frame.pack();
        // finally, make the frame visible
        frame.setVisible(true);

        // uncomment this to make a debugging view
        JFrame debugView = new DebugViewer(level, 1000, 2000);

        // start our game level simulation!
        level.start();


    }
    public void goToNextLevel(){

        if (level instanceof Level1){
            //stop the current level
            level.stop();
            level.gameMusic.pause();
            //create the new (appropriate) level
            //level now refers to new level
            level = new Level2(this);
            level.populate(this);
            //change the view to look into new level

            view.setWorld(level);
            view.setZoom(10);
            view.setBack(level.paintBackground());
            //change the controller to control the
            //student in the new world
            controllerW.updateWatergirl(level.getWatergirl());
            controllerF.updateFireboy(level.getFireboy());
            //start the simulation in the new level
            level.start();
        }
        else if (level instanceof Level2){
            //stop the current level
            level.stop();
            level.gameMusic.pause();
            //create the new (appropriate) level
            //level now refers to new level
            level = new Level3(this);
            level.populate(this);
            //change the view to look into new level
            view.setWorld(level);
            view.setZoom(10);
            view.setBack(level.paintBackground());

            //change the controller to control the
            //student in the new world

            controllerW.updateWatergirl(level.getWatergirl());
            controllerF.updateFireboy(level.getFireboy());
            //start the simulation in the new level
            level.start();
        }
        else if (level instanceof Level3){
            System.out.println("Well done! Game complete.");
            System.exit(0);
        }
    }

    public void restartLevel(){
        if (level instanceof Level1){
            //stop the current level
            level.stop();
            level.gameMusic.pause();
            //create the new (appropriate) level
            //level now refers to new level
            level = new Level1(this);
            level.populate(this);
            //change the view to look into new level

            view.setWorld(level);
            view.setZoom(10);
            view.setBack(level.paintBackground());
            //change the controller to control the
            //student in the new world
            controllerW.updateWatergirl(level.getWatergirl());
            controllerF.updateFireboy(level.getFireboy());
            //start the simulation in the new level
            level.start();
        }
        else if (level instanceof Level2){
            //stop the current level
            level.stop();
            level.gameMusic.pause();
            //create the new (appropriate) level
            //level now refers to new level
            level = new Level2(this);
            level.populate(this);
            //change the view to look into new level
            view.setWorld(level);
            view.setZoom(10);
            view.setBack(level.paintBackground());

            //change the controller to control the
            //student in the new world

            controllerW.updateWatergirl(level.getWatergirl());
            controllerF.updateFireboy(level.getFireboy());
            //start the simulation in the new level
            level.start();
        }
        else if (level instanceof Level3){
            level.stop();
            level.gameMusic.pause();
            //create the new (appropriate) level
            //level now refers to new level
            level = new Level3(this);
            level.populate(this);
            //change the view to look into new level
            view.setWorld(level);
            view.setZoom(10);
            view.setBack(level.paintBackground());

            //change the controller to control the
            //student in the new world

            controllerW.updateWatergirl(level.getWatergirl());
            controllerF.updateFireboy(level.getFireboy());
            //start the simulation in the new level
            level.start();
        }

    }

    public void pause(){
        level.stop();
    }
    public void unpause(){
        level.start();
    }

    public GameLevel getLevel(){
        return level;
    }
    public void setLevel(int i){
        if (i==1){
            level = new Level1(this);

            restartLevel();

        }
        else if (i==2){
            level = new Level2(this);

            restartLevel();

        }
        else if (i==3){
            level = new Level3(this);

            restartLevel();

        }


    }
    public void setLevelAlt(GameLevel level){

            //stop the current level
            this.level.stop();
            this.level.gameMusic.pause();

            //create the new (appropriate) level
            //level now refers to new level
            this.level = level;


            //change the view to look into new level

            view.setWorld(level);
            view.setZoom(10);
            view.setBack(level.paintBackground());
            //change the controller to control the
            //student in the new world
            controllerW.updateWatergirl(level.getWatergirl());
            controllerF.updateFireboy(level.getFireboy());
            //start the simulation in the new level
            level.start();

    }

    /** Run the game. */
    public static void main(String[] args) {

        new Game();


    }
}
