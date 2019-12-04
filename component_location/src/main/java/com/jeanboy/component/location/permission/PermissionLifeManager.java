package com.jeanboy.component.location.permission;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.jeanboy.component.location.lifecycle.LifeManager;
import com.jeanboy.component.location.permission.constant.Code;
import com.jeanboy.component.location.permission.constant.Tag;

/**
 * @author caojianbo
 * @since 2019/12/3 15:58
 */
public class PermissionLifeManager extends LifeManager {

    private String permission;
    private PermissionCallback permissionCallback;

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public void setPermissionCallback(PermissionCallback permissionCallback) {
        this.permissionCallback = permissionCallback;
    }

    private void requestPermissions(FragmentActivity activity, String[] permissions,
                                    int requestCode) {
        Fragment lifeFragment = activity.getSupportFragmentManager()
                .findFragmentByTag(Tag.LIEF_MANAGER);
        if (lifeFragment != null) {
            lifeFragment.requestPermissions(permissions, requestCode);
        }
    }

    @Override
    public void onAttach(Context context) {
        Log.e(PermissionLifeManager.class.getSimpleName(), "====onAttach=======");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.e(PermissionLifeManager.class.getSimpleName(), "====onCreate=======");
    }

    @Override
    public void onStart() {
        Log.e(PermissionLifeManager.class.getSimpleName(), "====onStart=======");
        if (TextUtils.isEmpty(permission)) return;
        if (context != null && context instanceof FragmentActivity) {
            requestPermissions((FragmentActivity) context, new String[]{permission}, Code.REQUEST);
        }
    }

    @Override
    public void onResume() {
        Log.e(PermissionLifeManager.class.getSimpleName(), "====onResume=======");
    }

    @Override
    public void onPause() {
        Log.e(PermissionLifeManager.class.getSimpleName(), "====onPause=======");
    }

    @Override
    public void onStop() {
        Log.e(PermissionLifeManager.class.getSimpleName(), "====onStop=======");
    }

    @Override
    public void onDestroy() {
        Log.e(PermissionLifeManager.class.getSimpleName(), "====onDestroy=======");
    }

    @Override
    public void onDetach() {
        Log.e(PermissionLifeManager.class.getSimpleName(), "====onDetach=======");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        Log.e(PermissionLifeManager.class.getSimpleName(), "====onRequestPermissionsResult=======");
        if (Code.REQUEST == requestCode
                && grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (permissionCallback != null) {
                permissionCallback.onGranted();
            }
        } else {
            if (permissionCallback != null) {
                permissionCallback.onDenied();
            }
        }
    }
}
