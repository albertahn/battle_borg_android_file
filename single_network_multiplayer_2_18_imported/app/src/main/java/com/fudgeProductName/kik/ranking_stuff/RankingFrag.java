package com.fudgeProductName.kik.ranking_stuff;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.fudgeProductName.kik.LoginHelper;
import com.fudgeProductName.kik.R;
import com.fudgeProductName.kik.loginStuff.LoginItem;
import com.fudgeProductName.kik.loginStuff.ReadFromServer;

import java.util.ArrayList;

/**
 * Created by albert on 2/20/15.
 */
public class RankingFrag  extends Fragment {

    View rootViewman;
    Activity activity;

    final LoginHelper loginHelper = new LoginHelper();

    ArrayList<LoginItem> generatedLoginItem;


    View mainActLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {


        activity = getActivity();

        rootViewman = inflater.inflate(R.layout.coming_soon, container, false);



        return rootViewman;
    }//end oncreate



}//end
