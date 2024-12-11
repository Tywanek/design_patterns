package com.radlab.wzorce.data.helper

import com.radlab.wzorce.data.model.DesignPatterns
import com.radlab.wzorce.data.model.DesignPatternsData
import com.radlab.wzorce.utils.Constant
import com.radlab.wzorce.utils.ResourcesProvider
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class JsonHelperIpm(private val resourcesProvider: ResourcesProvider) : JsonAssetsHelper<DesignPatternsData> {
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private val jsonAdapter = moshi.adapter(DesignPatterns::class.java)

    private suspend fun loadJsonFromAssets(fileName: String): String {
        return withContext(Dispatchers.IO) {
            resourcesProvider.loadJsonFromAsset(fileName)
        }
    }

    override suspend fun designPatterns(): Result<DesignPatternsData> = runCatching {
        val patternsJson = loadJsonFromAssets(Constant.FILE_NAME)
        val designPatterns = jsonAdapter.fromJson(patternsJson)?.designPatterns
            ?: throw IllegalStateException()

        designPatterns
    }
}