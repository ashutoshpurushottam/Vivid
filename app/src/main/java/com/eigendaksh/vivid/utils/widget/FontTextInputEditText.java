package com.eigendaksh.vivid.utils.widget;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.util.AttributeSet;

import com.eigendaksh.vivid.utils.utilities.FontUtil;

/**
 * Created by Ashutosh Purushottam
 * EigenDaksh - App Development
 */

public class FontTextInputEditText extends TextInputEditText {

    public FontTextInputEditText(Context context) {
        super(context);
        FontTextView.init(this, context, null, 0);
    }

    public FontTextInputEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        FontTextView.init(this, context, attrs, 0);
    }

    public FontTextInputEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        FontTextView.init(this, context, attrs, defStyle);
    }

    public void setTypeface(String typeface) {
        FontUtil.setTypeface(this, typeface);
    }

}
