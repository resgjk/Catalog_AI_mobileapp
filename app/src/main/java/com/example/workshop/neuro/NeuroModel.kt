package com.example.workshop.neuro

data class FullNeuroModel(
    val id: Int,
    val title: String,
    val description: String,
    val short_description: String,
    val icon: String,
    val category_id: Int
)

data class FavNeuroModel(
    val id: Int,
    val title: String,
    val icon: String
)

data class CardNeuroModel(
    val id: Int,
    val title: String,
    val short_description: String,
    val icon: String
)