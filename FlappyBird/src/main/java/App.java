package src.main.java;

import javax.swing.*;
public class App {

    public static int WIDTH = 500;
    public static int HEIGHT = 500;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Flappy Bird");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null);//null置于屏幕中央

        Keyboard keyboard = Keyboard.getInstance();
        frame.addKeyListener(keyboard);//按键监听器

        GamePanel panel=GamePanel.getInstance();
        frame.add(panel);
    }
}
