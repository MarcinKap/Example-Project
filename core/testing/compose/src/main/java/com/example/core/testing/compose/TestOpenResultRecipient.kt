package com.example.core.testing.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisallowComposableCalls
import com.ramcosta.composedestinations.result.NavResult
import com.ramcosta.composedestinations.result.OpenResultRecipient

class TestOpenResultRecipient<R> : OpenResultRecipient<R> {

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
}
