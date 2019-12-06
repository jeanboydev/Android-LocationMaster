package com.jeanboy.component.location.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import androidx.annotation.StringRes;

import com.jeanboy.component.location.R;

/**
 * @author caojianbo
 * @since 2019/12/3 16:40
 */
public class DialogHelper {

    public static void showRequestAlert(Context context, final DialogCallback callback) {
        showAlert(context, R.string.dialog_location_title, R.string.dialog_location_message,
                R.string.dialog_location_ok, R.string.dialog_location_cancel, callback);
    }

    public static void showOpenSettingsAlert(Context context, final DialogCallback callback) {
        showAlert(context, R.string.dialog_settings_title, R.string.dialog_settings_message,
                R.string.dialog_settings_ok, R.string.dialog_settings_cancel, callback);
    }

    private static void showAlert(Context context, @StringRes int titleId,
                                  @StringRes int messageId, @StringRes int positiveId,
                                  @StringRes int negativeId, final DialogCallback callback) {
        if (context == null) return;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(titleId)
                .setMessage(messageId)
                .setCancelable(false)
                .setPositiveButton(positiveId,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                if (callback != null) {
                                    callback.onPositiveClick();
                                }
                            }
                        })
                .setNegativeButton(negativeId,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                if (callback != null) {
                                    callback.onNegativeClick();
                                }
                            }
                        });
        AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }
}
