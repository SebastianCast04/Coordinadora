package com.example.coordinadoraapp.domain.util



import android.content.ContentValues
import android.content.Context
import android.provider.MediaStore
import android.util.Base64
import java.io.IOException

fun savePDF(base64: String, context: Context): String? {
    val pdfBytes = Base64.decode(base64, Base64.DEFAULT)
    val fileName = "archivo.pdf"
    val resolver = context.contentResolver
    val contentValues = ContentValues().apply {
        put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
        put(MediaStore.MediaColumns.MIME_TYPE, "application/pdf")
        put(MediaStore.MediaColumns.RELATIVE_PATH, "Download/pruebacoordi")
    }

    // Insertar en MediaStore
    val uri = resolver.insert(MediaStore.Files.getContentUri("external"), contentValues)

    // Guardar el PDF en el OutputStream
    uri?.let {
        try {
            resolver.openOutputStream(it).use { outputStream ->
                outputStream?.write(pdfBytes)
                outputStream?.flush()
            }
            // Devuelve la ruta del archivo guardado
            return uri.toString() // o return pdfPath si decides construirlo manualmente
        } catch (e: IOException) {
            e.printStackTrace() // Manejo de excepciones
        }
    }
    return null // Retorna null si no se pudo guardar
}
