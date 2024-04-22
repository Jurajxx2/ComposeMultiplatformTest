package theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import nunitoExtraBoldFontFamily
import nunitoMediumFontFamily

val typography = Typography(
    h1 = TextStyle(
        fontFamily = nunitoExtraBoldFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp
    ),
    h2 = TextStyle(
        fontFamily = nunitoExtraBoldFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),
    h3 = TextStyle(
        fontFamily = nunitoExtraBoldFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    body1 = TextStyle(
        fontFamily = nunitoMediumFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontFamily = nunitoMediumFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    )
)