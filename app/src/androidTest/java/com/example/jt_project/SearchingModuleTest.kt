package com.example.jt_project

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.example.jt_project.api.models.Post
import com.example.jt_project.api.models.Owner
import com.example.jt_project.ui.searchingmodule.DataEnum
import com.example.jt_project.ui.searchingmodule.compose.SearchingModuleCompose
import com.example.jt_project.ui.searchingmodule.compose.SinglePostModelCompose
import com.example.jt_project.ui.searchingmodule.compose.setDropDownMenuState
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test



class SearchingModuleTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp(){
    }

    @Test
    fun setDropDownMenuState_assertFalseWhenBackDropStateIsInHiding() {
        Assert.assertTrue(
            !setDropDownMenuState(
                isConcealed = false,
                isRevealed = true,
                isAnimationRunning = true,
                isDropDownMenuExpanded = true
            )
        )
    }

    @Test
    fun setDropDownMenuState_assertFalseWhenBackDropStateIsInHidden() {
        Assert.assertTrue(
            !setDropDownMenuState(
                isConcealed = true,
                isRevealed = false,
                isAnimationRunning = false,
                isDropDownMenuExpanded = true
            )
        )
    }

    @Test
    fun assertFalseWhenBackDropStateIsInHidden2() {
        Assert.assertTrue(
            !setDropDownMenuState(
                isConcealed = true,
                isRevealed = false,
                isAnimationRunning = false,
                isDropDownMenuExpanded = false
            )
        )
    }

    @Test
    fun setDropDownMenuState_assertWhenBackDropStateIsInRevealing() {
        Assert.assertTrue(
            setDropDownMenuState(
                isConcealed = true,
                isRevealed = false,
                isAnimationRunning = true,
                isDropDownMenuExpanded = false
            )
        )
    }

    @Test
    fun setDropDownMenuState_assertWhenBackDropStateIsInRevealing2() {
        Assert.assertTrue(
            setDropDownMenuState(
                isConcealed = true,
                isRevealed = false,
                isAnimationRunning = true,
                isDropDownMenuExpanded = true
            )
        )
    }

    @Test
    fun setDropDownMenuState_assertWhenBackDropStateIsRevealed() {
        assert(
            setDropDownMenuState(
                isConcealed = false,
                isRevealed = true,
                isAnimationRunning = false,
                isDropDownMenuExpanded = true
            )
        )
    }

    @Test
    fun SinglePostModelCompose_DisplayName() {
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
        composeTestRule.setContent {
            SinglePostModelCompose(post = data)
        }
        composeTestRule.onNodeWithText("${data.owner.firstName} ${data.owner.lastName}").assertIsDisplayed()
    }

    @Test
    fun SearchingModuleCompose_DisplayBackLayerContentAfterClickOnButtonAndHideAfter2ndClick() {
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


        var selectedList = DataEnum.POSTS

        composeTestRule.setContent {
            SearchingModuleCompose(
                dataList = listOf(data),
                openDetails = {},
                selectedList = selectedList,
                setSelectedList = {selectedList = it}
            )
        }

        composeTestRule.onNodeWithTag("dropDownButton").performClick()
        composeTestRule.onNodeWithText("SELECT LIST").assertIsDisplayed()
        composeTestRule.onNodeWithText("POSTS").assertIsDisplayed()
        composeTestRule.onNodeWithText("USERS").assertIsDisplayed()
        composeTestRule.onNodeWithTag("dropDownButton").performClick()
        composeTestRule.onNodeWithText("SELECT LIST").assertDoesNotExist()
    }

    @Test
    fun SearchingModuleCompose_HideBackLayerContentAfterClickOnList() {
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
        var selectedList = DataEnum.POSTS
        composeTestRule.setContent {
            SearchingModuleCompose(
                dataList = listOf(data),
                openDetails = {},
                selectedList = selectedList,
                setSelectedList = {selectedList = it}
            )
        }

        composeTestRule.onNodeWithTag("dropDownButton").performClick()
        composeTestRule.onNodeWithText("USERS").performClick()
        assert(selectedList == DataEnum.USERS)
    }





}