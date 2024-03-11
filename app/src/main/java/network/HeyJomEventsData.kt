package network

data class HeyJomEventsData(
    val id: Int,
    val name: String,
    val type: String,
    val banner: String,
    val date: String
)

data class HeyJomEventsResponse(
    val events: List<HeyJomEventsData>
)
