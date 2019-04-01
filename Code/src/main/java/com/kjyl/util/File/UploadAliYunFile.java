package com.kjyl.util.File;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.OSSClient;
import com.kjyl.config.Aliyun.Constant;
import com.kjyl.util.ConstantUtils;

public class UploadAliYunFile {
	/**
	 * 文件上传
	 * @param is
	 * @param file
	 * @return
	 */
	public static String UploadAliYunFileService(InputStream inputStream, File file, String fileType){
		String name = file.getName();
		String prefix = name.substring(name.lastIndexOf(".")+1);
		Date date = new Date();
		String path = fileType + "/" + ConstantUtils.sdfDate.format(date) + "/";
		path += UUID.randomUUID().toString() +"." + prefix;
		// endpoint以杭州为例，其它region请按实际情况填写
		String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
		// accessKey请登录https://ak-console.aliyun.com/#/查看
//		String accessKeyId = "LTAIIQkISE7PpDu1";
//		String accessKeySecret = "j5Fd1wNx5w5J3Ox3ntouL8HqWwh3Vs";
		// 创建OSSClient实例
		OSSClient ossClient = new OSSClient(endpoint, Constant.APP_ID, Constant.APP_SECRET);
		//       Images/2017-03-15/----------------cb21aa0a-cceb-49d6-a507-77f3dd71ee5c_800.PNG
		ossClient.putObject("wenews", path, inputStream);
		// 关闭client
		ossClient.shutdown();
//		System.out.println("http://wenews.oss-cn-hangzhou.aliyuncs.com/" + path);
		return "http://wenews.oss-cn-hangzhou.aliyuncs.com/" + path;
	}
	
	public static String UploadAliYunFileService(InputStream inputStream, MultipartFile file, String fileType) throws FileNotFoundException{
		String name = file.getOriginalFilename();
		String prefix = name.substring(name.lastIndexOf(".")+1);
		Date date = new Date();
		String path = fileType + "/" + ConstantUtils.sdfDate.format(date) + "/";
		path += UUID.randomUUID().toString() + "." + prefix;
		// endpoint以杭州为例，其它region请按实际情况填写
		String endpoint = "http://oss-cn-hangzhou.aliyuncs.com/";
		// accessKey请登录https://ak-console.aliyun.com/#/查看
//		String accessKeyId = "LTAItihMSmlTuWst";
//		String accessKeySecret = "CozuTYt0d2NSX9LICTJ8iaQnmhg6g7";
		// 创建OSSClient实例
		OSSClient ossClient = new OSSClient(endpoint, Constant.APP_ID, Constant.APP_SECRET);
		//       Images/2017-03-15/----------------cb21aa0a-cceb-49d6-a507-77f3dd71ee5c_800.PNG
		ossClient.putObject("readbank", path, inputStream);
		// 关闭client
		ossClient.shutdown();
//		System.out.println("http://readbank.oss-cn-hangzhou.aliyuncs.com/" + path);
		return "http://readbank.oss-cn-hangzhou.aliyuncs.com/" + path;
	}

}
