package com.jeanboy.component.location.lifecycle;

import android.content.Context;
import android.os.Bundle;

/**
 * @author caojianbo
 * @since 2019/12/3 15:52
 */
public class ActivityFragmentLifeCycle implements LifeCycleListener {

    private LifeCycleListener lifeCycleListener;

    public void addListener(LifeCycleListener listener) {
        this.lifeCycleListener = listener;
    }


    @Override
    public void onAttach(Context context) {
        if (lifeCycleListener != null) {
            lifeCycleListener.onAttach(context);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (lifeCycleListener != null) {
            lifeCycleListener.onCreate(savedInstanceState);
        }
    }

    @Override
    public void onStart() {
        if (lifeCycleListener != null) {
            lifeCycleListener.onStart();
        }
    }

    @Override
    public void onResume() {
        if (lifeCycleListener != null) {
            lifeCycleListener.onResume();
        }
    }

    @Override
    public void onPause() {
        if (lifeCycleListener != null) {
            lifeCycleListener.onPause();
        }
    }

    @Override
    public void onStop() {
        if (lifeCycleListener != null) {
            lifeCycleListener.onStop();
        }
    }

    @Override
    public void onDestroy() {
        if (lifeCycleListener != null) {
            lifeCycleListener.onDestroy();
        }
    }

    @Override
    public void onDetach() {
        if (lifeCycleListener != null) {
            lifeCycleListener.onDetach();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (lifeCycleListener != null) {
            lifeCycleListener.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
