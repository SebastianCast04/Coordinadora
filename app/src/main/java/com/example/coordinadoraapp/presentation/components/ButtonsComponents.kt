package com.example.coordinadoraapp.presentation.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coordinadoraapp.presentation.enums.RightLeftEnum
import com.example.coordinadoraapp.presentation.theme.colorScheme
import com.example.coordinadoraapp.presentation.theme.typography

@Composable
fun ButtonComponent(
    text: String,
    typography: androidx.compose.ui.text.TextStyle = typography().bodySmall,
    textAlign: TextAlign? = TextAlign.Center,
    icon: ImageVector? = null,
    iconPosition: RightLeftEnum = RightLeftEnum.LEFT,
    sizeIcon: Dp? = 24.dp,
    setFontWeight: FontWeight? = null,
    textColor: Color = colorScheme.inverseSurface,
    iconColor: Color = Color(0xFF2C421F),
    backgroundColor: Color = Color.White,
    backgroundColorDisabled: Color = Color(0xFFC8C8C8),
    disabled: Boolean = false,
    buttonType: ButtonType = ButtonType.Button,
    fontSize: TextUnit = 16.sp,
    buttonStyle: (Modifier) -> Modifier = { it },
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    squareShape: Shape = MaterialTheme.shapes.small,
    onClick: () -> Unit = {}
) {
    when (buttonType) {
        ButtonType.TextButton -> TextButton(
            onClick = onClick,
            enabled = !disabled,
            shape = squareShape,
            colors = ButtonDefaults.textButtonColors(
                containerColor = backgroundColor,
                disabledContainerColor = backgroundColorDisabled,
            )
        ) {
            ButtonContent(
                text = text,
                typography,
                textAlign,
                icon = icon,
                iconPosition,
                sizeIcon,
                textColor,
                iconColor,
                fontSize = fontSize,
                setFontWeight = setFontWeight,
                horizontalArrangement = horizontalArrangement,
                modifier = buttonStyle
            )
        }

        ButtonType.ElevatedButton -> ElevatedButton(
            onClick = onClick,
            enabled = !disabled,
            shape = squareShape,
            colors = ButtonDefaults.textButtonColors(
                containerColor = backgroundColor,
                disabledContainerColor = backgroundColorDisabled,
            )
        ) {
            ButtonContent(
                text = text,
                typography,
                textAlign,
                icon = icon,
                iconPosition,
                sizeIcon,
                textColor,
                iconColor,
                fontSize = fontSize,
                setFontWeight = setFontWeight,
                horizontalArrangement = horizontalArrangement,
                modifier = buttonStyle
            )
        }

        ButtonType.OutlinedButton -> OutlinedButton(
            onClick = onClick,
            enabled = !disabled,
            border = BorderStroke(1.2.dp, backgroundColor),
            shape = squareShape,
            colors = ButtonDefaults.textButtonColors(
                disabledContainerColor = backgroundColorDisabled,
            )
        ) {
            ButtonContent(
                text = text,
                typography,
                textAlign,
                icon = icon,
                iconPosition,
                sizeIcon,
                textColor,
                iconColor,
                fontSize = fontSize,
                setFontWeight = setFontWeight,
                horizontalArrangement = horizontalArrangement,
                modifier = buttonStyle
            )
        }

        ButtonType.FilledTonalButton -> FilledTonalButton(
            onClick = onClick,
            enabled = !disabled,
            shape = squareShape,
            colors = ButtonDefaults.textButtonColors(
                containerColor = backgroundColor,
                disabledContainerColor = backgroundColorDisabled,
            )
        ) {
            ButtonContent(
                text = text,
                typography,
                textAlign,
                icon = icon,
                iconPosition,
                sizeIcon,
                textColor,
                iconColor,
                fontSize = fontSize,
                setFontWeight = setFontWeight,
                horizontalArrangement = horizontalArrangement,
                modifier = buttonStyle
            )
        }

        ButtonType.Button -> Button(
            onClick = onClick,
            enabled = !disabled,
            colors = ButtonDefaults.buttonColors(
                containerColor = backgroundColor,
                disabledContainerColor = backgroundColorDisabled,
            ),
            shape = squareShape,
        ) {
            ButtonContent(
                text = text,
                typography,
                textAlign,
                icon = icon,
                iconPosition,
                sizeIcon,
                textColor,
                iconColor,
                fontSize = fontSize,
                setFontWeight = setFontWeight,
                horizontalArrangement = horizontalArrangement,
                modifier = buttonStyle
            )

        }
        ButtonType.ButtonWithLine -> Box(
            modifier = Modifier
                .clickable(enabled = !disabled, onClick = onClick)
                .padding(vertical = 8.dp, horizontal = 16.dp),
        ) {
            Column {
                ButtonContent(
                    text = text,
                    icon = icon,
                    iconPosition = RightLeftEnum.RIGHT,
                    sizeIcon = sizeIcon,
                    textColor = textColor,
                    iconColor = iconColor,
                    fontSize = fontSize,
                    setFontWeight = setFontWeight,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = buttonStyle
                )
                Divider(color = textColor, thickness = 1.dp)
            }
        }
    }
}

@Composable
private fun ButtonContent(
    text: String,
    typography: androidx.compose.ui.text.TextStyle = typography().bodySmall,
    textAlign: TextAlign? = TextAlign.Center,
    icon: ImageVector?,
    iconPosition: RightLeftEnum = RightLeftEnum.RIGHT,
    sizeIcon: Dp? = 28.dp,
    textColor: Color,
    iconColor: Color,
    fontSize: TextUnit,
    setFontWeight: FontWeight? = null,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    modifier: (Modifier) -> Modifier = { it },
) {
    Row(
        modifier = modifier(Modifier),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if ((iconPosition == RightLeftEnum.LEFT) && icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconColor,
                modifier = sizeIcon?.let { Modifier.size(it) } ?: Modifier)
            Spacer(modifier = Modifier.width(8.dp))
        }

        Text(
            text = text,
            style = typography,
            color = textColor,
            fontSize = fontSize,
            textAlign = textAlign,
            fontWeight = setFontWeight
        )

        if ((iconPosition == RightLeftEnum.RIGHT) && icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconColor,
                modifier = sizeIcon?.let { Modifier.size(it) } ?: Modifier)
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}

enum class ButtonType {
    TextButton,
    ElevatedButton,
    OutlinedButton,
    FilledTonalButton,
    Button,
    ButtonWithLine
}


@Composable
fun ButtonComponentLogin(
    text: String,
    icon: ImageVector? = null,
    iconPosition: RightLeftEnum = RightLeftEnum.LEFT,
    iconColor: Color = colorScheme.error,
    sizeIcon: Dp? = 24.dp,
    setFontWeight: FontWeight? = null,
    textColor: Color = colorScheme.inverseSurface,
    backgroundColor: Color = colorScheme.error,
    disabled: Boolean = false,
    buttonType: ButtonTypeLogin = ButtonTypeLogin.ButtonWithLine,
    fontSize: TextUnit = 16.sp,
    borderStroke: BorderStroke? = null,
    defaultText: String? = "",
    buttonStyle: (Modifier) -> Modifier = { it },
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Center,
    squareShape: Shape = MaterialTheme.shapes.small,
    onClick: () -> Unit = {},

    ) {

    val displayText = if (text.isEmpty()) defaultText else text

    when (buttonType) {

        ButtonTypeLogin.ButtonWithLine -> Box(
            modifier = Modifier
                .clickable(enabled = !disabled, onClick = onClick)
                .padding(vertical = 8.dp, horizontal = 16.dp)
        ) {
            Column {
                ButtonContentLogin(
                    text = displayText ?: "",
                    icon = icon,
                    iconPosition = RightLeftEnum.RIGHT,
                    iconColor = iconColor,
                    sizeIcon = sizeIcon,
                    textColor = textColor,
                    fontSize = fontSize,
                    setFontWeight = setFontWeight,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = buttonStyle,

                    )
                Divider(color = backgroundColor, thickness = 1.dp)
            }
        }

        ButtonTypeLogin.CircularButton -> ElevatedButton(
            onClick = onClick,
            enabled = !disabled,
            border = borderStroke,
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = if (disabled) Color(0XFFC8C8C8) else backgroundColor,
                disabledContainerColor = Color(0XFFC8C8C8)
            ),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                ButtonContentLogin(
                    text = displayText ?: "",
                    icon = icon,
                    iconPosition = iconPosition,
                    sizeIcon = sizeIcon,
                    textColor = textColor,
                    fontSize = fontSize,
                    setFontWeight = setFontWeight,
                    horizontalArrangement = Arrangement.Center,
                    modifier = buttonStyle
                )
            }
        }
    }
}

@Composable
private fun ButtonContentLogin(
    text: String,
    icon: ImageVector?,
    iconPosition: RightLeftEnum = RightLeftEnum.RIGHT,
    sizeIcon: Dp? = 28.dp,
    iconColor: Color = colorScheme.onSecondary,
    textColor: Color,
    fontSize: TextUnit,
    setFontWeight: FontWeight? = null,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Center,
    modifier: (Modifier) -> Modifier = { it },
) {
    Row(
        modifier = modifier(Modifier.fillMaxWidth()),
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (icon != null && iconPosition == RightLeftEnum.LEFT) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconColor,
                modifier = sizeIcon?.let { Modifier.size(it) } ?: Modifier,

                )
        }

        Text(
            text = text,
            color = textColor,
            fontSize = fontSize,
            textAlign = TextAlign.Center,
            fontWeight = setFontWeight
        )

        if (icon != null && iconPosition == RightLeftEnum.RIGHT) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconColor,
                modifier = sizeIcon?.let { Modifier.size(it) } ?: Modifier
            )
        }
    }
}

enum class ButtonTypeLogin {
    ButtonWithLine,
    CircularButton
}
