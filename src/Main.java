import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;


public class Main extends JFrame implements KeyListener {
    private static char[][] lab;
    private static Player player;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";


    public Main() {
        addKeyListener(this);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }


    public static void main(String[] args) {
        Main f = new Main();
        lab = Labirint.initLab();
        player = new Player(0, 0, '@');
        animate();
    }


    public static void animate() {
        final String os = System.getProperty("os.name");
        try {
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < lab.length; i++) {
            for (int j = 0; j < lab[0].length; j++)
                if ((player.getx() == i) && (player.gety() == j)) {
                    System.out.print(player.view);
                } else {
                    if (lab[i][j] == '#') {
                        System.out.print(ANSI_RED);
                    }
                    else if (lab[i][j] == '.') {
                        System.out.print(ANSI_BLUE);
                    }
                    System.out.print(lab[i][j]);
                    System.out.print(ANSI_RESET);
                }
            System.out.println();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            int curX = player.getx();
            int curY = player.gety();
            if ((curY > 0) && (lab[curX][curY - 1] != '#')) {
                player.setx(curX);
                player.sety(curY - 1);
            }
            animate();
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            int curX = player.getx();
            int curY = player.gety();
            if ((curY < lab[0].length) && (lab[curX][curY + 1] != '#')) {
                player.setx(curX);
                player.sety(curY + 1);
            }
            animate();
        }

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            int curX = player.getx();
            int curY = player.gety();
            if ((curX > 0) && (lab[curX - 1][curY] != '#')) {
                player.setx(curX - 1);
                player.sety(curY);
            }
            animate();
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            int curX = player.getx();
            int curY = player.gety();
            if ((curX < lab.length) && (lab[curX + 1][curY] != '#')) {
                player.setx(curX + 1);
                player.sety(curY);
            }

            animate();

        }

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
