package com.zhaoliangchen.revealdrawable;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView iv;
    private int[] mImgIds = new int[] { //7个
            R.mipmap.avft,
            R.mipmap.box_stack,
            R.mipmap.bubble_frame,
            R.mipmap.bubbles,
            R.mipmap.bullseye,
            R.mipmap.circle_filled,
            R.mipmap.circle_outline,

            R.mipmap.avft,
            R.mipmap.box_stack,
            R.mipmap.bubble_frame,
            R.mipmap.bubbles,
            R.mipmap.bullseye,
            R.mipmap.circle_filled,
            R.mipmap.circle_outline
    };
    private int[] mImgIds_active = new int[] {
            R.mipmap.avft_active,
            R.mipmap.box_stack_active,
            R.mipmap.bubble_frame_active,
            R.mipmap.bubbles_active,
            R.mipmap.bullseye_active,
            R.mipmap.circle_filled_active,
            R.mipmap.circle_outline_active,
            R.mipmap.avft_active,
            R.mipmap.box_stack_active,
            R.mipmap.bubble_frame_active,
            R.mipmap.bubbles_active,
            R.mipmap.bullseye_active,
            R.mipmap.circle_filled_active,
            R.mipmap.circle_outline_active
    };

    public Drawable[] revealDrawables;
    protected int level = 5000;
    private ZLHorizontalScrollView hzv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        revealDrawables = new Drawable[mImgIds.length];

        for (int i = 0; i < mImgIds.length; i++) {
            ZLRevealDrawable rd = new ZLRevealDrawable(
                    getResources().getDrawable(mImgIds[i]),
                    getResources().getDrawable(mImgIds_active[i]),
                    ZLRevealDrawable.HORIZONTAL);
            revealDrawables[i] = rd;
        }
        hzv = (ZLHorizontalScrollView) findViewById(R.id.hsv);
        hzv.addImageViews(revealDrawables);
    }
}
