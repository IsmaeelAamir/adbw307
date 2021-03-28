package game;

import city.cs.engine.UserView;
import city.cs.engine.World;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.text.AttributedString;

public class GameView extends UserView {
    public Image back;
    private Game game;
    public GameView(World w, int width, int height, Game g) {
        super(w, width, height);
        this.game =g;

            //background = new ImageIcon("data/main3.jpg").getImage();

    }

    public void setBack(Image b){
        this.back =b;
    }

    @Override
    protected void paintBackground(Graphics2D g) {

        g.drawImage(back, 0, 0, this);
    }

    @Override
    protected void paintForeground(Graphics2D g) {
        Font font = new Font("LucidaSans", Font.PLAIN, 14);
        String str1 =("Watergirl points:" +String.valueOf(game.getLevel().getWatergirl().getWaterCount()));
        String str2= "Watergirl Lives:" +String.valueOf(game.getLevel().getWatergirl().getLiveCount());
        String str3= "Fireboy points:" +String.valueOf(game.getLevel().getFireboy().getFireCount());
        String str4= "Fireboy Lives:" +String.valueOf(game.getLevel().getFireboy().getLiveCount());




        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.setColor(Color.green);
        g.drawString(str1,10,15);
        g.drawString(str2,10,50);
        g.drawString(str3,1100,15);
        g.drawString(str4,1100,50);

    }




}
