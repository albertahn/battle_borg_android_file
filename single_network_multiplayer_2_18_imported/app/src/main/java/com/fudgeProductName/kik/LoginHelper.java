package com.fudgeProductName.kik;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

import com.fudgeProductName.kik.loginStuff.ReadFromServer;
import com.fudgeProductName.kik.utils.JsonReader;
import com.fudgeProductName.kik.utils.UserDatabase;

import org.apache.http.client.HttpClient;
import org.json.JSONObject;

/**
 * Created by albert on 5/1/14.
 */


public class LoginHelper extends Activity{

    FragmentActivity mActivity;
    JsonReader myJSONreader = new JsonReader();
    final static String PROFILEURL = "http://mobile.coachparrot.com/user/user_profile/";

    TextView usernameText;
    HttpClient client;
    JSONObject json;
    JSONObject straightJson;
    Bitmap profilePhotoImage;


    UserDatabase myUserDatabase;

    Context context;


    public String[][] checkLogin(Context context) {

        context = context;

        UserDatabase info = new UserDatabase(context);
        info.open();
        String[][] data = info.getData();
        info.close();

        return data;


    } //end checklogin




    public void logoutUser(final Context context){

        this.context = context;

        //delete data
        UserDatabase info = new UserDatabase(context);
        info.open();
        info.deleteAllData();
        info.close();

       /* LayoutInflater inflater = (LayoutInflater) context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
*/
        //root view from activity
        View rootView = ((Activity) context).getWindow().getDecorView().findViewById(android.R.id.content);


      //  ViewGroup tabView = (ViewGroup)  rootView.findViewById(R.id.profile_tab);
      //  ViewGroup messageView = (ViewGroup)  rootView.findViewById(R.id.message_tab);
       // View layout = (View)  inflater.inflate(R.layout.click_to_login, null);

        //remove everything
      //  tabView.removeAllViewsInLayout();
//add the view
        //tabView.addView(layout);

        //rid of all stuff in message
     //   messageView.removeAllViewsInLayout();

//set so clickable


        //facebook session out
       //logoutFB_session(context);

    } //end logout

    public void loginUser (String email, String password, Context context){

       String[] paramy = new String[2];

        paramy[0] = email;
        paramy[1] = password;

        ReadFromServer reading = new  ReadFromServer(context, email, password);

        reading.execute(paramy);

       // String[][] logindata= checkLogin(context);

    }


//logout facebook
public void logoutFB_session(Context context){
    this.context = context;

    //Session session = Session.getActiveSession();

}// logout fb

}
