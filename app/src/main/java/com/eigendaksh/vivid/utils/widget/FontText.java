package com.eigendaksh.vivid.utils.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

import com.eigendaksh.vivid.R;

/**
 * Created by Ashutosh Purushottam
 * EigenDaksh - App Development
 */

public class FontText extends TextView {
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public FontText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init(attrs);
    }

    public FontText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    public FontText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);

    }

    public FontText(Context context) {
        super(context);
        init(null);
    }

    private void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.FontText);
            String fontName = a.getString(R.styleable.FontText_typefaceAsset);

            try {
                if (fontName != null) {
                    Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/" + fontName);
                    setTypeface(myTypeface);
                }
            } catch (Exception e) {
                //e.printStackTrace();
            }

            a.recycle();
        }
    }

}
