package com.compose.app.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import timber.log.Timber


@Composable
fun ComposableLifecycle(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    onEvent: (LifecycleOwner, Lifecycle.Event) -> Unit
) {

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { source, event ->
            onEvent(source, event)
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}

@Composable
fun ComposeLifecycleObserver() {
    ComposableLifecycle { _, event ->
        when (event) {
            Lifecycle.Event.ON_CREATE -> {
                Timber.wtf("onCreate")
            }

            Lifecycle.Event.ON_START -> {
                Timber.wtf("On Start")
            }

            Lifecycle.Event.ON_RESUME -> {
                Timber.wtf("On Resume")
            }

            Lifecycle.Event.ON_PAUSE -> {
                Timber.wtf("On Pause")
            }

            Lifecycle.Event.ON_STOP -> {
                Timber.wtf("On Stop")
            }

            Lifecycle.Event.ON_DESTROY -> {
                Timber.wtf("On Destroy")
            }

            else -> {}
        }
    }
}