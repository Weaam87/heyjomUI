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


data class HeyJomEventDetailsData(
    val id: Int,
    val name: String,
    val description: String,
    val cover: String,
    val registrationStartDate: Long,
    val registrationEndDate: Long,
    val venue: String,
    val latitude: Double,
    val longitude: Double,
    val repcVenue: String,
    val organizer: String,
    val type: String,
    val isActive: Int,
    val themeColor: String,
    val repcDates: List<Long>,
    val dates: List<Long>
)


data class HeyJomEventDetailsResponse(
    val event: HeyJomEventDetailsData
)
