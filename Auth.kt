package com.example.livestreamapp

import android.content.Context

object Auth {
    private const val PREF = "auth_pref"
    private const val KEY_LOGGED_IN = "logged_in"

    // DEMO kullanıcılar: burayı değiştir.
    // Gerçek kullanımda bunu sunucudan doğrula (API) ve token tut.
    private val users = mapOf(
        "admin" to "1234",
        "resul" to "resul123"
    )

    fun login(context: Context, username: String, password: String): Boolean {
        val ok = users[username] == password
        if (ok) {
            context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit()
                .putBoolean(KEY_LOGGED_IN, true)
                .apply()
        }
        return ok
    }

    fun isLoggedIn(context: Context): Boolean {
        return context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
            .getBoolean(KEY_LOGGED_IN, false)
    }

    fun logout(context: Context) {
        context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
            .edit()
            .clear()
            .apply()
    }
}
