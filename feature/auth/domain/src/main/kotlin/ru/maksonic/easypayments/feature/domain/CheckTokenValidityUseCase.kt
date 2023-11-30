package ru.maksonic.easypayments.feature.domain

import kotlinx.coroutines.flow.Flow
import ru.maksonic.easypayments.common.core.UseCase

/**
 * @Author maksonic on 29.11.2023
 */
interface CheckTokenValidityUseCase: UseCase.Default<Flow<TokenStatus>>