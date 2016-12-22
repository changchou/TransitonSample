package com.zhang.transitionsample.transition;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Visibility;
import android.view.View;

import com.zhang.transitionsample.BaseActivity;
import com.zhang.transitionsample.R;
import com.zhang.transitionsample.databinding.ActivityTransitionBinding;
import com.zhang.transitionsample.sample.Sample;

public class Transition1Activity extends BaseActivity {

    private Sample sample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bindData();
        setupWindowAnimations();
        setupToolbar();
        setupLayout();
    }

    private void bindData() {
        ActivityTransitionBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_transition);
        sample = (Sample) getIntent().getExtras().getSerializable(EXTRA_SAMPLE);
        binding.setTransition1Sample(sample);
    }

    private void setupWindowAnimations() {
        Visibility enterTransition = buildEnterTransition();
        getWindow().setEnterTransition(enterTransition);
    }

    private Visibility buildEnterTransition() {
        Fade enterTransition = new Fade();
        enterTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));
        // This view will not be affected by enter transition animation
        enterTransition.excludeTarget(R.id.sample1_button3, true);
        return enterTransition;
    }

    private void setupLayout() {
        findViewById(R.id.sample1_button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Transition1Activity.this, Transition2Activity.class);
                i.putExtra(EXTRA_SAMPLE, sample);
                i.putExtra(EXTRA_TYPE, TYPE_PROGRAMMATICALLY);
                transitionTo(i);
            }
        });

        findViewById(R.id.sample1_button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Transition1Activity.this, Transition2Activity.class);
                i.putExtra(EXTRA_SAMPLE, sample);
                i.putExtra(EXTRA_TYPE, TYPE_XML);
                transitionTo(i);
            }
        });

        findViewById(R.id.sample1_button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Transition1Activity.this, Transition3Activity.class);
                i.putExtra(EXTRA_SAMPLE, sample);
                i.putExtra(EXTRA_TYPE, TYPE_PROGRAMMATICALLY);
                transitionTo(i);
            }
        });

        findViewById(R.id.sample1_button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Transition1Activity.this, Transition3Activity.class);
                i.putExtra(EXTRA_SAMPLE, sample);
                i.putExtra(EXTRA_TYPE, TYPE_XML);
                transitionTo(i);
            }
        });

        findViewById(R.id.sample1_button5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getWindow().setReturnTransition(new Slide().setDuration(2000));

                finishAfterTransition();
            }
        });
        findViewById(R.id.sample1_button6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * If no return transition is defined Android will use reversed enter transition
                 * In this case, return transition will be a reversed Slide (defined in buildEnterTransition)
                 */
                finishAfterTransition();
            }
        });
    }
}
