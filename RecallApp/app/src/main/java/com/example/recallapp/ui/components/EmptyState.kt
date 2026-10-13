package com.example.recallapp.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.recallapp.ui.theme.RecallAppTheme
import com.example.recallapp.ui.theme.Spacing

// Friendly placeholder shown instead of a blank screen whenever a list is
// empty. Reused on the deck list (empty category filter) and study screen
// (deck with no cards yet).
@Composable
fun EmptyState(
    message: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
            .padding(Spacing.lg)
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun EmptyStatePreview() {
    RecallAppTheme { EmptyState(message = "No cards yet. Add your first card!") }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun EmptyStatePreviewDark() {
    RecallAppTheme { EmptyState(message = "No cards yet. Add your first card!") }
}
