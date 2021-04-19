import java.util.Map;

public class Pair implements Map.Entry<Integer, Integer> {
  private Integer coordX;
  private Integer coordY;

  Pair(int x, int y) {
    coordX = x;
    coordY = y;
  }

  @Override
  public Integer getKey() {
    return coordX;
  }

  @Override
  public Integer getValue() {
    return coordY;
  }

  @Override
  public Integer setValue(Integer value) {
    return coordX = value;
  }
}
