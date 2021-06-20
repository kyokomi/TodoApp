package dev.kyokomi.todoapp.ui.license

import android.webkit.WebView
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import dev.kyokomi.todoapp.ui.compose.TodoAppScaffold
import dev.kyokomi.todoapp.ui.theme.TodoAppTheme

private const val LICENSES_FILE_PATH = "file:///android_asset/licenses.html"

@Composable
fun LicenseContent() {
    LicenseScreen()
}

@Composable
fun LicenseScreen() {
    TodoAppScaffold("License") {
        LicenseWebView(modifier = Modifier.fillMaxHeight())
    }
}

@Composable
private fun LicenseWebView(modifier: Modifier = Modifier) {
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                settings.javaScriptEnabled = false
                loadUrl(LICENSES_FILE_PATH)
            }
        },
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewLicenseScreen() {
    TodoAppTheme {
        // LicenseScreen()
    }
}
