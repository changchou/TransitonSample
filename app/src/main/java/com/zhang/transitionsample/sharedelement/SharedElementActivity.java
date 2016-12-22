package com.zhang.transitionsample.sharedelement;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Gravity;

import com.zhang.transitionsample.BaseActivity;
import com.zhang.transitionsample.R;
import com.zhang.transitionsample.databinding.ActivitySharedElementBinding;
import com.zhang.transitionsample.sample.Sample;

public class SharedElementActivity extends BaseActivity {

    private Sample sample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_element);

        bindData();
        setupWindowAnimations();
        setupLayout();
        setupToolbar();
    }

    private void bindData() {
        ActivitySharedElementBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_shared_element);
        sample = (Sample) getIntent().getExtras().getSerializable(EXTRA_SAMPLE);
        binding.setSharedSample(sample);
    }

    private void setupWindowAnimations() {
        // We are not interested in defining a new Enter Transition. Instead we change default transition duration
        getWindow().setEnterTransition(new Fade().setDuration(getResources().getInteger(R.integer.anim_duration_long)));
    }

    private void setupLayout() {
        // Transition for fragment1
        Slide slideTransition = new Slide(Gravity.LEFT);
        slideTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));
        // Create fragment and define some of it transitions
        SharedElement1Fragment sharedElementFragment1 = SharedElement1Fragment.newInstance(sample);
        sharedElementFragment1.setReenterTransition(slideTransition);
        sharedElementFragment1.setExitTransition(slideTransition);
        sharedElementFragment1.setSharedElementEnterTransition(new ChangeBounds());

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.sample2_content, sharedElementFragment1)
                .commit();
    }
}
