package com.example.coordinadoraapp.domain.util

import android.graphics.pdf.PdfRenderer
import android.net.Uri
import android.os.ParcelFileDescriptor
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.coordinadoraapp.presentation.theme.colorScheme
import java.io.File

@Composable
fun ShowPDF(pdfUri: String) {
    var pdfRenderer: PdfRenderer? = null
    var pageCount by remember { mutableStateOf(0) }
    val bitmaps = remember { mutableStateListOf<android.graphics.Bitmap>() }
    var isLoading by remember { mutableStateOf(true) }
    val context = LocalContext.current

    println("SOY EL URI DEL PDF $pdfUri")

    DisposableEffect(pdfUri) {
        // Abre el archivo PDF usando el URI
        val uri = Uri.parse(pdfUri)
        val fileDescriptor = context.contentResolver.openFileDescriptor(uri, "r")

        pdfRenderer = fileDescriptor?.let { PdfRenderer(it) }
        pageCount = pdfRenderer?.pageCount ?: 0

        onDispose {
            pdfRenderer?.close()
            fileDescriptor?.close()
        }
    }

    LaunchedEffect(Unit) {
        isLoading = true
        pdfRenderer?.let { renderer ->
            (0 until pageCount).forEach { index ->
                val currentPage = renderer.openPage(index)
                val width = currentPage.width
                val height = currentPage.height
                val bmp = android.graphics.Bitmap.createBitmap(width, height, android.graphics.Bitmap.Config.ARGB_8888)
                currentPage.render(bmp, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
                bitmaps.add(bmp)
                currentPage.close()
            }
        }
        isLoading = false
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        if (isLoading) {
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(8.dp)
            ) {
                items(bitmaps) { bitmap ->
                    Image(
                        bitmap = bitmap.asImageBitmap(),
                        contentDescription = "PDF Page",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.fillMaxWidth().height(400.dp)
                    )
                }
            }
        }
    }
}

