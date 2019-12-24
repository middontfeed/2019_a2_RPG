package com.example.unlimitedisland2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.unlimitedisland2.heroInfo.HeroInfo;

public class PickHero extends AppCompatActivity implements View.OnClickListener{

    HeroInfo hero;
    ImageView picture;
    TextView name;
    TextView introduce;
    ImageView skill1pic;
    ImageView skill2pic;
    ImageView skill3pic;
    ImageView skill4pic;
    TextView skill1pro;
    TextView skill2pro;
    TextView skill3pro;
    TextView skill4pro;

    Button pick;
    Button back;

    boolean picked;

    int side;

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
        side=getIntent().getIntExtra("side",1);
        if(side==2){
            setContentView(R.layout.top_pick_hero);
        }
        else
            setContentView(R.layout.activity_pick_hero);

        picture=findViewById(R.id.picture);
        name=findViewById(R.id.name);
        introduce=findViewById(R.id.introduce);
        skill1pic=findViewById(R.id.skill1pic);
        skill2pic=findViewById(R.id.skill2pic);
        skill3pic=findViewById(R.id.skill3pic);
        skill4pic=findViewById(R.id.skill4pic);
        skill1pro=findViewById(R.id.skill1pro);
        skill2pro=findViewById(R.id.skill2pro);
        skill3pro=findViewById(R.id.skill3pro);
        skill4pro=findViewById(R.id.skill4pro);

        pick=findViewById(R.id.pick);pick.setOnClickListener(this);
        back=findViewById(R.id.back);back.setOnClickListener(this);

        hero=(HeroInfo) getIntent().getSerializableExtra("hero");

        picture.setImageResource(hero.picture);
        name.setText(hero.name);
        introduce.setText(hero.introduce);
        skill1pic.setImageResource(hero.skill1_pic);
        skill2pic.setImageResource(hero.skill2_pic);
        skill3pic.setImageResource(hero.skill3_pic);
        skill4pic.setImageResource(hero.skill4_pic);
        skill1pro.setText(hero.skill1_pro);
        skill2pro.setText(hero.skill2_pro);
        skill3pro.setText(hero.skill3_pro);
        skill4pro.setText(hero.skill4_pro);

    }

    @Override
    public void onClick(View v){

        switch (v.getId()){
            case R.id.pick:
                Intent intent=new Intent();
                intent.putExtra("hero",hero);
                setResult(1,intent);
                finish();
                break;
            case R.id.back:
                finish();
                break;
        }
    }

}
