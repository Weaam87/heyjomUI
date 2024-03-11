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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.heyjomui.R
import com.example.heyjomui.ui.theme.inter_bold
import com.example.heyjomui.ui.theme.inter_regular
import kotlinx.coroutines.runBlocking
import network.HeyJomApi
import network.HeyJomEventsData

val apiService = HeyJomApi.retrofitService

// Fetch the list of events using a coroutine
val eventsList: List<HeyJomEventsData> = try {
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
                imageVector = Icons.Default.ArrowBack,
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
fun HorizontalCardView(imageResId: Int, title: String, index: Int, navController: NavController) {

    Row(
        modifier = Modifier
            .clickable {
                navController.navigate("virtualRunDetailScreen")
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
                    painter = painterResource(id = imageResId),
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
                        if (index == 1) colorResource(id = R.color.charcoalGray) else colorResource(
                            id = R.color.yellowColor
                        ),
                    ),
                    contentPadding = PaddingValues(horizontal = 2.dp)
                ) {
                    Text(
                        text = if (index == 1) "Closed" else "Active",
                        color = if (index == 1) Color.White else Color.Black,
                        fontFamily = inter_bold,
                        fontSize = 14.sp,
                    )
                }
            }
            Text(
                text = title,
                fontFamily = inter_bold,
                style = MaterialTheme.typography.headlineSmall.copy(fontSize = 14.sp),
                color = Color.Black,
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
            Divider(modifier = Modifier.padding(8.dp))

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
                Divider(
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
fun ScrollableHorizontalCardViews(navController: NavController) {
    val state = rememberScrollState()

    Row(
        modifier = Modifier
            .background(colorResource(id = R.color.lavenderGray))
            .padding(8.dp, 8.dp, 8.dp, 24.dp)
            .horizontalScroll(state)
    ) {
        repeat(3) { index ->
            val (title, imageResId) = getHorizontalCardInfoForIndex(index)

            HorizontalCardView(
                imageResId = imageResId,
                title = title,
                index = index,
                navController = navController
            )
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

private fun getHorizontalCardInfoForIndex(index: Int): Pair<String, Int> {
    val title = when (index) {
        0 -> "HeadHunter Virtual Run"
        1 -> "New Year Virtual Run 2024"
        2 -> "BEASTMODE \"Pain is Fuel' 42K VR"
        else -> "Default Title"
    }

    val imageResId = when (index) {
        0 -> R.drawable.image_4
        1 -> R.drawable.image_5
        2 -> R.drawable.image_6
        else -> R.drawable.ic_launcher_foreground
    }

    return title to imageResId
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

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Header()
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
                // Display the content when eventsList is not empty
                ScrollableHorizontalCardViews(navController)
                VirtualRuns()
                VerticalCardViewsList(events = eventsList)
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