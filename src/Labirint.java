import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class Labirint {

  static boolean checkLeft(Pair topPair, int x, int y) {
    return topPair.getKey() - 1 >= 0;
  }

  static boolean checkRight(Pair topPair, int x, int y) {
    return topPair.getKey() + 1 < x;
  }

  static boolean checkUp(Pair topPair, int x, int y) {
    return topPair.getValue() - 1 >= 0;
  }

  static boolean checkDown(Pair topPair, int x, int y) {
    return topPair.getValue() + 1 < y;
  }

  static List<Pair> available_cells(boolean[][] visited, Pair topPair) {
    int rowNumber = visited.length;
    int colNumber = visited[0].length;
    List<Pair> collectedPairs = new ArrayList<>();
    if (checkLeft(topPair, rowNumber, colNumber)) {
      if (!visited[topPair.getKey() - 1][topPair.getValue()]) {
        collectedPairs.add(new Pair(topPair.getKey() - 1, topPair.getValue()));
      }
    }

    if (checkRight(topPair, rowNumber, colNumber)) {
      if (!visited[topPair.getKey() + 1][topPair.getValue()]) {
        collectedPairs.add(new Pair(topPair.getKey() + 1, topPair.getValue()));
      }
    }

    if (checkDown(topPair, rowNumber, colNumber)) {
      if (!visited[topPair.getKey()][topPair.getValue() + 1]) {
        collectedPairs.add(new Pair(topPair.getKey(), topPair.getValue() + 1));
      }
    }

    if (checkUp(topPair, rowNumber, colNumber)) {
      if (!visited[topPair.getKey()][topPair.getValue() - 1]) {
        collectedPairs.add(new Pair(topPair.getKey(), topPair.getValue() - 1));
      }
    }

    return collectedPairs;
  }

  public static char[][] initLab() {
    char[][] lab;
    int maxN = 15;
    int minN = 10;
    int rowNumber = ((int) (Math.random() * (maxN - minN)) + minN);
    int colNumber = ((int) (Math.random() * (maxN - minN)) + minN);

    lab = new char[3 * rowNumber][3 * colNumber];
    for (int i = 0; i < lab.length; i++)
      for (int j = 0; j < lab[0].length; j++)
        if (i % 3 == 2 || j % 3 == 2) {
          lab[i][j] = '#';
        } else {
          lab[i][j] = '.';
        }

    int allCells = rowNumber * colNumber;
    int curVisitedCells = 1;
    Stack<Pair> pairHandler = new Stack<>();
    boolean[][] used = new boolean[rowNumber][colNumber];

    pairHandler.push(new Pair(0, 0));
    while (curVisitedCells < allCells) {
      Pair topPair = pairHandler.peek();
      used[topPair.getKey()][topPair.getValue()] = true;
      List<Pair> curCells = available_cells(used, topPair);

      if (curCells.size() == 0) {
        pairHandler.pop();
        continue;
      }
      Random random = new Random();
      int getPairIndex = random.nextInt(curCells.size());
      Pair pair = curCells.get(getPairIndex);

      if (topPair.getKey().equals(pair.getKey()) && (topPair.getValue() > pair.getValue())) {
        // x, y + 1 -> x, y (delete | )
        lab[pair.getKey() * 3][pair.getValue() * 3 + 2] = '.';
        lab[pair.getKey() * 3 + 1][pair.getValue() * 3 + 2] = '.';
      } else if (topPair.getKey().equals(pair.getKey()) && (topPair.getValue() < pair.getValue())) {
        // x, y -> x, y + 1 (delete |)
        lab[topPair.getKey() * 3][topPair.getValue() * 3 + 2] = '.';
        lab[topPair.getKey() * 3 + 1][topPair.getValue() * 3 + 2] = '.';
      } else if (topPair.getKey() > pair.getKey()) {
        // x + 1, y -> x, y (delete -)
        lab[pair.getKey() * 3 + 2][pair.getValue() * 3] = '.';
        lab[pair.getKey() * 3 + 2][pair.getValue() * 3 + 1] = '.';
      } else {
        // x, y -> x + 1, y (delete -)
        lab[topPair.getKey() * 3 + 2][topPair.getValue() * 3] = '.';
        lab[topPair.getKey() * 3 + 2][topPair.getValue() * 3 + 1] ='.';
      }
      curVisitedCells += 1;
      pairHandler.push(pair);
    }
    return lab;
  }

  public static void main(String[] args) {
    char[][] lab = initLab();
    for (char[] ints : lab) {
      for (int j = 0; j < lab[0].length; j++) {
        System.out.print(ints[j]);
      }
      System.out.println();
    }
  }
}
