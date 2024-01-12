@file:Suppress("TooManyFunctions")

package com.example.core.testing.endpoints

import com.google.gson.JsonParser
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.kotest.matchers.uri.shouldHaveParameter
import kotlinx.serialization.json.Json
import okhttp3.HttpUrl
import okhttp3.mockwebserver.RecordedRequest
import java.net.URI

const val GET = "GET"
const val POST = "POST"
const val PUT = "PUT"
const val PATCH = "PATCH"
const val DELETE = "DELETE"

infix fun RecordedRequest.pathShouldBe(url: String) {
    URI.create(path!!).path shouldBe url
}

infix fun RecordedRequest.methodShouldBe(method: String) {
    this.method!!.lowercase() shouldBe method.lowercase()
}

infix fun RecordedRequest.shouldNotHaveParameter(name: String) {
    requestUrl!!.queryParameter(name).shouldBeNull()
}

infix fun RecordedRequest.shouldHaveParameter(name: String): QueryParamOngoing {
    requestUrl!!.toUri().shouldHaveParameter(name)
    return QueryParamOngoing(requestUrl!!, name)
}

infix fun QueryParamOngoing.withValue(value: String) {
    url.queryParameter(name) shouldBe value
}

infix fun QueryParamOngoing.withValue(values: List<String>) {
    url.queryParameter(name) shouldBe values.joinToString(separator = ",")
}

infix fun QueryParamOngoing.withValue(value: Long) {
    withValue(value.toString())
}

infix fun QueryParamOngoing.withValue(value: Int) {
    withValue(value.toString())
}

infix fun RecordedRequest.bodyShouldBe(fileName: String) {
    JsonParser.parseString(fileName.loadFileContent()) shouldBe
        JsonParser.parseString(body.readUtf8())
}

inline infix fun <reified T> RecordedRequest.bodyShouldEqualTo(data: T) {
    Json.decodeFromString<T>(body.readUtf8()) shouldBe data
}

infix fun RecordedRequest.bodyShouldContain(content: String): BodyOngoing {
    return BodyOngoing(body.readUtf8()) and content
}

infix fun BodyOngoing.and(content: String): BodyOngoing {
    body shouldContain content
    return this
}

class QueryParamOngoing(
    val url: HttpUrl,
    val name: String,
)

class BodyOngoing(val body: String)
