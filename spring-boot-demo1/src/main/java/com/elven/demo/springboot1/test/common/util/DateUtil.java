/** 
 * @Title: DateUtil.java 
 * @Package com.sitech.onloc.process.common.util 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author huangyue 
 * @date 2016年12月10日 上午11:29:41 
 * @version V1.0 
 */
package com.elven.demo.springboot1.test.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/** 
 * @ClassName: DateUtil 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author huangyue 
 * @date 2016年12月10日 上午11:29:41  
 */
public class DateUtil {
	public static final String format = "yyyy-MM-dd HH:mm:ss";
	public static SimpleDateFormat ymd=new SimpleDateFormat("yyyy-MM-dd"); 
	public static SimpleDateFormat ymdhms=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	
	
	
	/**
	 * 
	 * @Title: dateDiff 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param startTime
	 * @param @param endTime
	 * @param @param format 参数说明 
	 * @return void 返回类型 
	 * @throws
	 */
	public static void dateDiff(String startTime, String endTime, String format) {
		//按照传入的格式生成一个simpledateformate对象
		SimpleDateFormat sd = new SimpleDateFormat(format);
		long nd = 1000*24*60*60;//一天的毫秒数
		long nh = 1000*60*60;//一小时的毫秒数
		long nm = 1000*60;//一分钟的毫秒数
		long ns = 1000;//一秒钟的毫秒数long diff;try {
		//获得两个时间的毫秒时间差异
		long diff;
		try {
			diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
			long day = diff/nd;//计算差多少天
			long hour = diff%nd/nh;//计算差多少小时
			long min = diff%nd%nh/nm;//计算差多少分钟
			long sec = diff%nd%nh%nm/ns;//计算差多少秒//输出结果
//			System.out.println("时间相差："+day+"天"+hour+"小时"+min+"分钟"+sec+"秒。");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/** 
	 * @Title: toBetweenJiDu 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param beginTime
	 * @param @param endTime
	 * @param @return 参数说明 
	 * @return int 返回类型 
	 * @throws 
	 */
	public static long toBetweenJiDu(String beginTime, String endTime,String format) {
		// TODO Auto-generated method stub
		return 0;
	}

	/** 
	 * @Title: toBetweenYear 
	 * @Description: TODO(计算两个时间之间相差的年数) 
	 * @param @param beginTime
	 * @param @param endTime
	 * @param @return 参数说明 
	 * @return int 返回类型 
	 * @throws 
	 */
	public static long toBetweenYear(String beginTime, String endTime,String format) {
		int year1 = Integer.parseInt(beginTime.split("-")[0]);
		int year2 = Integer.parseInt(endTime.split("-")[0]);
		int chaYear = year2 - year1;
		int year = year1 + chaYear;
		String yearNew = year + beginTime.substring(4);		
		if(DateUtil.dateDiffCompare(yearNew, endTime, format) == 1){chaYear --;}
		if(chaYear<0){chaYear = 0;}
		return chaYear;
	}

	/** 
	 * @Title: toBetweenMonth 
	 * @Description: TODO(计算两个时间之间相差的月数) 
	 * @param @param beginTime
	 * @param @param endTime
	 * @param @return 参数说明 
	 * @return int 返回类型 
	 * @throws 
	 */
	public static long toBetweenMonth(String beginTime, String endTime,String format) {
		// TODO Auto-generated method stub
		return 0;
	}

	/** 
	 * @Title: toBetweenWeek 
	 * @Description: TODO(计算两个时间之间相差的周数) 
	 * @param @param beginTime
	 * @param @param endTime
	 * @param @return 参数说明 
	 * @return int 返回类型 
	 * @throws 
	 */
	public static long toBetweenWeek(String beginTime, String endTime,String format) {
		SimpleDateFormat sd = new SimpleDateFormat(format);
		long nd = 1000*24*60*60;//一天的毫秒数
		long day = 0;
		//获得两个时间的毫秒时间差异
		long diff;
		long week = 0;
			try {
				diff = sd.parse(endTime).getTime() - sd.parse(beginTime).getTime();
				day = diff/nd;//计算差多少天
				week = day / 7;
			} catch (ParseException e) {
				e.printStackTrace();
			}
		return week;
	}

	/** 
	 * @Title: toBetweenDay 
	 * @Description: TODO(计算两个时间之间相差的天数) 
	 * @param @param beginTime
	 * @param @param endTime
	 * @param @return 参数说明 
	 * @return int 返回类型 
	 * @throws 
	 */
	public static long toBetweenDay(String beginTime, String endTime,String format) {
		
		SimpleDateFormat sd = new SimpleDateFormat(format);
		long nd = 1000*24*60*60;//一天的毫秒数
		long day = 0;
		//获得两个时间的毫秒时间差异
		long diff;
			try {
				diff = sd.parse(endTime).getTime() - sd.parse(beginTime).getTime();
				day = diff/nd;//计算差多少天
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       return day;
	     
	}

	/** 
	 * @Title: dateDiffCompare 
	 * @Description: TODO(比较两个时间的大小) 
	 * @param @param beginTime
	 * @param @param endTime
	 * @param @param format 参数说明 
	 * @return int 返回类型   
	 * 返回0  代表beginTime = endTime
	 *   返回1  代表beginTime > endTime  第一个值大
	 *     返回2  代表beginTime < endTime
	 * @throws 
	 */
	public static long dateDiffCompare(String beginTime, String endTime,String format) {
		//2-01610-11
		if(beginTime!=null&&beginTime.length()<11){
			beginTime = beginTime + " 00:00:00";
		}
		
		if(endTime!=null&&endTime.length()<11){
			endTime = endTime + " 00:00:00";
		}
		//按照传入的格式生成一个simpledateformate对象
				SimpleDateFormat sd = new SimpleDateFormat(format);
				long nd = 1000*24*60*60;//一天的毫秒数
				long nh = 1000*60*60;//一小时的毫秒数
				long nm = 1000*60;//一分钟的毫秒数
				long ns = 1000;//一秒钟的毫秒数long diff;try {
				//获得两个时间的毫秒时间差异
				long diff;
				try {
					diff = sd.parse(endTime).getTime() - sd.parse(beginTime).getTime();
					long day = diff/nd;//计算差多少天
					long hour = diff%nd/nh;//计算差多少小时
					long min = diff%nd%nh/nm;//计算差多少分钟
					long sec = diff%nd%nh%nm/ns;//计算差多少秒//输出结果
					if(day == 0&&hour == 0&&min ==0&& sec == 0){
						return 0;
					}
					if(day<0||hour<0||min<0||sec<0){
						return 1;
					}else{
						return 2;
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return -1;
				}
		
	}

	/** 
	 * @Title: toBetweenDay_formatday 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param beginTime
	 * @param @param endTime
	 * @param @return 参数说明 
	 * @return int 返回类型 
	 * @throws 
	 */
	public static int toBetweenDay_formatday(String beginTime, String endTime) {
		try {
			Date date1 = ymd.parse(beginTime);
			Date date2 = ymd.parse(endTime);
			Calendar cal = Calendar.getInstance(); 
			cal.setTime(date1); 
			long time1 = cal.getTimeInMillis(); 
			cal.setTime(date2); 
			long time2 = cal.getTimeInMillis(); 
			long between_days=(time2-time1)/(1000*3600*24); 
			int a = Integer.parseInt(String.valueOf(between_days));
			return a+1; 
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	/** 
	 * @Title: toBetweenWeek_formatday 
	 * @Description: TODO(两个日期之间相差周数) 
	 * @param @param beginTime
	 * @param @param endTime
	 * @param @return 参数说明 
	 * @return long 返回类型 
	 * @throws 
	 */
	public static long toBetweenWeek_formatday(String beginTime, String endTime) {
	
		try {
            long from = ymd.parse(beginTime).getTime();
            long to = ymd.parse(endTime).getTime();
            return (to-from)/(1000*3600*24*7);
        } catch (ParseException e) {
            e.printStackTrace();
        }
		return 0;
	}

	/** 
	 * @Title: toBetweenMonth_formatday 
	 * @Description: TODO(计算两个日期之间相差的月数) 
	 * @param @param beginTime
	 * @param @param endTime
	 * @param @return 参数说明 
	 * @return long 返回类型 
	 * @throws 
	 */
	public static long toBetweenMonth_formatday(String beginTime, String endTime) {
		try {
			long n = countMonths(beginTime,endTime,"yyyy-MM-dd");
			return n;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	/** 
	 * @Title: toBetweenJiDu_formatday 
	 * @Description: TODO(获取两个时间段的季度差) 
	 * @param @param beginTime
	 * @param @param endTime
	 * @param @return 参数说明 
	 * @return long 返回类型 
	 * @throws 
	 */
	public static long toBetweenJiDu_formatday(String beginTime, String endTime) {
		String year1 =(String) beginTime.split("-")[0];
		String year2 =(String) endTime.split("-")[0];
		int y = Integer.parseInt(year2)-Integer.parseInt(year1);
//		System.out.println(beginTime.substring(5)); 
		String m1 =((String) beginTime.substring(5)).split("-")[0];
		String m2 =((String) endTime.substring(5)).split("-")[0]; 
		
		
		System.out.println(year1+"??"+year2);
		System.out.println(m1+"??"+m2);
		int m = Integer.parseInt(m2)-Integer.parseInt(m1);
//		System.out.println((y*12+m)/3+"++++++++++++++++++++++++++++");
		
		System.out.println("=========llll========="+(y*12+m)/3);
		return (y*12+m)/3;
	}

	/** 
	 * @Title: toBetweenYear_formatday 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param beginTime
	 * @param @param endTime
	 * @param @return 参数说明 
	 * @return long 返回类型 
	 * @throws 
	 */
	public static long toBetweenYear_formatday(String beginTime, String endTime) {
		String year1 =(String) beginTime.split("-")[0];
		String year2 =(String) endTime.split("-")[0];
		int y = Integer.parseInt(year2)-Integer.parseInt(year1);
		return y;
	}

	/** 
	 * @Title: getCurrentTime 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param string
	 * @param @return 参数说明 
	 * @return int  返回类型   
	 * 0  yyyy-MM-dd HH:mm:ss
	 * 其他  yyyy-MM-dd
	 * @throws 
	 */
	public static String getCurrentTime(int formatType) {
		if(formatType == 0){
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		}
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	}

	/** 
	 * @Title: addDay 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param beginTime
	 * @param @param i  正数 后i天  负数 前i天
	 * @param @return 参数说明 
	 * @return String 返回类型 
	 * @throws 
	 */
	public static String addDay(String beginTime, long i) {
		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(beginTime);
			
			return new SimpleDateFormat("yyyy-MM-dd").format(new Date(date.getTime() + i * 24 * 60 * 60 * 1000));
		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/** 
	 * @Title: addYear 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param beginTime
	 * @param @param nextPeriod
	 * @param @return 参数说明 
	 * @return String 返回类型 
	 * @throws 
	 */
	public static String addYear(String beginTime, long nextPeriod) {
		String year = beginTime.split("-")[0];
		long n= Long.parseLong(year) + nextPeriod;
		String time = n+beginTime.substring(4);
		return time;
	}

	/** 
	 * @Title: addJidu 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param beginTime
	 * @param @param nextPeriod
	 * @param @return 参数说明 
	 * @return String 返回类型 
	 * @throws 
	 */
	public static String addJidu(String beginTime, long nextPeriod) {
		System.out.println("addJidu:beginTime:"+beginTime + "nextPeriod:"+nextPeriod);
		String month = beginTime.substring(5).split("-")[0]; 
		long n= Long.parseLong(month) + nextPeriod*3;
		String monthStr = "";
		String yearStr = "";
		if(n>12){
			int x = (int) (n % 12);
			int y = (int) (n / 12);
			yearStr = (Integer.parseInt(beginTime.substring(0,4))+y) +"";
			monthStr = x+"";
		}else{
			if(n<10){
				monthStr = "0"+n;
			}else{
				monthStr = ""+n;
			}
			
			yearStr = beginTime.substring(0,4);
			 
		}
		
		String time = yearStr+ "-" + monthStr + beginTime.substring(7);
		return time; 
	}

	

	/** 
	 * @Title: addMonth 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param beginTime
	 * @param @param nextPeriod
	 * @param @return 参数说明 
	 * @return String 返回类型 
	 * @throws 
	 */
	public static String addMonth(String beginTime, long nextPeriod) {
		return DateUtil.getDateAddMonth(beginTime, nextPeriod);
	}

	/** 
	 * @Title: toBetweenWeek_formatday 
	 * @Description: TODO(获取两个日期之间包括有周几的个数) 
	 * @param @param beginTime
	 * @param @param endTime
	 * @param @param freqDay  周几
	 * @param @return 参数说明  
	 * @return long 返回类型   
	 * @throws 
	 */
	public static long toBetweenWeek_formatday(String beginTime,String endTime, int freqDay) {
		//获取日期的星期几
		try {
            long from = ymd.parse(beginTime).getTime();
            long to = ymd.parse(endTime).getTime();
            return (to-from)/(1000*3600*24*7);
        } catch (ParseException e) {
            e.printStackTrace();
        }
		return 0;
	}
	
		/**
		 * 
		 * @Title: getWeekData 
		 * @Description: TODO(获取指定日期所在周的周几的日期) 
		 * @param @param dateStr
		 * @param @param weekday  1-7  周一到周日
		 * @param @return 参数说明 
		 * @return String 返回类型 
		 * @throws
		 */
		public static String getWeekData(String dateStr,int weekday){
			
			 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式  
	         Calendar cal = Calendar.getInstance();  
	         Date time;
			try {
				time = sdf.parse(dateStr);
				cal.setTime(time);  
//		         System.out.println("要计算日期为:"+sdf.format(cal.getTime())); //输出要计算日期  
		         //判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了  
		         int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天  
//		         System.out.println("dayWeek:"+dayWeek);
		         if(1 == dayWeek) {  
		            cal.add(Calendar.DAY_OF_MONTH, -1);  
		         }  
		        cal.setFirstDayOfWeek(Calendar.MONDAY);//设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一  
		        int day = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天  
		        cal.add(Calendar.DATE, cal.getFirstDayOfWeek()-day);//根据日历的规则，给当前日期减去星期几与一个星期第一天的差值   
		        cal.add(Calendar.DATE, weekday -1);
		        return sdf.format(cal.getTime());
		        	
			} catch (ParseException e) {
				e.printStackTrace();
			}
	         
			return null;  
		}

		/** 
		 * @Title: addWeek 
		 * @Description: TODO(添加指定周数后返回日期) 
		 * @param @param beginTime
		 * @param @param nextPeriod
		 * @param @param freqDay
		 * @param @return 参数说明 
		 * @return String 返回类型 
		 * @throws 
		 */
		public static String addWeek(String firstDate, long nextPeriod) {
			String a =getDateAddDay(firstDate,nextPeriod * 7);
			return a;
		}
		
		/** 
		 * @Title: getDateAddDay 
		 * @Description: TODO(获取指定日期添加指定天数后的日期) 
		 * @param @param a0 指定日期 "2016-12-11"
		 * @param @param i  指定天数 1
		 * @param @return 参数说明   返回日期 2016-12-12
		 * @return String 返回类型  
		 * @throws 
		 */
		public static String getDateAddDay(String a0, long i) {
			Date date;
			try {
				date = ymd.parse(a0);
				 // 指定日期
				Date newDate = addDate(date, i); // 指定日期加上20天
				return ymd.format(newDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return "";
		}

		
		public static Date addDate(Date date,long day) {
			 long time = date.getTime(); // 得到指定日期的毫秒数
			 day = day*24*60*60*1000; // 要加上的天数转换成毫秒数
			 time+=day; // 相加得到新的毫秒数
			 return new Date(time); // 将毫秒数转换成日期
		}

		/** 
		 * @Title: getMonthData 
		 * @Description: TODO(指定日期所在月的几号所在的日期) 
		 * @param @param beginTime 2016-12-11
		 * @param @param freqDay
		 * @param @return 参数说明 
		 * @return String 返回类型 
		 * @throws 
		 */
		public static String getMonthData(String beginTime, int freqDay) {
			beginTime = beginTime.substring(0, 8);
			String day = freqDay +"";
			if(freqDay<10){
				day = "0"+freqDay;
			}
			return beginTime + day;
		}

		/** 
		 * @Title: getFirstDayMonth 
		 * @Description: TODO(这里用一句话描述这个方法的作用) 
		 * @param @param beginTime
		 * @param @return 参数说明 
		 * @return String 返回类型 
		 * @throws 
		 */
		public static String getFirstDayMonth(String beginTime) {
			beginTime = beginTime.substring(0,8);
			return beginTime+"01";
		}

		/** 
		 * @Title: getLastDayMonth 
		 * @Description: TODO(获取指定月份的最后一天) 
		 * @param @param beginTime
		 * @param @return 参数说明 
		 * @return String 返回类型 
		 * @throws 
		 */
		public static String getLastDayMonth(String beginTime) {
			
			String year = beginTime.substring(0,4);
			String month = beginTime.substring(5,7);
			//获取指定月份的最后一天日期
			Calendar cal = Calendar.getInstance();
			//设置年份
			cal.set(Calendar.YEAR,Integer.parseInt(year));
			//设置月份
			cal.set(Calendar.MONTH, Integer.parseInt(month)-1);
			//获取某月最大天数
			int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			//设置日历中月份的最大天数
			cal.set(Calendar.DAY_OF_MONTH, lastDay);
			//格式化日期
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String lastDayOfMonth = sdf.format(cal.getTime());
			return lastDayOfMonth;
		}

		/** 
		 * @Title: formatDateYMD 
		 * @Description: TODO(返回) 
		 * @param @param beginTime 参数说明  yyyy-MM-dd HH:mm:ss
		 * @return void 返回类型 yyyy-MM-dd
		 * @throws 
		 */
		public static String formatDateYMD(String beginTime) {
			return beginTime.substring(0,10);
		}

		/** 
		 * @Title: formatDateYMDHMS 
		 * @Description: TODO(这里用一句话描述这个方法的作用) 
		 * @param @param beginTime
		 * @param @return 参数说明 
		 * @return String 返回类型 
		 * @throws 
		 */
		public static String formatDateYMDHMS(String beginTime) {
			if(beginTime!=null&&beginTime.length()<11){
				beginTime = beginTime+" 00:00:00";
			}
			
			try {
				Date date = ymdhms.parse(beginTime);
				beginTime = ymdhms.format(date);
				return beginTime;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return "";
		}

		/** 
		 * @Title: formatYYYYMMDD 
		 * @Description: TODO(这里用一句话描述这个方法的作用) 
		 * @param @param beginTime yyyy-MM-dd
		 * @param @return 参数说明 
		 * @return int 返回类型 
		 * @throws 
		 */
		public static int formatYYYYMMDD(String beginTime) {
			// TODO Auto-generated method stub
			return Integer.parseInt(beginTime.substring(0,4) + beginTime.substring(5,7) + beginTime.substring(8,10));
		}

	
		/**
	     * 
	     * 计算两个日期相差的月份数	
	     * 
	     * @param date1 日期1
	     * @param date2 日期2
	     * @param pattern  日期1和日期2的日期格式
	     * @return  相差的月份数
	     * @throws ParseException
	     */
	    public static long countMonths(String date1,String date2,String pattern) throws ParseException{
	        SimpleDateFormat sdf=new SimpleDateFormat(pattern);
	        
	        Calendar c1=Calendar.getInstance();
	        Calendar c2=Calendar.getInstance();
	        
	        c1.setTime(sdf.parse(date1));
	        c2.setTime(sdf.parse(date2));
	        
	        int year =c2.get(Calendar.YEAR)-c1.get(Calendar.YEAR);
	        
	        //开始日期若小月结束日期
	        if(year<0){
	            year=-year;
	            return year*12+c1.get(Calendar.MONTH)-c2.get(Calendar.MONTH);
	        }
	       
	        return year*12+c2.get(Calendar.MONTH)-c1.get(Calendar.MONTH);
	    }

		/** 
		 * @Title: getDateAddMonth 
		 * @Description: TODO(指定日期添加指定月返回日期) 
		 * @param @param exeStartTime "2016-11-11"
		 * @param @param i
		 * @param @return 参数说明 
		 * @return String 返回类型 
		 * @throws 
		 */
		public static String getDateAddMonth(String exeStartTime, long i) {
			exeStartTime = formatDateYMD(exeStartTime)+" 00:00:00";
			int year = Integer.parseInt(exeStartTime.substring(0, 4)) ;
			int month = Integer.parseInt(exeStartTime.substring(5, 7)) ;
			String monthStr = "";
			String day = exeStartTime.substring(8, 10); 
			if(i > 0){
				int yu = (int)i % 12;
				int yearAdd = (int)(month+i) / 12;
				
				year = year + yearAdd;
				if(month + yu > 12){
					month = yu;
				}else{
					month = month + yu;
				}
				if(month<10&&(month+"").length() ==1){
					monthStr = "0"+month;
				}else{
					monthStr = ""+month;
				}
				return year +"-"+ monthStr + "-" +day;
			}
			
			return exeStartTime.substring(0, 10);
			
			
		}


		/** 
		 * @Title: formatBusiStyle1 
		 * @Description: TODO(这里用一句话描述这个方法的作用) 
		 * @param @param firstDay  2015-11-22 
		 * @param @return 参数说明 
		 * @return String 返回类型 
		 * @throws 
		 */
		public static String formatBusiStyle1(String firstDay) {
			if(firstDay!=null&&firstDay.length()>0){
				return firstDay.substring(5, 7)+"月"+firstDay.substring(8, 10)+"日";
			}
			return "";
		}

		/** 
		 * @Title: getDateMinusDay 
		 * @Description: TODO(指定日期减去指定天数返回日期) 
		 * @param @param firstDay
		 * @param @param i
		 * @param @return 参数说明 
		 * @return String 返回类型 
		 * @throws 
		 */
		public static Date MinusDay(String firstDay, long day) {
			Date date;
			try {
				date = ymd.parse(firstDay);
				long time = date.getTime(); // 得到指定日期的毫秒数
				 day = day*24*60*60*1000; // 要加上的天数转换成毫秒数
				 time-=day; // 相加得到新的毫秒数
				 return new Date(time); // 将毫秒数转换成日期
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 return null;
		}

		/** 
		 * @Title: addMonthReturnNextPeriodMonth 
		 * @Description: TODO(指定日期（如：2016-12-30）和指定i个月（如：2）后的日期，这个日期为2017-02-30的实际月份最后一天 即2017-02-28 ) 
		 * 正常情况:指定日期（如：2016-12-11）和指定nextPeriod个月（如：2）后的日期，这个日期为2017-02-11的实际月份最后一天 即2017-02-11
		 * 
		 * @param @param firstDate
		 * @param @param i
		 * @param @return 参数说明 
		 * @return String 返回类型 
		 * @throws 
		 */
		public static String addMonthReturnNextPeriodMonth(String firstDate,long i) {
			firstDate = formatDateYMD(firstDate)+" 00:00:00";
			int year = Integer.parseInt(firstDate.substring(0, 4)) ;
			int month = Integer.parseInt(firstDate.substring(5, 7)) ;
			String monthStr = "";
			String day = firstDate.substring(8, 10); 
			if(i > 0){
				int yu = (int)i % 12;
				int yearAdd = (int)(month+i) / 12;
				
				year = year + yearAdd;
				if(month + yu > 12){
					month = yu;
				}else{
					month = month + yu;
				}
				if(month<10&&(month+"").length() ==1){
					monthStr = "0"+month;
				}else{
					monthStr = ""+month;
				}
				
				if(day!=null&&!"".equals(day)&&Integer.parseInt(day)>28){
					String returnDate = year +"-"+ monthStr + "-01";
					//获取返回所在月最后一天
					int maxDay = Integer.parseInt(getLastDayMonth(returnDate).substring(8,10) );
					if(Integer.parseInt(day)>maxDay){
						day = ""+maxDay;
					}
				}
				
				return year +"-"+ monthStr + "-" +day;
			}
			
			return firstDate.substring(0, 10);
		}
	
}
