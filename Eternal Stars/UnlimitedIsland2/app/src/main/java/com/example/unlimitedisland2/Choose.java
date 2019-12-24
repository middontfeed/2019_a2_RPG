package com.example.unlimitedisland2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unlimitedisland2.hero.Hero;
import com.example.unlimitedisland2.hero.Yasuo;
import com.example.unlimitedisland2.heroInfo.HeroInfo;
import com.example.unlimitedisland2.heroInfo.Vladimir_info;
import com.example.unlimitedisland2.heroInfo.Yasuo_info;
import com.example.unlimitedisland2.heroInfo.Yuumi_info;
import com.example.unlimitedisland2.heroInfo.Zed_info;

import java.util.ArrayList;
import java.util.List;

public class Choose extends AppCompatActivity {
    private ListView mListView;
    private List<HeroInfo> heros=new ArrayList<HeroInfo>();
    HeroInfo heroInfo;
    private int side;

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
            setContentView(R.layout.top_choose);
        }
        else
            setContentView(R.layout.activity_choose);


        mListView=(ListView)findViewById(R.id.lv);
        mListView.setAdapter(new MyBaseAdapter());

        heros.add(new Yasuo_info());   //注册英雄
        heros.add(new Zed_info());
        heros.add(new Vladimir_info());
        heros.add(new Yuumi_info());


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(Choose.this,PickHero.class);
                intent.putExtra("hero",heros.get(position));
                intent.putExtra("side",side);
                startActivityForResult(intent,1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {//数据拿回来需要一个判断
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){//拿回来的数据不为空
            heroInfo=(HeroInfo) data.getSerializableExtra("hero");
            Intent intent=new Intent();
            intent.putExtra("hero",heroInfo);
            setResult(1,intent);
            finish();
        }
    }

    class MyBaseAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return heros.size();
        }

        @Override
        public Object getItem(int position) {
            return heros.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {//组装数据
            View view= View.inflate(Choose.this,R.layout.list_item,null);
            TextView name=(TextView) view.findViewById(R.id.name);
            TextView profile=(TextView)view.findViewById(R.id.profile);
            ImageView picture=(ImageView)view.findViewById(R.id.picture);
            //组件一拿到，开始组装

            name.setText(heros.get(position).name);
            profile.setText(heros.get(position).profile);
            picture.setImageResource(heros.get(position).picture);
            //组装完开始返回
            return view;
        }
    }

    public void back(View v){
        finish();
    }
}
