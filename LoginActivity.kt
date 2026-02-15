package com.example.livestreamapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Auth.isLoggedIn(this)) {
            startActivity(Intent(this, ChannelListActivity::class.java))
            finish()
            return
        }

        setContentView(R.layout.activity_login)

        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val tvError = findViewById<TextView>(R.id.tvError)

        btnLogin.setOnClickListener {
            val u = etUsername.text.toString().trim()
            val p = etPassword.text.toString()

            if (u.isEmpty() || p.isEmpty()) {
                tvError.text = "Kullanıcı adı ve şifre gerekli."
                return@setOnClickListener
            }

            val ok = Auth.login(this, u, p)
            if (ok) {
                startActivity(Intent(this, ChannelListActivity::class.java))
                finish()
            } else {
                tvError.text = "Hatalı kullanıcı adı veya şifre."
            }
        }
    }
}
