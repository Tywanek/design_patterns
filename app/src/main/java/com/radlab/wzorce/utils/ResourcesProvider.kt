package com.radlab.wzorce.utils

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ResourcesProvider @Inject constructor(private val context: Context) {

    suspend fun loadJsonFromAsset(fileName: String): String = withContext(Dispatchers.IO) {
        context.assets.open(fileName).bufferedReader().use { it.readText() }
    }
}