package com.rkbapps.neetflix.db;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferanceValues {

    public static boolean readNsfw(Context context) {
        SharedPreferences perf = context.getSharedPreferences("isNsfwAllowed", Context.MODE_PRIVATE);
        return perf.getBoolean("nsfw", false);
    }

    public static void writeNsfw(Context context, boolean isAllowed) {
        SharedPreferences mPerf = context.getSharedPreferences("isNsfwAllowed", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mPerf.edit();
        editor.putBoolean("nsfw", isAllowed);
        editor.commit();
    }


}
