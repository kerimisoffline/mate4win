/*
 * Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
 */

package app.mate4win.gg.data;

import android.content.Context;
import android.os.Build;
import android.os.StrictMode;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;

import app.mate4win.gg.BuildConfig;
import app.mate4win.gg.util.Config;
import app.mate4win.gg.util.MLog;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Connection {

    private WeakReference<Context> context=null;
    private String method=null;

    private static Response response;
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private String httpGetURL;
    private Boolean member;

    public JSONObject params;
    public JSONObject jsonObject;
    public JSONArray jsonArray;

    public Boolean aBoolean;

    public Connection(Context context, String method, String httpGetURL, Boolean member){

        this.context=new WeakReference<Context>(context);
        this.method=method;
        this.httpGetURL=httpGetURL;
        this.member=member;
        if(DataConfig.httpclient==null) {
            DataConfig.httpclient = DataConfig.getClient(this.context.get());
        }
    }

    public JSONObject getConnectionMethod(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        if(DataConfig.httpclient == null)
            DataConfig.httpclient = DataConfig.getClient(context.get());

        jsonObject = null;

        if(this.method.equals("PUT")){
            try {

                if(params!=null && Config.isNotNull(String.valueOf(params))) {

                    RequestBody body = RequestBody.create(JSON, String.valueOf(params));
                    if(body!=null) {
                        Request request = new Request.Builder()
                                .url(DataConfig.serviceURL.trim())
                                .addHeader("Content-Type", "application/json")
                                .addHeader("Accept", "application/json")
                                .put(body)
                                .build();

                        response = DataConfig.httpclient.newCall(request).execute();
                        if (response != null && response.isSuccessful() && response.code() == 200 && response.body() != null) {
                            String responseText = response.body().string();
                            MLog.setLog("responseText : " + responseText);
                            if (Config.isNotNull(responseText))
                                jsonObject = new JSONObject(responseText);
                        }
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }catch (Exception e) {
                e.printStackTrace();
            }finally {
                closeConnection();
            }
        }
        else if(method.equals("GET")){
            try {

                if(Config.isNotNull(httpGetURL)) {
                    Request request = new Request.Builder()
                            .url(httpGetURL)
                            .addHeader("Content-Type", "application/json")
                            .addHeader("Accept", "application/json")
                            .build();
                    response = DataConfig.httpclient.newCall(request).execute();
                    if (response != null && response.isSuccessful() && response.code() == 200 && response.body() != null) {
                        String responseText = response.body().string();
                        if(Config.isNotNull(responseText))
                            jsonObject = new JSONObject(responseText);
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }catch (Exception e) {
                e.printStackTrace();
            }finally {
                closeConnection();
            }
        }

        return jsonObject;

    }

    public Boolean getConnectionMethodBoolean(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        if(DataConfig.httpclient == null)
            DataConfig.httpclient = DataConfig.getClient(context.get());

        aBoolean = false;

        if(this.method.equals("PUT")){

            try {
                if (params != null && Config.isNotNull(String.valueOf(params))) {
                    RequestBody body = RequestBody.create(JSON, String.valueOf(params));
                    if(body!=null) {
                        Request request = new Request.Builder()
                                .url(DataConfig.serviceURL.trim())
                                .addHeader("Content-Type", "application/json")
                                .addHeader("Accept", "application/json")
                                .put(body)
                                .build();

                        response = DataConfig.httpclient.newCall(request).execute();
                        if (response!=null && response.isSuccessful() && response.code() == 200)
                            aBoolean = true;
                    }
                }

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }catch (Exception e) {
                e.printStackTrace();
            }finally {
                closeConnection();
            }
        }
        else if(method.equals("GET")){
            try {
                if(Config.isNotNull(httpGetURL)) {
                    Request request = new Request.Builder()
                            .url(httpGetURL)
                            .addHeader("Content-Type", "application/json")
                            .addHeader("Accept", "application/json")
                            .build();
                    response = DataConfig.httpclient.newCall(request).execute();
                    if (response!=null && response.isSuccessful() && response.code() == 200)
                        aBoolean = true;
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }catch (Exception e) {
                e.printStackTrace();
            } finally {
                closeConnection();
            }
        }

        return aBoolean;

    }

    public JSONArray getConnectionMethodArray() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        if (DataConfig.httpclient == null)
            DataConfig.httpclient = DataConfig.getClient(context.get());

        jsonArray = null;

        if (this.method.equals("PUT")) {
            try {
                if (params != null && Config.isNotNull(String.valueOf(params))) {
                    RequestBody body = RequestBody.create(JSON, String.valueOf(params));
                    if (body != null) {
                        Request request = new Request.Builder()
                                .url(DataConfig.serviceURL.trim())
                                .addHeader("Content-Type", "application/json")
                                .addHeader("Accept", "application/json")
                                .put(body)
                                .build();
                        response = DataConfig.httpclient.newCall(request).execute();
                        if (response != null && response.isSuccessful() && response.code() == 200) {
                            String responseText = response.body().string();
                            if (Config.isNotNull(responseText))
                                jsonArray = new JSONArray(responseText);
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                closeConnection();
            }

        } else if (method.equals("GET")) {
            try {

                if (Config.isNotNull(httpGetURL)) {
                    Request request = new Request.Builder()
                            .url(httpGetURL)
                            .addHeader("Content-Type", "application/json")
                            .addHeader("Accept", "application/json")
                            .build();
                    response = DataConfig.httpclient.newCall(request).execute();
                    if (response != null && response.isSuccessful() && response.code() == 200) {
                        String responseText = response.body().string();
                        if (Config.isNotNull(responseText))
                            jsonArray = new JSONArray(responseText);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                closeConnection();
            }
        }
        return jsonArray;

    }


    public void closeConnection(){
        try {
            /*if (response != null && response.body() != null)
                response.body().close();

            if (DataConfig.httpclient != null && DataConfig.httpclient.dispatcher()!=null && DataConfig.httpclient.dispatcher().executorService()!=null) {
                DataConfig.httpclient.dispatcher().executorService().shutdown();
            }*/

            //response = null;
        }catch (Exception ex){

        }

    }

}
