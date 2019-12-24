package com.example.unlimitedisland2.heroInfo;

import com.example.unlimitedisland2.R;

public class Vladimir_info extends HeroInfo {
    public Vladimir_info(){
        name="猩红收割者 弗拉基米尔";
        profile="回复 消耗生命值";
        introduce="弗拉基米尔是一个渴望凡人鲜血的魔鬼，他的血巫术能超越自然规律延长他的寿命，这个能力让他吸干敌人的鲜血。";
        picture= R.drawable.vladimir;
        skill1_pic=R.drawable.vladimirq;
        skill2_pic=R.drawable.vladimirw;
        skill3_pic=R.drawable.vladimire;
        skill4_pic=R.drawable.vladimirr;

        skill1_pro="鲜血转换\nmp-1,atk+1,hp+1\n弗拉基米尔从目标敌人身上偷取生命值。";
        skill2_pro="血红之池\nhp-1,escape\n弗拉基米尔潜入血泊之中，变得不可被选取。";
        skill3_pro="血之潮汐\nmp-1,hp-1,atk+2\n弗拉基米尔消耗他的生命值来持续灌注一个蓄血库，蓄血库在释放时会对他身边的敌人造成伤害。";
        skill4_pro="血之瘟疫\nmp-2,buff+2,hp+1\n弗拉基米尔令一片区域感染上剧毒瘟疫，增加受感染敌人所受的伤害,并治疗弗拉基米尔。";
    }

    @Override
    public String toString() {
        return "Vladimir";
    }
}
