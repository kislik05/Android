package com.example.recallapp.data

// In-memory sample data so every screen has real content to render.
// Replace with a Room database or a repository once you persist data locally.
object SampleData {

    val categories = listOf("All", "Languages", "Science", "History", "Math", "Arts")

    val decks = listOf(
        Deck("spanish-101", "Spanish Basics", "Languages", 12),
        Deck("french-verbs", "French Verbs", "Languages", 15),
        Deck("japanese-kana", "Japanese Kana", "Languages", 20),
        Deck("biology-101", "Biology Essentials", "Science", 18),
        Deck("chemistry-formulas", "Chemistry Formulas", "Science", 10),
        Deck("physics-laws", "Physics Laws", "Science", 14),
        Deck("world-history", "World History Dates", "History", 16),
        Deck("us-presidents", "US Presidents", "History", 46),
        Deck("algebra-rules", "Algebra Rules", "Math", 11),
        Deck("geometry-terms", "Geometry Terms", "Math", 13),
        Deck("art-movements", "Art Movements", "Arts", 9),
        Deck("music-theory", "Music Theory Basics", "Arts", 17)
    )

    // Generates placeholder cards for any deck so every screen works out of the box.
    // Swap the question/answer text for real content once you fill in your own deck.
    fun cardsFor(deckId: String): List<Flashcard> {
        val deck = decks.firstOrNull { it.id == deckId } ?: return emptyList()
        return List(deck.cardCount) { index ->
            Flashcard(
                id = "$deckId-$index",
                deckId = deckId,
                question = "${deck.title} - question ${index + 1}",
                answer = "${deck.title} - answer ${index + 1}",
                mastered = index % 3 == 0
            )
        }
    }
}
