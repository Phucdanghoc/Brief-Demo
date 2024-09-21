package com.example.demo.models

data class Product(
    val id: String,
    val title: String,
    val description: String,
    val icon: String,
    val theme: String,
    val productBackground: String,
    val ingredients: List<Map<String, String>>,
    val technology: List<Map<String, String>>,
    val uses_highlights: List<Map<String, String>>,
    val achievement: List<Map<String, String>>
) {
    fun getFormattedIngredients(): List<ChildItem> {
        return ingredients.map { ChildItem(it.keys.first(), it.values.first()) }
    }

    fun getFormattedTechnology(): List<ChildItem> {
        return technology.map { ChildItem(it.keys.first(), it.values.first()) }
    }

    fun getFormattedUsesHighlights(): List<ChildItem> {
        return uses_highlights.map { ChildItem(it.keys.first(), it.values.first()) }
    }

    fun getFormattedAchievements(): List<ChildItem> {
        return achievement.map { ChildItem(it.keys.first(), it.values.first()) }
    }
}