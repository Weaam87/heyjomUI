package screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
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
private val lightGray = Color(0xFFCCCCCC)
private val charcoalGray = Color(0xFF717171)


@Composable
fun SubmitResultScreen(navController: NavHostController) {
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
                            navController.navigate("virtualRunDetailScreen")
                        }
                )
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 24.dp),
                    text = "Submit Result",
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
                .height(560.dp)
        ) {
            UploadImageSection()
            SubmitEventInfoSection()
        }
        SubmitButtonsSection()
    }
}

@Composable
fun UploadImageSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.upload_image_section),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )

        Image(
            painter = painterResource(id = R.drawable.upload_icon),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.Center)
                .width(96.dp)
                .height(96.dp)
        )
    }
}

@Composable
fun SubmitEventInfoSection() {

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, 190.dp, 16.dp, 8.dp)
            .background(Color.White, shape = RoundedCornerShape(10.dp))
            .border(width = 0.5.dp, color = mutedGray, shape = RoundedCornerShape(10.dp))
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
                    color = yellowColor,
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

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(16.dp, 0.dp, 16.dp, 16.dp)
            ) {
                Column {
                    Text(
                        text = "Category",
                        color = mutedGray,
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp),
                        fontFamily = inter_regular,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                    Text(
                        text = "Category 2: 5KM (Men open are 18-45 yo)",
                        color = darkPurple,
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp),
                        fontFamily = inter_bold,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
            // horizontal divider
            Divider(modifier = Modifier.padding(bottom = 8.dp))

            Column(
                modifier = Modifier.padding(16.dp, 0.dp, 16.dp, 16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Distance (KM)",
                        color = Color.Black,
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp),
                        fontFamily = inter_bold,
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .weight(0.3f)
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .height(48.dp)
                            .border(
                                width = 1.dp,
                                color = lightGray,
                                shape = RoundedCornerShape(50.dp)
                            )
                            .weight(0.7f),
                        contentAlignment = Alignment.Center
                    ) {
                        OutlinedTextField(
                            value = "000.00",
                            onValueChange = { },
                            modifier = Modifier
                                .fillMaxSize(),
                            textStyle = LocalTextStyle.current.copy(
                                color = charcoalGray,
                                fontSize = 14.sp,
                                textAlign = TextAlign.Center
                            ),
                            colors = OutlinedTextFieldDefaults.colors(
                                cursorColor = charcoalGray,
                                focusedBorderColor = Transparent,
                                unfocusedBorderColor = Transparent,
                            )
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Time",
                        color = Color.Black,
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp),
                        fontFamily = inter_bold,
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .weight(0.3f)
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .height(48.dp)
                            .border(
                                width = 1.dp,
                                color = lightGray,
                                shape = RoundedCornerShape(50.dp)
                            )
                            .weight(0.7f)
                    ) {
                        OutlinedTextField(
                            value = "hh:mm:ss",
                            onValueChange = { },
                            modifier = Modifier
                                .fillMaxSize()
                                .align(Alignment.Center),
                            textStyle = LocalTextStyle.current.copy(
                                color = charcoalGray,
                                fontSize = 14.sp,
                                textAlign = TextAlign.Center
                            ),
                            colors = OutlinedTextFieldDefaults.colors(
                                cursorColor = charcoalGray,
                                focusedBorderColor = Transparent,
                                unfocusedBorderColor = Transparent,
                            )
                        )

                    }
                }
                Text(
                    text = "e.g: Moving Time / Total Distance Time / Duration",
                    color = charcoalGray,
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 10.sp),
                    fontFamily = inter_regular,
                    modifier = Modifier.padding(32.dp, 12.dp)
                )
            }
        }
    }
}


@Composable
fun SubmitButtonsSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(lightGray)
        ) {
            Text(
                text = "Submit",
                color = Color.Black,
                fontFamily = inter_bold,
                fontSize = 14.sp,
            )
        }
        Text(
            text = "By clicking Submit, you declare that your submission\n" +
                    "is truthful and agree to our Terms & Conditions",
            color = charcoalGray,
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 10.sp),
            fontFamily = inter_regular,
            modifier = Modifier
                .padding(32.dp, 12.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}
