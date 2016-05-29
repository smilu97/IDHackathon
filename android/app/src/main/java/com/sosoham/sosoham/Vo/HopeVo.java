package com.sosoham.sosoham.Vo;

/**
 * Created by AppCreater01 on 2016-05-29.
 */
public class HopeVo {
    public String UID;
    public String UNAME;
    public String STF_ID;
    public String STF_NAME;
    public String SEQ;
    public String UP_TIME;
    public String STF_TIME;
    public String GIFT_TITLE;
    public String GIFT_CONTENT;
    RepleVo [] REPLES;

    public HopeVo(String a,String b, String c,String d,String e,String f,String g,String h ,String i){
        this.UID =a;
        this.UNAME =b;
        this.STF_ID =c;
        this.STF_NAME =d;
        this.SEQ = e;
        this.UP_TIME =f;
        this.STF_TIME =g;
        this.GIFT_TITLE =h;
        this.GIFT_CONTENT=i;
       // this.REPLES = j;
    }

}
