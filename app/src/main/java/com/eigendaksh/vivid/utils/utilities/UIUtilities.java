package com.eigendaksh.vivid.utils.utilities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by Ashutosh Purushottam
 * EigenDaksh - App Development
 */

public class UIUtilities {

    public static void showToast(Context context, String message) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void showDialog(Context context,
                                  String title,
                                  String message,
                                  String posText,
                                  String negText,
                                  int icon,
                                  DialogInterface.OnClickListener posListener,
                                  DialogInterface.OnClickListener negListener) {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(posText, posListener);
        builder.setNegativeButton(negText, negListener);
        builder.setIcon(icon);
        builder.setPositiveButton(posText, posListener);
        builder.setNegativeButton(negText, negListener);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public static void showSingleDialog(Context context,
                                  String title,
                                  String message,
                                  String posText,
                                  int icon,
                                  DialogInterface.OnClickListener posListener) {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(posText, posListener);
        builder.setIcon(icon);
        builder.setPositiveButton(posText, posListener);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


}
