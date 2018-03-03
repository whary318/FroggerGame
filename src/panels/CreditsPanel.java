package panels;

import UI.CustomizedButtonUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CreditsPanel extends JPanel implements ActionListener {

    private Image softUniLogo;
    private Image maleAvatar;
    private Image femaleAvatar;
    private Image[] allImages;
    private Font customFont2;
    private JButton back;
    private int width = 660;

    public CreditsPanel() {
        int avatarWidth = 60;
        int avatarHeight = 60;

        setOpaque(false);
        setLayout(null);
        try {
            GraphicsEnvironment gEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
            this.customFont2 = Font.createFont(Font.TRUETYPE_FONT, new File("src/textures/Lato-Black.ttf")).deriveFont(14f);
            gEnvironment.registerFont(this.customFont2);
            this.softUniLogo = ImageIO.read(new File("src/textures/softuni.png")).getScaledInstance(100, 70, Image.SCALE_SMOOTH);
            this.maleAvatar = ImageIO.read(new File("src/textures/maleAvatar.png")).getScaledInstance(avatarWidth, avatarHeight, Image.SCALE_SMOOTH);
            this.femaleAvatar = ImageIO.read(new File("src/textures/femaleAvatar.png")).getScaledInstance(avatarWidth, avatarHeight, Image.SCALE_SMOOTH);
            this.allImages = new Image[11];
            for (int i = 0; i < 11; i++) {
                Image img = this.maleAvatar;
                if (i == 2) {
                    img = this.femaleAvatar;
                }
                this.allImages[i] = img;
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (FontFormatException f) {
            f.printStackTrace();
        }

        JLabel team = new JLabel("Team Dulcy", JLabel.CENTER);
        team.setFont(new Font("Ravie", Font.BOLD, 40));
        team.setBounds(0, 0, width / 2, 50);
        team.setLocation(width / 2 - width / 4, 88);
        team.setForeground(new Color(35, 68, 101));
        add(team);

        JLabel shadow = new JLabel("Team Dulcy", JLabel.CENTER);
        shadow.setFont(new Font("Ravie", Font.BOLD, 40));
        shadow.setBounds(0, 0, width / 2, 50);
        shadow.setLocation(width / 2 - width / 4 + 2, 88 + 2);
        shadow.setForeground(new Color(255, 160, 0));
        add(shadow);

        ArrayList<JLabel> lables = new ArrayList<>();
        lables.add(new JLabel("Angel", JLabel.CENTER));
        lables.add(new JLabel("Todor", JLabel.CENTER));
        lables.add(new JLabel("Milena", JLabel.CENTER));
        lables.add(new JLabel("Aleksander", JLabel.CENTER));
        lables.add(new JLabel("Ivan", JLabel.CENTER));
        lables.add(new JLabel("Kristiyan", JLabel.CENTER));
        lables.add(new JLabel("Buzov", JLabel.CENTER));
        lables.add(new JLabel("Yordanov", JLabel.CENTER));
        lables.add(new JLabel("Sapunova", JLabel.CENTER));
        lables.add(new JLabel("Leykov", JLabel.CENTER));
        lables.add(new JLabel("Pashkulev", JLabel.CENTER));
        lables.add(new JLabel("Mechkov", JLabel.CENTER));
        lables.add(new JLabel("Aleksandar", JLabel.CENTER));
        lables.add(new JLabel("Philip", JLabel.CENTER));
        lables.add(new JLabel("Teodor", JLabel.CENTER));
        lables.add(new JLabel("Sefan", JLabel.CENTER));
        lables.add(new JLabel("Yavor", JLabel.CENTER));
        lables.add(new JLabel("Georgiev", JLabel.CENTER));
        lables.add(new JLabel("Paskalev", JLabel.CENTER));
        lables.add(new JLabel("Gueorguiev", JLabel.CENTER));
        lables.add(new JLabel("Maslarski", JLabel.CENTER));
        lables.add(new JLabel("Donev", JLabel.CENTER));

        int namesY = 235;
        int namesX = 30;
        int namesDistance = 100;


        for (int i = 0; i < lables.size(); i++) {
            JLabel jl = lables.get(i);
            int X = namesX + namesDistance * i;
            int Y = namesY;
            if (i > 5 && i < 12) {
                X = namesX + namesDistance * (i - 6);
                Y += 19;
            }
            if (i == 11) {
                namesX += 50;
                namesY += 140;
            }
            if (i > 11 && i < 17) {
                X = namesX + namesDistance * (i - 12);
            }
            if (i > 16) {
                X = namesX + namesDistance * (i - 17);
                Y += 19;
            }
            jl.setFont(new Font(this.customFont2.getName(), Font.PLAIN, 16));
            jl.setBounds(0, 0, namesDistance, 22);
            jl.setLocation(X, Y);
            jl.setForeground(new Color(35, 68, 101));
            add(jl);
        }

        ArrayList<JLabel> textLines = new ArrayList<>();

        int textVerticalPosition = 430;
        textLines.add(new JLabel("This game was created as part of a team project for the", JLabel.CENTER));
        textLines.add(new JLabel("Java Advanced course (May 2016) at Software University.", JLabel.CENTER));
        for (int i = 0; i < textLines.size(); i++) {
            JLabel text = textLines.get(i);
            text.setBounds(0, 0, width, 40);
            text.setFont(new Font(this.customFont2.getName(), Font.PLAIN, 17));
            text.setForeground(Color.darkGray);
            text.setLocation(0, textVerticalPosition + 20 * i);
            add(text);
        }

        this.back = new JButton("Back");
        this.back.addActionListener(this);
        this.back.setBorder(BorderFactory.createEmptyBorder());
        this.back.setUI(new CustomizedButtonUI(new Color(35, 68, 101),
                new Color(255, 160, 0), new Color(100, 100, 100),
                new Font("Snap ITC", Font.PLAIN, 25), Color.white));
        this.back.setBounds(0, 0, 120, 40);
        this.back.setLocation(width / 2 - 120 / 2, 495);
        add(this.back);

    }

    public void actionPerformed(ActionEvent action) {
        if (action.getSource() == this.back) {
            SwingUtilities.getWindowAncestor(this).dispose();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(this.softUniLogo, (getWidth() - this.softUniLogo.getWidth(this)) / 2, 15, this);

        int circleVerticalPosition = 150;
        int circleHorizontalStart = 35;
        int circleDistance = 100;
        int circleWidth = 85;
        int circleHeight = 85;

        int avatarVerticalPosition = circleVerticalPosition + 8;
        int avatarHorizontalStart = circleHorizontalStart + 14;
        int avatarDistance = circleDistance;
        int secondRow = 0;

        for (int i = 0; i < 11; i++) {

            if (i == 6) {
                secondRow = 6;
                circleVerticalPosition += 140;
                avatarVerticalPosition = circleVerticalPosition + 8;
                circleHorizontalStart += 50;
                avatarHorizontalStart = circleHorizontalStart + 14;
            }
            //circles
            g2.setColor(new Color(255, 160, 0));
            Ellipse2D circle = new Ellipse2D.Float(circleHorizontalStart + circleDistance * (i - secondRow), circleVerticalPosition, circleWidth, circleHeight);
            g2.fill(circle);

            //avatars
            g2.drawImage(this.allImages[i], avatarHorizontalStart + avatarDistance * (i - secondRow), avatarVerticalPosition, this);
        }

    }
}