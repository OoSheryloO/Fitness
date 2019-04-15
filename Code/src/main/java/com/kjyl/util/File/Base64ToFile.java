package com.kjyl.util.File;

import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64ToFile {
	public static String Base64ToFileToAliCloud(String fileString, String id, String fileType) throws IOException{
		// 使用前端插件时可能有前有（"data:image/xxxx;base64,"）
        // 获取图片格式
        String suffix = fileString.substring(11,fileString.indexOf(";"));
        // 使用插件传输产生的前缀
        String prefix = fileString.substring(0,fileString.indexOf(",") + 1);
        // 替换前缀为空
        fileString = fileString.replace(prefix,"");
        // imageString = imageString.substring(imageString.indexOf(",") + 1);
        BASE64Decoder decoder = new BASE64Decoder();
		byte[] b = decoder.decodeBuffer(fileString);
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(b);
		String url = UploadAliYunFile.UploadAliYunFileService(byteArrayInputStream, id + ".jpg", fileType);
		return url;
	}
	
	 /**
     * 字符串转图片
     * @param base64String
     */
    public static boolean base64StringToImage(String base64String, String toImagePath,String imageType) {
        try {

            BASE64Decoder decoder = new BASE64Decoder();
            byte[] bytes1 = decoder.decodeBuffer(base64String);

            ByteArrayInputStream bais = new ByteArrayInputStream(bytes1);
            RenderedImage bi1 = ImageIO.read(bais);
            File w2 = new File(toImagePath);// 可以是jpg,png,gif格式
            if(!w2.exists()){
                w2.createNewFile();
                System.out.println("no exist=====");
            }
            System.out.println("pass...........");
            return ImageIO.write(bi1, imageType, w2);// 不管输出什么格式图片，此处不需改动
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static void main(String[] args) {
		String base64String = "data:image/png;base64,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";
		String toImagePath = "C:/Users/Administrator/Documents/13.png";
		String imageType = "png";
		base64StringToImage(base64String, toImagePath, imageType);
	}
}
