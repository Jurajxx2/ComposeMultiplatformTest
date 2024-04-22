package photodetail.vm

import base.State

data class PhotoDetailState(
    val state: PhotoDetailScreenState,
    val imageUrl: String?,
): State {
    enum class PhotoDetailScreenState {
        Loading,
        Error,
        Success
    }

    companion object {
        val DEFAULT = PhotoDetailState(
            PhotoDetailScreenState.Loading,
            "https://firebasestorage.googleapis.com/v0/b/mamio-bfc02.appspot.com/o/developer_task%2Fanezka.jpg?alt=media&token=3c66b98f-95cb-4963-9c96-1030b5136b2e"
        )
    }
}