package com.example.multipleactivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText nameOfFriend;
    private ListView listOfFriends;
    List<String> friendsList;
    ArrayAdapter<? extends String> friendsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listOfFriends = findViewById(R.id.listOfFriends);
        friendsList = new ArrayList<>();
        friendsList.add("Akira");
        friendsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, friendsList);
        listOfFriends.setAdapter(friendsAdapter);

        nameOfFriend = findViewById(R.id.nameOfFriend);
        nameOfFriend.setOnEditorActionListener((v, actionId, event) -> {
            boolean handled = false;
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                sendMessage(nameOfFriend.getText().toString());
                nameOfFriend.setText(null);
                softKeyBoardFocusOff();
                handled = true;
            }
            return handled;
        });
        listOfFriends.setOnItemClickListener((parent, view, position, id) -> {
            TextView v = (TextView) view;
            launchSecondActivity(v.getText().toString());
        });
        listOfFriends.setOnItemLongClickListener((parent, view, position, id) -> {
            //showPopup(view);
            return false;
        });
    }

    private void sendMessage(String message){
        friendsList.add(message);
        friendsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, friendsList);
        listOfFriends.setAdapter(friendsAdapter);
    }
    private void softKeyBoardFocusOff(){
        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        if(imm.isActive()) {
            imm.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
    }
    private void launchSecondActivity(String friendName){
        Intent secondActivity = new Intent(getApplicationContext(),SecondActivity.class);
        secondActivity.putExtra("nameOfFriend",friendName);
        startActivity(secondActivity);
    }



    /*public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu1, popup.getMenu());
        popup.show();
    }*/
}

/*
   TODO:
       onLongPress item leads to a menu with options -> Delete, Rename, Add Phone number etc.
 */