package com.jeanboy.component.location.core;

import androidx.fragment.app.FragmentActivity;

import com.jeanboy.component.location.dialog.DialogCallback;
import com.jeanboy.component.location.dialog.DialogHelper;
import com.jeanboy.component.location.permission.PermissionCallback;
import com.jeanboy.component.location.permission.PermissionHelper;
import com.jeanboy.component.location.utils.SettingsUtil;

/**
 * @author caojianbo
 * @since 2019/12/3 16:46
 */
public abstract class LocationAdapter implements LocationCallback {

    public abstract FragmentActivity getActivity();

    public abstract void onPermissionGranted();

    public abstract void onPermissionDeny();

    public abstract void onSettingsClosed();

    @Override
    public void onNeedPermission(final String permission) {
        PermissionHelper.request(getActivity(), permission, new PermissionCallback() {
            @Override
            public void onGranted() {
                onPermissionGranted();
            }

            @Override
            public void onDenied(boolean isNeedRationale) {
                if (isNeedRationale) {
                    toShowAlert();
                    return;
                }
                onPermissionDeny();
            }
        });
    }

    @Override
    public void onNeedOpenSettings(String provider) {
        DialogHelper.showOpenSettingsAlert(getActivity(), new DialogCallback() {
            @Override
            public void onPositiveClick() {
                SettingsUtil.toOpenLocationSettings(getActivity());
            }

            @Override
            public void onNegativeClick() {
                onSettingsClosed();
            }
        });
    }

    private void toShowAlert() {
        DialogHelper.showRequestAlert(getActivity(), new DialogCallback() {
            @Override
            public void onPositiveClick() {
                SettingsUtil.toOpenApplicationSettings(getActivity());
            }

            @Override
            public void onNegativeClick() {
                onPermissionDeny();
            }
        });
    }
}
