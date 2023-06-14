package com.oetsky.common.frame.utils;

import java.text.SimpleDateFormat;

public class DateTools extends org.apache.commons.lang3.time.DateUtils {

	private static ThreadLocal<SimpleDateFormat> format1 = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
	};
	private static ThreadLocal<SimpleDateFormat> format2 = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyMMddHHmmss");
		}
	};
	private static ThreadLocal<SimpleDateFormat> format3 = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyMMddHHmm");
		}
	};
	private static ThreadLocal<SimpleDateFormat> format4 = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyyMMddHHmmss");
		}
	};
	private static ThreadLocal<SimpleDateFormat> format5 = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyyMMdd");
		}
	};

	private static ThreadLocal<SimpleDateFormat> format6 = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy");
		}
	};
	private static ThreadLocal<SimpleDateFormat> format7 = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		}
	};
	private static ThreadLocal<SimpleDateFormat> format8 = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyyMMddHHmmssSSS");
		}
	};

	private static ThreadLocal<SimpleDateFormat> format9 = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd");
		}
	};
	/**
	 * yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static SimpleDateFormat getFormat1(){
		return format1.get();
	}

	/**
	 * yyMMddHHmmss
	 * @return
	 */
	public static SimpleDateFormat getFormat2(){
		return format2.get();
	}

	/**
	 * yyMMddHHmm
	 * @return
	 */
	public static SimpleDateFormat getFormat3(){
		return format3.get();
	}

	/**
	 * yyyyMMddHHmmss
	 * @return
	 */
	public static SimpleDateFormat getFormat4(){
		return format4.get();
	}

	/**
	 * yyyyMMdd
	 * @return
	 */
	public static SimpleDateFormat getFormat5(){
		return format5.get();
	}

	/**
	 * yyyy
	 * @return
	 */
	public static SimpleDateFormat getFormat6(){
		return format6.get();
	}

	/**
	 * yyyy_MM_dd_HH_mm_ss
	 * @return
	 */
	public static SimpleDateFormat getFormat7(){
		return format7.get();
	}

	/**
	 * yyyyMMddHHmmssSSS
	 * @return
	 */
	public static SimpleDateFormat getFormat8(){
		return format8.get();
	}
	/**
	 * yyyy-MM-dd
	 * @return
	 */
	public static SimpleDateFormat getFormat9(){
		return format9.get();
	}
}
