package com.jeanboy.component.location.permission;

import android.content.Context;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

/**
 * @author caojianbo
 * @since 2019/11/22 17:57
 */
public class PermissionHelper {

    public static boolean isGranted(@NonNull Context context, String permission) {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    public static void request(Context context, String permission,
                               PermissionCallback callback) {
        PermissionLifeManager manager = new PermissionLifeManager(new String[]{permission},
                callback);
        manager.bind(context);
    }
}
