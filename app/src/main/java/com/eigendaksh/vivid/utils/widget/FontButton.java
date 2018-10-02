package com.eigendaksh.vivid.utils.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

import com.eigendaksh.vivid.utils.utilities.FontUtil;

/**
 * Created by Ashutosh Purushottam
 * EigenDaksh - App Development
 */

public class FontButton extends Button {

    public FontButton(Context context) {
        super(context);
        FontTextView.init(this, context, null, 0);
    }

    public FontButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        FontTextView.init(this, context, attrs, 0);
    }

    public FontButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        FontTextView.init(this, context, attrs, defStyle);
    }

    public void setTypeface(String typeface) {
        FontUtil.setTypeface(this, typeface);
    }

}