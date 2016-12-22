package com.zhang.transitionsample.sharedelement;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zhang.transitionsample.R;
import com.zhang.transitionsample.sample.Sample;


/**
 * A simple {@link Fragment} subclass.
 */
public class SharedElement2Fragment extends Fragment {

    private static final String EXTRA_SAMPLE = "sample";

    public static SharedElement2Fragment newInstance(Sample sample) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_SAMPLE, sample);
        SharedElement2Fragment fragment = new SharedElement2Fragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_shared_element2, container, false);
        Sample sample = (Sample) getArguments().getSerializable(EXTRA_SAMPLE);

        ImageView squareBlue = (ImageView) view.findViewById(R.id.square_blue);
        DrawableCompat.setTint(squareBlue.getDrawable(), sample.getColor());

        return view;
    }

}
