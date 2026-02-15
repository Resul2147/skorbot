package com.example.livestreamapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ChannelListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!Auth.isLoggedIn(this)) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        setContentView(R.layout.activity_channel_list)

        val channels = listOf(
            // Buraya kendi HLS (m3u8) linklerini koy.
            Channel("Canlı 1", "https://example.com/live1/playlist.m3u8"),
            Channel("Canlı 2", "https://example.com/live2/playlist.m3u8")
        )

        val recycler = findViewById<RecyclerView>(R.id.recycler)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = ChannelAdapter(channels) { ch ->
            val i = Intent(this, PlayerActivity::class.java)
            i.putExtra("name", ch.name)
            i.putExtra("url", ch.url)
            startActivity(i)
        }

        findViewById<Button>(R.id.btnLogout).setOnClickListener {
            Auth.logout(this)
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
