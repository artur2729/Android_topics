package lt.arturas.androidtopics.repository.newsapi

data class TopHeadlinesResponse(
    val status: String = "",
    val totalResults: Int = -1,
    val articles: MutableList<Article> = mutableListOf()
)
