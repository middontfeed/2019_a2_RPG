package com.example.unlimitedisland2.hero;

import android.widget.ImageView;

import com.example.unlimitedisland2.MainActivity;
import com.example.unlimitedisland2.R;

public class Zed extends Hero {
    public Zed(MainActivity activity, ImageView hp1, ImageView hp2, ImageView hp3, ImageView hp4, ImageView mp1, ImageView mp2, ImageView mp3, ImageView mp4){
        super(activity,hp1,hp2,hp3,hp4,mp1,mp2,mp3,mp4);

    }
    @Override
    public void skill1() {
        if(deMp(2)==true){
            atk +=2;
        }
    }

    @Override
    public void skill2() {
        if(deMp(1)==true){
            def +=1;
            buff +=1;
        }
    }

    @Override
    public void skill3() {
        if(deMp(1)==true){
            atk +=1;
        }
    }

    @Override
    public void skill4() {
        if(deMp(2)==true){
            buff +=1;
            buff *=2;
            escape=true;
        }
    }

    @Override
    public boolean[] skillStatus(){
        boolean[] res={true,true,true,true,true};
        if(getMp()<2)
            res[1]=false;
        if(getMp()<1)
            res[2]=false;
        if(getMp()<1)
            res[3]=false;
        if(getMp()<2)
            res[4]=false;
        return  res;
    }

    @Override
    public String toString(){
        return "Zed";
    }
}