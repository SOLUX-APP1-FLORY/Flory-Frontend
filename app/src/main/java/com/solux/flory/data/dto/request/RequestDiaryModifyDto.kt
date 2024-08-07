package com.solux.flory.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestDiaryModifyDto (
    @SerialName("diary_id") val diaryId: Int,
    @SerialName("title") val title: String,
    @SerialName("content") val content: String,
    @SerialName("flower") val flower: String,
)