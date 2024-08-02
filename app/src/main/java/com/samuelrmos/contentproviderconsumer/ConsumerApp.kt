package com.samuelrmos.contentproviderconsumer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.samuelrmos.contentproviderconsumer.ContentRequestState.Loading
import com.samuelrmos.contentproviderconsumer.ContentRequestState.Success

@Composable
fun ConsumerApp() {
    val viewModel = viewModel<ConsumerViewModel>()
    val context = LocalContext.current
    val requestState: ContentRequestState by viewModel.requestState

    LaunchedEffect(true) {
        viewModel.getContentList(context.contentResolver)
    }

    when (requestState) {
        is Loading -> Loading()
        is Success -> {
            Column(
                modifier = Modifier
                    .padding()
                    .fillMaxSize()
            ) {
                ContentList(list = requestState.getContentList())
            }
        }
    }
}

@Composable
private fun Loading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = Color.Black, modifier = Modifier.size(48.dp)
        )
    }
}
