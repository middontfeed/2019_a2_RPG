package com.example.unlimitedisland2.heroInfo;

import com.example.unlimitedisland2.R;

public class Zed_info extends HeroInfo {
    public Zed_info(){
        name="影流之主 劫";
        profile="隐忍 一击必杀";
        introduce="彻底抛弃了仁慈与怜悯的劫，是影流教派的领袖。在战争中，绝望指引他开启了神秘的暗影形态。";
        picture= R.drawable.zed;
        skill1_pic=R.drawable.zedq;
        skill2_pic=R.drawable.zedw;
        skill3_pic=R.drawable.zede;
        skill4_pic=R.drawable.zedr;

        skill1_pro="影奥义！诸刃\nmp-2,atk+2\n劫和他的影分身一起将他们的手里剑向前方掷出,造成伤害。";
        skill2_pro="影奥义！分身\nmp-1,def+1,buff+1\n劫的影分身向前突进，下次造成伤害增加";
        skill3_pro="影奥义！鬼斩\nmp-1,atk+1\n劫和他的影分身进行斩击，对附近的敌人造成伤害。";
        skill4_pro="禁奥义！瞬狱影杀阵\nmp-2,escape,(buff+1)*2\n劫变为不可被选取状态，躲避当前攻击。并突进至一名敌方英雄，同时为目标施加死亡印记，下次攻击造成巨额伤害";
    }

    @Override
    public String toString() {
        return "Zed";
    }
}
