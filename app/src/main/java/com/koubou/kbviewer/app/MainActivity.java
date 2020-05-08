package com.koubou.kbviewer.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.koubou.kbviewer.R;

public class MainActivity extends AppCompatActivity {


    FrameLayout fragments_container;
    Fragment mylistFragment;
    Fragment historyFragment;

    NavigationView mNavigationView;

    Toolbar toolbar_main;
    DrawerLayout drawlayout_main;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDrawLayoutNav();
        initFragments();
        initDrawLayoutNavItemsClick();

        switchFragment(mylistFragment);

    }



    void initDrawLayoutNav(){
        drawlayout_main=(DrawerLayout)findViewById(R.id.drawlayout_main);
        toolbar_main=(Toolbar)findViewById(R.id.toolbar_main);
        drawlayout_main.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        setSupportActionBar(toolbar_main);
        toolbar_main.setNavigationIcon(R.drawable.icon_navigation);
        toolbar_main.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawlayout_main.openDrawer(Gravity.LEFT);
            }
        });
    }

    void initFragments(){
        mylistFragment=new MyListFragment(toolbar_main);
        historyFragment=new HistoryFragment(toolbar_main);
    }

    void initDrawLayoutNavItemsClick(){
        mNavigationView=(NavigationView)findViewById(R.id.navigation_view);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                //在这里处理item的点击事件
                switch (item.getItemId()){
                    case R.id.navigation_item_mylist:
                        switchFragment(mylistFragment);
                        break;
                    case R.id.navigation_item_history:
                        switchFragment(historyFragment);
                        break;
                }
                drawlayout_main.closeDrawers();
                return true;
            }
        });
    }

    void switchFragment(Fragment fragment){
        FragmentManager supportFragmentManager;
        FragmentTransaction fragmentTransaction;
        //获取管理者
        supportFragmentManager = getSupportFragmentManager();
        //开启事务
        fragmentTransaction =  supportFragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in_short,R.anim.fade_exit_short);
        fragmentTransaction.replace(R.id.fragments_container,fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {

        if (drawlayout_main!=null){
            if (drawlayout_main.isDrawerOpen(Gravity.LEFT)){
                drawlayout_main.closeDrawers();
                return;
            }
        }

        super.onBackPressed();
    }
}
