package com.xiasuhuei321.loadingdialog;

import android.content.Context;

import com.xiasuhuei321.loadingdialog.manager.StyleManager;
import com.xiasuhuei321.loadingdialog.view.LoadingDialog;

public class XLoading {
    private StyleManager style = StyleManager.getDefault();
    private LoadingDialog dialog = null;

    public void show(Context context) {
        dialog = new LoadingDialog(context);
        LoadingDialog.initStyle(style);
        dialog.show();
    }

    public void dismiss() {
        if (dialog != null) {
            dialog.close();
        }
    }
}
