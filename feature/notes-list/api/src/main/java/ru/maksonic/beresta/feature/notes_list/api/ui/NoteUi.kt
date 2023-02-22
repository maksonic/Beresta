package ru.maksonic.beresta.feature.notes_list.api.ui

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @Author maksonic on 21.02.2023
 */
@Parcelize
data class NoteUi(
    val id: Long = 0L,
    val title: String = "",
    val message: String = "",
    val dateCreation: String = "",
    val currentFolder: String = "",
    val isSelected: Boolean = false,
    val isPinned: Boolean = false,
    val isMovedToTrash: Boolean = false
) : Parcelable {
    companion object {
        val preview = NoteUi(
            id = 0,
            title = "Note title preview",
            message = "Note message preview",
            dateCreation = "12:00 AM - 1 January 1970",
        )
    }
}