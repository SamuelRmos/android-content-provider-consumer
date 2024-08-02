package com.samuelrmos.contentproviderconsumer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.samuelrmos.contentproviderconsumer.ui.theme.AppTheme
import com.samuelrmos.contentproviderconsumer.ui.theme.PurpleGrey80

@Composable
fun ContentList(modifier: Modifier = Modifier, list: List<Content>) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(1),
        verticalArrangement = Arrangement.spacedBy(1.dp)
    ) {
        itemsIndexed(list) { _, item ->
            ContentCard(content = item)
        }
    }
}

@Composable
private fun ContentCard(modifier: Modifier = Modifier, content: Content) {
    Surface(modifier = modifier, color = PurpleGrey80) {
        Column(modifier = modifier) {
            Text(text = content.title, fontSize = 18.sp)
            Text(text = content.description, fontSize = 12.sp)
        }
    }
}

@Preview
@Composable
private fun ContentPreview() {
    AppTheme {
        Surface {
            Column {
                ContentList(
                    list = listOf(
                        Content("KitKat", "Android 4.4"),
                        Content("Jelly Beans", "Android 4.3")
                    )
                )
            }
        }
    }
}