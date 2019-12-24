package com.example.unlimitedisland2.hero;

import android.widget.ImageView;

import com.example.unlimitedisland2.MainActivity;

public class Vladimir extends Hero {
    public Vladimir(MainActivity activity, ImageView hp1, ImageView hp2, ImageView hp3, ImageView hp4, ImageView mp1, ImageView mp2, ImageView mp3, ImageView mp4){
        super(activity,hp1,hp2,hp3,hp4,mp1,mp2,mp3,mp4);
    }

    @Override
    public void skill1() {
        if(deMp(1)){
            atk+=1;
            addHp(1);
        }
    }

    @Override
    public void skill2() {
        if(deHp(1))
            escape=true;
    }

    @Override
    public void skill3() {
        if(deMp(1)&&deHp(1)){
            atk +=2;
        }
    }

    @Override
    public void skill4() {
        if(deMp(2)){
            buff+=2;
            addHp(1);
        }
    }

    @Override
    public boolean[] skillStatus(){
        boolean[] res={true,true,true,true,true};
        if(getMp()<1)
            res[1]=false;
        if(getHp()<2)
            res[2]=false;
        if(getMp()<1||getHp()<2)
            res[3]=false;
        if(getMp()<2)
            res[4]=false;
        return  res;
    }

    @Override
    public String toString(){
        return "Vladimir";
    }
}
