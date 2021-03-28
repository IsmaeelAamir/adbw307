package game;

import city.cs.engine.*;

public class Heart extends DynamicBody {
    private static final Shape heartShape = new PolygonShape(-0.08f,-0.98f, -0.992f,0.016f, -0.988f,0.66f, -0.236f,0.992f, 0.232f,0.996f, 0.988f,0.672f, 0.976f,0.012f, 0.076f,-0.98f
    );

    private static final BodyImage image =
            new BodyImage("data/heart.png", 2f);

    public Heart(World w) {
        super(w,heartShape);
        addImage(image);

    }


}
