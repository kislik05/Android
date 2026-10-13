package com.example.recallapp.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.recallapp.ui.theme.RecallAppTheme
import com.example.recallapp.ui.theme.Spacing

// Reused on every screen (deck list, deck detail, study) to title a section
// with an optional subtitle, so headings look identical across the app.
@Composable
fun SectionHeader(
    title: String,
    subtitle: String? = null,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(horizontal = Spacing.md, vertical = Spacing.sm)) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        if (subtitle != null) {
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SectionHeaderPreview() {
    RecallAppTheme { SectionHeader(title = "Your decks", subtitle = "12 decks") }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SectionHeaderPreviewDark() {
    RecallAppTheme { SectionHeader(title = "Your decks", subtitle = "12 decks") }
}
