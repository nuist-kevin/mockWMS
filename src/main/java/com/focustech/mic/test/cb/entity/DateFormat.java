package com.focustech.mic.test.cb.entity;

/**
 * @author caiwen
 */
public interface DateFormat {
  String ISO_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
  String ISO_DATETIME_FORMAT_2 = "MM-dd-yyyy HH:mm:ss";
  String ISO_DATETIME_TIME_ZONE_FORMAT = "yyyy-MM-dd HH:mm:ssZZ";
  String ISO_DATE_FORMAT = "yyyy-MM-dd";
  String ISO_TIME_FORMAT = "HH:mm:ss";
  String US_DATETIME_FORMAT = "MM-dd-yyyy HH:mm:ss";
  long ONE_DAY_MillIS = 24 * 60 * 60 * 1000;
  String ISO_DATE_PARSE = "MM/dd/yyyy";
  String ISO_DATE_PARSE_2 = "MM-dd-yyyy";
  String ISO_DATE_FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
  String ISO_DATE_FORMAT_YYMMDDHHMMSS = "yyMMddHHmmss";
  String ISO_UTC_DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ssXXX";
  String ISO_FSR_DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
  String DATE_FORMAT_BACKSLASH = "yyyy/MM/dd";
  String INB_PLAN_DATETIME_FORMAT = "MM/dd/yy hh:mm a";// 入库计划默认名称中包含的时间格式
  String ISO_DATE_FORMAT_MD = "M/d";
}