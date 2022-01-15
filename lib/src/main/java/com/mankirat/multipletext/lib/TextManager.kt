package com.mankirat.multipletext.lib

import android.graphics.Color
import android.text.Spannable
import android.text.Spanned
import android.text.TextPaint
import android.text.TextUtils
import android.text.method.LinkMovementMethod
import android.text.style.AbsoluteSizeSpan
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.util.TypedValue
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import kotlin.math.roundToInt

data class SpanModel(
    val text: String? = null,
    val textRes: Int? = null,
    val textChar: CharSequence? = null,

    val color: Int? = null,
    val colorRes: Int? = null,
    val colorCode: String? = null,//#ffffff

    val sizeSp: Float? = null,
    val sizeDp: Float? = null,
    val sizePx: Int? = null,

    val typeface: Int? = null,
    val isUnderline: Boolean = false,

    val bgColor: Int? = null,
    val bgColorRes: Int? = null,
    val bgColorCode: String? = null,//#ffffff

    val click: (() -> Unit)? = null,
)

fun TextView.setSpanText(vararg modelList: SpanModel) {
    var output: CharSequence = ""

    for (model in modelList) {//for (i in 0 until modelList.size) {
        val text: String = when {
            model.text != null -> model.text
            model.textRes != null -> context.getString(model.textRes)
            model.textChar != null -> model.textChar.toString()
            else -> ""
        }

        val color: Int? = when {
            model.color != null -> model.color
            model.colorRes != null -> ContextCompat.getColor(context, model.colorRes)
            model.colorCode != null -> Color.parseColor(model.colorCode)
            else -> null
        }

        val size: Int? = when {
            model.sizeSp != null -> TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, model.sizeSp, context.resources.displayMetrics).roundToInt()
            model.sizeDp != null -> TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, model.sizeDp, context.resources.displayMetrics).roundToInt()
            model.sizePx != null -> model.sizePx
            else -> null//this.textSize.roundToInt()
        }

        val bgColor: Int = when {
            model.bgColor != null -> model.bgColor
            model.bgColorRes != null -> ContextCompat.getColor(context, model.bgColorRes)
            model.bgColorCode != null -> Color.parseColor(model.bgColorCode)
            else -> Color.TRANSPARENT
        }


        val spanString = Spannable.Factory.getInstance().newSpannable(text)//SpannableString(text)


        size?.let { spanString.setSpan(AbsoluteSizeSpan(size), 0, text.length, Spanned.SPAN_INCLUSIVE_INCLUSIVE) }

        model.typeface?.let { spanString.setSpan(StyleSpan(model.typeface), 0, text.length, Spanned.SPAN_INCLUSIVE_EXCLUSIVE) }

        color?.let { spanString.setSpan(ForegroundColorSpan(color), 0, text.length, Spanned.SPAN_INCLUSIVE_EXCLUSIVE) }

        model.click?.let {
            val clickableSpan = object : ClickableSpan() {
                override fun onClick(textView: View) {
                    model.click.invoke()
                }

                override fun updateDrawState(textPaint: TextPaint) {
                    super.updateDrawState(textPaint)
                    color?.let { textPaint.color = color }

                    textPaint.isUnderlineText = model.isUnderline
                    textPaint.bgColor = bgColor

                    textPaint.linkColor = Color.RED
                }
            }

            spanString.setSpan(clickableSpan, 0, text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }

        output = TextUtils.concat(output, spanString)
    }

    this.text = output
    this.movementMethod = LinkMovementMethod.getInstance()//important for click
}