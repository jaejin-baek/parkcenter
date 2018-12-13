package com.apollo.android.cleanarchitecture.presentation.second

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.apollo.android.cleanarchitecture.R
import com.apollo.android.cleanarchitecture.presentation.player.PlayerHolder
import com.apollo.android.cleanarchitecture.presentation.player.PlayerState
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    private lateinit var playerHolder: PlayerHolder

    val state = PlayerState()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_second)

        playerHolder = PlayerHolder(this, videoView, state)
    }

    override fun onStart() {
        super.onStart()

        playerHolder.start()
    }

    override fun onStop() {
        super.onStop()

        playerHolder.stop()
    }

    override fun onDestroy() {
        super.onDestroy()

        playerHolder.release()
    }
}