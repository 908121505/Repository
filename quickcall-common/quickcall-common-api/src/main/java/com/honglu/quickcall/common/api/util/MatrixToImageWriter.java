package com.honglu.quickcall.common.api.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;



/**
 * 二维码生成工具
 */
public class MatrixToImageWriter {

    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;
    private static final int MARGIN = 1; //边框
    
    private static final String FORMAT = "png";

    private MatrixToImageWriter() {
    }

//    public static void createRqCode(String textOrUrl, int width, int height, Boolean haslogo,String Logopath,String txt,OutputStream toStream)
//            throws WriterException, IOException {
//
//        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
//        hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); // 内容所使用字符集编码
//        hints.put(EncodeHintType.MARGIN, new Integer(MARGIN));
//
//        BitMatrix bitMatrix = new MultiFormatWriter().encode(textOrUrl, BarcodeFormat.QR_CODE, width, height, hints);
//
//        BufferedImage image = toBufferedImage(bitMatrix);
//        if(haslogo)
//        	applyLogo(image,Logopath,txt);//应用LOGO
//
//        writeToStream(image, FORMAT, toStream);
//
//    }
//    
//    public static BufferedImage createErWeiMaCode(String textOrUrl, int width, int height, Boolean haslogo,String Logopath,String txt)
//            throws WriterException, IOException {
//
//        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
//        hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); // 内容所使用字符集编码
//        hints.put(EncodeHintType.MARGIN, new Integer(MARGIN));
//
//        BitMatrix bitMatrix = new MultiFormatWriter().encode(textOrUrl, BarcodeFormat.QR_CODE, width, height, hints);
//
//        BufferedImage image = toBufferedImage(bitMatrix);
//        if(haslogo)
//        	applyLogo(image,Logopath,txt);//应用LOGO
//        
//        return  image;
//
//    }
//
//    private static void applyLogo(BufferedImage image,String logpath,String txt) throws IOException {
//
//        Graphics2D gs = image.createGraphics();
//
//        //ClassPathResource resource = new ClassPathResource(logpath);//logo图片
//        
//        // 载入logo
//        Image img = ImageIO.read(new File(logpath));
//
//        int left = image.getWidth() / 2 - img.getWidth(null) / 2;
//        int top = image.getHeight() / 2 - img.getHeight(null) / 2;
//        
//        int matrixWidth = image.getWidth();  
//        int matrixHeigh = image.getHeight();  
//
//        gs.drawImage(img, left, top, null);
////        gs.dispose();
//        
////        gs.drawImage(img,matrixWidth/5*2,matrixHeigh/5*2, matrixWidth/5, matrixHeigh/5, null);//绘制       
////        BasicStroke stroke = new BasicStroke(5,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);   
////        gs.setStroke(stroke);// 设置笔画对象  
////        //指定弧度的圆角矩形  
////        RoundRectangle2D.Float round = new RoundRectangle2D.Float(matrixWidth/5*2, matrixHeigh/5*2, matrixWidth/5, matrixHeigh/5,20,20);  
////        gs.setColor(Color.white);  
////        gs.draw(round);// 绘制圆弧矩形  
////
////        //设置logo 有一道灰色边框  
////        BasicStroke stroke2 = new BasicStroke(1,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);   
////        gs.setStroke(stroke2);// 设置笔画对象  
////        RoundRectangle2D.Float round2 = new RoundRectangle2D.Float(matrixWidth/5*2+2, matrixHeigh/5*2+2, matrixWidth/5-4, matrixHeigh/5-4,20,20);  
////        gs.setColor(new Color(128,128,128));  
////        gs.draw(round2);// 绘制圆弧矩形 
//        
//        if(!StringUtils.isBlank(txt))
//        {
//        	//Color.parseColor("#ff4d4d");
//        	//Color color = new Color(255,77,77);
//	        gs.setColor(Color.white); 
//	        gs.setFont(new Font("宋体",Font.BOLD,16)); //字体、字型、字号 
//	        int strWidth = gs.getFontMetrics().stringWidth(txt);
//	        int strHeight = gs.getFontMetrics().getHeight();
//	        gs.drawString(txt, (matrixWidth  - strWidth)/2 , top + img.getHeight(null) - strHeight/2 + 4 ); //画文字 
//        }
//        gs.dispose();  
//
//        
//        img.flush();
//
//    }
//
//    private static BufferedImage toBufferedImage(BitMatrix matrix) {
//        int width = matrix.getWidth();
//        int height = matrix.getHeight();
//        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//        for (int x = 0; x < width; x++) {
//            for (int y = 0; y < height; y++) {
//                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
//            }
//        }
//        return image;
//    }

    public static void writeToFile(BufferedImage image, String format, File file) throws IOException {
        
        if (!ImageIO.write(image, format, file)) {
            throw new IOException("Could not write an image of format " + format + " to " + file);
        }
    }

    public static void writeToStream(BufferedImage image, String format, OutputStream stream) throws IOException {
        if (!ImageIO.write(image, format, stream)) {
            throw new IOException("Could not write an image of format " + format);
        }
    }

}