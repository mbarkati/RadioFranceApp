package com.example.radiofranceapp.presentation.brand_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.radiofranceapp.R
import com.example.radiofranceapp.domain.model.SimpleBrand

@Composable
fun BrandListItem(
    brand: SimpleBrand,
    onClick: () -> Unit
) {
        Card(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth(),
            elevation = 2.dp,
            shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        ) {
            Row(Modifier.clickable { onClick() }) {
                Image(
                    painter = painterResource(id = R.drawable.station_logo),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(8.dp)
                        .size(84.dp)
                        .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
                )
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .align(Alignment.CenterVertically)
                ) {
                    Text(text = brand.title, style = typography.h6, color = Color.Blue)
                    Text(text = brand.description, style = typography.caption)
                }
            }
        }
    }
