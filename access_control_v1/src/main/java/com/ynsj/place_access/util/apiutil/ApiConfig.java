package com.ynsj.place_access.util.apiutil;

import com.ynsj.place_access.util.ServerConfig;

/**
 * API相关常量wrist.linkfun.cc
 * Created by admin on 2016/11/5.
 */
public class ApiConfig {
    public static final String LOCAL_IP = "http://192.168.0.166:1201";
//    public static final String SERVER_IP = "http://api.gym.linkfun.cc";

    public static final String SERVER_IP = "http://"+ ServerConfig.getInstance().server_address+":2201";
    public static final String SERVER_IMAGE = SERVER_IP + "/data/upload/";
    public static final String UPLOAD_FILE_URL = SERVER_IP + "/upload";



//    public static final String WX_APP_ID = "wx0f8b2fa15745bcc1";
//    public static final String WX_SECRET = "370a177e935643135728415edec63cdb";
    public static final String WB_APP_ID = "1098884526";
    public static final String WB_REDIRECT_URL = "http://wawaji.linkfun.cc/index.php";
    public static final String WB_SCOPE = "email,direct_messages_read,direct_messages_write,"
            + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
            + "follow_app_official_microblog," + "invitation_write";
}
