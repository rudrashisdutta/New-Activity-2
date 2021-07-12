package com.example.multipleactivities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SecondActivity extends AppCompatActivity {

    TextView friendNameChosen;
    FloatingActionButton backButton;
    public void back(View v){
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        friendNameChosen = findViewById(R.id.friendName);
        backButton =  findViewById(R.id.backButton);
        friendNameChosen.setX(0);
        friendNameChosen.setY(0);
        friendNameChosen.setAlpha(0);
        Intent thisIntent =getIntent();
        String friend = thisIntent.getStringExtra("nameOfFriend");
        friendNameChosen.setText(friend);
        friendNameChosen.animate().scaleX(1).scaleY(1).alpha(0.75f).setDuration(2000).start();
        Log.i("Name:",friend);
        Handler handleRunnable =new Handler(Looper.myLooper());
        final long[] time = {5000};
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    if(time[0] >0){
                        time[0] -=500;
                        handleRunnable.postDelayed(this,500);
                    }
                    else{
                        Log.i("Running","newThread");
                        friendNameChosen.animate().alpha(1).setDuration(1000).start();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        newThread n = new newThread(runnable);
        n.start();
    }


    private static class newThread extends Thread{
        public newThread(@Nullable Runnable target) {
            super(target);
        }
    }
}

/*
   TODO:
       if Phone Number and Previous names available (Show)
 */