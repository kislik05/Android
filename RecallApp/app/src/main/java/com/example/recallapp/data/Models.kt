package com.example.recallapp.data

data class Deck(
    val id: String,
    val title: String,
    val category: String,
    val cardCount: Int
)

data class Flashcard(
    val id: String,
    val deckId: String,
    val question: String,
    val answer: String,
    val mastered: Boolean = false
)
