/**
 * 
 */
package com.huban.util;

import java.io.File;


import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.FFMPEGLocator;
import it.sauronsoftware.jave.MultimediaInfo;

/**
 * @author GeJiangbo
 * @date 2017年5月20日
 */
public class VideoReadUtil {
	
	
	public static Long videotime(File file){
		
	Long ls=null;
	 FFMPEGLocator locator = new FFMPEGLocator() {  
         @Override  
         protected String getFFMPEGExecutablePath() {  
             // <ffmpeg_path>是你的ffmpeg.exe路径  
             return "C:\\Users\\Hello-Happytoo\\Desktop\\ffmpeg\\ffmpeg.exe";  
         }  
     }; 
	Encoder encoder = new Encoder(locator);  
    try {  
         MultimediaInfo m = encoder.getInfo(file);  
     ls = m.getDuration();  
     // 输出毫秒数  
     // System.out.println(ls);  
    // 输出0时0分0秒0毫秒的格式  
    //    videotimeString=ls / (60 * 60 * 1000) + ":" + (ls % (60 * 60 * 1000)) / 60000 + ":"  
    //    + ((ls % (60 * 60 * 1000)) % 60000) / 1000;  
 } catch (Exception e) {  
     e.printStackTrace();  
 }
	return ls;  
	}
}
