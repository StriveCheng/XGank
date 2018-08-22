package com.example.strive.xgank.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.MenuItem;

import com.example.strive.xgank.R;
import com.example.strive.xgank.common.base.BaseActivity;
import com.example.strive.xgank.common.base.BaseFragment;
import com.example.strive.xgank.ui.fragment.GanHuoFragment;
import com.example.strive.xgank.ui.fragment.MeiZiFragment;
import com.example.strive.xgank.ui.fragment.MyFragment;
import com.example.strive.xgank.ui.fragment.XianDuFragment;

import java.lang.reflect.Field;

/**
 * Create by xingcc on 2018/8/22
 * main function:
 *
 * @author xingcc
 */
public class MainActivity extends BaseActivity {


    private GanHuoFragment ganHuoFragment;
    private MeiZiFragment meiZiFragment;
    private XianDuFragment xianDuFragment;
    private MyFragment myFragment;
    private Fragment[] mFragments = new Fragment[4];
    private BaseFragment currentFragment;
    private FragmentManager fragmentManager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.gan_huo:
                    if (currentFragment != null) {
                        beginTransaction.hide(currentFragment);
                    }
                    if (ganHuoFragment == null) {
                        ganHuoFragment = GanHuoFragment.newInstance(new Bundle());
                        beginTransaction.add(R.id.fg_container, ganHuoFragment, GanHuoFragment.TAG).commit();
                    } else {
                        if (ganHuoFragment.isAdded()) {
                            beginTransaction.show(ganHuoFragment).commit();
                        } else {
                            beginTransaction.add(R.id.fg_container, ganHuoFragment, GanHuoFragment.TAG).commit();
                        }
                    }
                    currentFragment = ganHuoFragment;

                    return true;
                case R.id.mei_zi:
                    if (currentFragment != null) {
                        beginTransaction.hide(currentFragment);
                    }
                    if (meiZiFragment == null) {
                        meiZiFragment = MeiZiFragment.newInstance(new Bundle());
                        beginTransaction.add(R.id.fg_container, meiZiFragment, MeiZiFragment.TAG).commit();
                    } else {
                        if (meiZiFragment.isAdded()) {
                            beginTransaction.show(meiZiFragment).commit();
                        } else {
                            beginTransaction.add(R.id.fg_container, meiZiFragment, MeiZiFragment.TAG).commit();
                        }
                    }
                    currentFragment = meiZiFragment;
                    return true;
                case R.id.xian_du:
                    if (currentFragment != null) {
                        beginTransaction.hide(currentFragment);
                    }
                    if (xianDuFragment == null) {
                        xianDuFragment = XianDuFragment.newInstance(new Bundle());
                        beginTransaction.add(R.id.fg_container, xianDuFragment, XianDuFragment.TAG).commit();
                    } else {
                        if (xianDuFragment.isAdded()) {
                            beginTransaction.show(xianDuFragment).commit();
                        } else {
                            beginTransaction.add(R.id.fg_container, xianDuFragment, XianDuFragment.TAG).commit();
                        }
                    }
                    currentFragment = xianDuFragment;

                    return true;
                case R.id.wo_de:
                    if (currentFragment != null) {
                        beginTransaction.hide(currentFragment);
                    }
                    if (myFragment == null) {
                        myFragment = MyFragment.newInstance(new Bundle());
                        beginTransaction.add(R.id.fg_container, myFragment, MyFragment.TAG).commit();
                    } else {
                        if (myFragment.isAdded()) {
                            beginTransaction.show(myFragment).commit();
                        } else {
                            beginTransaction.add(R.id.fg_container, myFragment, MyFragment.TAG).commit();
                        }
                    }
                    currentFragment = myFragment;
                    return true;
                default:
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();


    }

    private void initView() {
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        disableShiftMode(navigation);

        ganHuoFragment = GanHuoFragment.newInstance(new Bundle());
        currentFragment = ganHuoFragment;
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        beginTransaction.add(R.id.fg_container, ganHuoFragment, GanHuoFragment.TAG).commit();


    }


    private void showFragment(BaseFragment fragment) {

    }


    @SuppressLint("RestrictedApi")
    public void disableShiftMode(BottomNavigationView view) {
        //获取子View BottomNavigationMenuView的对象
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            //设置私有成员变量mShiftingMode可以修改
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                //去除shift效果
                item.setShiftingMode(false);
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e("BNVHelper", "没有mShiftingMode这个成员变量", e);
        } catch (IllegalAccessException e) {
            Log.e("BNVHelper", "无法修改mShiftingMode的值", e);
        }
    }
}
