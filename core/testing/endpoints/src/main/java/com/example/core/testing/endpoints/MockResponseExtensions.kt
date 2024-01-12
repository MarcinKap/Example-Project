package com.example.core.testing.endpoints

import okhttp3.mockwebserver.MockResponse

fun MockResponse.setJsonBody(json: String) = apply { setBody(json.loadFileContent()) }
