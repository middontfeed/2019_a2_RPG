package com.example.unlimitedisland2.util;

import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.unlimitedisland2.MainActivity;
import com.example.unlimitedisland2.R;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;


public class Utils {
    Animation alphaAnimation1;
    Animation alphaAnimation2;

//    Activity activity=getCurrentActivity();

    public Utils(MainActivity activity){
        alphaAnimation1= AnimationUtils.loadAnimation(activity, R.anim.alphaout);
        alphaAnimation2=AnimationUtils.loadAnimation(activity, R.anim.alphain);
    }

    public void fadeOut(View v){
   //     v.startAnimation(alphaAnimation1);
        v.setAlpha((float)0);
    }

    public void fadeIn(View v){
    //    v.startAnimation(alphaAnimation2);
        v.setAlpha((float)1);
    }


}
