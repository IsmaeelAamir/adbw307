package game;

import city.cs.engine.*;

public class FirePickup extends DynamicBody {
    private static final Shape fireShape = new CircleShape(1f);

    private static final BodyImage image =
            new BodyImage("data/fire-pickup.png", 2f);

    public FirePickup(World w) {
        super(w,fireShape);
        addImage(image);

    }


}
