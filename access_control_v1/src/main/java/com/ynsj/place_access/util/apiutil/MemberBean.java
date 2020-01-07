package com.ynsj.place_access.util.apiutil;

import java.io.Serializable;
import java.util.List;

/**
 * 地址
 * Created by kenvi-samsung on 2016-04-26.
 */
public class MemberBean implements Serializable {

    /**
     * ret : true
     * errMsg : null
     * info : [{"id":42,"type":{"id":1,"name":"普通会员","smeta":"{\"thumb\":\"20180519204944.png\",\"photo\":[{\"alt\":\"\"},{\"alt\":\"\"},{\"alt\":\"\"},{\"alt\":\"\"},{\"alt\":\"\"},{\"alt\":\"\"},{\"alt\":\"\"},{\"alt\":\"\"},{\"alt\":\"\"}]}","level":1,"delflag":false},"card":{"id":1,"name":"初级会员卡","type":1,"level":1,"buy_online":true,"listorder":100,"memo":"1111112341234","transfer_type":1,"transfer_fee":500,"scope":0,"attr":null,"create_time":1530587677000,"delflag":false},"create_time":1535186192000,"smeta":"","face_file":"http://pb2p5cas9.bkt.clouddn.com/201808291559211560.kk","face_id":null,"finger_id":"WFhYWFhYWFhYWFhYWFhYWA==\n","last_course_time":null,"start_time":"2018-08-25","end_time":"2018-08-25","delflag":false,"user_nicename":"娃哈哈","reset":0,"source":"","memo":"","tags":"","mobile":"13226632540","card_no":"1111","sex":0,"status":0,"member_type":0,"has_extended":false,"bath_minutes":0,"weight":0,"height":200,"birthday":null,"weixin":null,"card_name":"初级会员卡","card_type":1,"type_id":1,"type_level":1,"user_id":19,"card_id":1,"user_login":null,"avatar":"http://p7h46npyl.bkt.clouddn.com/201808021808521751.png","store_id":0,"store_name":"","type_name":"普通会员"}]
     */

    private boolean ret;
    private Object errMsg;
    private List<InfoBean> info;

    public boolean isRet() {
        return ret;
    }

    public void setRet(boolean ret) {
        this.ret = ret;
    }

    public Object getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(Object errMsg) {
        this.errMsg = errMsg;
    }

    public List<InfoBean> getInfo() {
        return info;
    }

    public void setInfo(List<InfoBean> info) {
        this.info = info;
    }

    public static class InfoBean {
        /**
         * id : 42
         * type : {"id":1,"name":"普通会员","smeta":"{\"thumb\":\"20180519204944.png\",\"photo\":[{\"alt\":\"\"},{\"alt\":\"\"},{\"alt\":\"\"},{\"alt\":\"\"},{\"alt\":\"\"},{\"alt\":\"\"},{\"alt\":\"\"},{\"alt\":\"\"},{\"alt\":\"\"}]}","level":1,"delflag":false}
         * card : {"id":1,"name":"初级会员卡","type":1,"level":1,"buy_online":true,"listorder":100,"memo":"1111112341234","transfer_type":1,"transfer_fee":500,"scope":0,"attr":null,"create_time":1530587677000,"delflag":false}
         * create_time : 1535186192000
         * smeta :
         * face_file : http://pb2p5cas9.bkt.clouddn.com/201808291559211560.kk
         * face_id : null
         * finger_id : WFhYWFhYWFhYWFhYWFhYWA==

         * last_course_time : null
         * start_time : 2018-08-25
         * end_time : 2018-08-25
         * delflag : false
         * user_nicename : 娃哈哈
         * reset : 0
         * source :
         * memo :
         * tags :
         * mobile : 13226632540
         * card_no : 1111
         * sex : 0
         * status : 0
         * member_type : 0
         * has_extended : false
         * bath_minutes : 0
         * weight : 0
         * height : 200
         * birthday : null
         * weixin : null
         * card_name : 初级会员卡
         * card_type : 1
         * type_id : 1
         * type_level : 1
         * user_id : 19
         * card_id : 1
         * user_login : null
         * avatar : http://p7h46npyl.bkt.clouddn.com/201808021808521751.png
         * store_id : 0
         * store_name :
         * type_name : 普通会员
         */

        private int id;
        private TypeBean type;
        private CardBean card;
        private long create_time;
        private String smeta;
        private String face_file;
        private Object face_id;
        private String finger_id;
        private String nfc_id;
        private Object last_course_time;
        private String start_time;
        private String end_time;
        private boolean delflag;
        private String user_nicename;
        private int reset;
        private String source;
        private String memo;
        private String tags;
        private String mobile;
        private String card_no;
        private int sex;
        private int status;
        private int member_type;
        private boolean has_extended;
        private int bath_minutes;
        private int weight;
        private int height;
        private Object birthday;
        private Object weixin;
        private String card_name;
        private int card_type;
        private int type_id;
        private int type_level;
        private int user_id;
        private int card_id;
        private Object user_login;
        private String avatar;
        private int store_id;
        private String store_name;
        private String type_name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public TypeBean getType() {
            return type;
        }

        public void setType(TypeBean type) {
            this.type = type;
        }

        public CardBean getCard() {
            return card;
        }

        public void setCard(CardBean card) {
            this.card = card;
        }

        public long getCreate_time() {
            return create_time;
        }

        public void setCreate_time(long create_time) {
            this.create_time = create_time;
        }

        public String getSmeta() {
            return smeta;
        }

        public void setSmeta(String smeta) {
            this.smeta = smeta;
        }

        public String getFace_file() {
            return face_file;
        }

        public void setFace_file(String face_file) {
            this.face_file = face_file;
        }

        public Object getFace_id() {
            return face_id;
        }

        public void setFace_id(Object face_id) {
            this.face_id = face_id;
        }

        public String getFinger_id() {
            return finger_id;
        }

        public void setFinger_id(String finger_id) {
            this.finger_id = finger_id;
        }

        public String getNfc_id() {
            return nfc_id;
        }

        public void setNfc_id(String nfc_id) {
            this.nfc_id = nfc_id;
        }

        public Object getLast_course_time() {
            return last_course_time;
        }

        public void setLast_course_time(Object last_course_time) {
            this.last_course_time = last_course_time;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public boolean isDelflag() {
            return delflag;
        }

        public void setDelflag(boolean delflag) {
            this.delflag = delflag;
        }

        public String getUser_nicename() {
            return user_nicename;
        }

        public void setUser_nicename(String user_nicename) {
            this.user_nicename = user_nicename;
        }

        public int getReset() {
            return reset;
        }

        public void setReset(int reset) {
            this.reset = reset;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getCard_no() {
            return card_no;
        }

        public void setCard_no(String card_no) {
            this.card_no = card_no;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getMember_type() {
            return member_type;
        }

        public void setMember_type(int member_type) {
            this.member_type = member_type;
        }

        public boolean isHas_extended() {
            return has_extended;
        }

        public void setHas_extended(boolean has_extended) {
            this.has_extended = has_extended;
        }

        public int getBath_minutes() {
            return bath_minutes;
        }

        public void setBath_minutes(int bath_minutes) {
            this.bath_minutes = bath_minutes;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public Object getBirthday() {
            return birthday;
        }

        public void setBirthday(Object birthday) {
            this.birthday = birthday;
        }

        public Object getWeixin() {
            return weixin;
        }

        public void setWeixin(Object weixin) {
            this.weixin = weixin;
        }

        public String getCard_name() {
            return card_name;
        }

        public void setCard_name(String card_name) {
            this.card_name = card_name;
        }

        public int getCard_type() {
            return card_type;
        }

        public void setCard_type(int card_type) {
            this.card_type = card_type;
        }

        public int getType_id() {
            return type_id;
        }

        public void setType_id(int type_id) {
            this.type_id = type_id;
        }

        public int getType_level() {
            return type_level;
        }

        public void setType_level(int type_level) {
            this.type_level = type_level;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getCard_id() {
            return card_id;
        }

        public void setCard_id(int card_id) {
            this.card_id = card_id;
        }

        public Object getUser_login() {
            return user_login;
        }

        public void setUser_login(Object user_login) {
            this.user_login = user_login;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public int getStore_id() {
            return store_id;
        }

        public void setStore_id(int store_id) {
            this.store_id = store_id;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }

        public static class TypeBean {
            /**
             * id : 1
             * name : 普通会员
             * smeta : {"thumb":"20180519204944.png","photo":[{"alt":""},{"alt":""},{"alt":""},{"alt":""},{"alt":""},{"alt":""},{"alt":""},{"alt":""},{"alt":""}]}
             * level : 1
             * delflag : false
             */

            private int id;
            private String name;
            private String smeta;
            private int level;
            private boolean delflag;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSmeta() {
                return smeta;
            }

            public void setSmeta(String smeta) {
                this.smeta = smeta;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public boolean isDelflag() {
                return delflag;
            }

            public void setDelflag(boolean delflag) {
                this.delflag = delflag;
            }
        }

        public static class CardBean {
            /**
             * id : 1
             * name : 初级会员卡
             * type : 1
             * level : 1
             * buy_online : true
             * listorder : 100
             * memo : 1111112341234
             * transfer_type : 1
             * transfer_fee : 500.0
             * scope : 0
             * attr : null
             * create_time : 1530587677000
             * delflag : false
             */

            private int id;
            private String name;
            private int type;
            private int level;
            private boolean buy_online;
            private int listorder;
            private String memo;
            private int transfer_type;
            private double transfer_fee;
            private int scope;
            private Object attr;
            private long create_time;
            private boolean delflag;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public boolean isBuy_online() {
                return buy_online;
            }

            public void setBuy_online(boolean buy_online) {
                this.buy_online = buy_online;
            }

            public int getListorder() {
                return listorder;
            }

            public void setListorder(int listorder) {
                this.listorder = listorder;
            }

            public String getMemo() {
                return memo;
            }

            public void setMemo(String memo) {
                this.memo = memo;
            }

            public int getTransfer_type() {
                return transfer_type;
            }

            public void setTransfer_type(int transfer_type) {
                this.transfer_type = transfer_type;
            }

            public double getTransfer_fee() {
                return transfer_fee;
            }

            public void setTransfer_fee(double transfer_fee) {
                this.transfer_fee = transfer_fee;
            }

            public int getScope() {
                return scope;
            }

            public void setScope(int scope) {
                this.scope = scope;
            }

            public Object getAttr() {
                return attr;
            }

            public void setAttr(Object attr) {
                this.attr = attr;
            }

            public long getCreate_time() {
                return create_time;
            }

            public void setCreate_time(long create_time) {
                this.create_time = create_time;
            }

            public boolean isDelflag() {
                return delflag;
            }

            public void setDelflag(boolean delflag) {
                this.delflag = delflag;
            }
        }
    }
}
