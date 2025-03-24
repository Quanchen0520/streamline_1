package com.cyq.streamline

import android.media.MediaPlayer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(
    private val itemList: List<String>,
    private val musicList: List<Int>
) : RecyclerView.Adapter<Adapter.ViewHolder>() {
    private val mediaPlayerMap = mutableMapOf<Int, MediaPlayer?>()
    private val isPlayingMap = mutableMapOf<Int, Boolean>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textView)
        val playBtn: ImageButton = itemView.findViewById(R.id.imageButton2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.musiclist, parent, false)
        )
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = itemList[position]
        if (!mediaPlayerMap.containsKey(position)) {
            mediaPlayerMap[position] =
                MediaPlayer.create(holder.itemView.context, musicList[position])
            isPlayingMap[position] = false
        }

        holder.playBtn.setOnClickListener {
            val mediaPlayer = mediaPlayerMap[position]
            val isPlaying = isPlayingMap[position] ?: false
            if (mediaPlayer!!.isPlaying) {
                mediaPlayer.pause()
                holder.playBtn.setImageResource(R.drawable.baseline_play_arrow_24)
                Log.e("playing", "stop")
            } else {
                mediaPlayer.start()
                holder.playBtn.setImageResource(R.drawable.baseline_stop_24)
                Log.e("playing", "play")
            }
            isPlayingMap[position] = !isPlaying
        }
    }

    fun releaseAllPlayers() {
        mediaPlayerMap.values.forEach { it?.release() }
        mediaPlayerMap.clear()
    }
}