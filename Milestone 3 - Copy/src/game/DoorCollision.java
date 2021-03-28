package game;
import city.cs.engine.*;

public class DoorCollision implements CollisionListener{

    private GameLevel level;
    private Game game;

    public DoorCollision(GameLevel level, Game game){
        this.level = level;
        this.game = game;
    }
    @Override
    public void collide(CollisionEvent e) {

        if (e.getOtherBody() instanceof Door
            && level.isComplete()
                ){
            game.goToNextLevel();
        }
    }
}
