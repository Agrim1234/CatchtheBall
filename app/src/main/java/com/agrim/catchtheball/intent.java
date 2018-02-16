package com.agrim.catchtheball;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by agrim on 14/1/18.
 */

public class intent extends AppCompatActivity {
    TextView tv3,tv4;
    Button try1,exit;
    Context context=intent.this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent);

        tv3=(TextView)findViewById(R.id.txt5);
        tv4=(TextView)findViewById(R.id.txt6);
        try1=(Button)findViewById(R.id.btn1);
        exit=(Button)findViewById(R.id.btn2);
        int score=getIntent().getIntExtra("SCORE",0);
        tv3.setText("YOUR SCORE :"+score);

        SharedPreferences settings=getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        int highscore=settings.getInt("HIGH_SCORE",0);

        if (score > highscore)
        {
            tv4.setText("HIGH SCORE :"+score);

            SharedPreferences.Editor editor=settings.edit();
            editor.putInt("HIGH_SCORE",score);
            editor.commit();
        }else
        {
            tv4.setText("HIGH SCORE :"+highscore);
        }
        try1.setOnClickListener(new View.OnClickListener() {
            public Context getContext() {
                return context;
            }

            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(this.getContext(),Main2Activity.class));
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
