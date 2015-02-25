package com.fudgeProductName.kik.comment_stuff;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fudgeProductName.kik.R;
import com.fudgeProductName.kik.loginStuff.LoginItem;
import com.fudgeProductName.kik.utils.UserDatabase;

import java.util.ArrayList;

/**
 * Created by albert on 8/25/14.
 */
public class CommentsFrag extends Fragment {

    ArrayList<LoginItem> generatedLoginItem;
    View rootView;
    int RESULT_LOAD_IMAGE= 1;
    Button sendMessageBtn;
    TextView inputMessageEdit;
    String inputText;
    String[] messageArray;
    String userIndex;
    Button imageInsertBtn;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        //if logged in else

        UserDatabase userDatabase= new UserDatabase(getActivity());
        userDatabase.open();
        String[][] data = userDatabase.getData();
        userDatabase.close();


        generatedLoginItem = generateData(data);

        if(generatedLoginItem.toString() !="[]"){
            userIndex = data[0][1];

        }else{

            userIndex ="1";
        }

        rootView = inflater.inflate(R.layout.message_input_part, container, false);

        new LoadComments(userIndex ,rootView, getActivity()).execute();


        //button

        sendMessageBtn = (Button) rootView.findViewById(R.id.send_message_btn);
        inputMessageEdit = (TextView) rootView.findViewById(R.id.input_edit_text);
        imageInsertBtn = (Button) rootView.findViewById(R.id.add_image_btn);


        //onclick
        sendMessageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                inputMessageEdit = (EditText) rootView.findViewById(R.id.input_edit_text);
                //get the text
                inputText = (String) inputMessageEdit.getText().toString();

                if(!inputText.isEmpty()){

                    InputComment inputMessage = new InputComment(getActivity(), userIndex, inputText);
                    inputMessage.execute();

                    inputMessageEdit.setText("");
                    sendMessageBtn.setEnabled(false);

                    ProgressBar progressBar = (ProgressBar) rootView.findViewById(R.id.input_progress_bar);
                    progressBar.setVisibility(View.VISIBLE);
                }//end if


            }
        });

return  rootView;
    }//end on create



    public static ArrayList<LoginItem> generateData(String[][] data){
        ArrayList<LoginItem> items = new ArrayList<LoginItem>();

        for (int i =0; i<data.length ; i++){
            items.add(new LoginItem( data[i][1], data[i][2], data[i][3],data[i][4],data[i][5],data[i][6],data[i][7]));

        }
        return items;
    } //end generate

}
