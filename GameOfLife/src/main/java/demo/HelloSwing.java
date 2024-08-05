package demo;

import javax.swing.*;

public class HelloSwing {
    public static void main(String[] args) {
        // JFrameの作成
        JFrame frame = new JFrame("Hello Swing");

        // アプリケーションの終了を設定
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ラベルの作成
        JLabel label = new JLabel("Hello, Swing!");

        // ラベルをフレームに追加
        frame.getContentPane().add(label);

        // フレームのサイズを設定
        frame.setSize(300, 200);

        // フレームを可視化
        frame.setVisible(true);
    }
}
