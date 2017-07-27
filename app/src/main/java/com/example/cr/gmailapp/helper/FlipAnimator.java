package com.example.cr.gmailapp.helper;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.view.View;
import android.view.animation.AnimationSet;

import com.example.cr.gmailapp.R;

public class FlipAnimator
{
    private static String TAG = FlipAnimator.class.getSimpleName();
    private static Animator leftIn;
    private static Animator rightOut;
    private static Animator leftOut;
    private static Animator rightIn;

    public static void flipView(Context context, final View back, final View front, boolean showFront)
    {
        leftIn = AnimatorInflater.loadAnimator(context, R.animator.card_flip_left_in);
        leftOut = AnimatorInflater.loadAnimator(context, R.animator.card_flip_left_out);
        rightIn = AnimatorInflater.loadAnimator(context, R.animator.card_flip_right_in);
        rightOut = AnimatorInflater.loadAnimator(context, R.animator.card_flip_right_out);

        final AnimatorSet showFrontAdmin = new AnimatorSet();
        final AnimatorSet showBackAdmin = new AnimatorSet();

        leftIn.setTarget(back);
        rightOut.setTarget(front);
        showFrontAdmin.playTogether(leftIn, rightOut);

        leftOut.setTarget(back);
        rightIn.setTarget(front);
        showBackAdmin.playTogether(rightIn, leftOut);

        if(showFront)
        {
            showFrontAdmin.start();
        }
        else
        {
            showBackAdmin.start();
        }
    }
}
