package com.example.workshop

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.workshop.databinding.CategoriesActivityBinding

class CategoriesActivity : AppCompatActivity() {

    lateinit var binding: CategoriesActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CategoriesActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.favBtn.setOnClickListener(::openFavAIView)
    }

    private fun openFavAIView(view: View) {
        val intent = Intent(this, FavoritesActivity::class.java)
        startActivity(intent)
        finish()
    }
}