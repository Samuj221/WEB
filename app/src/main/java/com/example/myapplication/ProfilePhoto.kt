package com.example.myapplication

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ProfilePhoto(modifier: Modifier = Modifier) {
    var visible by remember {
        mutableStateOf(true)
    }
    val context: Context = LocalContext.current

    AnimatedVisibility(visible, exit = fadeOut() + slideOutVertically() + shrinkOut()) {
        Box() {
            Icon(
                Icons.Default.Person, contentDescription = "Artist image",
                modifier = modifier
                    .clip(CircleShape)
                    .clickable(enabled = true, onClick = {
                        visible = !visible
                        Toast.makeText(context, "Image clicked", Toast.LENGTH_SHORT).show()
                    }),
                tint = MaterialTheme.colorScheme.inversePrimary
            )
            Icon(
                Icons.Filled.Check,
                contentDescription = "Check mark",
                tint = Color.Green,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(24.dp)
                    .align(Alignment.BottomEnd)
                    .background(Color.White)
            )
        }
    }
}

@Preview
@Composable
fun ProfilePhotoPreview() {
    ProfilePhoto(
        modifier = Modifier.size(64.dp)
    )
}