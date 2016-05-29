package com.sosoham.sosoham.sosodb;

import android.util.Log;

import org.json.JSONObject;

/**
 * Created by mac on 2016. 5. 29..
 */
public class FaceBook {

    public static String my_id = "1";
    public static String my_name = "baek";
    public static String my_friend_ids = "2,3,4,5";
    public static String my_friend_names = "a,b,c,d";

    public static void loginFB(final FaceBookListener fbl){
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("my_id", my_id);
            jsonObject.put("my_name", my_name);
            jsonObject.put("my_friend_list",my_friend_ids);
            jsonObject.put("method","add_user");
            Log.d("json", jsonObject.toString());
            SOSODB sosodb = new SOSODB();
            sosodb.add(jsonObject, new SOSODB.SOSODBListener() {
                @Override
                public void onRequestResult(JSONObject jobj) {
                    fbl.onAfterLogin();
                }
            });
        }catch (Exception e){
            Log.d("FB", e.toString());
        }
    }

    public static String getFBname(String id){
        String[] mfl = my_friend_ids.split(",");
        String[] mln = my_friend_names.split(",");
        for(int i = 0 ; i < mfl.length ; i++){
            if(mfl[i].equals(id)){
                return mln[i];
            }
        }
        return null;
    }

    public interface FaceBookListener{
        public void onAfterLogin();
    }
}
