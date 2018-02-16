package com.agrim.catchtheball;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextView tv1,tv2,tv3,tv4;
        ImageView im1,im2,im3;
        Button start;
        final Context context=Main2Activity.this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        im1=(ImageView)findViewById(R.id.im2);
        start=(Button)findViewById(R.id.btn2);
        im2=(ImageView)findViewById(R.id.im3);
        im3=(ImageView)findViewById(R.id.im4);
        tv1=(TextView)findViewById(R.id.txt4);
        tv2=(TextView)findViewById(R.id.txt5);
        tv3=(TextView)findViewById(R.id.txt6);
        tv4=(TextView)findViewById(R.id.txt7);
        Picasso.with(context).load(R.drawable.bpink1).fit().centerInside().into(im1);
        Picasso.with(context).load(R.drawable.yball2).fit().centerInside().into(im2);
        Picasso.with(context).load(R.drawable.bball5).fit().centerInside().into(im3);
        start.setOnClickListener(new View.OnClickListener() {
            public Context getContext() {
                return context;
            }

            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(this.getContext(),MainActivity.class));
                finish();
            }
        });
    }
}
