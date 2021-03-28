package game;

import city.cs.engine.BodyImage;
import city.cs.engine.SoundClip;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class FireboyController implements KeyListener {

    private static final float WALKING_SPEED = 50;
    private SoundClip jumpnoise;
    private static final BodyImage fireboyImage = new BodyImage("data/fireboy.png",5);
    private static final BodyImage firewalk = new BodyImage("data/left.png",5);
    private static final BodyImage firejump = new BodyImage("data/fire jump.png",5);
    private Game game;

    private Fireboy fireboy;

    public FireboyController(Fireboy w, Game g){
        fireboy = w;
        game = g;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        // other key commands omitted
        if (code == KeyEvent.VK_LEFT) {
            fireboy.startWalking(-WALKING_SPEED);
            fireboy.removeAllImages();
            fireboy.addImage(firewalk);
        } else if (code == KeyEvent.VK_RIGHT) {
            fireboy.startWalking(WALKING_SPEED);
            fireboy.removeAllImages();
            fireboy.addImage(firewalk).flipHorizontal();
        }else if (code ==KeyEvent.VK_UP){
            fireboy.jump(18);
            fireboy.removeAllImages();
            fireboy.addImage(firejump);
            try {
                jumpnoise = new SoundClip("data/jump1.wav");   // Open an audio input stream
                jumpnoise.play();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException n) {
                System.out.println(n);
            }
        }
        else if (code == KeyEvent.VK_S ||code == KeyEvent.VK_F5){
            try {
                GameSaverLoader.save(game.getLevel(),"data/save.txt");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

        else if (code == KeyEvent.VK_L ||code == KeyEvent.VK_F9){
            try {
                GameLevel level = GameSaverLoader.load(game,"data/save.txt");
                game.setLevelAlt(level);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_LEFT) {
            fireboy.stopWalking();
            fireboy.removeAllImages();
            fireboy.addImage(fireboyImage);
        } else if (code == KeyEvent.VK_RIGHT) {
            fireboy.stopWalking();
            fireboy.removeAllImages();
            fireboy.addImage(fireboyImage);
        }
    }

    public void updateFireboy(Fireboy fireboy){
        this.fireboy = fireboy;
    }



}
