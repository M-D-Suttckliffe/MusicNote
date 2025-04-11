package com.music.musicnote

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import java.io.IOException

class PlayerFragment : Fragment() {

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable

    var isPlaying = false
    var currentPosition = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_player, container, false)

//        val audioFile = intent.getParcelableExtra<AudioFileModel>("AUDIOFILE")
//        if (audioFile != null) {
//
//        }

        return view
    }

    private fun setupMediaPlayer(view: View, uri: String?) {

        mediaPlayer = MediaPlayer()
        handler = Handler(Looper.getMainLooper())

        val playBtn = view.findViewById<MaterialButton>(R.id.playBtn)
        val mediaFileSeekBar = view.findViewById<TextView>(R.id.mediaFileSeekBar)

        playBtn.setOnClickListener {
            if (!isPlaying)
                playAudio(view, uri)
            else
                pauseAudio(view)
        }

        runnable = object : Runnable {
            override fun run() {
                if (mediaPlayer.isPlaying) {
                    currentPosition = mediaPlayer.currentPosition
                    mediaFileSeekBar.text = formatTime(currentPosition)
                }
                handler.postDelayed(this, 1000)
            }
        }
    }

    private fun playAudio(view: View, previewUrl: String?) {
        if (previewUrl.isNullOrEmpty()) return

        try {
            if (!mediaPlayer.isPlaying) {
                mediaPlayer.reset()
                mediaPlayer.setDataSource(previewUrl)
                mediaPlayer.prepareAsync()
                mediaPlayer.setOnPreparedListener {
                    mediaPlayer.seekTo(currentPosition)
                    mediaPlayer.start()
                    isPlaying = true
                    view.findViewById<MaterialButton>(R.id.playBtn).setIconResource(R.drawable.baseline_play_arrow_24)
                    handler.post(runnable)
                }
            } else {
                mediaPlayer.start()
                isPlaying = true
                view.findViewById<MaterialButton>(R.id.playBtn).setIconResource(R.drawable.baseline_pause_24)
                handler.post(runnable)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun pauseAudio(view: View) {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
            isPlaying = false
            view.findViewById<MaterialButton>(R.id.playBtn).setIconResource(R.drawable.baseline_play_arrow_24)
            handler.removeCallbacks(runnable)
        }
    }

    private fun formatTime(milliseconds: Int): String {
        val seconds = (milliseconds / 1000) % 60
        val minutes = (milliseconds / (1000 * 60)) % 60
        return String.format("%02d:%02d", minutes, seconds)
    }
}