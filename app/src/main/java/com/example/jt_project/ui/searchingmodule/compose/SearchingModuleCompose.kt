package com.example.jt_project.ui.searchingmodule.compose

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.jt_project.R
import com.example.jt_project.api.Status
import com.example.jt_project.models.Owner
import com.example.jt_project.models.Post
import com.example.jt_project.ui.searchingmodule.DataEnum
import com.example.jt_project.ui.searchingmodule.SearchingModuleViewModel
import com.google.android.material.composethemeadapter.MdcTheme
import kotlinx.coroutines.launch


@Composable
fun SearchingModuleComposeView(
    viewModel: SearchingModuleViewModel,
    openDetails: (String) -> Unit
) {
    val postList by viewModel.postList.collectAsState()
    var selectedList by remember { mutableStateOf(DataEnum.POSTS) }

    if (postList?.status == Status.SUCCESS) {
        SearchingModuleCompose(
            dataList = postList!!.data!!.posts,
            openDetails = openDetails,
            selectedList = selectedList,
            setSelectedList = { selectedList = it }
        )
    }
}

@Composable
fun SearchingModuleCompose(
    dataList: List<Any>,
    openDetails: (String) -> Unit,
    selectedList: DataEnum,
    setSelectedList: (DataEnum) -> Unit
) {
    Surface() {
        BackDropScaffoldModule(
            modifier = Modifier,
            dataList = dataList,
            openDetails = openDetails,
            selectedList = selectedList,
            setSelectedList = setSelectedList
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BackDropScaffoldModule(
    modifier: Modifier = Modifier,
    dataList: List<Any>,
    openDetails: (String) -> Unit,
    selectedList: DataEnum,
    setSelectedList: (DataEnum) -> Unit
) {
    val backDropState = rememberBackdropScaffoldState(initialValue = BackdropValue.Concealed)
    var isDropDownMenuExpanded by remember { mutableStateOf(false) }

//    Its for fluent changing icon in backdropscaffold
    isDropDownMenuExpanded = setDropDownMenuState(
        isConcealed = backDropState.isConcealed,
        isRevealed = backDropState.isRevealed,
        isAnimationRunning = backDropState.isAnimationRunning,
        isDropDownMenuExpanded = isDropDownMenuExpanded
    )

    BackdropScaffold(
        modifier = modifier,
        gesturesEnabled = false,
        scaffoldState = backDropState,
        appBar = {
            val coroutineScope = rememberCoroutineScope()
            DropDownChosingProgram(
                changeStateBackLayerContent = {
                    if (isDropDownMenuExpanded) {
                        coroutineScope.launch {
                            backDropState.conceal()
                        }
                    } else {
                        coroutineScope.launch {
                            backDropState.reveal()
                        }
                    }
                },
                isRevealedBackLayerContent = isDropDownMenuExpanded,
                selectedList = selectedList
            )
        },
        backLayerContent = {
            val coroutineScope = rememberCoroutineScope()
            BackLayerContent(
                selectedList = selectedList,
                concealBackLayerContent = { coroutineScope.launch { backDropState.conceal() } },
                setSelectedList = setSelectedList
            )
        },
        frontLayerContent = {
            FrontLayerContent(
                dataList = dataList,
                selectedList = selectedList,
                openDetails = openDetails
            )
        },
        backLayerBackgroundColor = colorResource(id = R.color.primary_700),
        frontLayerShape = RoundedCornerShape(0.dp),
    )
}

@Preview(showBackground = true)
@Composable
fun BackDropScaffoldModulePreview() {
    val dataList = listOf(
        Post(
            id = "id",
            image = "url",
            likes = 34,
            tags = arrayListOf("dog", "holiday"),
            text = "Text",
            publishDate = "",
            owner = Owner(
                id = "0",
                title = "title",
                firstName = "Gary",
                lastName = "Owen",
                picture = "url"
            )
        )
    )

    MdcTheme {
        BackDropScaffoldModule(
            dataList = dataList,
            openDetails = {},
            selectedList = DataEnum.POSTS,
            setSelectedList = {})
    }
}

fun setDropDownMenuState(
    isConcealed: Boolean,
    isRevealed: Boolean,
    isAnimationRunning: Boolean,
    isDropDownMenuExpanded: Boolean
): Boolean {
    if ((isConcealed && !isAnimationRunning) ||
        (isRevealed && isAnimationRunning)
    ) {
        return false
    } else if (
        (isRevealed && !isAnimationRunning) ||
        (isConcealed && isAnimationRunning)
    ) {
        return true
    } else {
        return isDropDownMenuExpanded
    }
}

@Composable
fun DropDownChosingProgram(
    selectedList: DataEnum,
    changeStateBackLayerContent: () -> Unit,
    isRevealedBackLayerContent: Boolean,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 8.dp)
                .wrapContentHeight()
                .weight(1f),
            text = if (isRevealedBackLayerContent) "Select list".uppercase() else selectedList.name,
            fontSize = 18.sp,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Medium,
            color = colorResource(id = R.color.white),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Crossfade(targetState = isRevealedBackLayerContent) { crossFade ->
            IconButton(
                modifier = Modifier
                    .padding(10.dp)
                    .size(30.dp)
                    .testTag("dropDownButton"),
                onClick = { changeStateBackLayerContent() }
            ) {
                Icon(
                    painter = if (!crossFade) painterResource(id = R.drawable.ic_trailing) else painterResource(
                        id = R.drawable.ic_close_navigator
                    ),
                    contentDescription = "",
                    tint = colorResource(id = R.color.primary_100)
                )
            }
        }
    }
}


@ExperimentalMaterialApi
@Composable
fun BackLayerContent(
    selectedList: DataEnum,
    concealBackLayerContent: () -> Unit,
    setSelectedList: (DataEnum) -> Unit,
) {

    Surface() {
        LazyColumn {
            item {
                DataEnum.values().forEach { dataEnum ->
                    val isSelectedProgram = dataEnum == selectedList
                    Box(modifier = Modifier.background(colorResource(id = R.color.primary_700))) {
                        Box(
                            modifier = Modifier
                                .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
                                .clip(RoundedCornerShape(3.dp))
                                .background(
                                    if (isSelectedProgram) colorResource(id = R.color.white).copy(
                                        0.16f
                                    ) else colorResource(
                                        id = R.color.primary_700
                                    )
                                )
                                .clickable {
                                    setSelectedList(dataEnum)
                                    concealBackLayerContent()
                                }
                        )
                        {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp, vertical = 10.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    modifier = Modifier.weight(1f),
                                    text = dataEnum.name,
                                    color = if (isSelectedProgram) colorResource(id = R.color.white) else colorResource(
                                        id = R.color.primary_100
                                    ),
                                    style = MaterialTheme.typography.subtitle2,
                                    fontWeight = if (isSelectedProgram) FontWeight.Medium else FontWeight.Normal
                                )
                                if (isSelectedProgram) {
                                    Icon(
                                        modifier = Modifier.padding(start = 10.dp),
                                        painter = painterResource(id = R.drawable.ic_check_rounded),
                                        contentDescription = "",
                                        tint = colorResource(id = R.color.white)
                                    )
                                }

                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview(showBackground = true)
@Composable
fun BackLayerContentPreview() {
    MdcTheme {
        BackLayerContent(
            selectedList = DataEnum.POSTS,
            concealBackLayerContent = { },
            setSelectedList = {}
        )
    }
}

@Composable
fun FrontLayerContent(
    dataList: List<Any>,
    selectedList: DataEnum,
    openDetails: (String) -> Unit
) {
    LazyColumn() {
        if (selectedList == DataEnum.POSTS) {
            (dataList as List<Post>).forEach {
                item {
                    SinglePostModelCompose(
                        post = it,
                        modifier = Modifier
                            .clickable { openDetails(it.id) }
                            .padding(start = 10.dp, end = 10.dp, top = 10.dp),
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FrontLayerContentPreview() {
    val dataList = listOf(
        Post(
            id = "id",
            image = "url",
            likes = 34,
            tags = arrayListOf("dog", "holiday"),
            text = "Text",
            publishDate = "",
            owner = Owner(
                id = "0",
                title = "title",
                firstName = "Gary",
                lastName = "Owen",
                picture = "url"
            )
        )
    )

    MdcTheme {
        FrontLayerContent(
            dataList = dataList,
            selectedList = DataEnum.POSTS,
            openDetails = {})
    }
}


@Composable
fun SinglePostModelCompose(
    modifier: Modifier = Modifier,
    post: Post
) {
    Box(modifier = modifier) {
        Row(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .background(Color.White)
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .padding(end = 16.dp)
                    .width(88.dp)
                    .height(64.dp),
                contentAlignment = Alignment.Center
            )
            {
                Image(
                    modifier = Modifier,
                    contentScale = ContentScale.Fit,
                    painter = rememberImagePainter(
                        data = post.image,
                        builder = {
                            placeholder(R.drawable.no_photo)
                        }
                    ),
                    contentDescription = "",
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column() {
                    Text(
                        text = "${post.owner.firstName} ${post.owner.lastName}",
                        style = MaterialTheme.typography.h6,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        modifier = Modifier.padding(vertical = 8.dp),
                        text = post.text,
                        style = MaterialTheme.typography.body2
                    )
                }

                Divider(
                    thickness = 1.dp, modifier = Modifier.background(
                        color = colorResource(
                            id = R.color.cadet_grey_200
                        )
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SingleModelComposePreview() {
    MdcTheme() {
        val data = Post(
            id = "id",
            image = "url",
            likes = 34,
            tags = arrayListOf("dog", "holiday"),
            text = "Text",
            publishDate = "",
            owner = Owner(
                id = "0",
                title = "title",
                firstName = "Gary",
                lastName = "Owen",
                picture = "url"
            )
        )
        SinglePostModelCompose(post = data)
    }
}