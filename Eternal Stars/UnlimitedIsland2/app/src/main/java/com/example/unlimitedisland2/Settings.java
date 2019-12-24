package com.example.unlimitedisland2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.unlimitedisland2.hero.Hero;
import com.example.unlimitedisland2.hero.Yasuo;
import com.example.unlimitedisland2.heroInfo.HeroInfo;
import com.example.unlimitedisland2.heroInfo.Yasuo_info;
import com.example.unlimitedisland2.heroInfo.Zed_info;

import org.w3c.dom.Text;

import java.util.Random;

public class Settings extends AppCompatActivity implements View.OnClickListener{

    SeekBar seekBar;
    int duration;
    ImageButton topchoose;
    ImageButton bottomchoose;
    Button start;
    Button exit;
    HeroInfo heroInfo;
    HeroInfo tophero=new Zed_info();
    HeroInfo bottomhero= new Yasuo_info();


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
        setContentView(R.layout.activity_settings);

        setvideo();

        topchoose=(ImageButton) findViewById(R.id.topchoose);
        topchoose.setOnClickListener(this);
        bottomchoose=(ImageButton) findViewById(R.id.bottomchoose);
        bottomchoose.setOnClickListener(this);
        start=(Button) findViewById(R.id.start);
        start.setOnClickListener(this);
        exit=(Button) findViewById(R.id.exit);
        exit.setOnClickListener(this);

        seekBar = (SeekBar) findViewById(R.id.progress);


        duration=seekBar.getProgress();
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // 当拖动条的滑块位置发生改变时触发该方法,在这里直接使用参数progress，即当前滑块代表的进度值
                duration=progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.bottomchoose:
                intent=new Intent(this,Choose.class);
                intent.putExtra("side",1);
                startActivityForResult(intent,1);
                break;
            case R.id.topchoose:
                intent=new Intent(this,Choose.class);
                intent.putExtra("side",2);
                startActivityForResult(intent,2);
                break;
            case R.id.start:
                intent=new Intent(this,MainActivity.class);
                intent.putExtra("duration",duration);
                intent.putExtra("bottomhero",bottomhero);
                intent.putExtra("tophero",tophero);
                startActivity(intent);
                break;
            case R.id.exit:
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {//数据拿回来需要一个判断
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){//拿回来的数据不为空
            heroInfo=(HeroInfo) data.getSerializableExtra("hero");
            if(requestCode==1){
                bottomhero=heroInfo;
                bottomchoose.setImageResource(heroInfo.picture);
            }
            else{
                tophero=heroInfo;
                topchoose.setImageResource(heroInfo.picture);
            }
        }
    }

    private void setvideo(){
        //设置视频背景的代码
        final VideoView videoview=(VideoView)findViewById(R.id.videoview);
        final String videopath = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.video).toString();
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
