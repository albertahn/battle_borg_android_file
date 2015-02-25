package com.fudgeProductName.kik.loginStuff;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.TextView;

import com.fudgeProductName.kik.UnityPlayerNativeActivity;
import com.fudgeProductName.kik.utils.UserDatabase;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MIME;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by albert on 10/8/14.
 */
public class ReadFromServer extends AsyncTask<String, Integer, String> {

    Context context;
    String[] params;
    String jsonString;
    //Activity activity;

    HttpResponse httpResponse;
    String user_email, user_password, courseIndex;



    public ReadFromServer(Context context, String email, String password){
        this.context = context;
        this.user_email = email;
        this.user_password = password;

        //this.activity = activity;



    }

    //interface to get result
    @Override
    protected String doInBackground(String...params) {


        try {

            MultipartEntityBuilder builder = MultipartEntityBuilder.create();




            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

            builder.addTextBody("email", user_email, ContentType.create("text/plain", MIME.UTF8_CHARSET));
            builder.addTextBody("password", user_password, ContentType.create("text/plain", MIME.UTF8_CHARSET));



            HttpClient httpClient = new DefaultHttpClient();

            httpClient.getParams().setParameter(CoreProtocolPNames.USER_AGENT,
                    System.getProperty("http.agent"));

            HttpPost httpPost = new HttpPost("http://mobile.coachparrot.com/login/run");

            httpPost.setHeader("Connection", "Keep-Alive");
            httpPost.setHeader("Accept-Charset", HTTP.UTF_8);
            httpPost.setHeader("ENCTYPE", "multipart/form-data");

            httpPost.setEntity( builder.build());

            //httpPost.setEntity( new UrlEncodedFormEntry( nameValuePairs, HTTP.UTF_8 ) );
            httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            String is;

            try {

                BufferedReader rd = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(), "MS949"));
                String body = "";
                String content = "";

                while ((body = rd.readLine()) != null) {
                    content += body + "\n";
                }

                return content;
            } catch (IOException e) {

                // TODO Auto-generated catch block
                e.printStackTrace();

            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
       /* try {
            HttpURLConnection connection;
            OutputStreamWriter request = null;

            String parameters = "email="+params[0]+"&password="+ params[1];

            URL url = new URL("http://mobile.coachparrot.com/login/run");
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestMethod("POST");

            request = new OutputStreamWriter(connection.getOutputStream());
            request.write(parameters);
            request.flush();
            request.close();
            String line = "";
            InputStreamReader isr = new InputStreamReader(connection.getInputStream());
            BufferedReader reader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null)
            {
                sb.append(line + "\n");
            }
            // Response from server after login process will be stored in response variable.
            String response = sb.toString();
            // onPostExecute(response);

            jsonString = response;

            return response;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;*/
    }

    @Override
    protected void onPostExecute(String result) {

        super.onPostExecute(result);
        try {
            JSONObject json = new JSONObject(result);

           /* Dialog d = new Dialog(context);
            d.setTitle("results: "+json.get("username").toString());
            d.show();
*/
            String user_index = json.get("index").toString();
            String username = json.get("username").toString();
            String email = json.get("email").toString();
            String password =  json.get("password").toString();
            String profile_picture = json.get("profile_picture").toString();
            String FID =  json.get("FID").toString();
            String level =  json.get("level").toString();

            //Log.d("jsonfuck", ""+ username + email+password+profile_picture+FID);

            UserDatabase entry = new UserDatabase(context);
            entry.open();
            entry.createEntry(user_index, username, email, password, profile_picture, FID,level);
            entry.close();


            if(user_index!=null){
//logged in



            }else{

                Dialog dick = new Dialog(context);
                dick.setTitle("Email or Passord is wrong");
                TextView tv = new TextView(context);
                tv.setText("");
                dick.setContentView(tv);
                dick.show();
            }


        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }





}// end read