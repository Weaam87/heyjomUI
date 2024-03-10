package network

import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://devapi.heyjom.com/api/v4/"
private const val AUTHORIZATION = "dcZywoFjYBMuKA2LLqj8UJu7vUrXgXRErDm1e39x9Xj1L6eEXVtQbZSrm4mO"

private val moshi =
    Moshi.Builder().add(com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory()).build()

private val retrofit =
    Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(
        BASE_URL
    ).build()

interface HeyJomApiService {
    @GET("events")
    suspend fun getEvents(): List<HeyJomEventsData>
}

object HeyJomApi {
    val retrofitService: HeyJomApiService by lazy {
        retrofit.create(HeyJomApiService::class.java)
    }
}