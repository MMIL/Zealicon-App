package com.zealicon.android.mmil.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.zealicon.android.mmil.App;

/**
 * Created by Dmytro Denysenko on 5/6/15.
 */
public class CanaroTextView extends TextView {
    public CanaroTextView(Context context) {
        this(context, null);
        setTypeface(App.canaroExtraBold);
    }

    public CanaroTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        setTypeface(App.canaroExtraBold);
    }

    public CanaroTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(App.canaroExtraBold);
    }

}
