package com.ilya.retrofit.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ilya.data.retrofit.Post


@Composable
fun Post(postData: Post) {
    CardLayout {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = postData.title,
                fontSize = 30.sp,
                modifier = Modifier.padding(bottom = 10.dp),
                color = Color.White
            )
            Text(text = postData.body, fontSize = 20.sp, color = Color.White)
        }
    }
}