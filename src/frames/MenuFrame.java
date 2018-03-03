package frames;

import Objects.FroggerFrame;
import UI.CustomizedButtonUI;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class MenuFrame extends JFrame implements ActionListener {

    private JButton start;
    private JButton score;
    private JButton credits;
    private JButton exit;
    private Image backgroundIMG;
    private int width;
    private int height;

    public MenuFrame(String title) {
        super(title);
        width = 660;
        height = 410;
        setSize(width, height);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setResizable(false);
        setLayout(null);

        try {
            this.backgroundIMG = ImageIO.read(new File("src/textures/menu_background.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        Border emptyBorder = BorderFactory.createEmptyBorder();

        int titleWidth = 270;
        int titleHeight = 55;
        int titleX = 24;
        int titleY = 30;
        int titleSize = 47;

        JLabel gameTitle = new JLabel("Frogger");
        gameTitle.setBounds(0, 0, titleWidth, titleHeight);
        gameTitle.setFont(new Font("Ravie", Font.BOLD, titleSize));
        gameTitle.setForeground(new Color(251, 102, 8));
        gameTitle.setLocation(titleX, titleY);
        add(gameTitle);

        JLabel gameTitleShadow = new JLabel("Frogger");
        gameTitleShadow.setBounds(0, 0, titleWidth, titleHeight);
        gameTitleShadow.setFont(new Font("Ravie", Font.BOLD, titleSize));
        gameTitleShadow.setForeground(new Color(68, 71, 140));
        gameTitleShadow.setBackground(Color.black);
        gameTitleShadow.setLocation(titleX + 2, titleY + 2);
        add(gameTitleShadow);

        //show borders of title/shadow
        //Border border = BorderFactory.createLineBorder(Color.BLACK, 1); //- see border
        //gameTitle.setBorder(border);
        //Border border2 = BorderFactory.createLineBorder(Color.BLACK, 1); //- see border
        // gameTitle.setBorder(border2);

        Color normalColor = new Color(183, 233, 98);
        Color hoverColor = Color.black;
        Color pressedColor = new Color(45, 45, 45);
        Color fontColor = new Color(251, 102, 8);
        String fontName = "Snap ITC";
        int fontSizeButtons = 25;
        int buttonPositionX = 75;
        int buttonPositionY = 135;
        int verticalDistance = 55;

        this.start = new JButton("Start");
        this.start.addActionListener(this);
        this.start.setBorder(emptyBorder);
        this.start.setUI(new CustomizedButtonUI(normalColor,hoverColor, pressedColor,
                new Font(fontName, Font.PLAIN, fontSizeButtons), fontColor));
        this.start.setBounds(buttonPositionX, buttonPositionY, 160, 40);
        add(this.start);

        this.score = new JButton("Scores");
        this.score.addActionListener(this);
        this.score.setBorder(emptyBorder);
        this.score.setUI(new CustomizedButtonUI(normalColor,hoverColor, pressedColor,
                new Font(fontName, Font.PLAIN, fontSizeButtons), fontColor));
        this.score.setBounds(buttonPositionX, buttonPositionY + verticalDistance, 160, 40);
        add(this.score);

        this.credits = new JButton("Credits");
        this.credits.addActionListener(this);
        this.credits.setBorder(emptyBorder);
        this.credits.setUI(new CustomizedButtonUI(normalColor,hoverColor, pressedColor,
                new Font(fontName, Font.PLAIN, fontSizeButtons), fontColor));
        this.credits.setBounds(buttonPositionX, buttonPositionY + verticalDistance * 2, 160, 40);
        add(this.credits);

        this.exit = new JButton("Exit");
        this.exit.addActionListener(this);
        this.exit.setBorder(emptyBorder);
        this.exit.setUI(new CustomizedButtonUI(normalColor,hoverColor, pressedColor,
                new Font(fontName, Font.PLAIN, fontSizeButtons), fontColor));
        this.exit.setBounds(buttonPositionX, buttonPositionY + verticalDistance * 3, 160, 40);
        add(this.exit);

        //Layer
        JLabel background = new JLabel(new ImageIcon(this.backgroundIMG));
        background.setBounds(28, getHeight() - 438, 400, 438);
        background.setBounds(0, 0, width, height);
        add(background);
    }

    public void actionPerformed(ActionEvent action) {
        if (action.getSource() == this.exit) {
            System.exit(0);
        } else if (action.getSource() == this.start) {
            //start a new game
            new FroggerFrame();
        } else if (action.getSource() == credits) {
            CreditsWindow cWindow = new CreditsWindow("Credits");
            cWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            cWindow.setVisible(true);
            //here we can add a window with info about the game
            // and the contributors e.g. This was created as part of the SoftUni program bla bla.
        } else if (action.getSource() == score) {
            ScoreWindow sWindow = new ScoreWindow("Scores");
            sWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            sWindow.setVisible(true);
        }
    }

}

