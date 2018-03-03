package Objects;


public class LilyPad {

    public static final int WIDTH = 40;
    int x, y;
    boolean frog;

    public LilyPad(int x, int y) {
        this.x = x;
        this.y = y;
        frog = false;
    }

    public static int getWidth() {
        return WIDTH;
    }

    public boolean isFrog() {
        return frog;
    }

    public void setFrog(boolean frog) {
        this.frog = frog;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
