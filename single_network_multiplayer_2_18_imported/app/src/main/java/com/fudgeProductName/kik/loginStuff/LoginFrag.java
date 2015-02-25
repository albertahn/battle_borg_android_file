package com.fudgeProductName.kik.loginStuff;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.fudgeProductName.kik.LoginHelper;
import com.fudgeProductName.kik.R;

import java.util.ArrayList;

/**
 * Created by albert on 2/20/15.
 */
public class LoginFrag extends Fragment {

    View rootViewman;
    Activity activity;

    final LoginHelper loginHelper = new LoginHelper();

    ArrayList<LoginItem> generatedLoginItem;


    View mainActLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {


        activity = getActivity();

        rootViewman = inflater.inflate(R.layout.login_page, container, false);


        final EditText email = (EditText) rootViewman.findViewById(R.id.user_email);
        final EditText password = (EditText) rootViewman.findViewById(R.id.user_password_input);

        Button loginTanggoalBTN = (Button)  rootViewman.findViewById(R.id.send_apply_btn);

        loginTanggoalBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dialog d = new Dialog(activity);
                d.setTitle(email.getText().toString()+":"+password.getText().toString());
                d.show();

                //loginHelper.loginUser(email.getText().toString(), password.getText().toString(), getActivity());


                ReadFromServer reading = new  ReadFromServer(activity, email.getText().toString(), password.getText().toString());

                reading.execute();

                View mainView = ((Activity) activity).getWindow().getDecorView().findViewById(android.R.id.content);

                FrameLayout myLayout = (FrameLayout) mainView.findViewById(R.id.home_frame);

                View hiddenInfo = activity.getLayoutInflater().inflate(R.layout.coming_soon, myLayout, false);
                myLayout.addView(hiddenInfo);

            } //click view
        }); //onclick listener

        //register new person button
        Button register_new_btn = (Button) rootViewman.findViewById(R.id.register_new_btn);

        register_new_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//fragment for registering
               /* Intent i = new Intent(context,
                        RegisterActivity.class);
                startActivity(i);
*/
            }
        });

        //login_facebook

//button


        return rootViewman;
    }//end oncreate










    public static ArrayList<LoginItem> generateData(String[][] data){
        ArrayList<LoginItem> items = new ArrayList<LoginItem>();

        for (int i =0; i<data.length ; i++){

            items.add(new LoginItem( data[i][1], data[i][2], data[i][3],data[i][4],data[i][5],data[i][6],data[i][7]));

        }
        return items;
    } //end generate


}//end
