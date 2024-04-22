package home.vm

import base.State

data class HomeState(
    val state: HomeScreenState,
    val imageUrl: String,
): State {
    enum class HomeScreenState {
        Loading,
        Error,
        Success
    }

    companion object {
        val DEFAULT = HomeState(
            HomeScreenState.Loading,
            "https://firebasestorage.googleapis.com/v0/b/mamio-bfc02.appspot.com/o/developer_task%2Fanezka.jpg?alt=media&token=3c66b98f-95cb-4963-9c96-1030b5136b2e"
        )
    }
}
