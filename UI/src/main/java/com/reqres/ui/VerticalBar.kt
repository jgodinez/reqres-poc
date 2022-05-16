package com.reqres.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

class VerticalBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var data: VerticalBarData? = null

    private val paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.FILL
        color = Color.parseColor("#FFB71C1C")
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (isInEditMode) {
            canvas?.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paint)
        }

        data?.let {
            paint.color = ContextCompat.getColor(context, it.color)
            canvas?.drawRect(
                0f, height - (it.percent / 100 * height), width.toFloat(), height.toFloat(), paint
            )
        }
    }

    fun renderData(item: VerticalBarData) {
        data = item
        invalidate()
    }
}

data class VerticalBarData(
    val current: Float,
    val total: Float,
    @ColorRes val color: Int
) {
    val percent = if (current == total) {
        100f
    } else {
        current / total * 100f

    }
}