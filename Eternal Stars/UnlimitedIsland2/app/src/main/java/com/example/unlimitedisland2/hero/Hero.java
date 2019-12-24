package com.example.unlimitedisland2.hero;

import android.widget.ImageView;

import com.example.unlimitedisland2.MainActivity;
import com.example.unlimitedisland2.R;
import com.example.unlimitedisland2.util.Utils;

import java.util.Stack;

public abstract class Hero {
    Utils utils;               //引入工具

    protected Stack<ImageView> hp;
    protected Stack<ImageView> mp;
    protected Stack<ImageView> ohp;
    protected Stack<ImageView> omp;

    public int atk;
    public int def;
    public int buff;
    public boolean escape;
    public boolean die;


    protected static int max_hp=4;
    protected static int max_mp=4;

    public Hero(MainActivity activity,ImageView hp1,ImageView hp2,ImageView hp3,ImageView hp4,ImageView mp1,ImageView mp2,ImageView mp3,ImageView mp4){

        utils=new Utils(activity);
        hp=new Stack<ImageView>();
        mp=new Stack<ImageView>();
        ohp=new Stack<ImageView>();
        omp=new Stack<ImageView>();

        hp.push(hp1);
        hp.push(hp2);
        hp.push(hp3);
        hp.push(hp4);


        omp.push(mp4);
        omp.push(mp3);
        omp.push(mp2);
        omp.push(mp1);


        atk =0;
        def =0;
        buff =0;
        escape=false;
        die=false;
    }

    private void pushin(int type){
        ImageView item;
        if(type==1){
            item=ohp.pop();
            utils.fadeIn(item);
            hp.push(item);
        }
        else{
            item=omp.pop();
            utils.fadeIn(item);
            mp.push(item);
        }
    }

    private void popout(int type){
        ImageView item;
        if(type==1){
            item=hp.pop();
            utils.fadeOut(item);
            ohp.push(item);
        }
        else{
            item=mp.pop();
            utils.fadeOut(item);
            omp.push(item);
        }
    }


    abstract public void skill1();
    abstract public void skill2();
    abstract public void skill3();
    abstract public void skill4();
    abstract public boolean[] skillStatus();  //判断技能是否可以使用

    public void addMp(int val){
        while(val-->0&&mp.size()<max_mp){
            pushin(2);
        }
    }

    public boolean deMp(int val){
        if(mp.size()>=val) {
            while(val-->0){
                popout(2);
            }
            return true;
        }
        else{
            return false;  //mp not enough
        }
    }

    public void addHp(int val){
        while(val-->0&&hp.size()<max_hp){
            pushin(1);
        }
    }

    public boolean deHp(int val){
        while(val-->0&&!hp.isEmpty()){
            popout(1);
        }
        if(hp.isEmpty())
            return false;  //died
        return true;
    }



    public void cleanRound(){
        atk =0;
        def =0;
        escape=false;
    }

    public int getHp() {
        return hp.size();
    }

    public int getMp() {
        return mp.size();
    }

    public String status(){
        return "{" +this+
                " hp=" + hp.size() +
                ", mp=" + mp.size() +
                ", atk=" + atk +
                ", def=" + def +
                ", buff=" + buff +
                ", escape=" + escape +
                '}';
    }

}