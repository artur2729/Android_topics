package lt.arturas.androidtopics.repository.newsapi

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceWithOkHttp {
    @GET("v2/top-headlines")
    suspend fun getTopNews(
        @Query("country") country: String,
        @Query("page") page: Int = 1,
        @Query("pageSize") pageSize: Int = 20
    ): Response<TopHeadlinesResponse>
}
