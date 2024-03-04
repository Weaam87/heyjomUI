package com.example.heyjomui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.heyjomui.ui.theme.inter_bold
import com.example.heyjomui.ui.theme.inter_regular

@Composable
fun Header() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        contentColor = Color.Black,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFBBA00)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
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
            .background(color = Color(0xFFD9D9D9))
            .padding(16.dp, 8.dp),
        fontSize = 18.sp,
        color = Color.Black,
        textAlign = TextAlign.Start,
        fontFamily = inter_bold
    )
}

@Composable
fun HorizontalCardView(imageResId: Int, title: String, index: Int) {
    val iconBackgroundColor = Color(0xFFFBBA00)

    Row(
        modifier = Modifier
            .width(340.dp)
            .padding(horizontal = 8.dp)
            .background(Color.White, shape = RoundedCornerShape(10.dp))
            .border(width = 0.5.dp, color = Color(0xFF495E57), shape = RoundedCornerShape(10.dp))
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
                        if (index == 1) Color(0xFF5B5B5B) else iconBackgroundColor,
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
                modifier = Modifier.padding(16.dp, 4.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                // Display the icon inside a circle with the icon background color
                Surface(
                    shape = CircleShape,
                    color = iconBackgroundColor,
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
                        style = MaterialTheme.typography.labelLarge.copy(fontSize = 12.sp),
                    )
                    Text(
                        text = "Multiple Submissions",
                        fontFamily = inter_bold,
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
                        style = MaterialTheme.typography.labelLarge.copy(fontSize = 12.sp),
                        modifier = Modifier.padding(start = 16.dp)
                    )
                    Text(
                        text = "Result Submitted",
                        fontFamily = inter_bold,
                        style = MaterialTheme.typography.labelLarge.copy(fontSize = 12.sp),
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
        }
    }
}


@Composable
fun ScrollableHorizontalCardViews() {
    val state = rememberScrollState()

    Row(
        modifier = Modifier
            .background(color = Color(0xFFD9D9D9))
            .padding(8.dp, 8.dp, 8.dp, 24.dp)
            .horizontalScroll(state)
    ) {
        repeat(3) { index ->
            val (title, imageResId) = getHorizontalCardInfoForIndex(index)

            HorizontalCardView(
                imageResId = imageResId,
                title = title,
                index = index,
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
        color = Color.Black,
        textAlign = TextAlign.Start,
        fontFamily = inter_bold
    )
}

@Composable
fun VerticalCardView(imageResId: Int, title: String) {
    val iconBackgroundColor = Color(0xFFFBBA00)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 8.dp)
            .background(Color.White, shape = RoundedCornerShape(10.dp))
            .border(width = 0.5.dp, color = Color(0xFF495E57), shape = RoundedCornerShape(10.dp))
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = imageResId),
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
                text = title,
                fontFamily = inter_bold,
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
                    color = iconBackgroundColor,
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


fun getVerticalCardInfoForIndex(index: Int): Pair<String, Int> {
    val title = when (index) {
        0 -> "Kopi Cats 5K VR"
        1 -> "Gamer Life 5K VR"
        2 -> "KOI Dash Virtual Run"
        else -> "Default Title"
    }

    val imageResId = when (index) {
        0 -> R.drawable.image_1
        1 -> R.drawable.image_2
        2 -> R.drawable.image_3
        else -> R.drawable.ic_launcher_foreground
    }

    return title to imageResId
}

@Composable
fun VerticalCardViewsList() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        repeat(3) { index ->
            val (title, imageResId) = getVerticalCardInfoForIndex(index)
            VerticalCardView(
                imageResId = imageResId,
                title = title
            )
        }
    }
}
