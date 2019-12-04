package com.jeanboy.component.location.permission;

import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

/**
 * @author caojianbo
 * @since 2019/11/22 17:57
 */
public class PermissionHelper {

    public static boolean isGranted(Context context, String permission) {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    public static void request(FragmentActivity activity, String permission,
                               PermissionCallback callback) {
        PermissionLifeManager requestManager = new PermissionLifeManager();
        requestManager.setPermissionCallback(callback);
        requestManager.setPermission(permission);
        requestManager.bind(activity);
    }
}
