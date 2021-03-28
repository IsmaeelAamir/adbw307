package game;

import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import org.jbox2d.common.Vec2;

public class Tracker implements StepListener {
    private GameView view;
    private Fireboy fireboy;
    public Tracker(GameView view, Fireboy fireboy) {
        this.view = view;
        this.fireboy = fireboy;
    }
    public void preStep(StepEvent e) {}
    public void postStep(StepEvent e) {
        view.setCentre(new Vec2(fireboy.getPosition()));
    }

}