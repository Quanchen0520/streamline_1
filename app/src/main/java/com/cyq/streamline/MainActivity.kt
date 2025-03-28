package com.cyq.streamline

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: Adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val viewpager = findViewById<ViewPager2>(R.id.viewpager)
        val nameList = listOf("ocean_wave", "brook", "glassland", "rain_thunder")
        val imageList = listOf(
            R.drawable.ocean_wave,
            R.drawable.brook,
            R.drawable.glassland,
            R.drawable.rain_thunder
        )
        val musicList = listOf(R.raw.ocean_wave, R.raw.brook, R.raw.glassland, R.raw.rain_thunder)

        adapter = Adapter(nameList, musicList, imageList)
        viewpager.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        adapter.releaseAllPlayers()
    }
}