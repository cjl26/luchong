package cloudPayAdmin.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Random;

public class ImgValidateUtil {
	
	// 图片的宽度。
		private static final int width = 150;
		// 图片的高度。
		private static final int height = 30;
		// 验证码字符个数
		// private static final int[] codeLenArray = {4,5,6};
		private static final int[] codeLenArray = { 4 };
		// 验证码干扰线数
		private static final int lineCount = 10;
		//红绿蓝
		private static final Color color[] = {Color.red,Color.blue};

		private static final char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F',
				'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U',
				'V', 'W', 'X', 'Y', 'Z', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'm', 'n',
				'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

		private final static String[] fontSequence = { "Georgia", "Verdana",
				"Arial", "Tahoma", "Time News Roman", "Courier New", "Fixedsys",
				"Latin", "Gill Sans", "Harlow Solid", "Jokerman", "Kristen ITC" };

		public static HashMap<String, Object> createCaptcha() {
			int xx = 0;
			int fontHeight = 0;
			int codeY = 0;
			int red = 0, green = 0, blue = 0;

			// 创建一个随机数生成器类
			Random random = new Random();

			// 随机选取验证码长度
			int codeLen = codeLenArray[random.nextInt(codeLenArray.length)];

			xx = width / (codeLen + 2);// 每个字符的宽度
			fontHeight = height - 2;// 字体的高度
			codeY = height - 4;

			// 定义图像buffer
			BufferedImage buffImg = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			// Graphics2D g = buffImg.createGraphics();
			// Graphics2D g = (Graphics2D) buffImg.getGraphics();
			Graphics g = buffImg.getGraphics();

			// 将图像填充为白色
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(0, 0, width, height);

			// 创建字体，字体的大小应该根据图片的高度来定。
			String fontStyle = String.valueOf(fontSequence[random.nextInt(12)]);
			Font font = new Font(fontStyle, Font.BOLD + Font.ITALIC, fontHeight);
			// 设置字体。
			g.setFont(font);

			// 画边框。
			g.setColor(Color.WHITE);
			g.drawRect(0, 0, width - 1, height - 1);

			// 随机产生40条干扰线，使图象中的认证码不易被其它程序探测到。

			for (int i = 0; i < lineCount; i++) {

				int xs = random.nextInt(width);
				int ys = random.nextInt(height);
				int xe = xs + random.nextInt(width / 8);
				int ye = ys + random.nextInt(height / 8);
				red = random.nextInt(255);
				green = random.nextInt(255);
				blue = random.nextInt(255);
				g.setColor(new Color(red, green, blue));
				g.drawLine(xs, ys, xe, ye);
			}

			// randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
			StringBuffer randomCode = new StringBuffer();

			// 随机产生codeCount数字的验证码。
			for (int i = 0; i < codeLen; i++) {
				// 得到随机产生的验证码数字。
				String code = String.valueOf(codeSequence[random.nextInt(56)]);
				// 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。
//				red = random.nextInt(255);
//				green = random.nextInt(255);
//				blue = random.nextInt(255);
				
				// 用随机产生的颜色将验证码绘制到图像中。
//				g.setColor(new Color(red, green, blue));
				Color fontColor = color[random.nextInt(2)];
				g.setColor(fontColor);
				g.drawString(code, (i + 1) * xx, codeY);

				// 将产生的四个随机数组合在一起。
				randomCode.append(code);
			}
			HashMap<String, Object> result = new HashMap<String, Object>();
			result.put("buffImg", buffImg);
			result.put("systemRandomCode", randomCode.toString());
			return result;
		}
}
