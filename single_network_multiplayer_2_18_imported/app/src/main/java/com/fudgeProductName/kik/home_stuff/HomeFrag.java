package com.fudgeProductName.kik.home_stuff;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.fudgeProductName.kik.R;
import com.fudgeProductName.kik.UnityPlayerNativeActivity;
import com.fudgeProductName.kik.loginStuff.LoginItem;

import java.util.ArrayList;

/**
 * Created by albert on 4/27/14.
 */
public class HomeFrag extends Fragment {

    View rootViewman;
    Activity activity;
    //counter stuff
    int counter;
    ImageButton add;
    ImageButton restart;

    TextView nfDay, hourText;

    EditText achieveNote;

    //wheel

    ActionBar actionBar;
    ArrayList<LoginItem> generatedLoginItem;
    Vibrator vibe;
    String lastDay;
    int mySuccess;
    int plussone;
    Context context = getActivity();

    View mainActLayout;
    boolean running;

    String userLevel;

    int levelRuler;
boolean islogg;
    //FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {


        rootViewman = inflater.inflate(R.layout.home_frag, container, false);



/*
        LoginActivity loginActivity = new LoginActivity();
        islogg= loginActivity.isLoggedIn(getActivity());

//progress_peak
         activity= getActivity();


        DatabaseStuff lastDayInfo = new DatabaseStuff(context);
        lastDayInfo.open();
        lastDay=lastDayInfo.getLastDay();
        lastDayInfo.close();

        try{

            counter = Integer.parseInt(nfDay.getText().toString());

        }catch(NumberFormatException nfe){
            System.out.println("could not parse" + nfe);
        }



        //vibrator
        vibe=(Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

        if(!lastDay.equals("")) {

            mySuccess = Integer.parseInt(lastDay);
            plussone = mySuccess+1;

            if(mySuccess<10){
                nfDay.setText("0"+lastDay);

            }else{
                nfDay.setText(lastDay);
            }



        }else{

            mySuccess =0;
            plussone = 1;

            nfDay.setText("00");
        }
//add button click
        add.setOnClickListener(new

                                       View.OnClickListener() {


                                           @Override
                                           public void onClick (View v){



                                    if(islogg){


                                           }else{//logged

                                                        Intent i = new Intent(getActivity(),
                                                                LoginActivity.class);
                                                        startActivity(i);


                                                    }//else

//quote activity


                                           }
                                       }//end click

        ); //end onclick



        restart.setOnClickListener(new

                                           View.OnClickListener() {
                                               @Override
                                               public void onClick (View view){

                                                   if(islogg) {
                                                   }else{
                                                       Intent i = new Intent(getActivity(),
                                                               LoginActivity.class);
                                                       startActivity(i);
                                                   }


                                               }//end click
                                           }

        );

        //info btn

        ImageButton shareBTN = (ImageButton) rootViewman.findViewById(R.id.setting_btn);

        shareBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Try out this app to become better and help people! http://bit.ly/1lt0Xiv ";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "http://bit.ly/1lt0Xiv");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));


            }
        });

        //hide keyboard
        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

  //check user login and level
        UserDatabase info = new UserDatabase(context);
        info.open();
        String[][] data = info.getData();
        info.close();

        generatedLoginItem = generateData(data);

        TextView my_level = (TextView) rootViewman.findViewById(R.id.my_level);

        if( islogg){

            userLevel = data[0][7];

            Log.e("userLevel", "" + userLevel);

            if(userLevel ==null) {

                my_level.setText("LV.00");

            }else{

                try{

                    int levelInt = Integer.parseInt(userLevel);

                    if(levelInt<10){

                        my_level.setText("LV.0"+userLevel);

                    }else{
                        my_level.setText("LV."+userLevel);

                    }


                }catch (Exception e){

                    Log.e("levelIntfuck", userLevel.toString() + "" + e);


                }


            }//end else





        }else{

            userLevel= "Log In";

            my_level.setText(userLevel);
            //my_level.setBackgroundResource(R.drawable.add_btn);

            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) my_level
                    .getLayoutParams();

           // mlp.setMargins(0, 500, 0, 0);

            String strColor = "#aacccc99";

            my_level.setTextColor(Color.parseColor(strColor));  //cccc99

            //LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            //llp.setMargins(50, 0, 0, 0); // llp.setMargins(left, top, right, bottom);
            //my_level.setLayoutParams(llp);


            my_level.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent i = new Intent(getActivity(),
                            LoginActivity.class);
                    startActivity(i);

                }
            });


        }//else

*/

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
