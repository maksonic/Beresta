package ru.maksonic.beresta.language_engine.shell.components

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @Author maksonic on 13.11.2023
 */
@Serializable
data class LangComponentTags(
    @SerialName("top_bar_title_tags") val topBarTitleTags: String,
    @SerialName("top_bar_title_select_tags") val topBarTitleSelectTags: String,
    @SerialName("top_bar_title_tags_management") val topBarTitleTagsManagement: String,
    @SerialName("btn_title_add_tags") val btnTitleAddTags: String,
    @SerialName("btn_title_create_new_tag") val btnTitleCreateNewTag: String,
    @SerialName("btn_title_edit_tag") val btnTitleEditTag: String,
    @SerialName("hint_empty_tag_list") val hintEmptyTagList: String,
    @SerialName("hint_error_empty_tag_name") val hintErrorEmptyTagName: String
) {
    companion object {
        val Default = LangComponentTags(
            topBarTitleTags = "Tags",
            topBarTitleSelectTags = "Select tags",
            topBarTitleTagsManagement = "Tag management",
            btnTitleAddTags = "Add tags",
            btnTitleCreateNewTag = "Create a new tag",
            btnTitleEditTag = "Edit tag",
            hintEmptyTagList = "Tag list is empty",
            hintErrorEmptyTagName = "Tag name cannot be empty"
        )
    }
}
