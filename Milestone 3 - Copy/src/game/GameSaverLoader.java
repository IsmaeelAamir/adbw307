package game;

import city.cs.engine.DynamicBody;
import city.cs.engine.StaticBody;
import org.jbox2d.common.Vec2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GameSaverLoader {

    public static void save(GameLevel level, String fileName)
            throws IOException
    {

        boolean append = false;
        FileWriter writer = null;
        try {
            writer = new FileWriter(fileName, append);
            writer.write(level.getLevelName()  + "\n" );
            for (StaticBody body :level.getStaticBodies()){
                // static bodies not worth saving.
            }
            for (DynamicBody body :level.getDynamicBodies()){
                if (body instanceof Watergirl){
                    writer.write("watergirl,"+body.getPosition().x + "," +body.getPosition().y +
                            ","+ ((Watergirl) body).getWaterCount() + "," + ((Watergirl) body).getLiveCount() + "\n" );

                }
                else if (body instanceof WaterPickup){
                    writer.write("waterpickup,"+body.getPosition().x + "," +body.getPosition().y+ "\n");


                }
                else if (body instanceof Fireboy){
                    writer.write("fireboy,"+body.getPosition().x + "," +body.getPosition().y
                    + ","+ ((Fireboy) body).getFireCount() +","+ ((Fireboy) body).getLiveCount()+ "\n");

                }
                else if (body instanceof FirePickup){
                    writer.write("Firepickup," + body.getPosition().x + "," +body.getPosition().y+ "\n");

                }
                else if (body instanceof Heart){
                    writer.write("heart," + body.getPosition().x + "," +body.getPosition().y+ "\n");

                }
            }

        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    public static GameLevel load(Game game,String fileName) throws IOException
    {
        FileReader fr = null;
        BufferedReader reader = null;
        try {
            System.out.println("Reading " + fileName + " ...");
            fr = new FileReader(fileName);
            reader = new BufferedReader(fr);
            String line = reader.readLine();


            GameLevel level =null;
            if(line.equals("Level1")){
                level = new Level1(game);
            }
            else if(line.equals("Level2")){
                level = new Level2(game);
            }
            else if(line.equals("Level3")){
                level = new Level3(game);
            }

            line = reader.readLine();
            while(line!=null){
                String[] tokens = line.split(",");

                if (tokens[0].equals("watergirl")){
                    Watergirl w = new Watergirl(level);
                    float x = Float.parseFloat(tokens[1]);
                    float y = Float.parseFloat(tokens[2]);
                    w.setPosition(new Vec2(x,y));
                    int wc = Integer.parseInt(tokens[3]);
                    int lives = Integer.parseInt(tokens[4]);

                    level.setWatergirl(w);
                    WaterCollision pickup2 = new WaterCollision(w);
                    w.addCollisionListener(pickup2);
                    DoorCollision doorCollision = new DoorCollision(level,game);
                    w.addCollisionListener(doorCollision);



                }
                if (tokens[0].equals("waterpickup")){
                    WaterPickup wp = new WaterPickup((level));
                    float x = Float.parseFloat(tokens[1]);
                    float y = Float.parseFloat(tokens[2]);
                    wp.setPosition(new Vec2(x,y));

                }
                if (tokens[0].equals("fireboy")){
                    Fireboy f = new Fireboy(level);
                    float x = Float.parseFloat(tokens[1]);
                    float y = Float.parseFloat(tokens[2]);
                    f.setPosition(new Vec2(x,y));
                    int fc = Integer.parseInt(tokens[3]);
                    int lives = Integer.parseInt(tokens[4]);
                    level.setFireboy(f);

                    DoorCollision doorCollision = new DoorCollision(level,game);

                    FireCollision pickup1 = new FireCollision(f,level,game);
                    f.addCollisionListener(pickup1);
                    f.addCollisionListener(doorCollision);

                }
                if (tokens[0].equals("Firepickup")){
                    FirePickup fp = new FirePickup(level);
                    float x = Float.parseFloat(tokens[1]);
                    float y = Float.parseFloat(tokens[2]);
                    fp.setPosition(new Vec2(x,y));

                }
                if (tokens[0].equals("heart")){
                    Heart h = new Heart(level);
                    float x = Float.parseFloat(tokens[1]);
                    float y = Float.parseFloat(tokens[2]);
                    h.setPosition(new Vec2(x,y));

                }


                line = reader.readLine();
            }







            return level;


        } finally {
            if (reader != null) {
                reader.close();
            }
            if (fr != null) {
                fr.close();
            }
        }
    }
}
