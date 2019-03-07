package cn.net.sheryl.BaseUtil.TimerTask;
//package wtb.smUtil;
//
//import java.util.Calendar;
//import java.util.Timer;
//
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//
//public class MyListen implements ServletContextListener{
//	private Timer timer = null; 
//	@Override
//	public void contextDestroyed(ServletContextEvent arg0) {
//		//timer.cancel(); 
//	}
//
//	@Override
//	public void contextInitialized(ServletContextEvent arg0) {
//			
//		
//		Calendar calendar = Calendar.getInstance();
//	    int year = calendar.get(Calendar.YEAR);
//	    int month = calendar.get(Calendar.MONTH);
//	    int day = calendar.get(Calendar.DAY_OF_MONTH);//每天
//	        //定制每天的00:01:00执行，
//
//		    calendar.set(year, month, day, 00, 05, 00);
//			timer = new Timer(true); 
//	        //设置任务计划，启动和间隔时间 
//	        timer.schedule(new MyTask(), calendar.getTime(),1000*60*60*24);
// 
//	}
//
//}
