package game;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GameOfLife extends JPanel {
    private int size;
    private final int limitedCount;
    boolean[][] currentGeneration = new boolean[size][size];
    boolean[][] nextGeneration = new boolean[size][size];

    public GameOfLife(int size, int limitedCount) {
        this.size = size;
        this.limitedCount = limitedCount;
    }

    public void run() {
        initialize();

        preparedFrame();

        startSimulation();
    }

    public void preparedFrame() {
        JFrame frame = new JFrame("Game of Life");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(size * 100, size * 100);
        frame.add(this);
        frame.setVisible(true);
    }

    private void initialize() {
        currentGeneration = new boolean[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                currentGeneration[i][j] = new Random().nextBoolean();
            }
        }
        nextGeneration = currentGeneration;
    }

    /*
    生存ルール【Survival rule】:
        生きているセル（生セル）は、周囲に2つまたは3つの生セルがあれば次の世代でも生存する。
        それ以外の場合は、次の世代では死滅する。
    誕生ルール【Birth rule】:
        死んでいるセル（死セル）は、周囲にちょうど3つの生セルがあれば次の世代で誕生する。
    過疎ルール【Underpopulation rule】:
        生セルが周囲に1つまたは0つの生セルしかない場合、次の世代で孤立し死滅する。
    過密ルール【Overpopulation rule】:
        生セルが周囲に4つ以上の生セルがある場合、次の世代で過密により死滅する。
    */
    private boolean generateNextGeneration(int i, int j) {
        int[] x = {-1, 0, 1, -1, 1, -1, 0, 1};
        int[] y = {-1, -1, -1, 0, 0, 1, 1, 1};
        var countAliveCells = 0;
        for (int a = 0; a < 8; a++) {
                int indexX = x[a] + i;
                int indexY = y[a] + j;
                if (indexX >= size || indexX < 0 ||
                        indexY >= size || indexY < 0) continue;
                if (currentGeneration[indexX][indexY]) {
                    ++countAliveCells;
                }
        }

        var currentCell = currentGeneration[i][j];
        if (currentCell) {
            return countAliveCells == 2 || countAliveCells == 3;
        } else {
            return countAliveCells == 3;
        }
    }

    private void slideGeneration() {
        boolean[][] temp = currentGeneration;
        currentGeneration = nextGeneration;
        nextGeneration = temp;
    }

    private void startSimulation() {
        int count = 0;
        while (count < limitedCount) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    nextGeneration[i][j] = generateNextGeneration(i, j);
                }
            }
            slideGeneration();
            repaint();
            count++;
            try {
                Thread.sleep(500); // 更新間隔を500ミリ秒に設定
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.BLACK);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (currentGeneration[i][j]) {
                    g.fillRect(j * 10, i * 10, 10, 10);
                }
            }
        }
    }
}
