import game.GameOfLife;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        GameOfLife gameOfLife = new GameOfLife(100, 30);
        gameOfLife.run();
    }
}