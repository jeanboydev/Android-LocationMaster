package com.jeanboy.component.location.permission;

/**
 * @author caojianbo
 * @since 2019/12/3 15:02
 */
public interface PermissionCallback {

    void onGranted();

    void onDenied(boolean isNeedRationale);
}
