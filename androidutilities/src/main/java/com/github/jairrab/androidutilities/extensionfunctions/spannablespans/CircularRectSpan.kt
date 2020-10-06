package com.github.jairrab.androidutilities.extensionfunctions.spannablespans

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Paint.FontMetricsInt
import android.graphics.RectF
import android.text.style.ReplacementSpan
import kotlin.math.absoluteValue

class CircularRectSpan(
    private val backgroundColor: Int,
    private val textColor: Int,
    private val radiusCorner: Float = 0.5f,
) : ReplacementSpan() {
    override fun getSize(
        paint: Paint,
        text: CharSequence,
        start: Int,
        end: Int,
        fm: FontMetricsInt?
    ): Int {
        val subSequence = text.subSequence(start, end)
        val textWidth = paint.measureText(subSequence.toString())
        return textWidth.toInt()
    }

    override fun draw(
        canvas: Canvas,
        text: CharSequence,
        start: Int,
        end: Int,
        x: Float,
        top: Int,
        y: Int,
        bottom: Int,
        paint: Paint
    ) {
        val width = paint.measureText(text.subSequence(start, end).toString())
        val upper = top.toFloat()
        val lower = bottom.toFloat()
        val corner = ((upper - lower) * radiusCorner).absoluteValue
        val rect = RectF(x, upper, x + width, lower)
        paint.color = backgroundColor
        canvas.drawRoundRect(rect, corner, corner, paint)
        paint.color = textColor
        canvas.drawText(text, start, end, x, y.toFloat(), paint)
    }
}