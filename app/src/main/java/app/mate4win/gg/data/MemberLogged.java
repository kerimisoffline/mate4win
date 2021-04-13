/*
 * Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
 */

package app.mate4win.gg.data;

import android.content.Context;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import app.mate4win.gg.model.Cache;
import app.mate4win.gg.model.Member;
import app.mate4win.gg.util.Data;
import app.mate4win.gg.util.NetworkUtil;

import static app.mate4win.gg.data.DataConfig.call_count;
import static app.mate4win.gg.util.Config.isNotNull;
import static app.mate4win.gg.util.Config.ltrim;
import static app.mate4win.gg.util.Config.rtrim;

public class MemberLogged {
    private Context context;
    private String errorMessage;
    int CallCount = 0;

    public void Call(final Context context,final String email) {
        if(CallCount >= call_count)
            return;

        CallCount++;
        this.context = context;
        JSONObject jsonObject = null;
        try {
            String cache_logged = Data.getCache(context).getCache(Cache.KEY_LOGGED_MEMBER);
            DataConfig.serviceURL = "http://192.168.1.103:8888/logged.php";
            if(NetworkUtil.isOnline(context)) {
                final JSONObject params = new JSONObject();
                params.put("email", ltrim(rtrim(email)));

                jsonObject = new CallService(context).getService(params, "PUT", null, true);
                if (jsonObject == null)
                    Call(context,ltrim(rtrim(email)));
                else {
                    if (jsonObject.has("result"))
                        Response(jsonObject);
                    else
                        Call(context,ltrim(rtrim(email)));
                }
            }else if(isNotNull(cache_logged)){
                jsonObject = new JSONObject(cache_logged);
                if(jsonObject != null)
                    Response(jsonObject);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            jsonObject = null;
        }
    }

    private void Response(JSONObject jsonObject){
        try {
            if(jsonObject!=null) {

                if(jsonObject.has("result")) {

                    Boolean result_bool = jsonObject.getBoolean("result");

                    if (!result_bool) {
                        String logged_err_message = "";
                        if (jsonObject.has("messages") && isNotNull(jsonObject.getString("messages"))) {
                            try{
                                for (int i = 0; i < jsonObject.getJSONArray("messages").length(); i++) {
                                    logged_err_message += jsonObject.getJSONArray("messages").getString(i) + "\n";
                                }
                            }catch (JSONException ex){
                                logged_err_message = jsonObject.getString("messages");
                            }
                        }
                        setErrorMessage(logged_err_message);

                    } else {
                        Data.getCache(context).setCache(Cache.KEY_LOGGED_MEMBER, jsonObject.toString());
                        JSONObject memberObject = jsonObject.getJSONObject("data");

                        Data.member = new Gson().fromJson(memberObject.toString(), Member.class);
                    }
                }
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }finally {
            jsonObject = null;
            close();
        }
    }

    public void setErrorMessage(String errrMsg){
        errorMessage=errrMsg;
    }

    public String getErrorMessage(){
        return errorMessage;
    }

    private void close(){
        errorMessage = null;
    }
}
