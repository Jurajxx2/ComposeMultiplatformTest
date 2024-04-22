import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.lifecycle.compose.LocalLifecycleOwner
import test.mamio.app.R
import androidx.lifecycle.ViewModel as LifecycleViewModel
import androidx.lifecycle.viewModelScope as androidViewModelScope


class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

actual abstract class ViewModel : LifecycleViewModel() {
    actual val viewModelScope = androidViewModelScope

    actual override fun onCleared() {
        super.onCleared()
    }

    actual fun clear() {
        onCleared()
    }
}

@Composable
actual fun <T: ViewModel> rememberViewModel(viewModel: () -> T): T {
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val vm = remember { viewModel.invoke() }
    DisposableEffect(lifecycle) {
        onDispose {
            vm.clear()
        }
    }

    return vm
}


actual val nunitoExtraBoldFontFamily: FontFamily = FontFamily(
    Font(R.font.nunito_extra_bold)
)

actual val nunitoMediumFontFamily: FontFamily = FontFamily(
    Font(R.font.nunito_medium)
)