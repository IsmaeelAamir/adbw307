package game;

import javax.swing.*;

public class ControlPanel {
    private JPanel mainPanel;
    private JButton pauseButton;
    private JButton restartButton;
    private JButton quitButton;
    private JButton unpauseButton;
    private JButton stopMusicButton;
    private JButton playMusicButton;
    private JButton level1Button;
    private JButton level2Button;
    private JButton level3Button;
    private Game game;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public ControlPanel(Game g){
        this.game = g;
        restartButton.addActionListener(e -> game.restartLevel());
        quitButton.addActionListener(e -> System.exit(0));
        pauseButton.addActionListener(e -> game.pause());
        unpauseButton.addActionListener(e -> game.unpause());
        stopMusicButton.addActionListener(e -> game.getLevel().gameMusic.pause());
        playMusicButton.addActionListener(e -> game.getLevel().gameMusic.play());
        level1Button.addActionListener(e -> game.setLevel(1));
        level2Button.addActionListener(e -> game.setLevel(2));
        level3Button.addActionListener(e -> game.setLevel(3));

    }
}
