public class Actor {
  private int coordx;
  private int coordy;

  Actor(int x, int y) {
    coordx = x;
    coordy = y;
  }

  public int getx() {
    return coordx;
  }

  public int gety() {
    return coordy;
  }

  public void setx(int x) {
    this.coordx = x;
  }

  public void sety(int y) {
    this.coordy = y;
  }
}

