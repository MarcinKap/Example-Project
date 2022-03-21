package com.example.jt_project.ui.postdetails.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.jt_project.api.Status
import com.example.jt_project.ui.postdetails.PostDetailsViewModel
import com.google.android.material.composethemeadapter.MdcTheme
import com.example.jt_project.R
import com.example.jt_project.api.Res
import com.example.jt_project.api.models.Data

@Composable
fun PostDetailsComposeView(viewModel: PostDetailsViewModel) {
    val post by viewModel.post.collectAsState(Res.loading())

    if (post.status == Status.SUCCESS){
        PostDetailsCompose(data = post!!.data!!)
    }
}



@Composable
fun PostDetailsCompose(data: Data) {
    LazyColumn{
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 200.dp)
                    .padding(10.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier,
                    contentScale = ContentScale.Fit,
                    painter = rememberImagePainter(
                        data = data.image,
                        builder = {
                            error(R.drawable.no_photo)
                        }
                    ),
                    contentDescription = "",
                )
            }
        }
        item {
            Box(
                modifier = Modifier
                    .heightIn(min = 48.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier
                        .padding(horizontal = 16.dp),
                    text = "${data.owner.firstName} ${data.owner.lastName}"
                )
            }
        }
        item {
            Box(
                modifier = Modifier
                    .heightIn(min = 48.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier
                        .padding(horizontal = 16.dp),
                    text = data.text
                )
            }
        }

        item {
            PostInformation(
                painter = painterResource(id = R.drawable.baseline_thumb_up_black_36dp),
                information = data.likes.toString()
            )
        }
        item {
            PostInformation(
                painter = painterResource(id = R.drawable.baseline_calendar_today_black_36dp),
                information = data.publishDate
            )
        }
    }
}

@Composable
fun PostInformation(
    painter: Painter,
    information: String
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .heightIn(min = 48.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painter,
            contentDescription = null
        )
        Text(
            style = MaterialTheme.typography.body2,
            modifier = Modifier.padding(start = 12.dp),
            text = information
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PostInformationPreview() {
    MdcTheme() {
        PostInformation(
            painter = painterResource(id = R.drawable.baseline_thumb_up_black_36dp),
            information = "Business"
        )
    }
}

