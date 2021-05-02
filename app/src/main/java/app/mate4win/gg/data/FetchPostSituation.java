/*
 * Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
 */

package app.mate4win.gg.data;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import app.mate4win.gg.util.Data;
import app.mate4win.gg.util.NetworkUtil;

import static app.mate4win.gg.data.DataConfig.baseURL;
import static app.mate4win.gg.data.DataConfig.call_count;
import static app.mate4win.gg.util.Config.isNotNull;

public class FetchPostSituation {

    private String e_message="";
    private String errorMessage;
    private String successMessage;
    private JSONObject jsonObject;
    int CallCount = 0;

    public void Call(final Context context, String group_id, Boolean situation) {
        if(!NetworkUtil.isOnline(context) || CallCount >= call_count)
            return;

        CallCount++;
        final JSONObject params = new JSONObject();
        try {
            DataConfig.serviceURL = baseURL + "update_group.php";
            params.put("command", "post");
            params.put("group_id", group_id);
            params.put("situation", situation ? "1" : "0");

            jsonObject = new CallService(context).getService(params, "PUT", null, false);
            if(jsonObject == null)
                Call(context, group_id, situation);
            else{
                if(jsonObject.has("result"))
                    Response(jsonObject);
                else
                    Call(context, group_id, situation);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void Response(JSONObject jsonObject){
        try {
            if(jsonObject!=null) {

                if(jsonObject.has("result")) {

                    Boolean result_bool = jsonObject.getBoolean("result");

                    if (!result_bool) {
                        e_message = "";
                        if (jsonObject.has("messages") && isNotNull(jsonObject.getString("messages"))) {
                            String phoneNumberVal = "", passwordVal = "";
                            try {
                                if (jsonObject.getJSONObject("messages").has("phone_number") && isNotNull(jsonObject.getJSONObject("messages").getString("phone_number")))
                                    phoneNumberVal = jsonObject.getJSONObject("messages").getString("phone_number");
                                if (jsonObject.getJSONObject("messages").has("password") && isNotNull(jsonObject.getJSONObject("messages").getString("password")))
                                    passwordVal = jsonObject.getJSONObject("messages").getString("password");


                                if (!phoneNumberVal.equals("")) {
                                    e_message = phoneNumberVal;
                                }
                                if (!passwordVal.equals("")) {
                                    e_message += "\n" + passwordVal;
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();

                                if (e_message.equals("")) {
                                    for (int i = 0; i < jsonObject.getJSONArray("messages").length(); i++) {
                                        e_message += jsonObject.getJSONArray("messages").getString(i) + "\n";
                                    }
                                }

                            }
                            setErrorMessage(e_message);
                        }else if (jsonObject.has("errors") && isNotNull(jsonObject.getString("errors"))) {
                            JSONArray errorsArray = jsonObject.getJSONArray("errors");
                            if(errorsArray!=null && errorsArray.length()>0){
                                for(int i=0; i<errorsArray.length();i++){
                                    e_message += (String) errorsArray.get(i) + "\n";
                                }

                                setErrorMessage(e_message);
                            }
                        }
                    }else if (jsonObject.has("messages") && isNotNull(jsonObject.getString("messages"))) {
                        String s_message="";
                        JSONArray errorsArray = jsonObject.getJSONArray("messages");
                        if(errorsArray!=null && errorsArray.length()>0){
                            for(int i=0; i<errorsArray.length();i++){
                                s_message += (String) errorsArray.get(i) + "\n";
                            }

                            setSuccessMessage(s_message);
                        }
                    }
                }
            }


        } catch (JSONException e) {
            e.printStackTrace();
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

    public void setSuccessMessage(String sccMessage){
        successMessage=sccMessage;
    }

    public String getSuccessMessage(){
        return successMessage;
    }


}