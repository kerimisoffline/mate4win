/*
 * Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
 */

package app.mate4win.gg.data;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

public class CallService {

    private WeakReference<Context> context;
    private Connection connection;
    private JSONObject returnObject;
    private Boolean returnBoolean;
    private JSONArray returnArray;
    private String method;
    private String httpGetURL;
    private Boolean member;

    public void createConnection(){
        if(this.method!=null)
            this.connection=new Connection(this.context.get(),method,httpGetURL,member);
    }

    public CallService(Context context){
        this.context=new WeakReference<Context>(context);
    }

    public JSONObject getService(JSONObject params, String method, String httpGetURL, Boolean member) throws JSONException {
        if (context != null) {
            this.member=member;
            this.method=method;
            this.httpGetURL=httpGetURL;
            if(this.connection!=null)
                this.connection.closeConnection();
            else
                createConnection();
            this.connection.params = params;
            returnObject = this.connection.getConnectionMethod();
        }

        if(returnObject!=null && returnObject.has("result"))
            return returnObject;
        else
            return null;
    }

    public Boolean getServiceBool(JSONObject params, String method, String httpGetURL, Boolean member) throws JSONException {
        if (context != null) {

            this.member=member;
            this.method=method;
            this.httpGetURL=httpGetURL;
            if(this.connection!=null)
                this.connection.closeConnection();
            else
                createConnection();
            this.connection.params = params;
            returnBoolean = this.connection.getConnectionMethodBoolean();
        }

        if(returnBoolean!=null)
            return returnBoolean;
        else
            return false;
    }

    public JSONArray getServiceArray(JSONObject params, String method, String httpGetURL, Boolean member) throws JSONException {
        if (context != null) {

            this.member=member;
            this.method=method;
            this.httpGetURL=httpGetURL;
            if(this.connection!=null)
                this.connection.closeConnection();
            else
                createConnection();
            this.connection.params = params;
            returnArray = this.connection.getConnectionMethodArray();
        }

        return returnArray;

    }

    public Boolean getConnectionCheck(String httpGetURL) throws JSONException {
        if (context != null) {

            this.member=false;
            this.method="GET";
            this.httpGetURL=httpGetURL;
            if(this.connection!=null)
                this.connection.closeConnection();
            else
                createConnection();
            this.connection.params = null;
            returnBoolean = this.connection.getConnectionMethodBoolean();
        }

        if(returnBoolean!=null)
            return returnBoolean;
        else
            return false;
    }

}
