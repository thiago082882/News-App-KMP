package org.thiago.project.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import coil3.compose.AsyncImage
import kmp_news_app.composeapp.generated.resources.Res
import kmp_news_app.composeapp.generated.resources.logo
import org.jetbrains.compose.resources.painterResource
import org.thiago.project.data.model.Article
import org.thiago.project.theme.imageSize
import org.thiago.project.theme.mediumPadding
import org.thiago.project.theme.xxSmallPadding



@Composable
fun ArticleItem(
    article: Article,
    onClick: () -> Unit
) {

    Row(
        modifier = Modifier.clickable { onClick() },
        horizontalArrangement = Arrangement.spacedBy(mediumPadding)
    ) {

        AsyncImage(
            modifier = Modifier
                .size(imageSize)
                .clip(MaterialTheme.shapes.large)
                .background(Color.Gray),
            model = article.urlToImage,
            error = painterResource(Res.drawable.logo),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(xxSmallPadding)
        ) {
            Text(
                text = article.title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )

            article.description?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2
                )
            }

            Text(
                text = article.source.name,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}
