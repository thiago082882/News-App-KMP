package org.thiago.project.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.thiago.project.data.model.Article

@Serializable
data class NewsResponse(
    @SerialName("articles")
    val articles: List<Article>,
    @SerialName("status")
    val status: String,
    @SerialName("totalResults")
    val totalResults: Int
)