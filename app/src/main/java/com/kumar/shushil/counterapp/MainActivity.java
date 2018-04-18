package com.kumar.shushil.counterapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
TextView textView;
Button plus,minus,reset;
int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        count=getPreferences(MODE_PRIVATE).getInt("count",0);
        textView=findViewById(R.id.count);
        textView.setText(""+count);
        plus=findViewById(R.id.plus);
        minus=findViewById(R.id.minus);
        reset=findViewById(R.id.reset);
        final Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
        fadeIn.setDuration(1000);

        final Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator()); //and this
        fadeOut.setStartOffset(1000);
        fadeOut.setDuration(1000);

//        final AnimationSet animation = new AnimationSet(false); //change to false
//        animation.addAnimation(fadeIn);
//        animation.addAnimation(fadeOut);
//        //textView.setAnimation(animation);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.startAnimation(fadeOut);
                textView.setText(""+ ++count);
                textView.startAnimation(fadeIn);
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.startAnimation(fadeOut);
                textView.setText(""+ --count);
                textView.startAnimation(fadeIn);           }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPreferences(MODE_PRIVATE).edit().clear().commit();
                count=0;
                textView.startAnimation(fadeOut);
                textView.setText(""+ count);
                textView.startAnimation(fadeIn);
            }
        });
    }

    @Override
    public void onBackPressed() {
        SharedPreferences preferences=getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor= preferences.edit();
        editor.putInt("count",count);
        editor.commit();
        finishAndRemoveTask();
    }
}
