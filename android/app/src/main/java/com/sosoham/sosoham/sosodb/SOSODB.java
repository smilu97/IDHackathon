package com.sosoham.sosoham.sosodb;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by becxer on 2016. 5. 29..
 */
public class SOSODB {

    public void httprequest(JSONObject req_obj, final SOSODBListener sosodbListener){
        try {
            Log.d("get", req_obj.getString("method"));
            String method = req_obj.getString("method");
            HttpRequestJSON hrj = new HttpRequestJSON();
            hrj.sendPost(method, req_obj, new HttpRequestJSON.HttpRequestJSONListener() {
                @Override
                public void onRequestResult(JSONObject jsonObject) {
                    sosodbListener.onRequestResult(jsonObject);
                }
            });
        }catch (Exception e){
            Log.d("get",e.toString());
        }
    }

    public void get(final JSONObject req_obj, final SOSODBListener sosodbListener){
        try {
            Log.d("get", req_obj.getString("method"));
            String method = req_obj.getString("method");
            if(method.equals("get_friends_list")){
                String[] ids = FaceBook.my_friend_ids.split(",");
                String[] names = FaceBook.my_friend_names.split(",");
                JSONArray jsonArray = new JSONArray();
                for(int i = 0 ; i < ids.length ; i++){
                    JSONObject jobj = new JSONObject();
                    jobj.put("friend_id",ids[i]);
                    jobj.put("friend_name",names[i]);
                    jsonArray.put(jobj);
                }
                JSONObject res = new JSONObject();
                res.put("friends_list",jsonArray);
                sosodbListener.onRequestResult(res);
            }else{
                httprequest(req_obj,sosodbListener);
            }
        }catch (Exception e){
            Log.d("get",e.toString());
        }
    }

    public void add(final JSONObject req_obj, final SOSODBListener sosodbListener){
        httprequest(req_obj,sosodbListener);
    }

    public void give(final JSONObject req_obj, final SOSODBListener sosodbListener){
        httprequest(req_obj, sosodbListener);
    }

    public interface SOSODBListener{
        public void onRequestResult(JSONObject jobj);
    }
}
