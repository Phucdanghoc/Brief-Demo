package com.example.demo.utils

import android.content.Context

import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

class VideoPlayer(private val context: Context, private val videoView: PlayerView) {


    private val player: ExoPlayer = ExoPlayer.Builder(context).build()

    init {
        videoView.player = player
    }

    fun playVideo(videoUrl: String) {
        val mediaItem = MediaItem.fromUri(videoUrl)
        player.setMediaItem(mediaItem)
        player.repeatMode = Player.REPEAT_MODE_ALL

        player.prepare()
        player.playWhenReady = true
    }

    fun release() {
        player.release()
    }

}
