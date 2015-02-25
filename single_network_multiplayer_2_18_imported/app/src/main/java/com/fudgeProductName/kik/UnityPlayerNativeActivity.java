package com.fudgeProductName.kik;

import com.fudgeProductName.kik.comment_stuff.CommentsFrag;
import com.fudgeProductName.kik.home_stuff.HomeFrag;
import com.fudgeProductName.kik.loginStuff.LoginFrag;
import com.fudgeProductName.kik.ranking_stuff.RankingFrag;
import com.fudgeProductName.kik.utils.UserDatabase;
import com.unity3d.player.*;

import android.app.Activity;
import android.app.Dialog;

import android.content.pm.ActivityInfo;
import android.widget.LinearLayout.LayoutParams;
import android.app.Fragment;
import android.app.FragmentTransaction;

import android.app.NativeActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class UnityPlayerNativeActivity extends NativeActivity implements TabHost.OnTabChangeListener
{
    protected UnityPlayer mUnityPlayer;		// don't change the name of this variable; referenced from native code

    TabHost mTabHost;
    Button  Playbtn;
    Activity activity = this;
    // ActionBarActivity actionBarActivity;
    private BroadcastReceiver receiver;
    View tabsView;

    // Setup activity layout
    @Override protected void onCreate (Bundle savedInstanceState)
    {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);


        //setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        getWindow().takeSurface(null);
        setTheme(android.R.style.Theme_NoTitleBar_Fullscreen);
        getWindow().setFormat(PixelFormat.RGBX_8888); // <--- This makes xperia play happy


            mUnityPlayer = new UnityPlayer(activity);




        if (mUnityPlayer.getSettings ().getBoolean ("hide_status_bar", true)){


        }


		/*setContentView(mUnityPlayer);
		mUnityPlayer.requestFocus();*/


        setContentView(R.layout.main);

 // if loggedin show else login

       LoginActivity loginActivity= new LoginActivity();



        if(!loginActivity.isLoggedIn(activity)){

           /* FrameLayout layout = (FrameLayout) findViewById(R.id.home_frame);
            LayoutParams lp = new LayoutParams(LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
            layout.removeAllViews();
*/


            Fragment loginfrag = new LoginFrag();

            //layout.addView(loginfrag, 0, lp);

            getFragmentManager().beginTransaction()
                    .add(R.id.home_frame,  loginfrag)
                    .commit();

        }else{

            Button playbtn = new Button(activity);
            playbtn.setId(R.id.play_button);

            FrameLayout layout = (FrameLayout) findViewById(R.id.home_frame);

            TextView name = new TextView(activity);

            layout.addView(name);

            UserDatabase userDatabase= new UserDatabase(activity);
            userDatabase.open();
            String[][] data = userDatabase.getData();
            userDatabase.close();

            name.setText("welcome "+data[0][2]);

            //layout.addView(playbtn);
/*
            Playbtn = (Button) findViewById(R.id.play_button);


            Playbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    setContentView(mUnityPlayer);
                    mUnityPlayer.requestFocus();

                }
            });
*/

        }//end lese



//set tabhost

        mTabHost = (TabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup();

        setupTab(new TextView(activity), "home", R.drawable.home, R.id.home_tab);
        setupTab(new TextView(activity), "story", R.drawable.chat, R.id.comment_tab);
        setupTab(new TextView(activity), "rank", R.drawable.level, R.id.ranking_tab);

        mTabHost.setOnTabChangedListener(this);

        mTabHost.getTabWidget().setDividerDrawable(null);


        //set fitst page to homefrag

        getFragmentManager().beginTransaction()
                .add(R.id.home_tab,  new HomeFrag())
                .commit();



          Playbtn = (Button) findViewById(R.id.play_button);


        Playbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getWindow ().setFlags (WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN);

                setContentView(mUnityPlayer);
                mUnityPlayer.requestFocus();

               //set the new view with the layout inflater


/*

                int glesMode = mUnityPlayer.getSettings().getInt("gles_mode", 1);
                boolean trueColor8888 = false;
                mUnityPlayer.init(glesMode, trueColor8888);


                // Add the Unity view
                FrameLayout layout = (FrameLayout) findViewById(R.id.home_frame);
               LayoutParams lp = new LayoutParams(LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
                layout.addView(mUnityPlayer.getView(), 0, lp);


                Playbtn.setVisibility(View.INVISIBLE);

                */

                //mTabHost.setVisibility(View.INVISIBLE);
            }
        });

        Button logout_button = (Button) findViewById(R.id.logout_button);

        logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginHelper loginHelper = new LoginHelper();

                loginHelper.logoutUser(activity);


//show login frap
                Fragment loginfrag = new LoginFrag();

                //layout.addView(loginfrag, 0, lp);

                getFragmentManager().beginTransaction()
                        .add(R.id.home_frame,  loginfrag)
                        .commit();

            }
        });

        /*IntentFilter filter = new IntentFilter();
        filter.addAction("SOME_ACTION");
        filter.addAction("SOME_OTHER_ACTION");*/

        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                //do something based on the intent's action

                Toast toast = Toast.makeText(activity,
                        "recived fuckera", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                LinearLayout toastView = (LinearLayout) toast.getView();

                toast.show();

                Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage("com.fudgeProductName.kik");
                startActivity(LaunchIntent);
            }
        };
       // registerReceiver(receiver, filter);


    }//endoncreate




    private void setupTab(final View view, final String tag, int drawableId, int tabcontentid) {


        View tabview = createTabView(mTabHost.getContext(), tag, drawableId);
        TabHost.TabSpec setContent = mTabHost.newTabSpec(tag).setIndicator(tabview).setContent(tabcontentid);

        mTabHost.addTab(setContent);
    }




    private static View createTabView(final Context context, final String text, final int drawableId) {

        View view = LayoutInflater.from(context).inflate(R.layout.tabs_bg, null);

        TextView title= (TextView) view.findViewById(R.id.title);
        title.setText(text);

        //image
        ImageView icon = (ImageView) view.findViewById(R.id.icon);
        icon.setImageResource(drawableId);

        return view;
    }//tabview


    @Override
    public void onTabChanged(String tabId){

        //Context context = mTabHost.getContext();
        String msg = "id of messnea is: "+ mTabHost.getCurrentTab();

        if ("home".equals(tabId)) {

            /*
            getFragmentManager().beginTransaction()
                    .add(R.id.home_tab,  new HomeFrag())
                    .commit();
         */
        } else if ("story".equals(tabId)) {
            System.out.println(msg);
            getFragmentManager().beginTransaction()
                    .add(R.id.comment_tab,  new CommentsFrag())
                    .commit();


        } else if ("rank".equals(tabId)) {
            getFragmentManager().beginTransaction()
                    .add(R.id.ranking_tab,  new RankingFrag())
                    .commit();


        }

    }//end tabchange

    // Quit Unity
    @Override protected void onDestroy ()
    {
       // super.onDestroy();
       // mUnityPlayer.quit();

//        unregisterReceiver(receiver);
       super.onDestroy();
        mUnityPlayer.pause();

       /* Toast toast = Toast.makeText(activity,
                "fuck destrot", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        LinearLayout toastView = (LinearLayout) toast.getView();

        toast.show();
*/
        /* Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage("com.fudgeProductName.kik");
        startActivity(LaunchIntent);*/
     /*   Intent i = new Intent(activity,
                UnityPlayerNativeActivity.class);//LoginActivity.class);

        activity.startActivity(i);
*/

       // setContentView(R.layout.main);  http://answers.unity3d.com/questions/294255/why-does-ondestroy-in-unityplayeractivity-kill-the.html
    }

    // Pause Unity
    @Override protected void onPause()
    {
        super.onPause();
        mUnityPlayer.pause();
    }

    // Resume Unity
    @Override protected void onResume()
    {
        super.onResume();
        mUnityPlayer.resume();
    }

    // This ensures the layout will be correct.
    @Override public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        mUnityPlayer.configurationChanged(newConfig);
    }

    // Notify Unity of the focus change.
    @Override public void onWindowFocusChanged(boolean hasFocus)
    {
        super.onWindowFocusChanged(hasFocus);
        mUnityPlayer.windowFocusChanged(hasFocus);
    }

    // For some reason the multiple keyevent type is not supported by the ndk.
    // Force event injection by overriding dispatchKeyEvent().
    @Override public boolean dispatchKeyEvent(KeyEvent event)
    {
        if (event.getAction() == KeyEvent.ACTION_MULTIPLE)
            return mUnityPlayer.injectEvent(event);
        return super.dispatchKeyEvent(event);
    }

    // Pass any events not handled by (unfocused) views straight to UnityPlayer
    @Override public boolean onKeyUp(int keyCode, KeyEvent event)     { return mUnityPlayer.injectEvent(event); }
    @Override public boolean onKeyDown(int keyCode, KeyEvent event)   { return mUnityPlayer.injectEvent(event); }
    @Override public boolean onTouchEvent(MotionEvent event)          { return mUnityPlayer.injectEvent(event); }
    /*API12*/ public boolean onGenericMotionEvent(MotionEvent event)  { return mUnityPlayer.injectEvent(event); }


    private BroadcastReceiver batteryInfoReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {


            Toast toast = Toast.makeText(activity,
                    "recievedman ", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);

            toast.show();
        }
    };

}//endclass
