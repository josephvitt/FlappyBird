package src.main.java;

import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Util {

	/**
	 * HashMap存储路径对应的图像
	 */
    private static HashMap<String, Image> cache = new HashMap<String, Image>();

	/**
	 * 加载图像
	 * @param path
	 * @return
	 */
	public static Image loadImage(String path) {
        Image image = null;

		/**
		 * 对应的路径已存在 直接返回图像
		 */
		if (cache.containsKey(path)) {
			return cache.get(path);
		}

		/**
		 * 存储图像
		 */
		try {
			image = ImageIO.read(new File(path));

			if (!cache.containsKey(path)) {
				cache.put(path, image);
			}
		} 
		catch (IOException e) {
		    e.printStackTrace();
        }

		return image;
	}
}
