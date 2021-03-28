package game;

import city.cs.engine.BodyImage;
import city.cs.engine.SoundClip;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class WatergirlController implements KeyListener {

    private static final float WALKING_SPEED = 10;
    private SoundClip jumpnoise;
    private static final BodyImage watergirlImage = new BodyImage("data/watergirl.png",5);
    private static final BodyImage waterwalk = new BodyImage("data/left water.png",7);
    private static final BodyImage waterjump = new BodyImage("data/water jump.png",5);

    private Watergirl watergirl;

    public WatergirlController(Watergirl w){
        watergirl = w;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        // other key commands omitted
        if (code == KeyEvent.VK_A) {
            watergirl.startWalking(-WALKING_SPEED);
            watergirl.removeAllImages();
            watergirl.addImage(waterwalk);
        } else if (code == KeyEvent.VK_D) {
            watergirl.startWalking(WALKING_SPEED);
            watergirl.removeAllImages();
            watergirl.addImage(waterwalk).flipHorizontal();
        }else if (code ==KeyEvent.VK_W){
            watergirl.jump(18);
            watergirl.removeAllImages();
            watergirl.addImage(waterjump);
            try {
                jumpnoise = new SoundClip("data/jump2.wav");   // Open an audio input stream
                jumpnoise.play();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException n) {
                System.out.println(n);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_A) {
            watergirl.stopWalking();
            watergirl.removeAllImages();
            watergirl.addImage(watergirlImage);
        } else if (code == KeyEvent.VK_D) {
            watergirl.stopWalking();
            watergirl.removeAllImages();
            watergirl.addImage(watergirlImage);
        }
    }


    public void updateWatergirl(Watergirl watergirl){
        this.watergirl = watergirl;
    }


}
