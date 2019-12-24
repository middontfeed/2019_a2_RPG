package com.example.unlimitedisland2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.unlimitedisland2.hero.Hero;
import com.example.unlimitedisland2.hero.Vladimir;
import com.example.unlimitedisland2.hero.Yasuo;
import com.example.unlimitedisland2.hero.Yuumi;
import com.example.unlimitedisland2.hero.Zed;
import com.example.unlimitedisland2.heroInfo.HeroInfo;
import com.example.unlimitedisland2.util.Utils;

import java.util.Random;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Utils utils;

    //view start

    ImageView outline;

    ImageView bottomhead;
    TextView bottombuff;
    public ImageView bottomhp1;
    public ImageView bottomhp2;
    public ImageView bottomhp3;
    public ImageView bottomhp4;
    public ImageView bottommp1;
    public ImageView bottommp2;
    public ImageView bottommp3;
    public ImageView bottommp4;
    ImageButton bottomskill1;
    ImageButton bottomskill2;
    ImageButton bottomskill3;
    ImageButton bottomskill4;

    Drawable choose;
    ImageButton circle;
    int duration;

    ImageView tophead;
    TextView topbuff;
    public ImageView tophp1;
    public ImageView tophp2;
    public ImageView tophp3;
    public ImageView tophp4;
    public ImageView topmp1;
    public ImageView topmp2;
    public ImageView topmp3;
    public ImageView topmp4;
    ImageButton topskill1;
    ImageButton topskill2;
    ImageButton topskill3;
    ImageButton topskill4;

    //view end

    Animation chooseAnimation;   //animation
    Animation judgeAnimation;
    Animation alphaAnimation1;
    Animation alphaAnimation2;


    int choice_bot;          //logic
    int choice_top;
    Hero player_bot;
    Hero player_top;

    HeroInfo hero_bot;
    HeroInfo hero_top;

    MainActivity mainActivity;

    public MainActivity() {
        mainActivity = this;
    }

    protected void hideBottomUIMenu() {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        hideBottomUIMenu();
        setContentView(R.layout.activity_main);

        setvideo();

        utils=new Utils(mainActivity);

        outline=(ImageView)findViewById(R.id.outline);


        bottomhead=(ImageView) findViewById(R.id.bottomhead);
        bottombuff=(TextView) findViewById(R.id.bottombuff);
        bottomhp1=(ImageView) findViewById(R.id.bottomhp1);
        bottomhp2=(ImageView) findViewById(R.id.bottomhp2);
        bottomhp3=(ImageView) findViewById(R.id.bottomhp3);
        bottomhp4=(ImageView) findViewById(R.id.bottomhp4);
        bottommp1=(ImageView) findViewById(R.id.bottommp1);
        bottommp2=(ImageView) findViewById(R.id.bottommp2);
        bottommp3=(ImageView) findViewById(R.id.bottommp3);
        bottommp4=(ImageView) findViewById(R.id.bottommp4);


        bottomskill1 = (ImageButton) findViewById(R.id.bottomskill1);
        bottomskill1.setOnClickListener(this);
        bottomskill2 = (ImageButton) findViewById(R.id.bottomskill2);
        bottomskill2.setOnClickListener(this);
        bottomskill3 = (ImageButton) findViewById(R.id.bottomskill3);
        bottomskill3.setOnClickListener(this);
        bottomskill4 = (ImageButton) findViewById(R.id.bottomskill4);
        bottomskill4.setOnClickListener(this);

        choose=getDrawable(R.drawable.choose);
        circle = (ImageButton) findViewById(R.id.circle);
        circle.setOnClickListener(this);

        tophead=(ImageView) findViewById(R.id.tophead);
        topbuff=(TextView) findViewById(R.id.topbuff);
        tophp1=(ImageView) findViewById(R.id.tophp1);
        tophp2=(ImageView) findViewById(R.id.tophp2);
        tophp3=(ImageView) findViewById(R.id.tophp3);
        tophp4=(ImageView) findViewById(R.id.tophp4);

        topmp1=(ImageView) findViewById(R.id.topmp1);
        topmp2=(ImageView) findViewById(R.id.topmp2);
        topmp3=(ImageView) findViewById(R.id.topmp3);
        topmp4=(ImageView) findViewById(R.id.topmp4);

        topskill1 = (ImageButton) findViewById(R.id.topskill1);
        topskill1.setOnClickListener(this);
        topskill2 = (ImageButton) findViewById(R.id.topskill2);
        topskill2.setOnClickListener(this);
        topskill3 = (ImageButton) findViewById(R.id.topskill3);
        topskill3.setOnClickListener(this);
        topskill4 = (ImageButton) findViewById(R.id.topskill4);
        topskill4.setOnClickListener(this);

//        alphaAnimation1=AnimationUtils.loadAnimation(this, R.anim.alphaout);
//        alphaAnimation2=AnimationUtils.loadAnimation(this, R.anim.alphain);



        utils.fadeOut(bottommp1);                       //初始化
        utils.fadeOut(bottommp2);
        utils.fadeOut(bottommp3);
        utils.fadeOut(bottommp4);
        utils.fadeOut(topmp1);
        utils.fadeOut(topmp2);
        utils.fadeOut(topmp3);
        utils.fadeOut(topmp4);



        Intent i=getIntent();
        duration=i.getIntExtra("duration",duration);
        rotate();

        hero_bot=(HeroInfo)i.getSerializableExtra("bottomhero");            //绑定英雄
        hero_top=(HeroInfo)i.getSerializableExtra("tophero");
        switch (hero_bot.toString()){
            case "Yasuo":
                player_bot=new Yasuo(mainActivity,bottomhp1,bottomhp2,bottomhp3,bottomhp4,bottommp1,bottommp2,bottommp3,bottommp4);
                adapt(hero_bot,bottomhead,bottomskill1,bottomskill2,bottomskill3,bottomskill4);
                break;
            case "Zed":
                player_bot=new Zed(mainActivity,bottomhp1,bottomhp2,bottomhp3,bottomhp4,bottommp1,bottommp2,bottommp3,bottommp4);
                adapt(hero_bot,bottomhead,bottomskill1,bottomskill2,bottomskill3,bottomskill4);
                break;
            case "Vladimir":
                player_bot=new Vladimir(mainActivity,bottomhp1,bottomhp2,bottomhp3,bottomhp4,bottommp1,bottommp2,bottommp3,bottommp4);
                adapt(hero_bot,bottomhead,bottomskill1,bottomskill2,bottomskill3,bottomskill4);
                break;
            case "Yuumi":
                player_bot=new Yuumi(mainActivity,bottomhp1,bottomhp2,bottomhp3,bottomhp4,bottommp1,bottommp2,bottommp3,bottommp4);
                adapt(hero_bot,bottomhead,bottomskill1,bottomskill2,bottomskill3,bottomskill4);
                break;
        }
        switch (hero_top.toString()){
            case "Yasuo":
                player_top=new Yasuo(mainActivity,tophp1,tophp2,tophp3,tophp4,topmp1,topmp2,topmp3,topmp4);
                adapt(hero_top,tophead,topskill1,topskill2,topskill3,topskill4);
                break;
            case "Zed":
                player_top=new Zed(mainActivity,tophp1,tophp2,tophp3,tophp4,topmp1,topmp2,topmp3,topmp4);
                adapt(hero_top,tophead,topskill1,topskill2,topskill3,topskill4);
                break;
            case "Vladimir":
                player_top=new Vladimir(mainActivity,tophp1,tophp2,tophp3,tophp4,topmp1,topmp2,topmp3,topmp4);
                adapt(hero_top,tophead,topskill1,topskill2,topskill3,topskill4);
                break;
            case "Yuumi":
                player_top=new Yuumi(mainActivity,tophp1,tophp2,tophp3,tophp4,topmp1,topmp2,topmp3,topmp4);
                adapt(hero_top,tophead,topskill1,topskill2,topskill3,topskill4);
                break;
        }

    }

    void adapt(HeroInfo heroInfo,ImageView head,ImageButton skill1,ImageButton skill2,ImageButton skill3,ImageButton skill4){
        head.setImageResource(heroInfo.picture);
        skill1.setImageResource(heroInfo.skill1_pic);
        skill2.setImageResource(heroInfo.skill2_pic);
        skill3.setImageResource(heroInfo.skill3_pic);
        skill4.setImageResource(heroInfo.skill4_pic);
    }





    static boolean judge(Hero player1, Hero player2){

        if(player1.atk>0){
            if(player1.buff>0){
                player1.atk+=player1.buff;
                player1.buff=0;
            }
            if(player2.escape)
                player1.atk=0;
            if(player1.atk>player2.def){
                if(!player2.deHp(player1.atk-player2.def)){   //玩家死亡
                    player2.die=true;
                    return true;
                }
            }

        }
        return false;
    }

    void use_skill(){
        switch(choice_bot){
            case 1:
                player_bot.skill1();
                break;
            case 2:
                player_bot.skill2();
                break;
            case 3:
                player_bot.skill3();
                break;
            case 4:
                player_bot.skill4();
                break;
        }

        switch(choice_top){
            case 1:
                player_top.skill1();
                break;
            case 2:
                player_top.skill2();
                break;
            case 3:
                player_top.skill3();
                break;
            case 4:
                player_top.skill4();
                break;
        }
    }



    public void rotate(){
        chooseAnimation = new RotateAnimation(0,180,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        chooseAnimation.setDuration(duration);
        chooseAnimation.setFillAfter(true);
        chooseAnimation.setInterpolator(new LinearInterpolator());
        chooseAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //    Toast.makeText(MainActivity.this,"选择阶段",Toast.LENGTH_SHORT).show();
                outline.setBackgroundResource(R.drawable.green_outline);
                player_top.addMp(1);
                player_bot.addMp(1);
                reshow();
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                circle.startAnimation(judgeAnimation);

            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        judgeAnimation = new RotateAnimation(180,360,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        judgeAnimation.setDuration(duration);
        judgeAnimation.setFillAfter(true);
        judgeAnimation.setInterpolator(new LinearInterpolator());
        judgeAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //    Toast.makeText(MainActivity.this,"结算阶段",Toast.LENGTH_SHORT).show();
                outline.setBackgroundResource(R.drawable.red_outline);
                topskill1.setEnabled(false);
                topskill2.setEnabled(false);
                topskill3.setEnabled(false);
                topskill4.setEnabled(false);
                bottomskill1.setEnabled(false);
                bottomskill2.setEnabled(false);
                bottomskill3.setEnabled(false);
                bottomskill4.setEnabled(false);
                use_skill();

                judge(player_bot,player_top);
                judge(player_top,player_bot);

                topbuff.setText("buff:"+player_top.buff);
                bottombuff.setText("buff:"+player_bot.buff);
                player_top.cleanRound();
                player_bot.cleanRound();

                if(player_bot.die&&player_top.die)                                          //死亡播放音乐
                    MediaPlayer.create(MainActivity.this,R.raw.aced).start();
                else {
                    if(player_bot.die)
                        diemusic(player_top);
                    if(player_top.die)
                        diemusic(player_bot);
                }
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                if(player_bot.die||player_top.die){
                    System.out.println("game over");
                }
                else
                    circle.startAnimation(chooseAnimation);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        circle.startAnimation(chooseAnimation);
    }

    void diemusic(Hero player){                  //注册配音
        if(player.toString()=="Yasuo"){
            music("yasuo",10);
        }
        if(player.toString()=="Zed"){
            music("zed",6);
        }
        if(player.toString()=="Vladimir"){
            music("vladimir",8);
        }
        if(player.toString()=="Yuumi"){
            music("yuumi",2);
        }
    }

    void music(String hero,int num){
        String fullname;
        Random random = new Random();
        int i = random.nextInt(num) ;
        fullname=hero+String.valueOf(i);
        int Id = getResources().getIdentifier(fullname, "raw", getPackageName());
        MediaPlayer.create(MainActivity.this,Id).start();
    }

    @Override
    public void onClick(View v){         //player choice
        switch (v.getId()){
            case R.id.bottomskill1:
                choice_bot=1;
                bottomskill1.setForeground(choose);
                bottomskill2.setForeground(null);
                bottomskill3.setForeground(null);
                bottomskill4.setForeground(null);
                break;
            case R.id.bottomskill2:
                choice_bot=2;
                bottomskill1.setForeground(null);
                bottomskill2.setForeground(choose);
                bottomskill3.setForeground(null);
                bottomskill4.setForeground(null);
                break;
            case R.id.bottomskill3:
                choice_bot=3;
                bottomskill1.setForeground(null);
                bottomskill2.setForeground(null);
                bottomskill3.setForeground(choose);
                bottomskill4.setForeground(null);
                break;
            case R.id.bottomskill4:
                choice_bot=4;
                bottomskill1.setForeground(null);
                bottomskill2.setForeground(null);
                bottomskill3.setForeground(null);
                bottomskill4.setForeground(choose);
                break;
            case R.id.topskill1:
                choice_top=1;
                topskill1.setForeground(choose);
                topskill2.setForeground(null);
                topskill3.setForeground(null);
                topskill4.setForeground(null);
                break;
            case R.id.topskill2:
                choice_top=2;
                topskill1.setForeground(null);
                topskill2.setForeground(choose);
                topskill3.setForeground(null);
                topskill4.setForeground(null);
                break;
            case R.id.topskill3:
                choice_top=3;
                topskill1.setForeground(null);
                topskill2.setForeground(null);
                topskill3.setForeground(choose);
                topskill4.setForeground(null);
                break;
            case R.id.topskill4:
                choice_top=4;
                topskill1.setForeground(null);
                topskill2.setForeground(null);
                topskill3.setForeground(null);
                topskill4.setForeground(choose);
                break;

            case R.id.circle:
                Log.e("---","test");
                finish();
        }
    }


    public void reshow(){                             //reshow choices
        boolean[] skillstatus_bot=player_bot.skillStatus();
        if(skillstatus_bot[1])
            putin(bottomskill1);
        else
            putout(bottomskill1);
        if(skillstatus_bot[2])
            putin(bottomskill2);
        else
            putout(bottomskill2);
        if(skillstatus_bot[3])
            putin(bottomskill3);
        else
            putout(bottomskill3);
        if(skillstatus_bot[4])
            putin(bottomskill4);
        else
            putout(bottomskill4);

        boolean[] skillstatus_top=player_top.skillStatus();
        if(skillstatus_top[1])
            putin(topskill1);
        else
            putout(topskill1);
        if(skillstatus_top[2])
            putin(topskill2);
        else
            putout(topskill2);
        if(skillstatus_top[3])
            putin(topskill3);
        else
            putout(topskill3);
        if(skillstatus_top[4])
            putin(topskill4);
        else
            putout(topskill4);

    }


    public void putout(View v){
        v.setEnabled(false);
        v.setAlpha((float) 0.5);
    }

    public void putin(View v){
        v.setEnabled(true);
        v.setAlpha((float) 1.0);
    }


    private void setvideo(){
        //设置视频背景的代码
        final VideoView videoview=(VideoView)findViewById(R.id.videoview);
        final String videopath = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.video3).toString();
        videoview.setVideoPath(videopath);
        videoview.start();

        videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener(){
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
                mediaPlayer.setLooping(true);
            }
        });
        videoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                videoview.setVideoPath(videopath);
                videoview.start();
            }
        });

        videoview.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mediaPlayer, int what, int extra) {
                return false;
            }
        });
    }




}
