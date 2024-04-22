import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.platform.Typeface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import platform.UIKit.UIDevice
import org.jetbrains.skia.FontStyle
import org.jetbrains.skia.Image
import org.jetbrains.skia.Typeface
import kotlin.coroutines.CoroutineContext

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()

actual abstract class ViewModel {
    actual val viewModelScope: CoroutineScope = object : CoroutineScope {
        override val coroutineContext: CoroutineContext
            get() = SupervisorJob() + Dispatchers.IO
    }

    protected actual open fun onCleared() {
        viewModelScope.cancel()
    }

    actual fun clear() {
        onCleared()
    }
}

@Composable
actual fun <T: ViewModel> rememberViewModel(viewModel: () -> T): T {
    val vm = remember { viewModel.invoke() }
    DisposableEffect(vm) {
        onDispose {
            vm.clear()
        }
    }

    return vm
}

actual val nunitoExtraBoldFontFamily: FontFamily = FontFamily(
    Typeface(loadCustomFont("nunito_extra_bold"))
)

actual val nunitoMediumFontFamily: FontFamily = FontFamily(
    Typeface(loadCustomFont("nunito_medium"))
)

private fun loadCustomFont(name: String): Typeface {
    return Typeface.makeFromName(name, FontStyle.NORMAL)
}