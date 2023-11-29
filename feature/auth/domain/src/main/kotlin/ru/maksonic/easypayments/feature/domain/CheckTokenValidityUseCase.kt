package ru.maksonic.easypayments.feature.domain

import ru.maksonic.easypayments.common.core.UseCase

/**
 * @Author maksonic on 29.11.2023
 */
interface CheckTokenValidityUseCase: UseCase.Default<Result<TokenStatus>>