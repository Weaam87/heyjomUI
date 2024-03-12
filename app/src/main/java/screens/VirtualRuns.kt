package screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.heyjomui.R
import com.example.heyjomui.ui.theme.inter_bold
import com.example.heyjomui.ui.theme.inter_regular
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import network.HeyJomApi
import network.HeyJomEventsData

val apiService = HeyJomApi.retrofitService

// Fetch the list of events using a coroutine
var eventsList: List<HeyJomEventsData> = try {
    runBlocking {
        Log.d("MyApp", "Attempting to fetch events...")

        // Fetch the response object
        val response = apiService.getEvents()

        // Check if the response contains the list of events
        val events = response.events

        Log.d("MyApp", "Successfully fetched ${events.size} events.")
        events
    }
} catch (e: Exception) {
    Log.e("MyApp", "Error fetching events: ${e.message}")
    emptyList()
}


@Composable
fun Header() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.yellowColor)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.padding(start = 16.dp)
            )
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 24.dp),
                text = "Virtual Runs",
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                fontFamily = inter_bold,
                color = Color.Black,
            )
        }
    }
}

@Composable
fun RegisteredEvents() {
    Text(
        text = "Registered Events (3)",
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.lavenderGray))
            .padding(16.dp, 8.dp),
        fontSize = 18.sp,
        textAlign = TextAlign.Start,
        color = Color.Black,
        fontFamily = inter_bold
    )
}

@Composable
fun HorizontalCardView(event: HeyJomEventsData, navController: NavController) {
    Row(
        modifier = Modifier
            .clickable {
                navController.navigate("virtualRunDetailScreen/${event.id}")
            }
            .width(340.dp)
            .padding(horizontal = 8.dp)
            .background(Color.White, shape = RoundedCornerShape(10.dp))
            .border(
                width = 0.5.dp,
                color = colorResource(id = R.color.darkSeaGreen),
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(178.dp)
            ) {
                Image(
                    painter = rememberImagePainter(data = event.banner),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .clip(
                            shape = RoundedCornerShape(
                                topStart = 10.dp,
                                topEnd = 10.dp,
                                bottomStart = 0.dp,
                                bottomEnd = 0.dp
                            )
                        )
                )

                Button(
                    onClick = { /* Handle button click */ },
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.TopEnd)
                        .width(80.dp)
                        .height(30.dp),
                    colors = ButtonDefaults.buttonColors(
                        if (event.id == 10) colorResource(id = R.color.charcoalGray) else colorResource(
                            id = R.color.yellowColor
                        ),
                    ),
                    contentPadding = PaddingValues(horizontal = 2.dp)
                ) {
                    Text(
                        text = if (event.id == 10) "Closed" else "Active",
                        color = if (event.id == 10) Color.White else Color.Black,
                        fontFamily = inter_bold,
                        fontSize = 14.sp,
                    )
                }
            }
            Text(
                event.name,
                fontFamily = inter_bold,
                style = MaterialTheme.typography.headlineSmall.copy(fontSize = 14.sp),
                color = Color.Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(16.dp, 4.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                // Display the icon inside a circle with the icon background color
                Surface(
                    shape = CircleShape,
                    color = colorResource(id = R.color.yellowColor),
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.safety_glasse),
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )
                }
                Text(
                    text = "5km Virtual Run",
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp),
                    fontFamily = inter_regular,
                    color = Color.Black,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            // horizontal divider
            HorizontalDivider(modifier = Modifier.padding(8.dp))

            // Create another row layout to display the texts in two columns
            Row(modifier = Modifier.padding(horizontal = 16.dp)) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(bottom = 32.dp, top = 16.dp)
                ) {
                    Text(
                        text = "Submission Opened",
                        fontFamily = inter_regular,
                        color = Color.Black,
                        style = MaterialTheme.typography.labelLarge.copy(fontSize = 12.sp),
                    )
                    Text(
                        text = "Multiple Submissions",
                        fontFamily = inter_bold,
                        color = Color.Black,
                        style = MaterialTheme.typography.labelLarge.copy(fontSize = 12.sp),
                    )
                }
                // Vertical Divider
                VerticalDivider(
                    modifier = Modifier
                        .height(72.dp)
                        .width(1.dp)
                        .padding(top = 4.dp)
                )
                // Display the texts in the right column
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(bottom = 32.dp, top = 16.dp)
                ) {
                    Text(
                        text = "Progress",
                        fontFamily = inter_regular,
                        color = Color.Black,
                        style = MaterialTheme.typography.labelLarge.copy(fontSize = 12.sp),
                        modifier = Modifier.padding(start = 16.dp)
                    )
                    Text(
                        text = "Result Submitted",
                        fontFamily = inter_bold,
                        color = Color.Black,
                        style = MaterialTheme.typography.labelLarge.copy(fontSize = 12.sp),
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ScrollableHorizontalCardViews(navController: NavController, events: List<HeyJomEventsData>) {
    Row(
        modifier = Modifier
            .background(colorResource(id = R.color.lavenderGray))
            .padding(8.dp, 8.dp, 8.dp, 24.dp)
            .horizontalScroll(rememberScrollState())
    ) {
        events.forEach { event ->
            HorizontalCardView(event = event, navController = navController)
        }
    }
}

@Composable
fun VirtualRuns() {
    Text(
        text = "Virtual Runs Nearby",
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 8.dp),
        fontSize = 18.sp,
        textAlign = TextAlign.Start,
        fontFamily = inter_bold
    )
}

@Composable
fun VerticalCardView(event: HeyJomEventsData) {
    Log.d("MyApp", "Image URL: ${event.banner}")

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 8.dp)
            .background(Color.White, shape = RoundedCornerShape(10.dp))
            .border(
                width = 0.5.dp,
                color = colorResource(id = R.color.darkSeaGreen),
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = rememberImagePainter(data = event.banner),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(178.dp)
                    .clip(
                        shape = RoundedCornerShape(
                            topStart = 10.dp,
                            topEnd = 10.dp,
                            bottomStart = 0.dp,
                            bottomEnd = 0.dp
                        )
                    )
            )
            Text(
                text = event.name,
                fontFamily = inter_bold,
                color = Color.Black,
                style = MaterialTheme.typography.headlineSmall.copy(fontSize = 14.sp),
                modifier = Modifier.padding(16.dp, 4.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(16.dp, 0.dp, 16.dp, 16.dp)
            ) {
                // Display the icon inside a circle with the icon background color
                Surface(
                    shape = CircleShape,
                    color = colorResource(id = R.color.yellowColor),
                    modifier = Modifier
                        .size(32.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.safety_glasse),
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )
                }
                Text(
                    text = "5km Virtual Run",
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp),
                    fontFamily = inter_regular,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}

@Composable
fun VerticalCardViewsList(events: List<HeyJomEventsData>) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        events.forEach { event ->
            VerticalCardView(event = event)
        }
    }
}

// ...

@Composable
fun VirtualRunsScreen(navController: NavHostController) {

    val refreshingState = remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    suspend fun refreshData() {
        refreshingState.value = true // Indicate that the refresh process has started

        try {
            // Fetch the latest data
            val response = apiService.getEvents()

            // Update your list with the new data
            eventsList = response.events
        } catch (e: Exception) {
            // Handle any errors here
            Log.e("MyApp", "Error fetching events: ${e.message}")
        } finally {
            refreshingState.value = false // Indicate that the refresh process has ended
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Header()
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing = refreshingState.value),
            onRefresh = {
                // Launch a coroutine within the scope
                coroutineScope.launch {
                    refreshData()
                }
            },
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                RegisteredEvents()

                // Check if eventsList is empty
                if (eventsList.isEmpty()) {
                    // Display wifi_error image and show toast message
                    Image(
                        painter = painterResource(id = R.drawable.wifi_error),
                        contentDescription = null,
                        modifier = Modifier
                            .width(200.dp)
                            .height(200.dp)
                            .padding(32.dp)
                            .align(alignment = Alignment.CenterHorizontally)
                    )
                    Toast("Error fetching events: ${getError()}")
                } else {
                    ScrollableHorizontalCardViews(navController, events = eventsList)
                    VirtualRuns()
                    VerticalCardViewsList(events = eventsList)
                }
            }
        }
    }
}

@Composable
fun Toast(message: String) {
    Text(
        text = message,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(color = Color.Gray, shape = RoundedCornerShape(8.dp)),
        color = Color.White,
        textAlign = TextAlign.Center
    )
}

fun getError(): String {
    return try {
        runBlocking {
            // Attempt to fetch events and capture the exception message if an error occurs
            val response = apiService.getEvents()
            response.events.toString()
        }
    } catch (e: Exception) {
        e.message ?: "Unknown error"
    }
}