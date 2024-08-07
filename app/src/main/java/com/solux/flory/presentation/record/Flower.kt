package com.solux.flory.presentation.record

import java.io.Serializable

data class Flower(
    val imageUrl: String,
    val meaning: String,
    val name: String
) : Serializable
