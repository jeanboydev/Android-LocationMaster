package com.jeanboy.component.location.lifecycle;

import android.content.Context;
import android.os.Bundle;

/**
 * @author caojianbo
 * @since 2019/12/3 15:50
 */
public interface LifeCycleListener {

    void onAttach(Context context);

    void onCreate(Bundle savedInstanceState);

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();

    void onDetach();

    void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults);
}
