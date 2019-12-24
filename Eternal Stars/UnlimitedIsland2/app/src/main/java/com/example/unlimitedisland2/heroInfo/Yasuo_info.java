package com.example.unlimitedisland2.heroInfo;

import com.example.unlimitedisland2.R;

public class Yasuo_info extends HeroInfo{
    public Yasuo_info(){
        name="疾风剑豪 亚索";
        profile="高风险 高收益";
        introduce="亚索是一个百折不屈的艾欧尼亚人，也是一名身手敏捷的御风剑客。这位生性自负的年轻人，最喜欢用e。";
        picture= R.drawable.yasuo;
        skill1_pic=R.drawable.yasuoq;
        skill2_pic=R.drawable.yasuow;
        skill3_pic=R.drawable.yasuoe;
        skill4_pic=R.drawable.yasuor;

        skill1_pro="斩钢闪\nmp-1,atk+1\n向前出剑，呈直线造成伤害。";
        skill2_pro="风之障壁\nmp-1,def+2\n形成一个气流之墙，来阻挡敌方的伤害。";
        skill3_pro="踏前斩\ndef-1,buff+1\n向目标敌人突进，每次施法都会使你下一次造成的伤害提升。";
        skill4_pro="狂风绝息斩\nmp-2,atk+2\n闪烁到一个敌方英雄身边，造成伤害";

    }

    @Override
    public String toString() {
        return "Yasuo";
    }
}
