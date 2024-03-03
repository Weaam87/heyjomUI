package com.example.heyjomui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
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
            .height(80.dp),
        contentColor = Color.Black,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFBBA00)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center // Set to Center to make the text center-aligned
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                modifier = Modifier.padding(start = 16.dp)
            )
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 24.dp), // This will make the Text take the available space and center it
                text = "Virtual Runs",
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                fontFamily = inter_bold,
            )
        }
    }
}

@Composable
fun HorizontalCardView() {

    // Define the colors for the icon background and the dividers
    val iconBackgroundColor = Color(0xFFFBBA00)

    // Create a card with some padding and a column layout
    Row(
        modifier = Modifier
            .width(340.dp)
            .padding(horizontal = 16.dp)
            .background(Color.White)
            .border(
                BorderStroke(
                    width = 0.5.dp,
                    color = Color(0xFF495E57),
                ),
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // Display the image at the top of the card with rounded corners
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(178.dp)
            ) {
                // Display the image
                Image(
                    painter = painterResource(id = R.drawable.image_4),
                    contentDescription = null,
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

                // Add the "Active" button with a white background in the top-right corner
                Button(
                    onClick = { /* Handle button click */ },
                    modifier = Modifier
                        .padding(8.dp)
                        .absolutePadding(top = 16.dp, right = 16.dp)
                ) {
                    Text(text = "Active")
                }
            }
            // Display the text "HeadHunter Virtual Run" below the image
            Text(
                text = "HeadHunter Virtual Run",
                fontFamily = inter_bold,
                style = MaterialTheme.typography.headlineSmall.copy(fontSize = 16.sp),
                modifier = Modifier.padding(16.dp, 4.dp)
            )
            // Create a row layout to display the icon and the text "5km Virtual Run"
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
                // Display the text "5km Virtual Run" next to the icon with some spacing
                Text(
                    text = "5km Virtual Run",
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp),
                    fontFamily = inter_regular,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            // Display a horizontal divider below the row layout
            Divider(modifier = Modifier.padding(8.dp))
            // Create another row layout to display the texts in two columns
            Row(modifier = Modifier.padding(horizontal = 16.dp)) {
                // Display the texts "Submission Opened" and "Multiple Submissions" in the left column
                Column(modifier = Modifier
                    .weight(1f)
                    .padding(bottom = 32.dp, top = 16.dp)) {
                    Text(
                        text = "Submission Opened",
                        fontFamily = inter_regular,
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp),
                    )
                    Text(
                        text = "Multiple Submissions",
                        fontFamily = inter_bold,
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp),
                    )
                }
                // Display a vertical divider in the middle of the row layout
                // Vertical Divider
                Divider(
                    modifier = Modifier
                        .height(72.dp)
                        .width(1.dp)
                        .padding(top = 8.dp)
                )
                // Display the texts "Progress" and "Result Submitted" in the right column
                Column(modifier = Modifier
                    .weight(1f)
                    .padding(bottom = 32.dp, top = 16.dp)) {
                    Text(
                        text = "Progress",
                        fontFamily = inter_regular,
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp),
                        modifier = Modifier.padding(start = 16.dp)
                    )
                    Text(
                        text = "Result Submitted",
                        fontFamily = inter_bold,
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp),
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
        }
    }
}

