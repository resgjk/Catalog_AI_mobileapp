package com.example.workshop

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.workshop.databinding.FavoritesActivityBinding

class FavoritesActivity : AppCompatActivity() {

    lateinit var binding: FavoritesActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FavoritesActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ctgBtn.setOnClickListener(::openCategoryView)
        binding.chatgpt.setOnClickListener(::goToGpt)
    }

    private fun goToGpt(view: View) {
        val intent = Intent(this, ChatActivity::class.java)
        startActivity(intent)
    }

    private fun openCategoryView(view: View) {
        val intent = Intent(this, CategoriesActivity::class.java)
        startActivity(intent)
        finish()
    }
}