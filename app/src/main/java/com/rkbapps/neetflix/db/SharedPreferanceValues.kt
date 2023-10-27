package com.rkbapps.neetflix.db

import android.content.Context

object SharedPreferanceValues {
    fun readNsfw(context: Context): Boolean {
        val perf = context.getSharedPreferences("isNsfwAllowed", Context.MODE_PRIVATE)
        return perf.getBoolean("nsfw", false)
    }

    fun writeNsfw(context: Context, isAllowed: Boolean) {
        val mPerf = context.getSharedPreferences("isNsfwAllowed", Context.MODE_PRIVATE)
        val editor = mPerf.edit()
        editor.putBoolean("nsfw", isAllowed)
        editor.apply()
    }
}