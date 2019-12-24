package com.example.unlimitedisland2;

import org.junit.Test;

import com.example.unlimitedisland2.hero.Hero;
import com.example.unlimitedisland2.hero.Yasuo;
import com.example.unlimitedisland2.hero.Zed;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void test() {
        System.out.println("initialize");
        Yasuo player1=new Yasuo();
        Zed player2=new Zed();
        output_status(player1,player2);


    }

    void round(Hero player1,Hero player2){


        player1.skill1();
        player2.skill1();
        judge(player1,player2);
        player1.cleanRound();
        player2.cleanRound();
        output_status(player1,player2);
    }

    void judge(Hero player1, Hero player2){
        int one_atk=player1.getAtk();
        int two_atk=player2.getAtk();
        if(two_atk>0 && !player1.isEscape()){
            if(!player1.deHp(two_atk))
                System.out.println("player1 died");
        }
        if(one_atk>0 && !player2.isEscape()){
            if(!player2.deHp(one_atk))
                System.out.println("player2 died");
        }
    }

    void output_status(Hero player1,Hero player2){
        System.out.println(player1+" "+player1.status());
        System.out.println(player2+" "+player2.status());
        System.out.println();

    }
}