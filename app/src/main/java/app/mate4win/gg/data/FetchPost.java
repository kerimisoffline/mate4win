/*
 * Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
 */

package app.mate4win.gg.data;

import android.content.Context;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import app.mate4win.gg.model.Cache;
import app.mate4win.gg.model.Groups;
import app.mate4win.gg.model.Post;
import app.mate4win.gg.util.Data;
import app.mate4win.gg.util.NetworkUtil;

import static app.mate4win.gg.data.DataConfig.call_count;
import static app.mate4win.gg.util.Config.isNotNull;


public class FetchPost {
    private Context context;
    private JSONObject object;
    private String category;
    int CallCount = 0;

    public FetchPost(Context ctx,String category) {
        this.context = ctx;
        this.category = category;
    }

    public void Call(){
        if(CallCount >= call_count)
            return;
        CallCount++;
        try {
            String cache = Data.getCache(context).getCache(Cache.KEY_POSTS);
            DataConfig.serviceURL = "http://192.168.1.103:8888/feed.php";
            if(NetworkUtil.isOnline(context)) {
                final JSONObject params = new JSONObject();
                params.put("category", category);
                object = new CallService(context).getService(params, "PUT", null, false);
                if (object == null)
                    Call();
                else {
                    Response(object);
                }
            }else if(isNotNull(cache)){
                object = new JSONObject(cache);
                if(object != null && object.has("result"))
                    Response(object);
            }
        } catch (JSONException ex) {
            //
        }
    }

    private void Response(JSONObject jsonObject){
        try{
            if (jsonObject != null && jsonObject.has("data") && isNotNull(jsonObject.getString("data"))) {
                object = null;
                Data.getCache(context).setCache(Cache.KEY_CATEGORIES, jsonObject.toString());
                JSONArray dataArray = jsonObject.getJSONArray("data");
                if(dataArray.length() > 0){
                    List<Post> posts = new ArrayList<Post>();
                    for(int i=0; i<dataArray.length(); i++){
                        if(dataArray.get(i) != null) {
                            Gson gson = new Gson();
                            posts.add(gson.fromJson(((JSONObject) dataArray.get(i)).toString(), Post.class));
                            gson = null;
                        }
                    }

                    if(posts.size() > 0)
                        Data.posts = posts;

                    posts = null;
                }
                dataArray = null;
            }else{
                Data.selectedCategory = null;
                Data.posts = null;
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            jsonObject = null;
        }
    }
}