package com.app.assignmentapp.presentation.view.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.app.assignmentapp.R
import com.app.assignmentapp.presentation.utils.Navigator


class SplashActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    val handler = Handler().postDelayed({
//        val intent = Intent(this, MainActivity::class.java)
//        startActivity(intent)
        Navigator.navigateToMainActivity(this)
        finish()
    }, 3000) // 3000 is the delayed time in milliseconds.
}
