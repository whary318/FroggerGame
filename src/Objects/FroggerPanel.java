package Objects;


import collision.CollisionDetector;
import panels.GameOver;
import panels.YouWin;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class FroggerPanel extends JPanel implements KeyListener, Runnable {

    public static int HEIGHT = 450;
    public static int WIDTH = 700;
    private CollisionDetector checkForCollision;

    BufferedImage car1_Left, car1_Right, car2_Left, car2_Right, limo_Left, limo_Right, semi_Left, semi_Right, frogUp, frogDown,
            frogLeft, frogRight, hsTurtle, hmTurtle, hlTurtle, sTurtle, mTurtle, lTurtle, sLog, mLog, lLog, lilyPad, frogLife;
    FroggerGame game;

    //BufferedImage buffer;
    int updatesPerSecond;
    int framesPerSecond; //todo used?


    public FroggerPanel() {
        setSize(WIDTH, HEIGHT);
        this.checkForCollision = new CollisionDetector();

        reset();
        Thread pThread;

        try {
            pThread = new Thread(this);
            pThread.start();
        } catch (Exception e) {
            System.err.println("Error creating thread.");
            e.printStackTrace();
            System.exit(-2);
        }

        try {
            car1_Left = ImageIO.read((new File("src/textures/Car1-Left.png")));
            car1_Right = ImageIO.read((new File("src/textures/Car1-Right.png")));
            car2_Left = ImageIO.read((new File("src/textures/Car2-Left.png")));
            car2_Right = ImageIO.read((new File("src/textures/Car2-Right.png")));
            limo_Left = ImageIO.read((new File("src/textures/Limo-Left.png")));
            limo_Right = ImageIO.read((new File("src/textures/Limo-Right.png")));
            semi_Left = ImageIO.read((new File("src/textures/Semi-Left.png")));
            semi_Right = ImageIO.read((new File("src/textures/Semi-Right.png")));
            frogUp = ImageIO.read((new File("src/textures/FrogUp.png")));
            frogDown = ImageIO.read((new File("src/textures/FrogDown.png")));
            frogLeft = ImageIO.read((new File("src/textures/FrogLeft.png")));
            frogRight = ImageIO.read((new File("src/textures/FrogRight.png")));
            hsTurtle = ImageIO.read((new File("src/textures/HS-Turtle.png")));
            hmTurtle = ImageIO.read((new File("src/textures/HM-Turtle.png")));
            hlTurtle = ImageIO.read((new File("src/textures/HL-Turtle.png")));
            sTurtle = ImageIO.read((new File("src/textures/S-Turtle.png")));
            mTurtle = ImageIO.read((new File("src/textures/M-Turtle.png")));
            lTurtle = ImageIO.read((new File("src/textures/L-Turtle.png")));
            sLog = ImageIO.read((new File("src/textures/S-Log.png")));
            mLog = ImageIO.read((new File("src/textures/M-Log.png")));
            lLog = ImageIO.read((new File("src/textures/L-Log.png")));
            lilyPad = ImageIO.read((new File("src/textures/lilyPad.png")));
            frogLife = ImageIO.read((new File("src/textures/FrogLife.png")));

        } catch (Exception e) {
            System.err.println("Error Loading Images: " + e.getMessage());
            e.printStackTrace();
            System.exit(-1); //if loading fails, end the program.
        }
        addKeyListener(this);

    }

    @Deprecated
    public void keyReleased(KeyEvent e) {
        //unused
    }

    @Deprecated
    public void keyPressed(KeyEvent e) {
        //unused

    }

    @Override
    public void run() {
        //noinspection InfiniteLoopStatement
        while (true) {
            update();
            repaint();
            try {
                if (game.DEAD) {
                    GameOver gameOver;
                    gameOver = new GameOver(0); //
                    gameOver.setBounds(0, 0, WIDTH, HEIGHT);
                    this.getParent().getParent().add(gameOver, new Integer(2), 0);
                    Thread.sleep(1000000000000000000L);
                }
                if (game.WIN) {
                    YouWin youWin;
                    youWin = new YouWin(true, 0); //
                    youWin.setBounds(0, 0, WIDTH, HEIGHT);
                    this.getParent().getParent().add(youWin, new Integer(2), 0);
                    Thread.sleep(1000000000000000000L);
                }
                Thread.sleep(35); //todo correct times per second?

            } catch (Exception e) {
                System.err.println("Error Sleeping.");
            }
        }
    }

    public void keyTyped(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'w':
                if ((game.getPlayer().getY() - 40) > 30)
                    game.getPlayer().setY(game.getPlayer().getY() - 40);
                game.getPlayer().setDirection(Frog.UP);
                break;
            case 's':
                if ((game.getPlayer().getY() + 40) < getHeight() - 100)
                    game.getPlayer().setY(game.getPlayer().getY() + 40);
                game.getPlayer().setDirection(Frog.DOWN);
                break;
            case 'a':
                if ((game.getPlayer().getX() - 30) > 0)
                    game.getPlayer().setX(game.getPlayer().getX() - 40);
                game.getPlayer().setDirection(Frog.LEFT);
                break;
            case 'd':
                if ((game.getPlayer().getX() + 40) < getWidth() - 30)
                    game.getPlayer().setX(game.getPlayer().getX() + 40);
                game.getPlayer().setDirection(Frog.RIGHT);
                break;
        }
    }

    public void paint(Graphics g) {
        g.setColor(Color.green);
        g.fillRect(0, 0, getWidth(), getHeight()); //fill the background

//        g.setColor(Color.BLUE); //fill water on upper part of map
//        g.fillRect(0, 65, getWidth(), 190);

//        //small water inlets for lilypads----------
//        g.fillRect(60, 20, 70, 50);
//        g.fillRect(240, 20, 70, 50);
//        g.fillRect(420, 20, 70, 50);
//        g.fillRect(600, 20, 70, 50);

        //white lines of the road-------------------
        g.setColor(Color.white);
        g.drawLine(0, 75, getWidth(), 75);
        g.drawLine(0, 275, getWidth(), 275);
        //road--------------------------------------
        g.setColor(Color.GRAY);
        g.fillRect(0, 76, getWidth(), 199);
        //bottom black bar----------------------
        g.setColor(Color.BLACK);
        g.fillRect(0, getHeight() - 100, getWidth(), 300);
        //yellow lines on road----------------
        g.setColor(Color.yellow);
        for (int y = 116; y < 264; y += 39) {
            for (int x = 10; x < getWidth() - 10; x += 90) {
                g.fillRect(x, y, 60, 4);
            }
        }

//        //lilypads------------------------------
//        for (int i = 75; i <= 615; i += 179)
//            g.drawImage(lilyPad, i, 30, null);

        //text----------------------------------
        g.setColor(Color.darkGray);
        g.setFont(new Font("Arial", Font.CENTER_BASELINE, 40));
        g.drawString("Lives:", 10, getHeight() - 15);
        g.drawString("Time Left:", 300, getHeight() - 15);

        //lives---------------------------------
        g.setColor(Color.RED);
        for (int i = 0; i < game.getLives(); i++) {
            g.drawString("♥", 130 + i * 30, getHeight() - 15);
        }

        //time left----------------
        int i = game.getTimeLeft();
        if (i >= 60) //change colour of bar based on time left
            g.setColor(Color.green);
        else if (i >= 40)
            g.setColor(Color.orange);
        else
            g.setColor(Color.RED);

        g.fillRect(500, getHeight() - 40, (i * 2) + 10, 20); //draw timer based on time left
        g.drawRect(500, getHeight() - 40, 170, 20); //timer outline

        //draw frog --------------------------
        //see the bounding box
//        Rectangle boundingBox = game.getPlayer().getBoundingBox();
//        g.setColor(Color.black);
//        g.drawRect( (int) boundingBox.getX(), (int) boundingBox.getY(), (int) boundingBox.getWidth(), (int) boundingBox.getHeight());
        switch (game.getPlayer().getDirection()) { //draw frog based on direction
            case Frog.UP:
                g.drawImage(frogUp, game.getPlayer().getX(), game.getPlayer().getY(), null);
                break;
            case Frog.DOWN:
                g.drawImage(frogDown, game.getPlayer().getX(), game.getPlayer().getY(), null);
                break;
            case Frog.LEFT:
                g.drawImage(frogLeft, game.getPlayer().getX(), game.getPlayer().getY(), null);
                break;
            case Frog.RIGHT:
                g.drawImage(frogRight, game.getPlayer().getX(), game.getPlayer().getY(), null);
                break;
        }

        //MOVING OBJECTS ---------------------------------------
        //cars ------------
        for (CarLane cl : game.getCarLanes()) //all car lanes
        {
            for (int p = 0; p < cl.laneItems.size(); p++) //each car in that lane
            {
                if (cl.laneItems.get(p).getDirection() == Lane.RIGHT && cl.laneItems.get(p).getType() == Car.CAR_1) {
                    g.drawImage(car1_Right, (int) cl.laneItems.get(p).getX(), (int) cl.laneItems.get(p).getY(), null);
                } else if (cl.laneItems.get(p).getDirection() == Lane.LEFT && cl.laneItems.get(p).getType() == Car.CAR_1) {
                    g.drawImage(car1_Left, (int) cl.laneItems.get(p).getX(), (int) cl.laneItems.get(p).getY(), null);
                } else if (cl.laneItems.get(p).getDirection() == Lane.RIGHT && cl.laneItems.get(p).getType() == Car.CAR_2) {
                    g.drawImage(car2_Right, (int) cl.laneItems.get(p).getX(), (int) cl.laneItems.get(p).getY(), null);
                } else if (cl.laneItems.get(p).getDirection() == Lane.LEFT && cl.laneItems.get(p).getType() == Car.CAR_2) {
                    g.drawImage(car1_Left, (int) cl.laneItems.get(p).getX(), (int) cl.laneItems.get(p).getY(), null);
                } else if (cl.laneItems.get(p).getDirection() == Lane.LEFT && cl.laneItems.get(p).getType() == Car.LIMO) {
                    g.drawImage(limo_Left, (int) cl.laneItems.get(p).getX(), (int) cl.laneItems.get(p).getY(), null);
                } else if (cl.laneItems.get(p).getDirection() == Lane.RIGHT && cl.laneItems.get(p).getType() == Car.LIMO) {
                    g.drawImage(limo_Right, (int) cl.laneItems.get(p).getX(), (int) cl.laneItems.get(p).getY(), null);
                } else if (cl.laneItems.get(p).getDirection() == Lane.LEFT && cl.laneItems.get(p).getType() == Car.SEMI) {
                    g.drawImage(semi_Left, (int) cl.laneItems.get(p).getX(), (int) cl.laneItems.get(p).getY(), null);
                } else if (cl.laneItems.get(p).getDirection() == Lane.RIGHT && cl.laneItems.get(p).getType() == Car.SEMI) {
                    g.drawImage(semi_Right, (int) cl.laneItems.get(p).getX(), (int) cl.laneItems.get(p).getY(), null);
                }

                //See the bounding box
//                g.setColor(Color.BLACK);
//                Rectangle boudingBoxe = cl.laneItems.get(p).getBoundingBox();
//                g.drawRect((int) boudingBoxe.getX(), (int) boudingBoxe.getY(), (int) boudingBoxe.getWidth(), (int) boudingBoxe.getHeight());
            }
        }
    }

    void update() {
        game.update();
    }

    public void addNotify() {
        super.addNotify();
        requestFocus();
    }

    void reset() {
        this.game = new FroggerGame();
    }


}
