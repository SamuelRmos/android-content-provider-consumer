package com.samuelrmos.contentproviderconsumer

sealed class ContentRequestState {
    data object Loading : ContentRequestState()
    data class Success(val list: List<Content>) : ContentRequestState()

    fun getContentList(): List<Content> = (this as Success).list
}