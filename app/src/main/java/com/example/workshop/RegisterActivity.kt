package com.example.workshop

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.workshop.response_data_classes.RegisterResponseData
import com.example.workshop.user.UserApiMethods
import com.example.workshop.user.UserModelRegister
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegisterActivity : AppCompatActivity() {
    private val connect = Retrofit.Builder().baseUrl("https://bottlenose-scarlet-nitrogen.glitch.me")
        .addConverterFactory(GsonConverterFactory.create()).build()
    private val userApi = connect.create(UserApiMethods::class.java)

    private var loginField: EditText? = null
    private var emailField: EditText? = null
    private var passwordField: EditText? = null
    private var registerBtn: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_activity)

        loginField = findViewById(R.id.loginEdit)
        emailField = findViewById(R.id.emailEdit)
        passwordField = findViewById(R.id.passwordEdit)
        registerBtn = findViewById(R.id.registerBtn)

        registerBtn?.setOnClickListener(::clickedRegisterButton)
    }

    private fun postUserInDataBase(user: UserModelRegister) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = userApi.registerUser(user).body()
            runOnUiThread {
                if (response?.response == "successfull") {
                    finish()
                } else if (response?.response == "User Already Exist") {
                    makeUserInDbToast()
                }
            }
        }
    }

    private fun makeUserInDbToast() {
        val text = "Пользователь существует"
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
    }

    private fun makeEmptyToast() {
        val text = "Введены не все данные"
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
    }

    private fun clickedRegisterButton(view: View) {
        if (
            loginField?.text.toString() != "" &&
            emailField?.text.toString() != "" &&
            passwordField?.text.toString() != ""
        ) {
            val newUser = UserModelRegister(
                login = loginField?.text.toString(),
                email = emailField?.text.toString(),
                password = passwordField?.text.toString()
            )
            postUserInDataBase(newUser)
        } else {
            makeEmptyToast()
        }
    }

}