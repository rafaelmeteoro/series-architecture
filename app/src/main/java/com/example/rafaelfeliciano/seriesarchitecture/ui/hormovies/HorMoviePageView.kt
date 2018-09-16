package com.example.rafaelfeliciano.seriesarchitecture.ui.hormovies

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Build
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import com.example.rafaelfeliciano.seriesarchitecture.R

class HorMoviePageView : View {

    private var mBulletPaint: Paint? = null
    private var mSelectedBulletPaint: Paint? = null
    private var mBulletDiameter: Float = 0.toFloat()
    private var mBulletSpace: Float = 0.toFloat()
    var current: Int = 0
        private set
    private var mCount: Int = 0
    private var mLines: Int = 0
    private var mSelectedBulletRect: AnimatableRectF? = null
    private var mAnimating: Boolean = false
    private var mBulletAnimation: AnimatorSet? = null
    private var listener: (position: Int) -> Unit = {}

    private val maxBulletsPerLine: Int
        get() = ((width + mBulletSpace) / (mBulletDiameter + mBulletSpace)).toInt()

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        mBulletPaint = Paint().apply {
            color = ContextCompat.getColor(context, R.color.movie_bullet_color)
            isAntiAlias = true
        }
        mSelectedBulletPaint = Paint().apply {
            color = ContextCompat.getColor(context, R.color.movie_bullet_selected_color)
        }
        mBulletDiameter = context.resources.getDimension(R.dimen.movie_bullet_diameter)
        mBulletSpace = context.resources.getDimension(R.dimen.movie_bullet_space)
        mSelectedBulletRect = AnimatableRectF()

        if (attrs != null) {
            val a = context.theme.obtainStyledAttributes(attrs, R.styleable.MoviePageView, 0, 0)

            try {
                mCount = a.getInteger(R.styleable.MoviePageView_count, 0)
            } finally {
                a.recycle()
            }
        }
    }

    fun setCount(count: Int) {
        mCount = count
        requestLayout()
    }

    operator fun next() {
        val next = if (current < mCount - 1) current + 1 else 0
        val maxBulletsPerLine = maxBulletsPerLine
        var currentLine = 0
        while (next >= maxBulletsPerLine * (currentLine + 1)) {
            currentLine++
        }
        val linePosition = maxBulletsPerLine * currentLine - next
        setCurrent(next, linePosition != 0)
    }

    @JvmOverloads
    internal fun setCurrent(position: Int, animated: Boolean = true) {
        mBulletAnimation?.takeIf { it.isRunning }?.cancel()
        val old = current
        current = position
        listener(current)

        if (animated) {
            startBulletAnim(if (old < position) Side.RIGHT else Side.LEFT)
        } else {
            invalidate()
        }
    }

    private fun startBulletAnim(to: Side) {
        mAnimating = true
        val expandAnimation = createAnimation(if (to == Side.RIGHT) Side.RIGHT else Side.LEFT, to)
        val collapseAnimation = createAnimation(if (to == Side.RIGHT) Side.LEFT else Side.RIGHT, to)
        mBulletAnimation = AnimatorSet().apply {
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationCancel(animation: Animator) {
                    mAnimating = false
                    postInvalidate()
                }

                override fun onAnimationEnd(animation: Animator) {
                    onAnimationCancel(animation)
                }
            })

            playSequentially(expandAnimation, collapseAnimation)
            setDuration(resources.getInteger(android.R.integer.config_shortAnimTime).toLong()).start()
        }
    }

    private fun createAnimation(side: Side, to: Side): ObjectAnimator {
        val initialValue = if (side == Side.RIGHT) {
            mSelectedBulletRect!!.right
        } else {
            mSelectedBulletRect!!.left
        }
        val step = mBulletDiameter + mBulletSpace
        val endValue = if (to == Side.RIGHT) {
            initialValue + step
        } else {
            initialValue - step
        }
        return ObjectAnimator.ofFloat(mSelectedBulletRect, side.value, initialValue, endValue).apply {
            addUpdateListener { postInvalidate() }
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (mCount > 0) {
            val radius = mBulletDiameter / 2
            var cy = (height / (2 * mLines)).toFloat()
            val maxBulletsPerLine = maxBulletsPerLine
            var i = 0
            while (i < mCount) {
                for (j in 0 until maxBulletsPerLine) {
                    if (i < mCount) {
                        val cx = radius * (j + 1) + mBulletSpace * j + radius * j
                        canvas.drawCircle(cx, cy, radius, mBulletPaint!!)
                        if (!mAnimating && i == current) {
                            mSelectedBulletRect!!.set(cx - radius, cy - radius, cx + radius, cy + radius)
                        }
                        i++
                    } else break
                }
                cy += mBulletDiameter
            }
            canvas.drawRoundRect(mSelectedBulletRect!!, radius, radius, mSelectedBulletPaint!!)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val widthMode = View.MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = View.MeasureSpec.getSize(widthMeasureSpec)

        val heightMode = View.MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = View.MeasureSpec.getSize(heightMeasureSpec)

        val desiredWidth = Math.ceil((if (mCount == 0) 0f else mBulletDiameter * mCount + mBulletSpace * (mCount - 1)).toDouble()).toInt()
        val desiredHeight = mBulletDiameter.toInt()

        // Measure Width
        @SuppressLint("SwitchIntDef")
        val width = when (widthMode) {
            View.MeasureSpec.EXACTLY -> // Must be this size
                widthSize
            View.MeasureSpec.AT_MOST -> // Can't be bigger than...
                Math.min(desiredWidth, widthSize)
            else -> // Be whatever you want
                desiredWidth
        }

        // Measure Height
        mLines = 1
        val height = if (heightMode == View.MeasureSpec.EXACTLY) {
            // Must be this size
            heightSize
        } else {
            val h = if (desiredWidth > widthSize) {
                mLines = Math.ceil(desiredWidth / widthSize.toDouble()).toInt()
                desiredHeight * mLines
            } else {
                desiredHeight
            }
            Math.min(h, heightSize)
        }

        setMeasuredDimension(width, height)
    }

    private enum class Side constructor(val value: String) {
        RIGHT("right"), LEFT("left")
    }
}