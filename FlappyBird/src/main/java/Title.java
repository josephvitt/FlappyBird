package src.main.java;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Title {

    private int meun_x = 175;
    private int meun_y = 100;
    private int index = 1;
    private static Title instance;

    private GamePanel gamePanel;

    public static Title getInstance(){
        if (instance == null) {
            instance = new Title();
        }
        return instance;
    }

    public void draw(Graphics g){
        g.setFont(new Font("宋体", Font.BOLD, 25));
        g.setColor(Color.white);
        Color c = g.getColor();
        if (index == 1) {
            g.setColor(Color.orange);
        }
        g.drawString("简单模式", meun_x, meun_y);

        g.setColor(c);
        if (index == 2) {
            g.setColor(Color.orange);
        }
        g.drawString("困难模式", meun_x, meun_y + 50);
        g.setColor(c);
        if (index == 3) {
            g.setColor(Color.orange);
        }
        g.drawString("地狱模式", meun_x, meun_y + 100);
        g.setColor(c);
        if (index == 4) {
            g.setColor(Color.orange);
        }
        g.drawString("深渊模式", meun_x, meun_y + 150);
        g.setColor(c);
        if (index == 5) {
            System.out.println();
            g.setColor(Color.red);
        }
        g.drawString("退出游戏", meun_x, meun_y + 200);

        //游戏说明
        //g.setColor(Color.gray);
        //g.setFont(new Font("隶书",Font.ITALIC,20));
        //g.drawString("作者：张航 吴泽宇 丁畅 杜辉", 80, 340);
        //g.drawString("余镱 赵珺雨 王子纯 沈琳", 100, 360);
    }
    private void select() {
        switch (index) {
            case 1:
                Game.setPipeDelay(100);
                System.out.println(1);
                break;

            case 2:
                Game.setPipeDelay(70);
                System.out.println(2);
                break;
            case 3:
                Game.setPipeDelay(50);
                System.out.println(3);
                break;

            case 4:
                Game.setPipeDelay(30);
                System.out.println(4);
                break;

            case 5:
                System.exit(0);
                System.out.println(5);
                break;
        }
    }
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP:
                if(index==1){
                    index=1;
                }else {
                    index--;
                }
                System.out.println("Up");
                break;
            case KeyEvent.VK_DOWN:
                if(index==5){
                    index=5;
                }else {
                    index++;
                }
                System.out.println("Down");
                break;
            case KeyEvent.VK_ENTER:
                select();
                gamePanel=GamePanel.getInstance();
                gamePanel.start();
                break;
        }
    }
}
