package screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.heyjomui.R
import com.example.heyjomui.ui.theme.inter_bold
import com.example.heyjomui.ui.theme.inter_regular


private val yellowColor = Color(0xFFFBBA00)
private val LavenderGray = Color(0xFFF2F1F9)
private val mutedGray = Color(0xFF747688)
private val darkPurple = Color(0xFF120D26)

@Composable
fun VirtualRunDetailScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LavenderGray)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(yellowColor),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .clickable {
                            navController.navigate("VirtualRunsScreen")
                        }
                )
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 24.dp),
                    text = "Virtual Run Detail",
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    fontFamily = inter_bold,
                    color = Color.Black,
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
        ) {
            ImageSection()
            EventInfoSection()
        }
        ActionButtonsSection()
    }
}

@Composable
fun ImageSection() {
    Image(
        painter = painterResource(id = R.drawable.image_4),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
    )
}

@Composable
fun EventInfoSection() {

    val iconBackgroundColor = Color(0xFFFBBA00)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 190.dp, 16.dp, 8.dp)
            .background(Color.White, shape = RoundedCornerShape(10.dp))
            .border(width = 0.5.dp, color = Color(0xFF495E57), shape = RoundedCornerShape(10.dp))
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {

            Text(
                text = "HeadHunter Virtual Run",
                fontFamily = inter_bold,
                color = Color.Black,
                style = MaterialTheme.typography.headlineSmall.copy(fontSize = 18.sp),
                modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 4.dp)
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
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp),
                    fontFamily = inter_regular,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            // horizontal divider
            Divider(modifier = Modifier.padding(bottom = 8.dp))

            Text(
                text = "Who’s Racing",
                fontFamily = inter_bold,
                color = Color.Black,
                style = MaterialTheme.typography.headlineSmall.copy(fontSize = 14.sp),
                modifier = Modifier.padding(16.dp, 4.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(16.dp, 0.dp, 16.dp, 16.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.running),
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .size(32.dp)
                )

                Column {
                    Text(
                        text = "Alan Walker",
                        color = darkPurple,
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp),
                        fontFamily = inter_bold,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                    Text(
                        text = "Distance: 5.00 Kilometers",
                        color = mutedGray,
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp),
                        fontFamily = inter_regular,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
            Text(
                text = "When You Can Race",
                fontFamily = inter_bold,
                color = darkPurple,
                style = MaterialTheme.typography.headlineSmall.copy(fontSize = 14.sp),
                modifier = Modifier.padding(16.dp, 8.dp, 8.dp, 4.dp)
            )
//.....................................................................................
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 0.dp, 16.dp, 16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.clock),
                        contentDescription = null,
                        tint = iconBackgroundColor,
                        modifier = Modifier
                            .size(24.dp)
                    )
                    Text(
                        text = "Begins: Race is open for Tracking. Good luck !",
                        color = Color.Black,
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 10.sp),
                        fontFamily = inter_bold,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(12.dp, 0.dp, 0.dp, 0.dp)
                        .height(20.dp)
                ) {
                    // Vertical Divider
                    Divider(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(1.dp)
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.clock),
                        contentDescription = null,
                        tint = iconBackgroundColor,
                        modifier = Modifier
                            .size(24.dp)
                    )
                    Text(
                        text = "Ends: 29 Feb 2024 11:59 PM",
                        color = Color.Black,
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 10.sp),
                        fontFamily = inter_bold,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }
//.....................................................................................
        }

    }
}


@Composable
fun ActionButtonsSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(yellowColor)
        ) {
            Text(
                text = "Link Recent Activity",
                color = Color.Black,
                fontFamily = inter_bold,
                fontSize = 14.sp,
            )
        }
        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(Color.White)
        ) {
            Text(
                text = "Submit Manually",
                color = Color.Black,
                fontFamily = inter_bold,
                fontSize = 14.sp,
            )
        }
    }
}
