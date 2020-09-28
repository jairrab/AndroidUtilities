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
    private val radius: Int
) : ReplacementSpan() {
    override fun getSize(
        paint: Paint,
        text: CharSequence,
        start: Int,
        end: Int,
        fm: FontMetricsInt?
    ): Int {
        val textWidth = paint.measureText(text.subSequence(start, end).toString())
        return (radius * 2 + textWidth).toInt()
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
        val radiusCorner = ((upper - lower) / 2).absoluteValue
        val rect = RectF(x - radius, upper, x + width + radius, lower)
        paint.color = backgroundColor
        canvas.drawRoundRect(rect, radiusCorner, radiusCorner, paint)
        paint.color = textColor
        canvas.drawText(text, start, end, x, y.toFloat(), paint)
    }
}