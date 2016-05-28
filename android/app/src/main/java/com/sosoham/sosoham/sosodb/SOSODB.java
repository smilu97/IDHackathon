package com.sosoham.sosoham.sosodb;

import org.json.JSONObject;

/**
 * Created by becxer on 2016. 5. 29..
 */
public class SOSODB {

    public static JSONObject convertStr2JSON(String str){
        JSONObject res = new JSONObject();
        return res;
    }

    public void get(final String req_obj, final SOSODBListener sosodbListener){
        sosodbListener.onRequestResult(new JSONObject());
    }

    public void add(final String req_obj, final SOSODBListener sosodbListener){
        sosodbListener.onRequestResult(new JSONObject());
    }

    public void give(final String req_obj, final SOSODBListener sosodbListener){
        sosodbListener.onRequestResult(new JSONObject());
    }

    public interface SOSODBListener{
        public void onRequestResult(JSONObject jobj);
    }
}
