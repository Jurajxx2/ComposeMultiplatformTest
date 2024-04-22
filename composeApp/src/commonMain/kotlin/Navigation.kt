import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import home.nav.HomeActions
import home.ui.HomeRoute
import home.nav.homeNavigationRoute
import net.thauvin.erik.urlencoder.UrlEncoderUtil
import photodetail.nav.PhotoDetailActions
import photodetail.nav.photoDetailNavigationParameter
import photodetail.nav.photoDetailNavigationParameterName
import photodetail.ui.PhotoDetailRoute
import photodetail.nav.photoDetailNavigationRoute

@Composable
fun MamioNavigationHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = homeNavigationRoute,
        modifier = Modifier
            .fillMaxSize()
    ) {
        composable(
            route = homeNavigationRoute
        ) {
            HomeRoute(
                HomeActions(
                    onShowDetailClick = {
                        navController.navigate(
                            photoDetailNavigationRoute
                        )
                    }
                )
            )
        }
        //TODO arguments not working on ios, use some lib for navigation, i.e. decompose
        composable(
            route = photoDetailNavigationRoute,
            arguments = listOf(navArgument(photoDetailNavigationParameterName) { type = NavType.StringType })
        ) {
            PhotoDetailRoute(
                PhotoDetailActions(
                    navigateUp = {
                        navController.navigateUp()
                    }
                )
            )
        }
    }
}