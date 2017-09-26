package br.com.dribble.wisley.mydribble.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.transition.Fade;
import android.transition.TransitionInflater;
import android.transition.TransitionSet;
import android.view.View;

import br.com.dribble.wisley.mydribble.R;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;


/**
 * This provides methods to help Activities load their UI.
 */
public class ActivityUtils {

    private static final long MOVE_DEFAULT_TIME = 1000;
    private static final long FADE_DEFAULT_TIME = 300;

    /**
     * The {@code fragment} is added to the container view with id {@code frameId}. The operation is
     * performed by the {@code fragmentManager}.
     */
    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                             @NonNull Fragment fragment, int frameId) {
        checkNotNull(fragmentManager);
        checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }

    /**
     * The {@code fragment} is replaced to the container view with id {@code frameId}. The operation is
     * performed by the {@code fragmentManager}.
     */
    public static void replaceFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                                 @NonNull Fragment fragment, int frameId) {
        checkNotNull(fragmentManager);
        checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(frameId, fragment);
        transaction.commit();
    }

    /**
     * The {@code fragment} is replaced to the container view with id {@code frameId}. The operation is
     * performed by the {@code fragmentManager}.
     */
    public static void replaceAndRemoveFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                                          @NonNull Fragment fragmentInserted, @NonNull Fragment fragmentRemoved, int frameId) {
        checkNotNull(fragmentManager);
        checkNotNull(fragmentInserted);
        checkNotNull(fragmentRemoved);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(frameId, fragmentInserted);
        transaction.remove(fragmentRemoved);
        transaction.commit();
    }

    /**
     * The {@code fragment} is replaced to the container view with id {@code frameId}
     * and added the old fragment to the backstack.
     * The operation is performed by the {@code fragmentManager}.
     */
    public static void replaceFragmentToActivityWithBackStack(Fragment previousFrag,@NonNull FragmentManager fragmentManager,
                                                              @NonNull Fragment fragment, int frameId) {
        checkNotNull(fragmentManager);
        checkNotNull(fragment);

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        // 1. Exit for Previous Fragment
        Fade exitFade = new Fade();
        exitFade.setDuration(FADE_DEFAULT_TIME);
        previousFrag.setExitTransition(exitFade);


        // 2. Shared Elements Transition
        TransitionSet enterTransition = new TransitionSet();
        enterTransition.addTransition(TransitionInflater.from(previousFrag.getActivity()).inflateTransition(android.R.transition.move));
        enterTransition.setDuration(MOVE_DEFAULT_TIME);
        enterTransition.setStartDelay(FADE_DEFAULT_TIME);
        fragment.setSharedElementEnterTransition(enterTransition);

        /*// 3. Enter Transition for New Fragment
        Fade enterFade = new Fade();
        enterFade.setStartDelay(MOVE_DEFAULT_TIME + FADE_DEFAULT_TIME);
        enterFade.setDuration(FADE_DEFAULT_TIME);
        fragment.setEnterTransition(enterFade);*/

        View logo = previousFrag.getActivity().findViewById(R.id.ivThumbnail);
        transaction.addSharedElement(logo, logo.getTransitionName());





        transaction.replace(frameId, fragment);

        transaction.addToBackStack(null);
        transaction.commit();
    }

    /**
     * Este código realiza a troca de activities basiado nos parametros enviados.
     * Caso tenha algum bundle, ele é enviado para a próxima activity.
     * @param context
     * @param activity
     * @param bundle
     */
    public static void goToActivity(Context context, Class activity, Bundle bundle) {

        Intent intent = new Intent(context, activity);
        if (bundle != null)
            intent.putExtras(bundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(intent);
        ((Activity) context).finish();
    }

    /**
     * Este código realiza a troca de activities basiado nos parametros enviados.
     * @param context
     * @param activity
     */
    public static void goToActivity(Context context, Class activity) {

        Integer intentFlag = Intent.FLAG_ACTIVITY_NEW_TASK;
        context.startActivity(new Intent(context, activity).setFlags(intentFlag));
        ((Activity) context).finish();
    }

    public static void goToActivityWithoutFlag(Context context, Class activity) {

        context.startActivity(new Intent(context, activity));
        ((Activity) context).finish();
    }

    public static void goToActivityWithoutFlagWithoutFinish(Context context, Class activity, Bundle bundle) {
        context.startActivity(new Intent(context, activity).putExtras(bundle));
    }




}
