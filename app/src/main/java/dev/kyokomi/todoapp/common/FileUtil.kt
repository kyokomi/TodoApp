package dev.kyokomi.todoapp.common

import android.database.Cursor
import android.net.Uri
import android.provider.OpenableColumns
import androidx.activity.ComponentActivity

class FileUtil {
    companion object {
        fun getFilename(activity: ComponentActivity, uri: Uri): String {
            val cursor: Cursor? = activity.contentResolver.query(uri, null, null, null, null, null)
            cursor?.use {
                if (it.moveToFirst()) {
                    return it.getString(it.getColumnIndex(OpenableColumns.DISPLAY_NAME))
                }
            }
            return ""
        }
    }
}