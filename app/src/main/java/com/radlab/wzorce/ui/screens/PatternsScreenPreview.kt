package com.radlab.wzorce.ui.screens
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.radlab.wzorce.data.helper.JsonAssetsHelper
import com.radlab.wzorce.data.model.DesignPatternsData
import com.radlab.wzorce.data.model.Pattern
import com.radlab.wzorce.viewModel.MainViewModel

@Preview(showBackground = true)
@Composable
fun PreviewPatternsScreen() {
    val viewModel = MainViewModel(FakeJsonAssetsHelper())
    PatternsScreen (viewModel = viewModel)
}

class FakeJsonAssetsHelper : JsonAssetsHelper<DesignPatternsData> {
    override suspend fun designPatterns(): Result<DesignPatternsData> {
        return Result.success(
            DesignPatternsData(
                creational = listOf(
                    Pattern("Singleton", "Ensures a class has only one instance."),
                    Pattern("Factory Method", "Creates an instance of several derived classes.")
                ),
                structural = listOf(
                    Pattern("Adapter", "Matches interfaces of different classes."),
                    Pattern("Bridge", "Separates an object’s interface from its implementation.")
                ),
                behavioral = listOf(
                    Pattern("Strategy", "Encapsulates an algorithm inside a class."),
                    Pattern("Observer", "A way of notifying change to a number of classes.")
                )
            )
        )
    }
}
