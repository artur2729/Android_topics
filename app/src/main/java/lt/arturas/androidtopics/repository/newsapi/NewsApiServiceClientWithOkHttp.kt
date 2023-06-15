package lt.arturas.androidtopics.repository.newsapi

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NewsApiServiceClientWithOkHttp {
    private const val BASE_URL = "https://newsapi.org/"

    val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val newRequest = chain.request().newBuilder()
                .addHeader("x-api-key", "a29f539bc9ad4aa6ade2f05a6e848e3d") //a29f539bc9ad4aa6ade2f05a6e848e3d  //144e0c5c31324ea199b7e9cb410e96d0
                .build()
            chain.proceed(newRequest)
        }
        .build()

    fun providesApiService(): ApiServiceWithOkHttp {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().create()
                )
            )
            .build()
            .create(ApiServiceWithOkHttp::class.java)
    }
}
