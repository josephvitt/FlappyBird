package src.main.java;

import java.awt.Image;
import java.awt.geom.AffineTransform;

/**
 * 渲染元素类
 */
public class Render {
    /**
     * 分别获取元素的坐标位置和图像
     */
    public int x;
    public int y;
    public Image image;
    /**
     * 实现2D绘制良好的体验
     *平移（Translation）、缩放（Scale）、翻转（Flip）、旋转（Rotation）和剪切（Shear）
     */
    public AffineTransform transform;

    public Render() {
    }

    /**
     * 参的构造方法
     * @param x
     * @param y
     * @param imagePath
     */
    public Render(int x, int y, String imagePath) {
        this.x = x;
        this.y = y;
        this.image = Util.loadImage(imagePath);
    }
}
