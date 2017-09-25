package br.com.dribble.wisley.mydribble.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;


/**
 * This provides methods to help Activities load their UI.
 */
public class ActivityUtils {

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
    public static void replaceFragmentToActivityWithBackStack(@NonNull FragmentManager fragmentManager,
                                                              @NonNull Fragment fragment, int frameId) {
        checkNotNull(fragmentManager);
        checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
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
