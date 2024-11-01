package com.example.coordinadoraapp.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.coordinadoraapp.R


@Composable
fun typography() = Typography(
    /** H1 */
    titleLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.noto_sans_bold)),
        fontWeight = FontWeight.Bold,
        fontSize = 36.sp
    ),
    /** H2 */
    titleMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.noto_sans_bold)),
        fontWeight = FontWeight.Bold,
        fontSize = 34.sp
    ),
    /** H3 */
    titleSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.noto_sans_semi_bold)),
        fontWeight = FontWeight.SemiBold,
        fontSize = 32.sp
    ),
    /** H4 */
    displayLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.noto_sans_medium)),
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp
    ),
    /** H5 */
    displayMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.noto_sans_medium)),
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    /** H6 */
    displaySmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.noto_sans_medium)),
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.noto_sans_medium)),
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.noto_sans_medium)),
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.noto_sans_medium)),
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    ),
    /** P1 */
    bodyLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.arimo_regular)),
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    ),
    /** P2 */
    bodyMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.arimo_regular)),
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    /** P3 */
    bodySmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.arimo_regular)),
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp

    ),
    /** Label */
    labelLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.arimo_regular)),
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    labelMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.arimo_regular)),
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.arimo_regular)),
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
)