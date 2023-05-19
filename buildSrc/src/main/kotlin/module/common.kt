package module

/**
 * @Author maksonic on 22.04.2023
 */
object common {
    object coroutineDispatchers : base(
        path = ":common:coroutine-dispatchers",
        namespace = "ru.maksonic.beresta.common.coroutine_dispatchers"
    )

    object jsonConverter : base(
        path = ":common:json-converter",
        namespace = "ru.maksonic.beresta.common.json_converter"
    )
}