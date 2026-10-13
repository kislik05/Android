package com.example.recallapp.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recallapp.R
import com.example.recallapp.data.Deck
import com.example.recallapp.data.SampleData
import com.example.recallapp.ui.theme.RecallAppTheme
import com.example.recallapp.ui.theme.Spacing

// Reused twice on the deck list screen: once in the full-width LazyColumn
// and once (compact = true) in the "Recently studied" LazyRow.
// Long titles never break the layout, thanks to maxLines + ellipsis.
@Composable
fun DeckCard(
    deck: Deck,
    onClick: () -> Unit,
    compact: Boolean = false,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        modifier = if (compact) modifier.width(160.dp) else modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(Spacing.md)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_deck),
                contentDescription = "Deck icon for ${deck.title}",
                modifier = Modifier.size(32.dp)
            )
            Column(
                modifier = Modifier
                    .padding(start = Spacing.sm)
                    .fillMaxWidth()
            ) {
                Text(
                    text = deck.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "${deck.category} - ${deck.cardCount} cards",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DeckCardPreview() {
    RecallAppTheme { DeckCard(deck = SampleData.decks.first(), onClick = {}) }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DeckCardPreviewDark() {
    RecallAppTheme { DeckCard(deck = SampleData.decks.first(), onClick = {}) }
}
