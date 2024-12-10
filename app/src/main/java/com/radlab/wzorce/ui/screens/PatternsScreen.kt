package com.radlab.wzorce.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.radlab.wzorce.R
import com.radlab.wzorce.viewModel.MainViewModel
import com.radlab.wzorce.data.model.Pattern
import com.radlab.wzorce.viewModel.UiState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PatternsScreen(viewModel: MainViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    val pagerState = rememberPagerState(pageCount = { 3 })

    LaunchedEffect(Unit) {
        viewModel.loadPatterns()
    }

    when (uiState) {
        is UiState.Loading -> {
            Text(
                text = stringResource(R.string.loading),
                modifier = Modifier.fillMaxSize(),
                style = MaterialTheme.typography.bodyLarge
            )
        }

        is UiState.Success -> {
            val data = (uiState as UiState.Success).designPatterns
            Column(modifier = Modifier.fillMaxSize()) {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxSize()
                ) { page ->
                    when (page) {
                        0 -> PatternsList(data.creational, stringResource(R.string.creational))
                        1 -> PatternsList(data.structural, stringResource(R.string.structural))
                        2 -> PatternsList(data.behavioral, stringResource(R.string.behavioral))
                    }
                }
            }
        }

        is UiState.Error -> {
            Text(
                text = stringResource(R.string.error_message),
                modifier = Modifier.fillMaxSize(),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun PatternsList(patterns: List<Pattern>, title: String) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 15.dp)
        )
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(patterns) { pattern ->
                Text(
                    text = "${pattern.name}: ${pattern.description}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
        }
    }
}
