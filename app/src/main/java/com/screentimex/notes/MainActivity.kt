package com.screentimex.notes


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.TypefaceSpan
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.NavController
import com.google.android.material.appbar.MaterialToolbar
import com.screentimex.notes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var navcontroller : NavController
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar: MaterialToolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        window.apply {
            statusBarColor = resources.getColor(R.color.red, theme)
                // If using Android R (API 30) or higher, use the following instead:
                // setDecorFitsSystemWindows(false)
        }

    }
}