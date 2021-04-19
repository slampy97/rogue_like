import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class RogueLike implements KeyListener {
  private static char[][] lab;
  private static Player player;
  private static boolean isEnd = true;

  public static void main(String[] args) {
    lab = Labirint.initLab();
    player = new Player(0, 0, '@');
    while (isEnd) {
    }
  }

  public static void animate() {
    System.out.flush();
    for (int i = 0; i < lab.length; i++) {
      for (int j = 0; j < lab[0].length; j++)
        if ((player.getx() == i) && (player.gety() == j)) {
          System.out.print(player.view);
        } else {
          System.out.print(lab[i][j]);
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
      if ((curX > 0) && (lab[curX - 1][curY] != '#')) {
        player.setx(curX - 1);
        player.sety(curY);
      }
    }
    if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
      int curX = player.getx();
      int curY = player.gety();
      if ((curX < lab.length) && (lab[curX + 1][curY] != '#')) {
        player.setx(curX + 1);
        player.sety(curY);
      }
    }
    if (e.getKeyCode() == KeyEvent.VK_UP) {
      int curX = player.getx();
      int curY = player.gety();
      if ((curY > 0) && (lab[curX][curY - 1] != '#')) {
        player.setx(curX);
        player.sety(curY - 1);
      }
    }
    if (e.getKeyCode() == KeyEvent.VK_DOWN) {
      int curX = player.getx();
      int curY = player.gety();
      if ((curY < lab[0].length) && (lab[curX][curY + 1] != '#')) {
        player.setx(curX);
        player.sety(curY + 1);
      }
    }
    if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
      isEnd = false;
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    if ((e.getKeyCode() == KeyEvent.VK_LEFT) ||
        (e.getKeyCode() == KeyEvent.VK_RIGHT) ||
        (e.getKeyCode() == KeyEvent.VK_UP) ||
        (e.getKeyCode() == KeyEvent.VK_DOWN)) {
      animate();
    }
    ;
  }
}