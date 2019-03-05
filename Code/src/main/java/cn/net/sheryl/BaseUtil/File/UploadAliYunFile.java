package cn.net.sheryl.BaseUtil.File;
//package wtb.smUtil;
//
//
//import java.io.File;
//
//import java.io.InputStream;
//import java.util.Date;
//import java.util.UUID;
//
//
//
//
//
//
//
//
//import com.aliyun.oss.OSSClient;
//
//public class UploadAliYunFile {
//	/**
//	 * 文件上传
//	 * @param is
//	 * @param file
//	 * @return
//	 */
//	public static String UploadAliYunFileService(InputStream inputStream,File file,String fileType){
//		String name=file.getName();
//		String prefix=name.substring(name.lastIndexOf(".")+1);
//		Date date=new Date();
//		String path=fileType+"/"+SmBaseGlobal.sdfDate.format(date)+"/";
//		path= path+UUID.randomUUID().toString()+"."+prefix;
//		// endpoint以杭州为例，其它region请按实际情况填写
//		String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
//		// accessKey请登录https://ak-console.aliyun.com/#/查看
//		String accessKeyId = "LTAIIQkISE7PpDu1";
//		String accessKeySecret = "j5Fd1wNx5w5J3Ox3ntouL8HqWwh3Vs";
//		// 创建OSSClient实例
//		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
//		//       Images/2017-03-15/----------------cb21aa0a-cceb-49d6-a507-77f3dd71ee5c_800.PNG
//		ossClient.putObject("wenews", path, inputStream);
//		// 关闭client
//		ossClient.shutdown();
//		System.out.println("http://wenews.oss-cn-hangzhou.aliyuncs.com/"+path);
//		return "http://wenews.oss-cn-hangzhou.aliyuncs.com/"+path;
//	}
//
//}
