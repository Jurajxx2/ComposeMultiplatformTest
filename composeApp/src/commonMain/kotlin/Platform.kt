import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import kotlinx.coroutines.CoroutineScope

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect abstract class ViewModel() {
    val viewModelScope: CoroutineScope

    protected open fun onCleared()
    fun clear()
}

@Composable
expect fun <T: ViewModel> rememberViewModel(viewModel: () -> T): T

expect val nunitoExtraBoldFontFamily: FontFamily
expect val nunitoMediumFontFamily: FontFamily