package com.example.myapplication;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class textView extends android.support.v7.widget.AppCompatTextView {
    public textView(Context context) {
        super(context);
    }

    public textView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public textView(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
//chiu ngan bang doc ô
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int dai=getMeasuredWidth();
        setMeasuredDimension(dai,dai);
    }
}
