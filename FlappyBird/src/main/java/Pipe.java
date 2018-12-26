package src.main.java;

import java.awt.Image;

public class Pipe {

    public int x;
    public int y;
    public int width;
    public int height;

    //管道运动速度
    public int speed = 3;

    public String orientation;

    private Image image;

    public Pipe(String orientation) {
        this.orientation = orientation;
        reset();
    }

    public void reset() {
        width = 66;
        height = 400;
        //x代表管道初始化出现的位置 +2为了判断计分
        x = App.WIDTH+2 ;

        //south代表上面的管道
        if (orientation.equals("south")) {
            y = -(int)(Math.random() * 120) - height / 2;
        }
    }

    public void update() {
        x -= speed;
    }

    public boolean collides(int _x, int _y, int _width, int _height) {

        int margin = 2;

        //横向位置在管道上
        if (_x + _width - margin > x && _x + margin < x + width) {

            //碰撞返回true
            if (orientation.equals("south") && _y < y + height) {
                return true;
            }
            else if (orientation.equals("north") && _y + _height > y) {
                return true;
            }
        }

        return false;
    }

    public Render getRender() {
        Render r = new Render();
        r.x = x;
        r.y = y;

        if (image == null) {
            image = Util.loadImage("lib/pipe-" + orientation + ".png");
        }
        r.image = image;

        return r;
    }
}
