package com.radlab.wzorce.data.helper

import com.radlab.wzorce.data.model.DesignPatternsData

interface JsonAssetsHelper {
    suspend fun designPatterns(fileName: String): Result<DesignPatternsData>
    suspend fun loadJsonFromAssets(fileName: String): String
}
