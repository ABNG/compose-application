package com.compose.app.data.datastore

import kotlinx.serialization.Serializable

@Serializable
data class AppPreferences(
    val showOnBoard: Boolean = true
)
