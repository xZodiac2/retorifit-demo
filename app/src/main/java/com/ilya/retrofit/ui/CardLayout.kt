package com.ilya.retrofit.ui

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CardLayout(content: @Composable ColumnScope.() -> Unit) {
    Card(
        modifier = Modifier
            .padding(30.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(92, 0, 255, 255)),
        elevation = CardDefaults.cardElevation(16.dp),
        content = content
    )
}