package com.rkbapps.neetflix

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rkbapps.neetflix.activityes.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        CoroutineScope(Dispatchers.Default).launch {
            delay(2000)
            val i = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(i)
            finish()
        }

//        Handler(Looper.getMainLooper()).postDelayed({
//            val i = Intent(this@SplashActivity, MainActivity::class.java)
//            startActivity(i)
//            finish()
//        }, 3000)
    }
}