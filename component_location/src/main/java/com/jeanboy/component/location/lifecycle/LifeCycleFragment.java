package com.jeanboy.component.location.lifecycle;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

/**
 * @author caojianbo
 * @since 2019/12/3 15:54
 */
public class LifeCycleFragment extends Fragment {

    private LifeCycle lifeCycle;

    public LifeCycleFragment() {
        this(new LifeCycle());
    }

    public LifeCycleFragment(LifeCycle lifeCycle) {
        this.lifeCycle = lifeCycle;
    }

    public LifeCycle getLifeCycle() {
        return lifeCycle;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        lifeCycle.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lifeCycle.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        lifeCycle.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        lifeCycle.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        lifeCycle.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        lifeCycle.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        lifeCycle.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        lifeCycle.onDetach();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        lifeCycle.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
