package com.ttit.myapp.activity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.ttit.myapp.R;
import com.ttit.myapp.entity.TabEntity;
import com.ttit.myapp.fragment.ContactFragment;
import com.ttit.myapp.fragment.FindFragment;
import com.ttit.myapp.fragment.HomeFragment;
import com.ttit.myapp.fragment.MyFragment;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    // 存储Fragment的ArrayList，用于ViewPager2的页面内容展示
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    // Tab标题数组，用于设置底部导航的标题
    private String[] mTitles = {"首页", "联系人", "发现", "我的"};
    // 底部导航图标未选中状态的资源ID数组
    private int[] mIconUnselectIds = {
            R.mipmap.home_unselect, R.mipmap.tab_contact_unselect,
            R.mipmap.tab_find_unselect, R.mipmap.my_unselect};
    // 底部导航图标选中状态的资源ID数组
    private int[] mIconSelectIds = {
            R.mipmap.tab_home_select, R.mipmap.tab_contact_select,
            R.mipmap.tab_find_select, R.mipmap.my_selected};

    // 存储每个Tab信息的ArrayList，包括标题和图标ID等
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    // ViewPager2组件，用于展示不同Fragment的内容
    private ViewPager2 homeViewPager2;
    // CommonTabLayout组件，用于展示底部导航栏
    private CommonTabLayout commonTabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 初始化视图分页器和标签布局
        homeViewPager2 = findViewById(R.id.home_vp2);
        commonTabLayout = findViewById(R.id.common_tab_layout);

        // 添加fragment实例到列表中，对应首页的四个板块
        mFragments.add(HomeFragment.newInstance());
        mFragments.add(ContactFragment.newInstance());
        mFragments.add(FindFragment.newInstance());
        mFragments.add(MyFragment.newInstance());

        // 循环遍历，为每个标签页创建TabEntity实例并添加到列表中
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }

        // 设置标签页数据源
        commonTabLayout.setTabData(mTabEntities);
        commonTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                homeViewPager2.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        // 设置视图分页器的适配器
        homeViewPager2.setAdapter(new MyPagerAdapter(getSupportFragmentManager(),getLifecycle()));



//        commonTabLayout.setCurrentTab(1);
    }

    private class MyPagerAdapter extends FragmentStateAdapter {


        public MyPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
            super(fragmentManager, lifecycle);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return mFragments.get(position);
        }

        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }


        @Override
        public int getItemCount() {
            return mFragments.size();
        }
    }
}