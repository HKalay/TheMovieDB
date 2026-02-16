package com.kalay.themoviedb.core.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.kalay.themoviedb.R
import com.kalay.themoviedb.core.theme.Dark1Color
import com.kalay.themoviedb.core.theme.font.urbanistTypography
import com.kalay.themoviedb.domain.model.remote.Discover

@Composable
fun MovieCard(
    discover: Discover,
    onClick: () -> Unit,
    updateFavoriteStatus: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Dark1Color
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(0.66f),
                contentAlignment = Alignment.Center
            ) {

                val icon = if (discover.isFavorite)
                    R.drawable.ic_favorite_selected
                else
                    R.drawable.ic_favorite_un_selected

                NetworkImage(
                    imageUrl = discover.posterPath,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(12.dp))
                )

                Image(
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(12.dp)
                        .clickable {
                           updateFavoriteStatus()
                        }
                )

            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = discover.title,
                style = urbanistTypography().typography.headlineSmall.copy(color = Color.White),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .height(
                        urbanistTypography().typography.headlineSmall.lineHeight.value.dp * 2
                    )
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_vote),
                        contentDescription = null,
                        tint = Color.Yellow,
                        modifier = Modifier
                            .size(16.dp)
                            .padding(end = 4.dp)
                    )

                    Text(
                        text = discover.voteAverage,
                        style = urbanistTypography().typography.bodySmall.copy(color = Color.White)
                    )
                }

                Text(
                    text = discover.releaseDate.split("-").getOrNull(0) ?: "-",
                    style = urbanistTypography().typography.bodySmall.copy(color = Color.Gray)
                )
            }
        }
    }
}