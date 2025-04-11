package com.music.musicnote

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView


class AudioAdapter(audios: ArrayList<AudioFileModel>, private val onItemClick: (AudioFileModel) -> Unit): RecyclerView.Adapter<AudioAdapter.AudioViewHolder>() {

    var audio: ArrayList<AudioFileModel> = ArrayList<AudioFileModel>()

    init {
        this.audio = audios
    }

    class AudioViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        var title: TextView
        var artist: TextView

        init {
            title = itemView.findViewById(R.id.file_name)
            artist = itemView.findViewById(R.id.file_artist)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AudioViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.audio_view_layout, parent, false)
        return AudioViewHolder(view)
    }

    override fun getItemCount(): Int {
        return audio.size
    }

    override fun onBindViewHolder(holder: AudioViewHolder, position: Int) {
        val audioFileModel: AudioFileModel = audio[position]

        holder.title.text = audioFileModel.title
        holder.artist.text = audioFileModel.artist

        holder.itemView.setOnClickListener{
            fun onClick(view: View) {
                onItemClick(audioFileModel)
                val intent = Intent(holder.itemView.context, PlayerFragment::class.java).apply {
                    putExtra("AUDIOFILE", audioFileModel)
                }
                holder.itemView.context.startActivity(intent)
//                supportFragmentManager.beginTransaction().replace(R.id.mediaFragment, playerFragment).addToBackStack(null).commit()
            }
        }
    }

}