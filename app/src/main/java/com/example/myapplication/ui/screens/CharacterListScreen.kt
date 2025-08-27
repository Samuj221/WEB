package com.example.myapplication.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.myapplication.data.model.Character
import com.example.myapplication.ui.CharactersState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CharacterListScreen(
    state: CharactersState,
    onClick: (Character) -> Unit
) {
    when {
        state.isLoading -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        state.error != null -> Text("Error: ${state.error}", modifier = Modifier.padding(16.dp))
        else -> {
            val context = LocalContext.current
            LazyColumn(Modifier.fillMaxSize()) {
                stickyHeader {
                    Surface(tonalElevation = 2.dp) {
                        Text(
                            "Total personajes: ${state.total}",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp)
                        )
                    }
                }
                items(state.list) { c ->
                    ListItem(
                        headlineContent = { Text(c.name) },
                        supportingContent = { Text(c.status) },
                        leadingContent = {
                            AsyncImage(
                                model = c.image,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(56.dp)
                                    .clip(CircleShape)
                                    .clickable {
                                        val intent = Intent(
                                            Intent.ACTION_DIAL,
                                            Uri.parse("tel:${c.id}")
                                        )
                                        context.startActivity(intent)
                                    }
                            )
                        },
                        modifier = Modifier
                            .clickable { onClick(c) } // tap en el resto del item → detalle
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                    Divider()
                }
            }
        }
    }
}
