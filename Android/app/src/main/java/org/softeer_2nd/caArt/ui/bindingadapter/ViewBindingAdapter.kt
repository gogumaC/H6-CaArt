package org.softeer_2nd.caArt.ui.bindingadapter

import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.databinding.BindingAdapter
import org.softeer_2nd.caArt.util.dp2px


@BindingAdapter("android:layout_marginEnd")
fun View.setMarginEnd(marginEnd: Float) {
    val lp = layoutParams as? ViewGroup.MarginLayoutParams
    lp?.marginEnd = marginEnd.dp2px(context)
    layoutParams = lp
}

@BindingAdapter("android:layout_marginStart")
fun View.setMarginStart(marginStart: Float) {
    val lp = layoutParams as? ViewGroup.MarginLayoutParams
    lp?.marginStart = marginStart.dp2px(context)
    layoutParams = lp
}

@BindingAdapter("android:layout_marginTop")
fun View.setMarginTop(marginTop: Float) {
    val lp = layoutParams as? ViewGroup.MarginLayoutParams
    lp?.topMargin = marginTop.dp2px(context)
    layoutParams = lp
}

@BindingAdapter("layout_minus_marginTop")
fun View.setMinusMarginTop(marginTop: Float) {
    val lp = layoutParams as? ViewGroup.MarginLayoutParams
    lp?.topMargin = -(marginTop.dp2px(context))
    layoutParams = lp
}

@BindingAdapter("android:layout_marginBottom")
fun View.setMarginBottom(marginBottom: Float) {
    val lp = layoutParams as? ViewGroup.MarginLayoutParams
    lp?.bottomMargin = marginBottom.dp2px(context)
    layoutParams = lp
}

@BindingAdapter("layout_minus_marginBottom")
fun View.setMinusMarginBottom(marginBottom: Float) {
    val lp = layoutParams as? ViewGroup.MarginLayoutParams
    lp?.bottomMargin = -(marginBottom.dp2px(context))
    layoutParams = lp
}

@BindingAdapter("android:layout_gravity")
fun View.setLayoutGravity(gravity: Int) {
    val lp = layoutParams as? FrameLayout.LayoutParams
    lp?.gravity = gravity
}

@BindingAdapter("marginTopConditionally")
fun View.setMarginTopConditionally(isOtherColor: Boolean) {
    val layoutParams = layoutParams as ViewGroup.MarginLayoutParams
    if (isOtherColor) {
        layoutParams.topMargin = 28f.dp2px(context)
    } else {
        layoutParams.topMargin = 0
    }
    this.layoutParams = layoutParams
}