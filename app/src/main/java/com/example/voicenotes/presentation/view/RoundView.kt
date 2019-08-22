package com.example.voicenotes.presentation.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.animation.ValueAnimator
import android.view.animation.LinearInterpolator
import com.example.voicenotes.R

/**
 * Round shaped view with blink animation
 */
class RoundView : View {

    private var path = Path()
    private val paint = Paint()
    private var animator: ValueAnimator
    private val defaultDuration = 800 //default duration
    private var animationDuration: Long = defaultDuration.toLong()

    constructor(context: Context) : super(context) {
        init(null)
    }

    constructor(context: Context, attr: AttributeSet) : super(context, attr) {
        init(attr)
    }

    constructor(context: Context, attr: AttributeSet, defStyle: Int) : super(context, attr, defStyle) {
        init(attr)
    }

    private fun init(attrs: AttributeSet?) {
        if (attrs != null) {
            context.obtainStyledAttributes(attrs, R.styleable.RoundView).apply {
                animationDuration = getInt(R.styleable.RoundView_animation_duration, defaultDuration).toLong()
                recycle()
            }
        }
    }

    init {
        animator = ValueAnimator.ofFloat(0f, 1f).apply {
            duration = animationDuration
            interpolator = LinearInterpolator()
            repeatMode = ValueAnimator.REVERSE
            repeatCount = ValueAnimator.INFINITE
            addUpdateListener {
                alpha = it.animatedValue as Float
            }
        }
    }

    fun startAnimation() {
        animator.start()
    }

    override fun draw(canvas: Canvas) {
        paint.isAntiAlias = true
        with (canvas) {
            drawPath(path, paint)
            save()
            clipPath(path)
            super.draw(this)
            restore()
        }
    }

    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        super.onSizeChanged(width, height, oldWidth, oldHeight)
        path = Path(). apply {
            addCircle((width / 2).toFloat(), (height / 2).toFloat(), (width / 2).toFloat(), Path.Direction.CW)
            close()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(measuredWidth, measuredHeight)
    }
}
