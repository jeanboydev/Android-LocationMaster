package com.jeanboy.component.location.core;

import android.app.Activity;

import androidx.fragment.app.FragmentActivity;

import com.jeanboy.component.location.dialog.DialogCallback;
import com.jeanboy.component.location.dialog.DialogHelper;
import com.jeanboy.component.location.permission.PermissionCallback;
import com.jeanboy.component.location.permission.PermissionHelper;
import com.jeanboy.component.location.settings.SettingsHelper;

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
        DialogHelper.showRequestAlert(getActivity(), new DialogCallback() {
            @Override
            public void onPositiveClick() {
                PermissionHelper.request(getActivity(), permission, new PermissionCallback() {
                    @Override
                    public void onGranted() {
                        onPermissionGranted();
                    }

                    @Override
                    public void onDenied() {
                        onPermissionDeny();
                    }
                });
            }

            @Override
            public void onNegativeClick() {
                onPermissionDeny();
            }
        });
    }

    @Override
    public void onNeedOpenSettings(boolean isGPS) {
        DialogHelper.showOpenSettingsAlert(getActivity(), new DialogCallback() {
            @Override
            public void onPositiveClick() {
                SettingsHelper.toOpenSettings(getActivity());
            }

            @Override
            public void onNegativeClick() {
                onSettingsClosed();
            }
        });
    }
}
