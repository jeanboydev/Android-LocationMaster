package com.jeanboy.component.location.lifecycle;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * @author caojianbo
 * @since 2019/12/4 16:52
 */
public abstract class LifeCycleManager implements LifeCycleListener {

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
        supportFragmentGet(fragmentManager);
    }

    private void supportFragmentGet(FragmentManager fragmentManager) {
        LifeCycleFragment current = new LifeCycleFragment();
        Fragment ready = fragmentManager.findFragmentByTag(getTag());
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (ready != null) {
            fragmentTransaction.remove(ready);
        }
        fragmentTransaction.add(current, getTag())
                .commitAllowingStateLoss();
        current.getLifeCycle().addListener(this);
    }

    protected abstract String getTag();

    @Override
    public void onAttach(Context context) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onDetach() {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {

    }
}
