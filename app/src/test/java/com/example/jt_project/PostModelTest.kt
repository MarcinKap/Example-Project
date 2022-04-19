package com.example.jt_project

import com.example.jt_project.models.Post
import com.example.jt_project.models.Owner
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.BlockJUnit4ClassRunner

@RunWith(BlockJUnit4ClassRunner::class)
class PostModelTest {

    @Test
    fun date_parse() {
        val data = Post(
            id = "id",
            image = "url",
            likes = 34,
            tags = arrayListOf("dog", "holiday"),
            text = "Text",
            publishDate = "2020-05-24T07:44:17.738Z",
            owner = Owner(
                id = "0",
                title = "title",
                firstName = "Gary",
                lastName = "Owen",
                picture = "url"
            )
        )

        Assert.assertTrue(data.date.toString("HH:mm:ss.SSS dd.MM.yyyy") == "07:44:17.738 24.05.2020")
    }

}