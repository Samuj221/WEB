package com.example.myapplication.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.myapplication.data.model.Character

@Composable
fun CharacterDetailScreen(character: Character) {
    val ctx = LocalContext.current

    // Gradiente animado para fondo y borde
    val colors = listOf(Color(0xFF8E2DE2), Color(0xFF4A00E0), Color(0xFF00C6FF), Color(0xFF0072FF))
    val anim = rememberInfiniteTransition(label = "bg")
    val off by anim.animateFloat(
        initialValue = 0f,
        targetValue = 800f,
        animationSpec = infiniteRepeatable(tween(3000, easing = LinearEasing), RepeatMode.Reverse),
        label = "off"
    )
    val bg = Brush.linearGradient(colors, start = Offset(0f, 0f), end = Offset(off, off))
    val border = Brush.linearGradient(colors.reversed(), start = Offset(off, 0f), end = Offset(0f, off))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(bg)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            shape = CircleShape,
            tonalElevation = 6.dp,
            shadowElevation = 12.dp,
            border = BorderStroke(3.dp, border)
        ) {
            AsyncImage(
                model = character.image,
                contentDescription = null,
                modifier = Modifier
                    .size(160.dp)
                    .clip(CircleShape)
                    .clickable {
                        // Tap en imagen -> abrir el dialer con el id
                        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${character.id}"))
                        ctx.startActivity(intent)
                    }
            )
        }

        Spacer(Modifier.height(16.dp))
        Text(
            character.name,
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.ExtraBold)
        )
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
