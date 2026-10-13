package com.example.recallapp.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.recallapp.data.SampleData
import com.example.recallapp.ui.components.DeckCard
import com.example.recallapp.ui.components.EmptyState
import com.example.recallapp.ui.components.SectionHeader
import com.example.recallapp.ui.components.TagChip
import com.example.recallapp.ui.theme.RecallAppTheme
import com.example.recallapp.ui.theme.Spacing

// Start screen: no back button. Shows a LazyRow of category filters, a
// LazyRow of recently studied decks, and the full LazyColumn of all decks
// (12 items, well over the 10-item minimum).
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeckListScreen(
    onDeckClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedCategory by remember { mutableStateOf("All") }

    val filteredDecks = if (selectedCategory == "All") {
        SampleData.decks
    } else {
        SampleData.decks.filter { it.category == selectedCategory }
    }
    val recentDecks = SampleData.decks.take(5)

    Scaffold(
        topBar = { TopAppBar(title = { Text("RecallIt") }) },
        modifier = modifier
    ) { padding ->
        if (filteredDecks.isEmpty()) {
            EmptyState(
                message = "No decks in this category yet",
                modifier = Modifier.padding(padding)
            )
        } else {
            LazyColumn(
                contentPadding = PaddingValues(vertical = Spacing.md),
                verticalArrangement = Arrangement.spacedBy(Spacing.sm),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                item {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(Spacing.sm),
                        contentPadding = PaddingValues(horizontal = Spacing.md),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(SampleData.categories) { category ->
                            TagChip(
                                text = category,
                                selected = category == selectedCategory,
                                onClick = { selectedCategory = category }
                            )
                        }
                    }
                }
                item { SectionHeader(title = "Recently studied") }
                item {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(Spacing.sm),
                        contentPadding = PaddingValues(horizontal = Spacing.md),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(recentDecks, key = { "recent-${it.id}" }) { deck ->
                            DeckCard(deck = deck, onClick = { onDeckClick(deck.id) }, compact = true)
                        }
                    }
                }
                item { SectionHeader(title = "Your decks", subtitle = "${filteredDecks.size} decks") }
                items(filteredDecks, key = { it.id }) { deck ->
                    DeckCard(
                        deck = deck,
                        onClick = { onDeckClick(deck.id) },
                        modifier = Modifier.padding(horizontal = Spacing.md)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DeckListScreenPreview() {
    RecallAppTheme { DeckListScreen(onDeckClick = {}) }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DeckListScreenPreviewDark() {
    RecallAppTheme { DeckListScreen(onDeckClick = {}) }
}
