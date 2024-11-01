package com.example.coordinadoraapp.presentation.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import com.example.coordinadoraapp.R
import com.example.coordinadoraapp.presentation.enums.NavigationItem
import com.example.coordinadoraapp.presentation.enums.ScreensDrawerEnum
import com.example.coordinadoraapp.presentation.screens.login.LoginScreen
import com.example.coordinadoraapp.presentation.screens.main.MainScreen
import com.example.coordinadoraapp.presentation.theme.colorScheme
import kotlinx.coroutines.launch

@SuppressLint("UnusedBoxWithConstraintsScope", "UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationDrawerContent(
    navigator: Navigator?,
    page: ScreensDrawerEnum,
    iconBack: Boolean = false,
    callBack: ((String) -> Unit)? = null,
    contentNavigation: @Composable () -> Unit,
    isShowTopBar: Boolean = true,
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    var names by remember { mutableStateOf(value = "") }
    var lastNames by remember { mutableStateOf(value = "") }
    val uriHandler = LocalUriHandler.current

    val gradientColors = listOf(
        Color(0xFF1464AC),
        Color(0xFFFFFFFF)
    )

    val showDialog = remember { mutableStateOf(false) }
    val navigator: Navigator? = LocalNavigator.current

    if (showDialog.value) {
        closeSesionDialog(
            showDialog = showDialog.value,
            onDismissRequest = { showDialog.value = false },
            onConfirm = {
                navigator?.replace(item = LoginScreen())
            },
            onCancel = {
                showDialog.value = false
            }
        )
    }


    Surface(
        modifier = Modifier.fillMaxSize(), color = colorScheme.inverseSurface
    ) {
        val scope = rememberCoroutineScope()
        var selectedItemIndex by rememberSaveable {
            mutableStateOf(0)
        }
        val items = listOf(
            NavigationItem(
                title = ScreensDrawerEnum.Main.title,
                icon = ScreensDrawerEnum.Main.image,
                menu = ScreensDrawerEnum.Main.order
            ),
            NavigationItem(
                title = ScreensDrawerEnum.Track.title,
                icon = ScreensDrawerEnum.Track.image,
                menu = ScreensDrawerEnum.Track.order
            ),
            NavigationItem(
                title = ScreensDrawerEnum.AirTransport.title,
                icon = ScreensDrawerEnum.AirTransport.image,
                menu = ScreensDrawerEnum.AirTransport.order
            ),
            NavigationItem(
                title = ScreensDrawerEnum.EspecialServices.title,
                icon = ScreensDrawerEnum.EspecialServices.image,
                menu = ScreensDrawerEnum.EspecialServices.order
            ),
        )
        ModalNavigationDrawer(
            drawerContent = {
                ModalDrawerSheet(
                    modifier = Modifier
                        .fillMaxHeight()
                        .background(Color.Transparent),
                    drawerShape = RoundedCornerShape(topEnd = 50.dp, bottomEnd = 50.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .verticalScroll(rememberScrollState())
                            .background(
                                brush = Brush.linearGradient(
                                    colors = listOf(

                                        Color(0xFFFFFFFF),
                                        Color(0xFFFFFFFF),
                                    ),
                                    start = Offset(0f, 0f),
                                    end = Offset(800f, 0f)
                                )
                            )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    Color.Transparent
                                )
                                .zIndex(1f)
                                .padding(start = 8.dp),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.usuario_icon),
                                modifier = Modifier
                                    .padding(16.dp)
                                    .size(65.dp)
                                    .offset(y = 6.dp),
                                contentDescription = null
                            )
                            Column(modifier = Modifier.padding(top = 34.dp)) {
                                Text(
                                    text = "Jhoan Sebastian",
                                    color = Color(0xFF1464AC),
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight(500),
                                    modifier = Modifier.fillMaxWidth(0.8f)
                                )
                                Text(
                                    text = "Castillo Usuriaga",
                                    color = Color(0xFF1464AC),
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight(500)
                                )
                                Row{
                                    Image(
                                        painter = painterResource(id = R.drawable.mostrar_contra),
                                        modifier = Modifier
                                            .size(16.dp)
                                            .offset(y = 10.dp),
                                        contentDescription = null
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = "Ver cuenta",
                                        color = colorScheme.inverseSurface,
                                        fontWeight = FontWeight(400),
                                        fontSize = 16.sp,
                                        modifier = Modifier
                                            .padding(top = 8.dp)
                                            .clickable {
                                            },
                                    )
                                }
                            }

                            Row{
                                Image(
                                    painter = painterResource(id = R.drawable.icono_cerrar),
                                    modifier = Modifier
                                        .size(28.dp)
                                        .offset(y = (-6).dp)
                                        .padding(end = 8.dp)
                                        .clickable {
                                            scope.launch {
                                                if (page.order == ScreensDrawerEnum.Main.order) {
                                                    drawerState.close()
                                                } else {
                                                    navigator?.replace(item = MainScreen())
                                                    drawerState.close()
                                                }
                                            }
                                        },
                                    contentDescription = null,
                                )
                            }
                        }



                        Spacer(modifier = Modifier.height(110.dp))

                        items.forEachIndexed { index, item ->
                            NavigationDrawerItem(
                                colors = NavigationDrawerItemDefaults.colors(
                                    selectedContainerColor = Color.Transparent,
                                    unselectedContainerColor = Color.Transparent,
                                    selectedTextColor = colorScheme.inverseSurface,
                                    unselectedTextColor = colorScheme.inverseSurface,
                                    selectedBadgeColor = colorScheme.inverseSurface,
                                ),
                                label = {
                                    Text(
                                        text = item.title,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight(500)
                                    )
                                },
                                selected = index == selectedItemIndex,
                                onClick = {
                                    selectedItemIndex = index
                                    when (index) {

                                        ScreensDrawerEnum.Main.order -> {
                                            if (page.order != ScreensDrawerEnum.Main.order) {
                                                navigator?.replace(item = MainScreen())
                                            }
                                        }

                                        ScreensDrawerEnum.Track.order -> {
                                            if (page.order != ScreensDrawerEnum.Track.order) {
                                            }
                                        }

                                        ScreensDrawerEnum.AirTransport.order -> {
                                            if (page.order != ScreensDrawerEnum.AirTransport.order) {
                                            }
                                        }

                                        ScreensDrawerEnum.EspecialServices.order -> {
                                            if (page.order != ScreensDrawerEnum.EspecialServices.order) {
                                            }
                                        }
                                    }
                                    scope.launch {
                                        drawerState.close()
                                    }
                                },
                                icon = {
                                    Image(
                                        painterResource(id = item.icon),
                                        contentDescription = "",
                                        modifier = Modifier.size(40.dp)
                                    )
                                },
                                modifier = Modifier
                                    .padding(horizontal = 8.dp, vertical = 0.dp)
                                    .background(color = Color.Transparent)
                                    .offset(y = (-80).dp)
                            )
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = Color.Transparent)
                                .offset(y = (190).dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.facebook),
                                modifier = Modifier
                                    .padding(16.dp)
                                    .size(28.dp)
                                    .clickable {
                                        uriHandler.openUri("https://www.facebook.com/CoordinadoraMercantil/?locale=es_LA")
                                    },
                                contentDescription = null
                            )

                            Image(
                                painter = painterResource(id = R.drawable.instagram),
                                modifier = Modifier
                                    .padding(16.dp)
                                    .size(28.dp)
                                    .clickable {
                                        uriHandler.openUri("https://www.instagram.com/coordinadoraoficial/?hl=es")
                                    },
                                contentDescription = null
                            )

                            Image(
                                painter = painterResource(id = R.drawable.x),
                                modifier = Modifier
                                    .padding(16.dp)
                                    .size(28.dp)
                                    .clickable {
                                        uriHandler.openUri("https://www.instagram.com/coordinadoraoficial/?hl=es")
                                    },
                                contentDescription = null
                            )
                            Image(
                                painter = painterResource(id = R.drawable.tiktok),
                                modifier = Modifier
                                    .padding(16.dp)
                                    .size(28.dp)
                                    .clickable {
                                        uriHandler.openUri("https://www.instagram.com/coordinadoraoficial/?hl=es")
                                    },
                                contentDescription = null
                            )
                            Image(
                                painter = painterResource(id = R.drawable.linke),
                                modifier = Modifier
                                    .padding(16.dp)
                                    .size(28.dp)
                                    .clickable {
                                        uriHandler.openUri("https://www.linkedin.com/company/coordinadora-mercantil/?originalSubdomain=co")
                                    },
                                contentDescription = null
                            )
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = Color.Transparent)
                                .offset(y = (220).dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                text = "1.0",
                                fontSize = 16.sp,
                                fontWeight = FontWeight(600),
                                color = Color(0xFF1464AC)
                            )
                        }
                    }
                }
            }, drawerState = drawerState
        ) {
            Scaffold(
                topBar = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp))
                            .background(
                                color = if (isShowTopBar) colorScheme.outline else Color.Transparent
                            )
                            .height(if(isShowTopBar) 60.dp else 900.dp)
                    ) {
                        TopAppBar(
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color.Transparent,
                                scrolledContainerColor = Color.Transparent
                            ),
                            title = {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center,
                                ) {
                                    Text(
                                        text = if (page.title != ScreensDrawerEnum.Main.title) {
                                            stringResource(id = page.title.toInt())
                                        } else {
                                            ""
                                        },
                                        fontSize = 19.sp,
                                        color = colorScheme.primary,
                                        modifier = Modifier.offset(x = (-25).dp),
                                        fontWeight = FontWeight.Normal,
                                        style = androidx.compose.ui.text.TextStyle(fontWeight = FontWeight.Normal)
                                    )
                                }
                            },
                            navigationIcon = {

                                IconButton(onClick = {
                                    scope.launch {
                                            drawerState.open()
                                    }
                                }) {
                                    if (iconBack) {
                                        Icon(
                                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                            contentDescription = "",
                                            tint = Color.White,
                                            modifier = Modifier.size(38.dp)
                                        )
                                    } else {
                                        Image(
                                            painter = painterResource(id = R.drawable.menu),
                                            contentDescription = null,
                                            modifier = Modifier
                                                .size(36.dp).padding(start = 5.dp),
                                            contentScale = ContentScale.Fit,
                                            colorFilter = ColorFilter.tint(color = Color.Black)
                                        )

                                    }
                                }
                            },
                            actions = {
                                if (page.order == ScreensDrawerEnum.Main.order) {
                                    IconButton(onClick = {
                                        scope.launch {
                                        }
                                    }) {
                                        Icon(
                                            imageVector = Icons.Default.Notifications,
                                            contentDescription = "",
                                            tint = Color(0xFF161515),
                                            modifier = Modifier.size(30.dp)
                                        )
                                    }
                                }
                            },
                            scrollBehavior = null
                        )
                    }
                },
                content = {
                    Column(modifier = Modifier.fillMaxSize()) {
                        Spacer(modifier = Modifier.fillMaxWidth().height(40.dp))
                        contentNavigation()
                    }
                },
            )
        }
    }
}