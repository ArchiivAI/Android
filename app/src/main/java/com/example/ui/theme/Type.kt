package com.example.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.archivai.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)


val play_fair_font = FontFamily(
    Font(R.font.playfair_extrabold , FontWeight.Normal)

)
val rubik_regular = FontFamily(
    Font(R.font.rubik_regular, FontWeight.Normal)
)

val rubik_bold = FontFamily(
    Font(R.font.rubik_bold, FontWeight.Bold)
)

val rubik_medium = FontFamily(
    Font(R.font.rubik_medium, FontWeight.Medium)
)

val rubik_light = FontFamily(
    Font(R.font.rubik_light, FontWeight.Light)
)

val rubik_semibold = FontFamily(
    Font(R.font.rubik_semibold, FontWeight.SemiBold)
)

val rubik_extrabold = FontFamily(
    Font(R.font.rubik_extra_bold, FontWeight.ExtraBold)
)

val rubik_black = FontFamily(
    Font(R.font.rubik_black, FontWeight.Black)
)

// Add italic variants if you have them
val rubik_italic = FontFamily(
    Font(R.font.rubik_italic, FontWeight.Normal)
)

val rubik_bold_italic = FontFamily(
    Font(R.font.rubik_bold_italic, FontWeight.Bold)
)

val rubik_medium_italic = FontFamily(
    Font(R.font.rubik_mediumitalic, FontWeight.Medium)
)
val RubikTypography = Typography(
    titleLarge = TextStyle(
        fontFamily = rubik_bold,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp, // Match FolderCard
        lineHeight = 20.sp,
        letterSpacing = 0.5.sp
    ),
    labelSmall = TextStyle(
        fontFamily = rubik_medium,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp, // Match FolderCard
        lineHeight = 18.sp,
        letterSpacing = 0.5.sp
    )
    // Add other styles as needed
)