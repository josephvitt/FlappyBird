package src.main.java;
import javax.swing.*;
import java.awt.*;


public class GamePanel extends JPanel implements Runnable {

    private Game game;
    private Title title;

    private static GamePanel instance;

    public static GamePanel getInstance(){
        if(instance==null){
            instance=new GamePanel();
        }
        return instance;
    }
    public GamePanel() {
        title=Title.getInstance();
        new Thread(this).start();
    }
    public void start(){
        title=null;
        game = new Game();
    }

    public void run() {
        try {
            while (true) {
                update();
                //线程控制游戏速度
                Thread.sleep(25);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        //game更新后 系统重新绘制UI
        if(title==null){
            game.update();
        }
        repaint();
    }

    /**
     * 重写方法 绘制UI
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(title!=null){

            Render render1=new Render(0, 0, "lib/background.png");
            Render render2=new Render(0, 0, "lib/foreground.png");
            g.drawImage(render1.image, render1.x, render1.y, null);
            g.drawImage(render2.image, render2.x, render2.y, null);
            title.draw(g);
        }
        else {
//        System.out.println("paintComponent");
            Graphics2D g2D = (Graphics2D) g;

            //绘制背景图
            for (Render r : game.getRenders()) {
                if (r.transform != null){
                    //绘制像素鸟
                    g2D.drawImage(r.image, r.transform, null);
                }
                else{
                    //绘制管道、背景图
                    g.drawImage(r.image, r.x, r.y, null);
                }
            }

            //字体颜色
            g2D.setColor(Color.RED);

            if (!game.started) {
                //绘制start界面
                g2D.setFont(new Font("TimesRoman", Font.PLAIN, 20));
                g2D.drawString("Press Space to restart", 150, 240);

            } else {
                //绘制得分情况
                g2D.setFont(new Font("TimesRoman", Font.PLAIN, 24));
                g2D.drawString(Integer.toString(game.score), 10, 30);
            }

            if (game.gameover) {
                //面绘制over界
                g2D.setFont(new Font("TimesRoman", Font.PLAIN, 20));
                g2D.drawString("Press R to restart", 150, 240);
            }
        }
    }
}
