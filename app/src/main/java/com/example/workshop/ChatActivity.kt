package com.example.workshop
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.workshop.databinding.ChatActivityBinding
import com.example.workshop.user.UserApiMethods
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ChatActivity : AppCompatActivity() {

    lateinit var binding: ChatActivityBinding

    private val connect = Retrofit.Builder().baseUrl("https://bottlenose-scarlet-nitrogen.glitch.me")
        .addConverterFactory(GsonConverterFactory.create()).build()
    private val userApi = connect.create(UserApiMethods::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ChatActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}