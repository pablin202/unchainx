package com.pdm.wallet.presentation.started

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import com.pdm.designsystems.component.button.ButtonType
import com.pdm.designsystems.component.button.UxButton
import com.pdm.designsystems.component.preview.PreviewSurface
import com.pdm.designsystems.component.preview.UxPreview
import com.pdm.designsystems.component.text.SpanType
import com.pdm.designsystems.component.text.TextType
import com.pdm.designsystems.component.text.UxText
import com.pdm.designsystems.component.text.createAnnotatedString
import com.pdm.designsystems.theme.LocalDimensions
import com.pdm.wallet.presentation.R

@Composable
fun StartScreen(onImportWallet: () -> Unit) {
    OnStart({
    }, onImportWallet)
}

@Composable
private fun OnStart(onCreateNewWallet: () -> Unit, onImportWallet: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(LocalDimensions.current.dimensions16),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.started_image),
            contentScale = ContentScale.FillWidth,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = LocalDimensions.current.dimensions16)
        )

        Spacer(
            modifier = Modifier
                .weight(1f)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            UxText(
                text = stringResource(R.string.on_start_title),
                TextType.DISPLAY_MEDIUM
            )
        }
        Spacer(
            modifier = Modifier
                .padding(LocalDimensions.current.dimensions8)
        )
        Spacer(
            modifier = Modifier
                .padding(vertical = LocalDimensions.current.dimensions24)
        )

        UxButton(
            text = stringResource(R.string.create_wallet),
            isFullWidth = true,
            buttonType = ButtonType.FILLED_TOTAL
        ) {
            onCreateNewWallet.invoke()
        }

        Spacer(
            modifier = Modifier
                .padding(vertical = LocalDimensions.current.dimensions4)
        )

        UxButton(
            text = stringResource(R.string.import_wallet),
            isFullWidth = true,
            buttonType = ButtonType.TEXT
        ) {
            onImportWallet.invoke()
        }

        Spacer(
            modifier = Modifier
                .padding(vertical = LocalDimensions.current.dimensions4)
        )

        UxText(
            text = createAnnotatedString(
                fullText = stringResource(R.string.term_of_use_title),
                spans = listOf(
                    SpanType.ClickSpan(
                        text = stringResource(R.string.term_of_use),
                        styles = TextLinkStyles(
                            SpanStyle(color = MaterialTheme.colorScheme.primary)
                        )
                    ) {
                    }
                )
            ),
            TextType.LABEL_SMALL,
            modifier = Modifier
                .padding(
                    top = LocalDimensions.current.dimensions48,
                    start = LocalDimensions.current.dimensions16
                )
        )

        Spacer(
            modifier = Modifier
                .padding(vertical = LocalDimensions.current.dimensions4)
        )
    }
}

@Composable
@UxPreview
fun StartScreenPreview() {
    PreviewSurface {
        OnStart({}, {})
    }
}
