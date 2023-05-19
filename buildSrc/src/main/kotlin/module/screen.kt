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

    object editNote : base(
        path = ":screen:edit-note",
        namespace = "ru.maksonic.beresta.screen.edit_note"
    )

    object trashList: base(
        path = ":screen:trash-list",
        namespace = "ru.maksonic.beresta.screen.trash_list"
    )
}