package com.pdm.designsystems.component.preview

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

@Retention
@Target(
    AnnotationTarget.ANNOTATION_CLASS,
    AnnotationTarget.FUNCTION
)
@Preview(
    name = "0.85",
    device = Devices.PIXEL_7,
    showBackground = true,
    showSystemUi = true,
    fontScale = 0.85f,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    name = "1.0",
    device = Devices.PIXEL_7,
    showBackground = true,
    showSystemUi = true,
    fontScale = 1.0f,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "1.5",
    device = Devices.PIXEL_7,
    showBackground = true,
    showSystemUi = true,
    fontScale = 1.5f,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
annotation class UxPreview
