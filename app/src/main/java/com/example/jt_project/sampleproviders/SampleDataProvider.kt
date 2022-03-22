package com.example.jt_project.sampleproviders

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.jt_project.api.models.Data
import com.example.jt_project.api.models.Owner

class SampleDataProvider: PreviewParameterProvider<Data> {
    override val values = sequenceOf(
        Data(
            id = "id",
            image = "url",
            likes = 34,
            tags = arrayListOf("dog", "holiday"),
            text="Text",
            publishDate = "2020-05-24T07:44:17.738Z",
            owner = Owner(
                id = "0",
                title = "title",
                firstName = "Gary",
                lastName = "Owen",
                picture = "url"
            )
        ),
        Data(
            id = "id",
            image = "url",
            likes = 33,
            tags = arrayListOf("dog2", "holiday2"),
            text="Text",
            publishDate = "2020-05-24T07:44:17.738Z",
            owner = Owner(
                id = "0",
                title = "title",
                firstName = "Gary",
                lastName = "Owen",
                picture = "url"
            )
        )
    )



}