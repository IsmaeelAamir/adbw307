package game;

import city.cs.engine.*;

public class WaterPickup extends DynamicBody {
    private static final Shape waterShape = new PolygonShape(-0.008f,0.772f, -0.485f,-0.15f, -0.469f,-0.423f, -0.116f,-0.731f, 0.104f,-0.731f, 0.477f,-0.435f, 0.473f,-0.15f
    );

    private static final BodyImage image =
            new BodyImage("data/water-pickup.png", 2f);

    public WaterPickup(World w) {
        super(w,waterShape);
        addImage(image);

    }


}
