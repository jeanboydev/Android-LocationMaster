package com.jeanboy.component.location.lifecycle;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.jeanboy.component.location.permission.PermissionLifeManager;

/**
 * @author caojianbo
 * @since 2019/12/3 15:54
 */
public class SupportManagerFragment extends Fragment {

    private ActivityFragmentLifeCycle lifeCycle;

    public SupportManagerFragment() {
        this(new ActivityFragmentLifeCycle());
    }

    public SupportManagerFragment(ActivityFragmentLifeCycle lifeCycle) {
        this.lifeCycle = lifeCycle;
    }

    public ActivityFragmentLifeCycle getLifeCycle() {
        return lifeCycle;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e(SupportManagerFragment.class.getSimpleName(), "====onAttach=======");
        lifeCycle.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(SupportManagerFragment.class.getSimpleName(), "====onCreate=======");
        lifeCycle.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e(SupportManagerFragment.class.getSimpleName(), "====onStart=======");
        lifeCycle.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(SupportManagerFragment.class.getSimpleName(), "====onResume=======");
        lifeCycle.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(SupportManagerFragment.class.getSimpleName(), "====onPause=======");
        lifeCycle.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e(SupportManagerFragment.class.getSimpleName(), "====onStop=======");
        lifeCycle.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(SupportManagerFragment.class.getSimpleName(), "====onDestroy=======");
        lifeCycle.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e(SupportManagerFragment.class.getSimpleName(), "====onDetach=======");
        lifeCycle.onDetach();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.e(SupportManagerFragment.class.getSimpleName(), "====onRequestPermissionsResult" +
                "=======");
        lifeCycle.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
