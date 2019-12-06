package com.jeanboy.component.location.permission;

import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.jeanboy.component.location.lifecycle.LifeCycleManager;
import com.jeanboy.component.location.lifecycle.Tag;

/**
 * @author caojianbo
 * @since 2019/12/3 15:58
 */
public class PermissionLifeManager extends LifeCycleManager {

    private final String[] permissions;
    private final PermissionCallback permissionCallback;

    public PermissionLifeManager(String[] permissions, PermissionCallback callback) {
        this.permissions = permissions;
        this.permissionCallback = callback;
    }

    private void requestPermissions(FragmentActivity activity, String[] permissions,
                                    int requestCode) {
        Fragment lifeFragment = activity.getSupportFragmentManager()
                .findFragmentByTag(getTag());
        if (lifeFragment != null) {
            lifeFragment.requestPermissions(permissions, requestCode);
        }
    }

    @Override
    protected String getTag() {
        return Tag.PERMISSION_LIFE_MANAGER;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (permissions == null) return;
        if (context == null) return;
        if (context instanceof FragmentActivity) {
            requestPermissions((FragmentActivity) context, permissions, Code.REQUEST);
        } else {
            // TODO: 2019/12/6 context 不是 activity
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (Code.REQUEST == requestCode
                && grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (permissionCallback != null) {
                permissionCallback.onGranted();
            }
        } else {
            if (context instanceof Activity && permissions.length > 0) {
                if (!ActivityCompat.shouldShowRequestPermissionRationale((Activity) context,
                        permissions[0])) {
                    // 用户点击了不再询问
                    if (permissionCallback != null) {
                        permissionCallback.onDenied(true);
                    }
                    return;
                }
            }
            if (permissionCallback != null) {
                permissionCallback.onDenied(false);
            }
        }
    }
}
