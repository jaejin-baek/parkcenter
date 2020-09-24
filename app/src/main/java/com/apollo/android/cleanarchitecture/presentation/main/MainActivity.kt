package com.apollo.android.cleanarchitecture.presentation.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.apollo.android.cleanarchitecture.R
import com.apollo.android.cleanarchitecture.presentation.main.viewer.VideoActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            startActivity(Intent(this, VideoActivity::class.java))
        }
    }
}
