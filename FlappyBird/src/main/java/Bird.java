package src.main.java;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;

public class Bird {

    public int x;
    public int y;
    public int width;
    public int height;

    public boolean dead;

    public double y1;
    public double gravity;

    private int jumpDelay;
    private double rotation;

    private Image image;
    private Keyboard keyboard;

    public Bird() {
        x = 100;
        y = 150;
        y1 = 0;
        width = 45;
        height = 32;
        gravity = 0.5;
        jumpDelay = 0;
        rotation = 0.0;
        dead = false;

        keyboard = Keyboard.getInstance();
    }

    public void update() {
        y1 += gravity;

        if (jumpDelay > 0)
            jumpDelay--;

        if (!dead && keyboard.isDown(KeyEvent.VK_SPACE) && jumpDelay <= 0) {
            y1 = -10;       //y轴累加计算
            jumpDelay = 10;  //下落延时
        }
//        System.out.println("x:"+x+" y:"+y+" yvel:"+yvel);
        y += (int)y1; //y轴变化核心
    }

    public Render getRender() {
        Render r = new Render();
        r.x = x;
        r.y = y;

        if (image == null) {
            image = Util.loadImage("lib/bird.png");     
        }
        r.image = image;

        rotation = (90 * (y1 + 20) / 20) - 90;

//        System.out.println(rotation);

        rotation = rotation * Math.PI / 180;

        if (rotation > Math.PI / 2) {
            rotation = Math.PI / 2;
        }
        r.transform = new AffineTransform();

        //平移
        r.transform.translate(x+ width/2, y + height/2);

        //设置图片旋转角度
        r.transform.rotate(rotation);

        //平移撤回
        r.transform.translate(-width/2, -height/2);
        return r;
    }
}
