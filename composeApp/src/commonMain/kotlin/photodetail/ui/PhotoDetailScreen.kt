package photodetail.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import mamiotest.composeapp.generated.resources.Res
import mamiotest.composeapp.generated.resources.ic_close
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import photodetail.nav.PhotoDetailActions
import photodetail.vm.PhotoDetailIntent
import photodetail.vm.PhotoDetailNavigationEvents
import photodetail.vm.PhotoDetailState
import photodetail.vm.PhotoDetailViewModel

@Composable
fun PhotoDetailRoute(actions: PhotoDetailActions) {
    val photoDetailViewModel = remember {
        PhotoDetailViewModel()
    }
    val photoDetailState = photoDetailViewModel.state.collectAsState()
    LaunchedEffect(Unit) {
        photoDetailViewModel.navigationEvent.onEach { event ->
            when (event) {
                PhotoDetailNavigationEvents.GoBack -> actions.navigateUp()
            }
        }.collect()
    }

    PhotoDetailScreen(photoDetailState.value) {
        photoDetailViewModel.intent(it)
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun PhotoDetailScreen(state: PhotoDetailState, intent: (PhotoDetailIntent) -> Unit) {
    Column (
        modifier = Modifier.height(IntrinsicSize.Min),
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd
        ) {
            Image(
                modifier = Modifier
                    .padding(16.dp)
                    .size(32.dp)
                    .clickable { intent(PhotoDetailIntent.GoBack) },
                painter = painterResource(Res.drawable.ic_close),
                contentDescription = null,
            )
        }

        if (state.imageUrl != null) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                KamelImage(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    resource = asyncPainterResource(data = state.imageUrl),
                    contentDescription = null,
                )
            }
        }
    }
}