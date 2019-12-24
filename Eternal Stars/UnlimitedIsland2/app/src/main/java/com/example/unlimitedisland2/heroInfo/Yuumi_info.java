package com.example.unlimitedisland2.heroInfo;

import com.example.unlimitedisland2.R;

public class Yuumi_info extends HeroInfo {
    public Yuumi_info(){
        name="魔法猫咪 悠米";
        profile="闪避 生存";
        introduce="作为一只来自班德尔城的魔法猫咪，悠米曾是一名约德尔魔女的守护灵。进入永恒星空后，悠米能藏在星空裂缝中躲避伤害。";
        picture= R.drawable.yuumi;
        skill1_pic=R.drawable.yuumiq;
        skill2_pic=R.drawable.yuumiw;
        skill3_pic=R.drawable.yuumie;
        skill4_pic=R.drawable.yuumir;

        skill1_pro="摸鱼飞弹\nmp-1,atk+1\n悠米发射一道飞弹，对命中的首个单位造成伤害。";
        skill2_pro="悠米出动！\nescape\n悠米躲进虚空，变得不可被选取。";
        skill3_pro="旺盛精力\nmp-1,hp+1,def+1\n治疗悠米并提供一个护盾。";
        skill4_pro="魔典终章\nmp-2,atk+2\n悠米引导伤害波纹，造成伤害。";
    }

    @Override
    public String toString() {
        return "Yuumi";
    }
}
