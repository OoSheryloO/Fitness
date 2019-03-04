package com.huban.util;


import com.huban.pojo.Picture;
import com.huban.service.PictureService;
//import com.sun.image.codec.jpeg.JPEGCodec;
//import com.sun.image.codec.jpeg.JPEGEncodeParam;
//import com.sun.image.codec.jpeg.JPEGImageEncoder;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;


/**
 * Created by MeetLucky on 16/5/25.
 */
public class FileUtil {
    private static FileUtil instance;

    public static FileUtil sharedInstance() {
        if (instance == null) {
            instance = new FileUtil();
        }

        return instance;
    }

    /**
     * 保存图片文件
     *
     * @param file 要保存的图片文件数据
     * @param path 保存文件的路径
     */
    public static void saveImageFile(MultipartFile file, String path) {
        try {
            //  文件写入到服务器文件系统
            InputStream inputStream = file.getInputStream();
            FileOutputStream fileOutputStream = new FileOutputStream(new File(path));
            byte[] portraitByte = new byte[1024];
            int nRead = 0;
            while ((nRead = inputStream.read(portraitByte)) != -1) {
                fileOutputStream.write(portraitByte, 0, nRead);
            }
            fileOutputStream.flush();
            fileOutputStream.close();
            inputStream.close();
        } catch (IOException e) {
            System.out.println("文件保存失败:" + path);
        }
    }

        public static void img_change(String url, String name) {
//            Tosmallerpic(url, new File(url + name), "_small", name, 64, 64,
//                    (float) 0.7);
//            Tosmallerpic(url, new File(url + name), "_100", name, 100, 100,
//                    (float) 0.7);
        }

        /**
         * * * @param f 图片所在的文件夹路径 * @param filelist 图片路径 *
         *
         * @param ext 扩展名
         * @param n   图片名
         * @param w   目标宽 *
         * @param h   目标高 *
         * @param per 百分比
         */
//        public   static void Tosmallerpic(String f, File filelist, String ext,
//                                         String n, int w, int h, float per) {
//            Image src;
//            try {
//                src = javax.imageio.ImageIO.read(filelist);
//                // 构造Image对象
//                String img_midname = f + n.substring(0, n.indexOf(".")) + ext
//                        + n.substring(n.indexOf("."));
//                int old_w = src.getWidth(null); // 得到源图宽
//                int old_h = src.getHeight(null);
//                int new_w = 0;
//                int new_h = 0; // 得到源图长
//                double w2 = (old_w * 1.00) / (w * 1.00);
//                double h2 = (old_h * 1.00) / (h * 1.00);
//                // 图片跟据长宽留白，成一个正方形图。
//                BufferedImage oldpic;
//                if (old_w > old_h) {
//                    oldpic = new BufferedImage(old_w, old_w,
//                            BufferedImage.TYPE_INT_RGB);
//                } else {
//                    if (old_w < old_h) {
//                        oldpic = new BufferedImage(old_h, old_h,
//                                BufferedImage.TYPE_INT_RGB);
//                    } else {
//                        oldpic = new BufferedImage(old_w, old_h,
//                                BufferedImage.TYPE_INT_RGB);
//                    }
//                }
//                Graphics2D g = oldpic.createGraphics();
//                g.setColor(Color.white);
//                if (old_w > old_h) {
//                    g.fillRect(0, 0, old_w, old_w);
//                    g.drawImage(src, 0, (old_w - old_h) / 2, old_w, old_h,
//                            Color.white, null);
//                } else {
//                    if (old_w < old_h) {
//                        g.fillRect(0, 0, old_h, old_h);
//                        g.drawImage(src, (old_h - old_w) / 2, 0, old_w, old_h,
//                                Color.white, null);
//                    } else {
//
//                        // g.fillRect(0,0,old_h,old_h);
//                        g.drawImage(src.getScaledInstance(old_w, old_h,
//                                Image.SCALE_SMOOTH), 0, 0, null);
//                    }
//                }
//                g.dispose();
//                src = oldpic;
//                // 图片调整为方形结束
//                if (old_w > w)
//                    new_w = (int) Math.round(old_w / w2);
//                else
//                    new_w = old_w;
//                if (old_h > h)
//                    new_h = (int) Math.round(old_h / h2);// 计算新图长宽
//                else
//                    new_h = old_h;
//                BufferedImage tag = new BufferedImage(new_w, new_h,
//                        BufferedImage.TYPE_INT_RGB);
//                // tag.getGraphics().drawImage(src,0,0,new_w,new_h,null);
//                // 绘制缩小后的图
//                tag.getGraphics().drawImage(
//                        src.getScaledInstance(new_w, new_h, Image.SCALE_SMOOTH), 0,
//                        0, null);
//                FileOutputStream newimage = new FileOutputStream(img_midname); // 输出到文件流
//                JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(newimage);
//                JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);
//            /* 压缩质量 */
//                jep.setQuality(per, true);
//                encoder.encode(tag, jep);
//                // encoder.encode(tag);
//                // 近JPEG编码
//                newimage.close();
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        }
    public static String getImageUrl(Picture picture){
        String url=picture.getPictureProtocol() + "://" + picture.getPictureIpaddress() + ":" + picture.getPicturePort()+ picture.getPictureFolder() + "/" + picture.getPicturePath() + "/" + picture.getPictureName();
        return url;
    }

    public static Picture SavePicture(String rootPath, String path, String FileName, Long userID, BaseUtil.PictureType PictureType,
                                      PictureService pictureService, HttpServletRequest request,CommonsMultipartFile file) throws IOException, URISyntaxException {
        File Folder = new File(rootPath + path);
        if (!Folder.exists()) { // 判断文件夹是否存在不存在则创建
            Folder.mkdirs(); // 创建历史保存的路径, 它的父文件夹为当前头像的保存路径
        }
        //  获取文件扩展名
        String fileType = BaseUtil.pictureType_JPG;
        Picture picture = null;
        //  当前头像的绝对路径
        String portraitPath = rootPath + path+"/" + FileName + fileType;

        picture = new Picture();
        picture.setPictureUserid(userID);;
        picture.setPictureTypeid((long) PictureType.getCode());
        picture.setPictureProtocol(HostUtil.sharedInstance().serverProtocol(request));
        picture.setPictureDomain(HostUtil.sharedInstance().serverDomain());
        picture.setPictureIpaddress(HostUtil.sharedInstance().serverAddress(request));
        picture.setPicturePort(HostUtil.sharedInstance().serverPort(request));
        picture.setPictureFolder(HostUtil.sharedInstance().projectName(request));
        picture.setPicturePath(path);
        picture.setPictureName(FileName + fileType);
        //保存原图
        pictureService.addPictue(picture);
        FileUtil.saveImageFile(file, portraitPath);
        return picture;
    }

}
