package com.yuni.sarangbang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.media.Rating;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    //FrameLayout에 각 메뉴의 fragment를 바꿔줌
    private FragmentManager fragementManager=getSupportFragmentManager();
    //메뉴에 들어갈 fragement들
    private Menu1Fragment menu1Fragment = new Menu1Fragment();
    private Menu2Fragment menu2Fragment=new Menu2Fragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //tool bar를 app bar로 지정
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        //첫 화면 지정
        FragmentTransaction transaction = fragementManager.beginTransaction();
        transaction.replace(R.id.frame_layout,menu1Fragment).commitAllowingStateLoss();

        //bottomNavigationView의 아이템이 선택될 때 호출될 리스너 등록
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = fragementManager.beginTransaction();
                switch (item.getItemId()){
                    case R.id.navigation_menu1:{
                        transaction.replace(R.id.frame_layout,menu1Fragment).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.navigation_menu2:{
                        transaction.replace(R.id.frame_layout,menu2Fragment).commitAllowingStateLoss();
                        break;
                    }
                }
                return true;
            }
        });
    }
}
