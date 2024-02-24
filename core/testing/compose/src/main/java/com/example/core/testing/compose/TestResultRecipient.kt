package com.example.core.testing.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisallowComposableCalls
import com.ramcosta.composedestinations.result.NavResult
import com.ramcosta.composedestinations.result.ResultRecipient
import com.ramcosta.composedestinations.spec.DestinationSpec

class TestResultRecipient<D : DestinationSpec<*>, R> : ResultRecipient<D, R> {

    private var listener: ((NavResult<R>) -> Unit)? = null

    fun emitValue(value: R) {
        listener?.invoke(NavResult.Value(value))
    }

    fun emitCancel() {
        listener?.invoke(NavResult.Canceled)
    }

    @Composable
    @Suppress("ComposableNaming")
    override fun onNavResult(listener: @DisallowComposableCalls (NavResult<R>) -> Unit) {
        this.listener = listener
    }

    @Suppress("ComposableNaming")
    @Deprecated("You should migrate to `onNavResult` as this API will be removed in the near future.")
    @Composable
    override fun onResult(listener: (R) -> Unit) {
        this.listener = {
            if (it is NavResult.Value) {
                listener.invoke(it.value)
            }
        }
    }
}
