package com.zhang.transitionsample.sample;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhang.transitionsample.animations.Animations1Activity;
import com.zhang.transitionsample.R;
import com.zhang.transitionsample.sharedelement.SharedElementActivity;
import com.zhang.transitionsample.transition.Transition1Activity;
import com.zhang.transitionsample.databinding.RowSampleBinding;

import java.util.List;

/**
 * Created by Mr.Z on 2016/12/8 0008.
 */

public class SamplesRecyclerAdapter extends RecyclerView.Adapter<SamplesRecyclerAdapter.SamplesViewHolder> {

    private final Activity activity;
    private final List<Sample> samples;

    public SamplesRecyclerAdapter(Activity activity, List<Sample> samples) {
        this.activity = activity;
        this.samples = samples;
    }

    @Override
    public SamplesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RowSampleBinding binding = RowSampleBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new SamplesViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(final SamplesViewHolder holder, int position) {
        final Sample sample = samples.get(holder.getAdapterPosition());
        holder.binding.setSample(sample);
        holder.binding.sampleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (holder.getAdapterPosition()) {
                    case 0:
                        transitionToActivity(Transition1Activity.class, sample);
                        break;
                    case 1:
                        transitionToActivity(SharedElementActivity.class, holder, sample);
                        break;
                    case 2:
                        transitionToActivity(Animations1Activity.class, sample);
                        break;

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return samples.size();
    }

    private void transitionToActivity(Class target, Sample sample) {
        final Pair<View, String>[] pairs = TransitionHelper.createSafeTransitionParticipants(activity, true);
        startActivity(target, pairs, sample);
    }

    private void transitionToActivity(Class target, SamplesViewHolder viewHolder, Sample sample) {
        final Pair<View, String>[] pairs = TransitionHelper.createSafeTransitionParticipants(activity, false,
                new Pair<>(viewHolder.binding.sampleIcon, activity.getString(R.string.square_blue_name)),
                new Pair<>(viewHolder.binding.sampleName, activity.getString(R.string.sample_blue_title)));
        startActivity(target, pairs, sample);
    }

    private void startActivity(Class target, Pair<View, String>[] pairs, Sample sample) {
        Intent i = new Intent(activity, target);
        ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, pairs);
        i.putExtra("sample", sample);
        activity.startActivity(i, transitionActivityOptions.toBundle());
    }

    public class SamplesViewHolder extends RecyclerView.ViewHolder {

        RowSampleBinding binding;

        public SamplesViewHolder(View itemView) {
            super(itemView);

            binding = DataBindingUtil.bind(itemView);
        }
    }
}
