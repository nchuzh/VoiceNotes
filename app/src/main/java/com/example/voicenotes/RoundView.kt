package com.example.voicenotes


import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.animation.ValueAnimator
import android.view.animation.LinearInterpolator
import android.R.attr.animationDuration
import android.R.animator



/**
 * Round shaped view with blink animation
 */
class RoundView : View {

    private var path = Path()
    private val paint = Paint()
    private lateinit var animator: ValueAnimator

    constructor(context: Context) : super(context)
    constructor(context: Context, attr: AttributeSet) : super(context, attr)
    constructor(context: Context, attr: AttributeSet, defStyle: Int) : super(context, attr, defStyle)

    init {
        animator = ValueAnimator.ofFloat(0f, 1f)
        animator.setDuration(1000)
        animator.setInterpolator(LinearInterpolator())
        animator.setRepeatMode(ValueAnimator.REVERSE)
        animator.setRepeatCount(ValueAnimator.INFINITE)
        animator.addUpdateListener(ValueAnimator.AnimatorUpdateListener {
            alpha = it.animatedValue as Float
        })
    }

    fun startAnimation() {
        animator.start()
    }

    override fun draw(canvas: Canvas) {
        paint.isAntiAlias = true
        canvas.drawPath(path, paint)
        canvas.save()
        canvas.clipPath(path)
        super.draw(canvas)
        canvas.restore()
    }

    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        super.onSizeChanged(width, height, oldWidth, oldHeight)
        path = Path()
        path.addCircle((width / 2).toFloat(), (height / 2).toFloat(), (width / 2).toFloat(), Path.Direction.CW)
        path.close()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(measuredWidth, measuredHeight)
    }
}
