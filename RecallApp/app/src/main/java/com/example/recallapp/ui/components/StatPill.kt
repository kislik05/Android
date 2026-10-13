package com.example.recallapp.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recallapp.ui.theme.RecallAppTheme
import com.example.recallapp.ui.theme.Spacing

// Small labeled stat badge. Reused on the deck detail screen (card count,
// mastered count) and on the study results screen (cards reviewed).
@Composable
fun StatPill(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .padding(horizontal = Spacing.md, vertical = Spacing.sm)
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun StatPillPreview() {
    RecallAppTheme { StatPill(label = "Mastered", value = "8") }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun StatPillPreviewDark() {
    RecallAppTheme { StatPill(label = "Mastered", value = "8") }
}
