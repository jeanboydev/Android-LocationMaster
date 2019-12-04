package com.jeanboy.component.location.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.jeanboy.component.location.permission.constant.Tag;

/**
 * @author caojianbo
 * @since 2019/12/4 16:52
 */
public abstract class LifeManager implements LifeCycleListener {

    protected Context context;

    public void bind(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("You cannot start a load on a null Context");
        } else if (!(context instanceof Application)) {
            if (context instanceof FragmentActivity) {
                bind((FragmentActivity) context);
            } else if (context instanceof ContextWrapper) {
                bind(((ContextWrapper) context).getBaseContext());
            }
        }
        this.context = context;
    }

    private void bind(FragmentActivity activity) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        supportFragmentGet(fragmentManager, isActivityVisible(activity));
    }

    private void supportFragmentGet(FragmentManager fragmentManager, boolean activityVisible) {
        SupportManagerFragment current = new SupportManagerFragment();
        fragmentManager.beginTransaction()
                .add(current, Tag.LIEF_MANAGER)
                .commitAllowingStateLoss();
        current.getLifeCycle().addListener(this);
//        if (activityVisible) {
//            current.getLifeCycle().onStart();
//        }
    }

    private boolean isActivityVisible(Activity activity) {
        return !activity.isFinishing();
    }
}
