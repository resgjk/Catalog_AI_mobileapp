package com.example.workshop

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.workshop.user.FullUserModel
import com.example.workshop.user.UserApiMethods
import com.example.workshop.user.UserModelLogin
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {
    private val connect = Retrofit.Builder().baseUrl("https://bottlenose-scarlet-nitrogen.glitch.me")
        .addConverterFactory(GsonConverterFactory.create()).build()
    private val userApi = connect.create(UserApiMethods::class.java)

    private var createView: TextView? = null
    private var loginBtn: Button? = null
    private var emailEdit: EditText? = null
    private var passwordEdit: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        loginBtn = findViewById(R.id.loginBtn)
        emailEdit = findViewById(R.id.emailEdit)
        passwordEdit = findViewById(R.id.passwordEdit)
        loginBtn?.setOnClickListener(::clickedLoginButton)
        createView = findViewById<TextView>(R.id.createView).apply {
            setOnClickListener {
                val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun makeIncorrectPasswordToast() {
        val text = "Пароль неверный"
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
    }

    private fun makeUserNotFoundToast() {
        val text = "Пользователь не найден"
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
    }

    private fun goToMainActivity(user: FullUserModel) {
        val intent = Intent(this, FavoritesActivity::class.java)
        intent.putExtra("id", user.id)
        intent.putExtra("login", user.login)
        intent.putExtra("email", user.email)
        intent.putExtra("userImage", user.userImage)
        startActivity(intent)
        finish()
    }

    private fun loginUser(loginUser: UserModelLogin) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = userApi.loginUser(loginUser).body()
            runOnUiThread {
                if (response?.response == null) {
                    val user = FullUserModel(
                        id = response!!.id,
                        login = response!!.login,
                        email = response!!.email,
                        userImage = response!!.user_image
                    )
                    goToMainActivity(user)
                } else {
                    if (response?.response == "Incorrect Password") {
                        makeIncorrectPasswordToast()
                    } else if (response?.response == "User Not Found") {
                        makeUserNotFoundToast()
                    }
                }
            }
        }
    }

    private fun makeEmptyToast() {
        val text = "Введены не все данные"
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
    }

    private fun clickedLoginButton(view: View) {
        if (emailEdit?.text.toString() != "" &&
            passwordEdit?.text.toString() != ""
        ) {
            val loginUser = UserModelLogin(
                login = emailEdit?.text.toString(),
                password = passwordEdit?.text.toString()
            )
            loginUser(loginUser)
        } else {
            makeEmptyToast()
        }
    }
}