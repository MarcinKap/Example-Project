package com.example.core.testing.endpoints

import java.io.File

fun String.loadFileContent(): String {
    val fileName = ClassLoader.getSystemResource(this).file
    val file = File(fileName)
    return file.readLines()
        .fold(StringBuilder()) { acc, line ->
            acc.append(line)
        }.toString()
}
