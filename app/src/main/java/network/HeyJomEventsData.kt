package network

import com.squareup.moshi.Json

data class HeyJomEventsData(val id : String , @Json(name = "img_src") val imgSrcUrl : String) {
}