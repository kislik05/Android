package com.example.recallapp.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recallapp.data.SampleData
import com.example.recallapp.ui.components.EmptyState
import com.example.recallapp.ui.components.SectionHeader
import com.example.recallapp.ui.components.StatPill
import com.example.recallapp.ui.theme.RecallAppTheme
import com.example.recallapp.ui.theme.Spacing

// Receives deckId as a navigation argument and looks the deck up by id.
// Has a working back button (required for every screen except the start screen).
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeckDetailScreen(
    deckId: String,
    onBack: () -> Unit,
    onStudyClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val deck = SampleData.decks.firstOrNull { it.id == deckId }
    val cards = SampleData.cardsFor(deckId)
    val masteredCount = cards.count { it.mastered }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = deck?.title ?: "Deck",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack, modifier = Modifier.heightIn(min = 48.dp)) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        modifier = modifier
    ) { padding ->
        if (cards.isEmpty()) {
            EmptyState(message = "No cards in this deck yet", modifier = Modifier.padding(padding))
        } else {
            Column(modifier = Modifier.padding(padding)) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(Spacing.md),
                    modifier = Modifier.padding(Spacing.md)
                ) {
                    StatPill(label = "Cards", value = cards.size.toString())
                    StatPill(label = "Mastered", value = masteredCount.toString())
                }
                Button(
                    onClick = { onStudyClick(deckId) },
                    modifier = Modifier
                        .padding(horizontal = Spacing.md)
                        .fillMaxWidth()
                        .heightIn(min = 48.dp)
                ) {
                    Text("Start studying")
                }
                SectionHeader(title = "All cards")
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(Spacing.sm),
                    contentPadding = PaddingValues(horizontal = Spacing.md, vertical = Spacing.sm),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(cards, key = { it.id }) { card ->
                        Text(
                            text = card.question,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = Spacing.sm)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DeckDetailScreenPreview() {
    RecallAppTheme { DeckDetailScreen(deckId = "spanish-101", onBack = {}, onStudyClick = {}) }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DeckDetailScreenPreviewDark() {
    RecallAppTheme { DeckDetailScreen(deckId = "spanish-101", onBack = {}, onStudyClick = {}) }
}
