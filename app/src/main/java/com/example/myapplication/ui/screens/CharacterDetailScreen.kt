package com.example.myapplication.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.myapplication.data.model.Character

@Composable
fun CharacterDetailScreen(character: Character) {
    Column(
        modifier = Modifier.fillMaxSize().padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = character.image,
            contentDescription = null,
            modifier = Modifier.size(160.dp).clip(CircleShape)
        )
        Spacer(Modifier.height(16.dp))
        Text(character.name, style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold))
        Spacer(Modifier.height(8.dp))
        ElevatedCard(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("ID: ${character.id}")
                Text("Status: ${character.status}")
                Text("Species: ${character.species}")
                if (character.type.isNotEmpty()) Text("Type: ${character.type}")
                Text("Gender: ${character.gender}")
                Text("Origin: ${character.origin.name}")
                Text("Location: ${character.location.name}")
                Text("Created: ${character.created}")
            }
        }
    }
}
