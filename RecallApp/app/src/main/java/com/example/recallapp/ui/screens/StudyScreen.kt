package com.example.recallapp.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recallapp.data.SampleData
import com.example.recallapp.ui.components.EmptyState
import com.example.recallapp.ui.components.SectionHeader
import com.example.recallapp.ui.components.StatPill
import com.example.recallapp.ui.theme.RecallAppTheme
import com.example.recallapp.ui.theme.Spacing

// Receives deckId as a navigation argument, same as DeckDetailScreen.
// currentIndex / showAnswer / reviewedCount are the interactive state
// (remember + mutableStateOf) required by the assignment.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudyScreen(
    deckId: String,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val deck = SampleData.decks.firstOrNull { it.id == deckId }
    val cards = remember(deckId) { SampleData.cardsFor(deckId) }

    var currentIndex by remember { mutableStateOf(0) }
    var showAnswer by remember { mutableStateOf(false) }
    var reviewedCount by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Studying ${deck?.title ?: ""}",
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
        when {
            cards.isEmpty() -> {
                EmptyState(
                    message = "Nothing to study yet. Add some cards first.",
                    modifier = Modifier.padding(padding)
                )
            }
            currentIndex >= cards.size -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxSize()
                        .padding(Spacing.lg)
                ) {
                    SectionHeader(title = "Session complete")
                    StatPill(label = "Reviewed", value = reviewedCount.toString())
                    Button(
                        onClick = {
                            currentIndex = 0
                            reviewedCount = 0
                            showAnswer = false
                        },
                        modifier = Modifier
                            .padding(top = Spacing.md)
                            .heightIn(min = 48.dp)
                    ) {
                        Text("Study again")
                    }
                }
            }
            else -> {
                val card = cards[currentIndex]
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxSize()
                        .padding(Spacing.lg)
                ) {
                    SectionHeader(title = "Card ${currentIndex + 1} of ${cards.size}")
                    Card(
                        onClick = { showAnswer = !showAnswer },
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(vertical = Spacing.md)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(Spacing.lg)
                        ) {
                            Text(
                                text = if (showAnswer) card.answer else card.question,
                                style = MaterialTheme.typography.titleLarge,
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = if (showAnswer) "Answer" else "Tap to reveal answer",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.padding(top = Spacing.sm)
                            )
                        }
                    }
                    if (showAnswer) {
                        Row(horizontalArrangement = Arrangement.spacedBy(Spacing.md)) {
                            Button(
                                onClick = {
                                    reviewedCount++
                                    currentIndex++
                                    showAnswer = false
                                },
                                modifier = Modifier.heightIn(min = 48.dp)
                            ) { Text("Again") }
                            Button(
                                onClick = {
                                    reviewedCount++
                                    currentIndex++
                                    showAnswer = false
                                },
                                modifier = Modifier.heightIn(min = 48.dp)
                            ) { Text("Easy") }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun StudyScreenPreview() {
    RecallAppTheme { StudyScreen(deckId = "spanish-101", onBack = {}) }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun StudyScreenPreviewDark() {
    RecallAppTheme { StudyScreen(deckId = "spanish-101", onBack = {}) }
}
