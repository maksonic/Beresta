package ru.maksonic.beresta.feature.onboarding.data

import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json
import ru.maksonic.beresta.common.core.json.JsonConverter
import ru.maksonic.beresta.feature.app_lang.domain.AppLangDomain
import ru.maksonic.beresta.feature.app_lang.domain.usecase.FetchAppLangUseCase
import ru.maksonic.beresta.feature.onboarding.data.lang.OnboardingsLangContainer
import ru.maksonic.beresta.feature.onboarding.data.lang.toUi
import ru.maksonic.beresta.feature.onboarding.domain.OnboardingRepository
import ru.maksonic.beresta.feature.onboarding.domain.OnboardingsList

/**
 * @Author maksonic on 27.09.2023
 */
class OnboardingRepositoryCore(
    jsonConverter: JsonConverter,
    private val json: Json,
    private val fetchAppLangUseCase: FetchAppLangUseCase,
) : OnboardingRepository {

    private companion object {
        private const val FILE_NAME = "onboardings.json"
    }

    private val OnboardingsLangContainer.russianLang get() = this.russian.list.toUi()
    private val OnboardingsLangContainer.englishLang get() = this.english.list.toUi()
    private val OnboardingsLangContainer.chineseLang get() = this.chinese.list.toUi()
    private val OnboardingsLangContainer.chineseTrLang get() = this.chineseTr.list.toUi()

    private val convertOnboardingsResult: Result<OnboardingsLangContainer> =
        jsonConverter.convertFileToString(FILE_NAME).fold(
            onSuccess = { Result.success(json.decodeFromString(it)) },
            onFailure = { Result.failure(it) }
        )

    private val images = listOf(
        R.drawable.onboarding_image_first,
        R.drawable.onboarding_image_second,
        R.drawable.onboarding_image_third,
        R.drawable.onboarding_image_fourth
    )

    override fun fetchOnboardings(): OnboardingsList = flow {
        convertOnboardingsResult.fold(
            onSuccess = { container ->
                fetchAppLangUseCase().collect { currentLang ->
                    val data = when (currentLang) {
                        AppLangDomain.RUSSIAN -> container.russianLang
                        AppLangDomain.ENGLISH -> container.englishLang
                        AppLangDomain.CHINESE -> container.chineseLang
                        AppLangDomain.CHINESE_TR -> container.chineseTrLang
                    }.mapIndexed { index, item -> item.copy(image = images[index]) }
                    emit(Result.success(data))
                }
            },
            onFailure = { emit(Result.failure(it)) })
    }
}