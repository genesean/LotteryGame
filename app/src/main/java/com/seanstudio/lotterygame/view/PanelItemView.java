package com.seanstudio.lotterygame.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.FrameLayout;

import com.seanstudio.lotterygame.R;

public class PanelItemView extends FrameLayout implements ItemView {

    private View overlay;
    private View viewflash;

    public PanelItemView(Context context) {
        this(context, null);
    }

    public PanelItemView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PanelItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.view_panel_item, this);
        overlay = findViewById(R.id.overlay);
        viewflash = findViewById(R.id.viewFlash);
    }

    @Override
    public void setFocus(boolean isFocused, int index) {
        if (overlay != null) {
            System.out.println(isFocused);
            viewflash.setVisibility(GONE);
            overlay.setVisibility(isFocused ? VISIBLE : INVISIBLE);
        }
    }


    public void alphaAnimation() {
        viewflash.setVisibility(VISIBLE);
        AlphaAnimation alphaAnimation1 = new AlphaAnimation(0.1f, 1.0f);
        alphaAnimation1.setDuration(500);
        alphaAnimation1.setRepeatCount(5);
        alphaAnimation1.setRepeatMode(Animation.REVERSE);
        viewflash.setAnimation(alphaAnimation1);
        alphaAnimation1.start();
    }
}
