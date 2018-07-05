package com.zhaoliangchen.revealdrawable;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ZLHorizontalScrollView extends HorizontalScrollView implements View.OnTouchListener {
    private LinearLayout container;
    private int centerX;
    private int icon_width;

    public ZLHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void addImageViews(Drawable[] revealDrawables) {
        for (int i=0; i<revealDrawables.length; i++) {
            ImageView imgV = new ImageView(getContext());
            imgV.setImageDrawable(revealDrawables[i]);
            container.addView(imgV);
            if (i==0) {
                imgV.setImageLevel(5000);
            }
        }
        addView(container);
    }

    private void init() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        container = new LinearLayout(getContext());
        container.setLayoutParams(params);
        setOnTouchListener(this);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        View v = container.getChildAt(0);
        icon_width = v.getWidth();
        centerX = getWidth()/2;
        //处理下，中心坐标改为中心图片的左边界
        centerX = centerX - icon_width/2;
        container.setPadding(centerX, 0, centerX, 0);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
            reveal();
        }
        return false;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        reveal();
    }

    private void reveal() {
        int scrollX = getScrollX();
        int index_left = scrollX/icon_width;
        int index_right = index_left+1;
        for (int i=0; i<container.getChildCount(); i++) {
            if (i==index_left || i==index_right) {
                float ratio = 5000f/icon_width;
                ImageView iv_left = (ImageView) container.getChildAt(index_left);
                //scrollX%icon_width:代表滑出去的距离
                //滑出去了icon_width/2  icon_width/2%icon_width
                iv_left.setImageLevel(
                        (int)(5000-scrollX%icon_width*ratio)
                );
                //右边
                if(index_right<container.getChildCount()) {
                    ImageView iv_right = (ImageView) container.getChildAt(index_right);
                    //scrollX%icon_width:代表滑出去的距离
                    //滑出去了icon_width/2  icon_width/2%icon_width
                    iv_right.setImageLevel(
                        (int)(10000-scrollX%icon_width*ratio)
                    );
                }
            } else {
                ImageView iv = (ImageView) container.getChildAt(i);
                iv.setImageLevel(0);
            }
        }
    }
}
