package com.zhang.transitionsample.sharedelement;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.transition.ChangeBounds;
import android.transition.Slide;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zhang.transitionsample.R;
import com.zhang.transitionsample.sample.Sample;


/**
 * A simple {@link Fragment} subclass.
 */
public class SharedElement1Fragment extends Fragment {

    private static final String EXTRA_SAMPLE = "sample";

    public static SharedElement1Fragment newInstance(Sample sample) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_SAMPLE, sample);
        SharedElement1Fragment fragment = new SharedElement1Fragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_shared_element1, container, false);
        final Sample sample = (Sample) getArguments().getSerializable(EXTRA_SAMPLE);

        final ImageView squareBlue = (ImageView) view.findViewById(R.id.square_blue);
        DrawableCompat.setTint(squareBlue.getDrawable(), sample.getColor());

        view.findViewById(R.id.sample2_button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNextFragment(sample, squareBlue, false);
            }
        });

        view.findViewById(R.id.sample2_button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNextFragment(sample, squareBlue, true);
            }
        });

        return view;
    }

    private void addNextFragment(Sample sample, ImageView squareBlue, boolean overlap) {

        SharedElement2Fragment sharedElementFragment2 = SharedElement2Fragment.newInstance(sample);

        Slide slideTransition = new Slide(Gravity.RIGHT);
        slideTransition.setDuration(getResources().getInteger(R.integer.anim_duration_medium));

        ChangeBounds changeBoundsTransition = new ChangeBounds();
        changeBoundsTransition.setDuration(getResources().getInteger(R.integer.anim_duration_medium));

        sharedElementFragment2.setEnterTransition(slideTransition);
        sharedElementFragment2.setAllowEnterTransitionOverlap(overlap);
        sharedElementFragment2.setAllowReturnTransitionOverlap(overlap);
        sharedElementFragment2.setSharedElementEnterTransition(changeBoundsTransition);

        getFragmentManager().beginTransaction()
                .replace(R.id.sample2_content, sharedElementFragment2)
                .addToBackStack(null)
                .addSharedElement(squareBlue, getString(R.string.square_blue_name))
                .commit();
    }

}
