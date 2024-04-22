package home.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import home.nav.HomeActions
import home.vm.HomeIntent
import home.vm.HomeNavigationEvents
import home.vm.HomeState
import home.vm.HomeViewModel
import io.kamel.core.ExperimentalKamelApi
import io.kamel.image.KamelImageBox
import io.kamel.image.asyncPainterResource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext
import mamiotest.composeapp.generated.resources.Res
import mamiotest.composeapp.generated.resources.home_content
import mamiotest.composeapp.generated.resources.home_error
import mamiotest.composeapp.generated.resources.home_show_photo_detail
import mamiotest.composeapp.generated.resources.home_title
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import theme.gradientColor1
import theme.gradientColor2
import theme.gradientColor3
import theme.transparentWhite

@Composable
fun HomeRoute(actions: HomeActions) {
    val homeViewModel = remember { HomeViewModel() }
    val homeState = homeViewModel.state.collectAsState()
    LaunchedEffect(Unit) {
        homeViewModel.navigationEvent.onEach { event ->
            when (event) {
                HomeNavigationEvents.GoToPhotoDetail -> actions.onShowDetailClick()
            }
        }.collect()
    }

    HomeScreen(homeState.value) {
        homeViewModel.intent(it)
    }
}

@OptIn(ExperimentalResourceApi::class, ExperimentalKamelApi::class)
@Composable
fun HomeScreen(state: HomeState, intent: (HomeIntent) -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize().background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        gradientColor1,
                        gradientColor2,
                        gradientColor3,
                    )
                )
            ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .widthIn(80.dp, 400.dp)
                    .padding(horizontal = 16.dp)
                    .wrapContentHeight(),
                shape = RoundedCornerShape(12.dp),
                elevation = 0.dp,
            ) {
                KamelImageBox(
                    resource = asyncPainterResource(data = state.imageUrl),
                    onLoading = {
                        intent(HomeIntent.ShowLoading)
                    },
                    onSuccess = {
                        intent(HomeIntent.ShowSuccess)
                        Column {
                            AnimatedVisibility(state.state == HomeState.HomeScreenState.Success) {
                                Image(
                                    painter = it,
                                    contentDescription = null,
                                )
                            }
                        }
                    },
                    onFailure = {
                        intent(HomeIntent.ShowError)
                    }
                )
            }

            if (state.state == HomeState.HomeScreenState.Error) {
                Text(
                    text = stringResource(Res.string.home_error)
                )
            }

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                text = stringResource(Res.string.home_title),
                style = MaterialTheme.typography.h2,
                textAlign = TextAlign.Center
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                text = stringResource(Res.string.home_content),
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center
            )

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFFD85B6B),
                    contentColor = Color.White,
                ),
                onClick = {
                    intent(HomeIntent.GoToDetail)
                },
            ) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = stringResource(Res.string.home_show_photo_detail)
                )
            }
        }

        if (state.state == HomeState.HomeScreenState.Loading) {
            Box(
                modifier = Modifier.fillMaxSize()
                    .background(transparentWhite),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}