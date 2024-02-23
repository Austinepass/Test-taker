package com.orgustine.testtaker.util

import com.airbnb.lottie.compose.LottieClipSpec
import com.airbnb.lottie.compose.LottieConstants
import com.orgustine.testtaker.R

enum class AnimationType(val param: AnimationParam?) {
    Loading(
        AnimationParam(
            animationId = R.raw.anim_loading_success_failed,
            msgId = R.string.loading_msg,
            iteration = LottieConstants.IterateForever,
            clipSpec = LottieClipSpec.Progress(
                max = 0.32f
            )
        )
    ),
    LoadingSuccess(
        AnimationParam(
            animationId = R.raw.anim_loading_success_failed,
            msgId = R.string.loading_success_msg,
            btnTextId = R.string.start_quiz,
            clipSpec = LottieClipSpec.Progress(
                max = 0.45f
            )
        )
    ),
    LoadingFailure(
        AnimationParam(
            animationId = R.raw.anim_loading_success_failed,
            msgId = R.string.loading_failure_msg,
            btnTextId = R.string.retry,
            clipSpec = LottieClipSpec.Progress(
                min = 0.7f,
                max = 0.95f
            )
        )
    ),
    Summary(
        AnimationParam(
            animationId = R.raw.congrats,
            msgId = R.string.summary_msg,
            btnTextId = R.string.home
        )
    ),
    Countdown(
        AnimationParam(
            animationId = R.raw.countdown,
            msgId = R.string.no_msg,
            clipSpec = LottieClipSpec.Progress(
                min = 0.2f,
                max = 0.95f
            )
        )
    ),
    Default(null)

}

data class AnimationParam(
    val animationId: Int,
    val msgId: Int,
    val clipSpec: LottieClipSpec? = null,
    val btnTextId: Int? = null,
    val iteration: Int = 1
)