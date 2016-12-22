package com.zhang.transitionsample.animations;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zhang.transitionsample.BaseActivity;
import com.zhang.transitionsample.R;
import com.zhang.transitionsample.databinding.ActivityAnimationsBinding;
import com.zhang.transitionsample.sample.Sample;

public class Animations1Activity extends BaseActivity {

    private ImageView square;
    private ViewGroup viewRoot;
    private boolean sizeChanged;
    private int savedWidth;
    private boolean positionChanged;
    private Sample sample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bindData();
        setupWindowAnimations();
        setupLayout();
        setupToolbar();
    }

    private void bindData() {
        ActivityAnimationsBinding binding =DataBindingUtil.setContentView(this, R.layout.activity_animations);
        sample = (Sample) getIntent().getExtras().getSerializable(EXTRA_SAMPLE);
        binding.setAnimationsSample(sample);
    }

    private void setupWindowAnimations() {
        getWindow().setReenterTransition(new Fade().setDuration(2000));
        getWindow().setEnterTransition(new Explode().setDuration(2000));
    }

    private void setupLayout() {

        square = (ImageView) findViewById(R.id.square_green);
        viewRoot = (ViewGroup) findViewById(R.id.sample3_root);

        findViewById(R.id.sample3_button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeLayout();
            }
        });
        findViewById(R.id.sample3_button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePosition();
            }
        });

        findViewById(R.id.sample3_button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Animations1Activity.this, Animations2Activity.class);
                i.putExtra(EXTRA_SAMPLE, sample);
                transitionTo(i);
            }
        });
    }


    private void changeLayout() {
        TransitionManager.beginDelayedTransition(viewRoot);

        ViewGroup.LayoutParams params = square.getLayoutParams();
        if (sizeChanged) {
            params.width = savedWidth;
        } else {
            savedWidth = params.width;
            params.width = 50;
        }
        sizeChanged = !sizeChanged;
        square.setLayoutParams(params);
    }

    private void changePosition() {
        TransitionManager.beginDelayedTransition(viewRoot);

        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) square.getLayoutParams();
        if (positionChanged) {
            lp.gravity = Gravity.CENTER;
        } else {
            lp.gravity = Gravity.LEFT;
        }
        positionChanged = !positionChanged;
        square.setLayoutParams(lp);
    }
}
