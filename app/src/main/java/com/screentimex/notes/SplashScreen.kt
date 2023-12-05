package com.screentimex.notes

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import androidx.core.content.ContextCompat

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        window.apply {
            statusBarColor = resources.getColor(R.color.red, theme)
            // If using Android R (API 30) or higher, use the following instead:
            // setDecorFitsSystemWindows(false)
        }

        supportActionBar?.hide()
        Handler(Looper.getMainLooper()).postDelayed(Runnable{
            startActivity(Intent(this,MainActivity::class.java))
            finishAffinity()
        },2000)

    }
}