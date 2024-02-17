package com.example.core.testing.screenshots

import app.cash.paparazzi.Paparazzi
import org.junit.Rule
import org.junit.experimental.categories.Category

@Category(Paparazzi::class)
open class PaparazziTest {
    @get:Rule
    open val paparazzi: Paparazzi = PaparazziFactory.compose()

    val context get() = paparazzi.context
    val resources get() = paparazzi.resources
}