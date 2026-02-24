package com.example.responsivescreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.responsivescreen.ui.theme.ResponsiveScreenTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ResponsiveScreenTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ResponsiveScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ResponsiveScreen(modifier: Modifier = Modifier) {
    var isFavorite by rememberSaveable { mutableStateOf(false) }
    val onFavoriteClick = { isFavorite = !isFavorite }

    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        val isWideScreen = maxWidth > 600.dp

        if (isWideScreen) {
            WideLayout(isFavorite, onFavoriteClick)
        } else {
            NarrowLayout(isFavorite, onFavoriteClick)
        }
    }
}

@Composable
fun WideLayout(isFavorite: Boolean, onFavoriteClick: () -> Unit) {
    Row(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .weight(0.5f)
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text("Article")
            Text("a".repeat(500))
        }

        VerticalDivider()

        Column(
            modifier = Modifier
                .weight(0.5f)
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("a".repeat(500))
            Button(onClick = onFavoriteClick) {
                Text(if (isFavorite) "★ Favorited" else "☆ Favorite")
            }
            Button(onClick = {  }) {
                Text("Next Article")
            }

        }
    }
}

@Composable
fun NarrowLayout(isFavorite: Boolean, onFavoriteClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Article")
        Text("a".repeat(1000))
        Button(onClick = onFavoriteClick) {
            Text(if (isFavorite) "★ Favorited" else "☆ Favorite")
        }
        Button(onClick = {  }) {
            Text("Next Article")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    ResponsiveScreenTheme {
        Scaffold(topBar = { TopAppBar(title = { Text("Adaptive Layout") }) }) { innerPadding ->
            ResponsiveScreen(modifier = Modifier.padding(innerPadding))
        }
    }
}