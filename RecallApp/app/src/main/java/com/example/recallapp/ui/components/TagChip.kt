package com.example.recallapp.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recallapp.ui.theme.RecallAppTheme
import com.example.recallapp.ui.theme.Spacing

// Reused in the category LazyRow on the deck list and as a mastered/learning
// tag on the deck detail screen. "selected" drives the highlight color.
@Composable
fun TagChip(
    text: String,
    selected: Boolean = false,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val background = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant
    val content = if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant
    Text(
        text = text,
        color = content,
        style = MaterialTheme.typography.labelLarge,
        modifier = modifier
            .clip(RoundedCornerShape(50))
            .background(background)
            .clickable { onClick() }
            .defaultMinSize(minHeight = 48.dp)
            .padding(horizontal = Spacing.md, vertical = Spacing.sm)
    )
}

@Preview(showBackground = true)
@Composable
private fun TagChipPreview() {
    RecallAppTheme { TagChip(text = "Languages", selected = true) }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun TagChipPreviewDark() {
    RecallAppTheme { TagChip(text = "Languages", selected = false) }
}
