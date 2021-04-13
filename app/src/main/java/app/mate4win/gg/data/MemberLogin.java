/*
 * Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
 */

package app.mate4win.gg.data;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import app.mate4win.gg.util.NetworkUtil;

import static app.mate4win.gg.data.DataConfig.call_count;
import static app.mate4win.gg.util.Config.isNotNull;
import static app.mate4win.gg.util.Config.ltrim;
import static app.mate4win.gg.util.Config.rtrim;

public class MemberLogin {

    private String errorMessage="";
    private JSONObject jsonObject;
    int CallCount = 0;

    public void Call(final Context context, String email, String password) {
        if(!NetworkUtil.isOnline(context) || CallCount >= call_count)
            return;

        CallCount++;
        final JSONObject params = new JSONObject();
        try {
            params.put("email", ltrim(rtrim(email)));
            params.put("password", ltrim(rtrim(password)));
            DataConfig.serviceURL = "http://192.168.1.103:8888/login.php";

            jsonObject = new CallService(context).getService(params, "PUT", null, true);
            if(jsonObject == null)
                Call(context, email, password);
            else{
                if(jsonObject.has("result"))
                    Response(jsonObject);
                else
                    Call(context, email, password);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void Response(JSONObject jsonObject){
        try {
            if (jsonObject != null) {
                if (jsonObject.has("result")) {
                    Boolean result_bool = jsonObject.getBoolean("result");

                    if (!result_bool) {
                        String errors = "";
                        if (jsonObject.has("messages") && isNotNull(jsonObject.getString("messages"))) {

                            try {
                                JSONObject error_object = jsonObject.getJSONObject("messages");

                                if (error_object != null) {
                                    if (error_object.has("email") && isNotNull(error_object.getString("email")))
                                        errors = error_object.getString("email") + "\r\n";
                                    if (error_object.has("password") && isNotNull(error_object.getString("password")))
                                        errors += error_object.getString("password");
                                }
                            } catch (JSONException ex) {
                                JSONArray messageArray = jsonObject.getJSONArray("messages");
                                for (int m = 0; m < messageArray.length(); m++) {
                                    String err = (String) messageArray.get(m);
                                    if (!isNotNull(errors)) {
                                        errors = err + "\r\n";
                                    } else
                                        errors += err + "\r\n";
                                }
                            }

                            if (isNotNull(errors))
                                setErrorMessage(errors);
                            else
                                setErrorMessage("");

                        }
                    }
                }
            }
        }catch (JSONException ex){

        }finally {
            this.jsonObject = null;
            jsonObject = null;
        }
    }

    public void setErrorMessage(String errrMsg){
        errorMessage=errrMsg;
    }

    public String getErrorMessage(){
        return errorMessage;
    }

}