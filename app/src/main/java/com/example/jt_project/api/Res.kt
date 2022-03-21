package com.example.jt_project.api

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
</T> */

data class Res<out T>(val status: Status, val data: T?, val throwable: Throwable?) {
    companion object {
        fun <T> success(data: T?): Res<T> {
            return Res(Status.SUCCESS, data, null)
        }

        fun <T> error(exception: Throwable, data: T? = null): Res<T> {
            return Res(Status.ERROR, data, exception)
        }

        fun <T> loading(data: T? = null): Res<T> {
            return Res(Status.LOADING, data, null)
        }
    }
}