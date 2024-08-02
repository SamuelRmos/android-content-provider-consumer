package com.samuelrmos.contentproviderconsumer

import android.content.ContentResolver
import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samuelrmos.contentproviderconsumer.ContentRequestState.Loading
import com.samuelrmos.contentproviderconsumer.ContentRequestState.Success
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

private const val AUTHORITY = "content://com.sam_chordas.android.androidflavors.app/flavor"

class ConsumerViewModel : ViewModel() {
    private val uri = Uri.parse(AUTHORITY)
    var requestState: MutableState<ContentRequestState> = mutableStateOf(Loading)
        private set

    fun getContentList(contentResolver: ContentResolver) {
        viewModelScope.launch(IO) {
            val listContent = mutableListOf<Content>()
            listContent.clear()

            contentResolver.query(
                uri,
                null,
                null,
                null,
                null
            )?.apply {
                while (moveToNext()) {
                    listContent.add(
                        Content(
                            title = getString(1),
                            description = getString(2)
                        )
                    )
                }
                requestState.value = Success(listContent)
            }?.close()
        }
    }
}