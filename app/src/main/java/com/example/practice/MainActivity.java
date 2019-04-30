package com.example.practice;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.example.practice.fragment.Fragment_one;
import com.example.practice.fragment.Fragment_three;
import com.example.practice.fragment.Fragment_two;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //todo  创建Fragment的集合
    List<Fragment> frglist = new ArrayList<>();
     ViewPager viewPagerMain;
     RadioButton poem_rb1;
     RadioButton poem_rb2;
     RadioButton poem_rb3;
     RadioGroup radioGroupMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initAdapter();
        initListener();
        poem_rb1.setChecked(true);
        viewPagerMain.setCurrentItem(0);
    }

    private void initListener() {
        radioGroupMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //checkedId代表的是选中的rb的id
                if(poem_rb1.getId()==checkedId){
                    viewPagerMain.setCurrentItem(0);
                }else if(poem_rb2.getId()==checkedId){
                    viewPagerMain.setCurrentItem(1);
                }else if(poem_rb3.getId()==checkedId){
                    viewPagerMain.setCurrentItem(2);
                }
            }
        });
        viewPagerMain.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if(i == 0){
                    poem_rb1.setChecked(true);
                }else if(i == 1){
                    poem_rb2.setChecked(true);
                }else if(i == 2){
                    poem_rb3.setChecked(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
}
            private void initAdapter() {
        Myadapter myadapter = new Myadapter(getSupportFragmentManager(),frglist);
        viewPagerMain.setAdapter(myadapter);
    }

    private void initData() {
        frglist.add(new Fragment_one());
        frglist.add(new Fragment_two());
        frglist.add(new Fragment_three());
    }

    private void initView() {
        viewPagerMain = (ViewPager) findViewById(R.id.viewPagerMain);
        poem_rb1 = (RadioButton) findViewById(R.id.poem_rb1);
        poem_rb2 = (RadioButton) findViewById(R.id.poem_rb2);
        poem_rb3 = (RadioButton) findViewById(R.id.poem_rb3);
        radioGroupMain = (RadioGroup) findViewById(R.id.radioGroupMain);
    }
}
