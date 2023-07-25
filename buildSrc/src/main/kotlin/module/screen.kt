package module

/**
 * @Author maksonic on 22.04.2023
 */
object screen {
    object main : base(
        path = ":screen:main",
        namespace = "ru.maksonic.beresta.screen.main"
    )

    object settings : base(
        path = ":screen:settings",
        namespace = "ru.maksonic.beresta.screen.settings"
    )

    object settingsAppearance : base(
        path = ":screen:settings:appearance",
        namespace = "ru.maksonic.beresta.screen.settings.appearance"
    )

    object editNote : base(
        path = ":screen:edit-note",
        namespace = "ru.maksonic.beresta.screen.edit_note"
    )

    object trashList {
        object notes : base(
            path = ":screen:trash-list:notes",
            namespace = "ru.maksonic.beresta.screen.trash_list.notes"
        )

        object folders : base(
            path = ":screen:trash-list:folders",
            namespace = "ru.maksonic.beresta.screen.trash_list.folders"
        )
    }

    object folders : base(
        path = ":screen:folders",
        namespace = "ru.maksonic.beresta.screen.folders"
    )

    object hiddenNotes : base(
        path = ":screen:hidden-notes",
        namespace = "ru.maksonic.beresta.screen.hidden_notes"
    )
}