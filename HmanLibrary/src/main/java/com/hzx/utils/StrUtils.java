package com.hzx.utils;

/**
 * Created by Hman on 2017/10/20.
 */
public class StrUtils {

    public static int LOGIN_ID_INT ;
    public final static String LOGIN = "LOGIN";
    public final static String VISITOR_LOGIN = "VISITOR_LOGIN";
    public final static String LOGIN_TYPE = "login_type";

    public final static String PIC = "DISPIC"; // 图片课件
    public final static String VIDEO = "DISVIDEO"; // 视频课件

    public final static int RESVIDEO = 1; // 视频
    public final static int RESVOICE = 2; // 音频
    public final static int RESPICTURE = 3; // 图片
    public final static int RESDOCUMENT = 4; // 文档

    public final static int REGISTER_FAILED = 0x0001; // 注册失败
    public final static int REGISTER_SUCCEED = 0x0000; // 注册成功

    public final static int LOGIN_SUCCEED = 0x0002; // 登录成功
    public final static int LOGIN_FAILED = 0x0003;  // 登录失败

    public final static String LOGIN_USER = "LOGINUSER"; // 用户名
    public final static String LOGIN_PWD = "LOGINPWD"; // 密码
    public final static String IS_LOGIN = "isLogin"; // 判断是否登录

    public final static int RESOURCE_VIDEO_FAILED = 0x0004; // 视频资源请求失败
    public final static int RESOURCE_VIDEO_SUCCEED = 0x0005; // 视频资源请求成功

    public final static int COLLECT_SUCCEED = 0x0006; // 收藏列表加载成功
    public final static int COLLECT_FAILED = 0x0007; // 收藏列表加载失败

    public final static int FORGET_PWD_SUCCEED = 0x0008; // 忘记密码修改成功
    public final static int FORGET_PWD_FAILED = 0x0009; // 忘记密码修改失败

    public final static int SUCCED = 0x0010; // 成功
    public final static int FAILED = 0x0011; // 失败

    public final static String PHONE = "phone"; // 电话号码
    public static String LOGIN_ID = "LOGINID"; // 登录ID字段
    public static String USER_INFO = "user_info"; //


    public final static String RESPONSE_FAILED = "failed"; // failed
    public final static String RESPONSE_SUCCEED = "succeed"; // succeed

    public final static String WATER_QUALITY_LATE_DATA = "WATER_QUALITY_LATE_DATA";// 水质点最新一条数据
    public final static String WATER_QUALITY_HISTORY_DATA = "WATER_QUALITY_HISTORY_DATA";//  水质点历史数据
    public final static String WATER_COLLECTION_IS_FLAG = "WATER_COLLECTION_IS_FLAG"; // 该水质是否收藏
    public final static String AREAPOINTDETAIL = "AREAPOINTDETAIL"; // 测点详细信息

    public final static String WATER_HIGHT_LATE_WEEK = "WATER_HIGHT_LATE_WEEK"; // 水位最新七条数据
    public final static String WATER_HIGHT_LATE_HISTORY = "WATER_HIGHT_LATE_HISTORY"; // 水位历史数据
    public final static String WATER_HIGHT_AREA_POINT_DETAIL = "WATER_HIGHT_AREA_POINT_DETAIL"; // 水位测点详细信息
    public final static String WATER_ADD_COLLECTION = "WATER_ADD_COLLECTION";// 添加收藏
    public final static String WATER_CANCLE_COLLECTION = "WATER_CANCLE_COLLECTION"; // 取消收藏


}
