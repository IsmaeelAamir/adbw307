package game;
import city.cs.engine.*;

public class Door extends StaticBody{
    private static final Shape DoorShape = new BoxShape(2.9f, 4);

    private static final BodyImage doorImage =
            new BodyImage("data/door.png", 20f);

    public Door(World w) {
        super(w, DoorShape);
        addImage(doorImage);
    }
}
