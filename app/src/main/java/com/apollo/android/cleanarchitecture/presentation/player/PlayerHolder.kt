package com.apollo.android.cleanarchitecture.presentation.player

import android.content.Context
import android.net.Uri
import android.util.Log
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.source.ConcatenatingMediaSource
import com.google.android.exoplayer2.source.DynamicConcatenatingMediaSource
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory

class PlayerHolder(
    private val context: Context,
    private val playerView: PlayerView,
    private val playerState: PlayerState
) {
    private val player: ExoPlayer

    private val playListMap = mapOf<SourceType, Uri>(
        SourceType.local_audio to Uri.parse("asset:///audio/file.mp3"),
        SourceType.local_video to Uri.parse("asset:///audio/file.mp3"),
        SourceType.http_audio to Uri.parse("asset:///audio/file.mp3"),
        SourceType.http_video to Uri.parse("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4")
    )

    init {
        player = ExoPlayerFactory.newSimpleInstance(
            context,
            DefaultTrackSelector()
        ).also {
            playerView.player = it
            Log.d("@jj", "[Exo] Simple ExoPlayer created")
        }
    }

    fun start() {
        Log.d("@jj", "[Exo] Start play")
        // Load Media Source
        player.prepare(buildMediaSource(playerState.sourceType))

        with(playerState) {
            // Play
            player.playWhenReady = playerState.playWhenReady

            player.seekTo(window, position)
        }
    }

    fun stop() {
        Log.d("@jj", "[Exo] Stop play")

        with(playerState) {
            window = player.currentWindowIndex
            position = player.currentPosition
            playWhenReady = player.playWhenReady

            Log.d("@jj", "[Exo] currentWindowIndex : ${player.currentWindowIndex}")
            Log.d("@jj", "[Exo] currentWindowIndex : ${player.currentPosition}")
            Log.d("@jj", "[Exo] currentWindowIndex : ${player.playWhenReady}")
        }

        player.stop()
    }

    fun release() {
        Log.d("@jj", "[Exo] release")

        player.release()
    }

    private fun createExtraMediaSource(sourceType: SourceType): MediaSource {
        return ExtractorMediaSource.Factory(DefaultDataSourceFactory(context, "exoplayer"))
            .createMediaSource(playListMap[sourceType])
    }

    private fun buildMediaSource(sourceType: SourceType): MediaSource {
        return when (sourceType) {
            SourceType.playlist -> {
                ConcatenatingMediaSource(
                    createExtraMediaSource(SourceType.http_video),
                    createExtraMediaSource(SourceType.http_video)
                )
            }

            else -> {
                createExtraMediaSource(sourceType)
            }
        }
    }
}

data class PlayerState(
    var window: Int = 0,
    var position: Long = 0,
    var playWhenReady: Boolean = true,
    //var sourceType: SourceType = SourceType.http_video
    var sourceType: SourceType = SourceType.playlist
)

enum class SourceType {
    local_audio, local_video, http_audio, http_video, playlist
}
