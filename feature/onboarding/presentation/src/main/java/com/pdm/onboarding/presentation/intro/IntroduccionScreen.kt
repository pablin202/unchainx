package com.pdm.onboarding.presentation.intro

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import com.pdm.designsystems.component.button.ButtonType
import com.pdm.designsystems.component.button.UxButton
import com.pdm.designsystems.component.pager.UxPagerWithIndicator
import com.pdm.designsystems.component.preview.PreviewSurface
import com.pdm.designsystems.component.preview.UxPreview
import com.pdm.designsystems.component.text.TextType
import com.pdm.designsystems.component.text.UxText
import com.pdm.designsystems.theme.LocalDimensions
import com.pdm.onboarding.presentation.R
import com.pdm.onboarding.presentation.dummy.DummyUserPreferenceRepository
import com.pdm.onboarding.presentation.mvi.Event
import com.pdm.onboarding.presentation.mvi.OnBoardingViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun IntroductionScreen(onNextScreen: () -> Unit, viewModel: OnBoardingViewModel = koinViewModel()) {
    OnBoarding(onNextScreen, viewModel::event)
}

@Composable
private fun OnBoarding(onNextScreen: () -> Unit, dispatch: (Event) -> Unit) {
    val images = listOf(
        R.drawable.onboarding_image_1,
        R.drawable.onboarding_image_2,
        R.drawable.onboarding_image_3
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(LocalDimensions.current.dimensions16),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val coroutineScope = rememberCoroutineScope()
        val pageCount = stringArrayResource(R.array.onboarding_title).size
        val pagerState = rememberPagerState(
            initialPage = 0,
            initialPageOffsetFraction = 0f,
            pageCount = { pageCount }
        )

        val buttonText = if (pagerState.currentPage == pageCount - 1) {
            stringResource(R.string.welcome_get_started)
        } else {
            stringResource(R.string.next)
        }

        UxPagerWithIndicator(
            modifier = Modifier
                .weight(1f)
                .padding(bottom = LocalDimensions.current.dimensions24),
            pageCount = pageCount,
            state = pagerState
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = LocalDimensions.current.dimensions16),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = images[it]),
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
                        text = stringArrayResource(R.array.onboarding_title)[it],
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
            }
        }

        UxButton(buttonText) {
            coroutineScope.launch {
                if (pagerState.currentPage < pageCount - 1) {
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                } else {
                    dispatch(Event.OnboardingCompleted)
                    onNextScreen.invoke()
                }
            }
        }

        Spacer(
            modifier = Modifier
                .padding(vertical = LocalDimensions.current.dimensions4)
        )

        UxButton(
            text = stringResource(R.string.skip),
            isFullWidth = true,
            buttonType = ButtonType.TEXT
        ) {
            dispatch(Event.OnboardingCompleted)
            onNextScreen.invoke()
        }
    }
}

@Composable
@UxPreview
fun IntroScreenPreview() {
    val dummyViewModel = OnBoardingViewModel(
        preferenceRepository = DummyUserPreferenceRepository()
    )

    PreviewSurface {
        IntroductionScreen(onNextScreen = {}, viewModel = dummyViewModel)
    }
}
