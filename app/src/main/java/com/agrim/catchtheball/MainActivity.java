package com.agrim.catchtheball;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.RunnableFuture;

public class MainActivity extends AppCompatActivity {

    private TextView score,start;
    private ImageView box,yellow,black,pink;
    private Context context=MainActivity.this;
    private int boxy,boxsize,framesize,screenwidth,screenheight,yellowy,yellowx,pinkx,pinky,blackx,blacky,score1=0;
    private Handler handler=new Handler();
    private Timer timer=new Timer();
    private boolean startflag=false;
    private boolean actionflag=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        score=(TextView)findViewById(R.id.txt);
        start=(TextView)findViewById(R.id.txt1);
        black=(ImageView)findViewById(R.id.im2);
        Picasso.with(context).load(R.drawable.bball5).fit().centerInside().into(black);
        yellow=(ImageView)findViewById(R.id.im3);
        Picasso.with(context).load(R.drawable.yball2).fit().centerInside().into(yellow);
        pink=(ImageView)findViewById(R.id.im4);
        Picasso.with(context).load(R.drawable.bpink1).fit().centerInside().into(pink);
        box=(ImageView)findViewById(R.id.im1);
        Picasso.with(context).load(R.drawable.box).fit().centerInside().into(box);
        WindowManager wm=getWindowManager();
        Display disp=wm.getDefaultDisplay();
        Point point=new Point();
        disp.getSize(point);
        screenheight=point.y;
        screenwidth=point.x;
        black.setX(1400);
        black.setY(809);
        yellow.setX(1400);
        yellow.setY(809);
        pink.setX(1480);
        pink.setY(80);
        start.setVisibility(View.VISIBLE);
        score.setText("Score : "+score1);

        Log.v("Main Activity","game starts");
    }

    public void changePos(){
        hitCheck();
        yellowx -= 12;
        if (yellowx<0)
        {
            yellowx=screenwidth+20;
            yellowy=(int)Math.floor(Math.random()*(framesize-yellow.getHeight()));
        }
        yellow.setX(yellowx);
        yellow.setY(yellowy);

        blackx -= 16;
        if (blackx<0)
        {
            blackx=screenwidth+10;
            blacky=(int)Math.floor(Math.random()*(framesize-black.getHeight()));
        }
        black.setX(blackx);
        black.setY(blacky);

        pinkx -= 20;
        if (pinkx<0)
        {
            pinkx=screenwidth+2000;
            pinky=(int)Math.floor(Math.random()*(framesize-pink.getHeight()));
        }
        pink.setX(pinkx);
        pink.setY(pinky);

        if (actionflag==false)
        {
            boxy+=20;
        } else
        {
            boxy-=20;
        }

        if (boxy<0)
            boxy=0;
        if (boxy>framesize-boxsize)
            boxy=framesize-boxsize;
        box.setY(boxy);
        runOnUiThread(new Runnable() {
                          @Override
                          public void run() {
                              score.setText("Score : " + score1);
                          }
        });
    }

    public void hitCheck(){
        int yellowcenterx=yellowx+yellow.getWidth()/2;
        int yellowcentery=yellowy+yellow.getHeight()/2;

        if (0 <= yellowcenterx && yellowcenterx <= boxsize && boxy <= yellowcentery && yellowcentery <= boxy + boxsize)
        {
            yellowx = -10;
            score1 += 10;
        }

        int pinkcenterx=pinkx+pink.getWidth()/2;
        int pinkcentery=pinky+pink.getHeight()/2;

        if (0 <= pinkcenterx && pinkcenterx <= boxsize && boxy <= pinkcentery && pinkcentery <= boxy + boxsize)
        {
            pinkx = -10;
            score1 += 30;
        }

        int blackcenterx=blackx+black.getWidth()/2;
        int blackcentery=blacky+black.getHeight()/2;

        if (0 <= blackcenterx && blackcenterx <= boxsize && boxy <= blackcentery && blackcentery <= boxy + boxsize)
        {
            timer.cancel();
            timer=null;

            Intent intent=new Intent(MainActivity.this, com.agrim.catchtheball.intent.class);
            intent.putExtra("SCORE",score1);
            startActivity(intent);
            finish();
        }

    }

    public boolean onTouchEvent(MotionEvent me){

        Log.v("Main Activity","touch event");

        if (startflag==false)
        {
            startflag=true;
            FrameLayout frame = (FrameLayout)findViewById(R.id.frame);
            framesize=frame.getHeight();

            boxy=(int)box.getY();
            boxsize=box.getHeight();

            start.setVisibility(View.GONE);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    changePos();
                }
            },0,20);

        }else
        {
            if (me.getAction()==MotionEvent.ACTION_DOWN)
            {
                actionflag=true;
            }else if (me.getAction()==MotionEvent.ACTION_UP)
            {
                actionflag=false;
            }
        }

        box.setY(boxy);
        return true;
    }
}