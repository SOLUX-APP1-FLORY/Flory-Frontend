package com.solux.flory.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class RequestBouquetDetailDto(
    @SerialName("giftId") val giftId: Int,
)