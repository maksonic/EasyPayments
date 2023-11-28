package ru.maksonic.easypayments.common.core.elm

/**
 * @Author maksonic on 24.06.2023
 */
data class ElmBaseModel(
    val isInitial: Boolean,
    val isLoading: Boolean,
    val isRefreshing: Boolean,
    val successAfterLoading: Boolean,
    val failAfterLoading: Boolean,
    val successAfterRefresh: Boolean,
    val failAfterRefresh: Boolean,
    val isFail: Boolean,
    val failMessage: String,
) {
    companion object {
        val Initial = ElmBaseModel(
            isInitial = true,
            isLoading = false,
            isRefreshing = false,
            successAfterLoading = false,
            failAfterLoading = false,
            successAfterRefresh = false,
            failAfterRefresh = false,
            isFail = false,
            failMessage = "",
        )

        val Loading = ElmBaseModel(
            isInitial = false,
            isLoading = true,
            isRefreshing = false,
            successAfterLoading = false,
            failAfterLoading = false,
            successAfterRefresh = false,
            failAfterRefresh = false,
            isFail = false,
            failMessage = "",
        )

        val ElmBaseModel.Initial
            get() = this.copy(
                isInitial = true,
                isLoading = false,
                isRefreshing = false,
                successAfterLoading = false,
                failAfterLoading = false,
                successAfterRefresh = false,
                failAfterRefresh = false,
                isFail = false,
                failMessage = "",
            )

        val ElmBaseModel.Loading
            get() = this.copy(
                isInitial = false,
                isLoading = true,
                isRefreshing = false,
                successAfterLoading = false,
                failAfterLoading = false,
                successAfterRefresh = false,
                failAfterRefresh = false,
                isFail = false,
                failMessage = "",
            )

        val ElmBaseModel.Refresh
            get() = this.copy(
                isInitial = false,
                isLoading = false,
                isRefreshing = true,
                successAfterLoading = false,
                failAfterLoading = false,
                successAfterRefresh = false,
                failAfterRefresh = false,
                isFail = false,
                failMessage = "",
            )

        val ElmBaseModel.loadedSuccess
            get() = this.copy(
                isInitial = false,
                isLoading = false,
                isRefreshing = false,
                successAfterLoading = true,
                failAfterLoading = false,
                successAfterRefresh = false,
                failAfterRefresh = false,
                isFail = false,
                failMessage = "",
            )

        val ElmBaseModel.loadedFailure: (String) -> ElmBaseModel
            get() = { failMessage ->
                this.copy(
                    isInitial = false,
                    isLoading = false,
                    isRefreshing = false,
                    successAfterLoading = false,
                    failAfterLoading = true,
                    successAfterRefresh = false,
                    failAfterRefresh = false,
                    isFail = false,
                    failMessage = failMessage,
                )
            }

        val ElmBaseModel.refreshedSuccess
            get() = this.copy(
                isInitial = false,
                isLoading = false,
                isRefreshing = false,
                successAfterLoading = false,
                failAfterLoading = false,
                successAfterRefresh = true,
                failAfterRefresh = false,
                isFail = false,
                failMessage = "",
            )

        val ElmBaseModel.refreshedFailure: (String) -> ElmBaseModel
            get() = { failMessage ->
                this.copy(
                    isInitial = false,
                    isLoading = false,
                    isRefreshing = false,
                    successAfterLoading = false,
                    failAfterLoading = false,
                    successAfterRefresh = false,
                    failAfterRefresh = true,
                    isFail = false,
                    failMessage = failMessage,
                )
            }

        val ElmBaseModel.failure: (String) -> ElmBaseModel
            get() = { failMessage ->
                this.copy(
                    isInitial = false,
                    isLoading = false,
                    isRefreshing = false,
                    successAfterLoading = false,
                    failAfterLoading = false,
                    successAfterRefresh = false,
                    failAfterRefresh = false,
                    isFail = true,
                    failMessage = failMessage,
                )
            }
    }
}