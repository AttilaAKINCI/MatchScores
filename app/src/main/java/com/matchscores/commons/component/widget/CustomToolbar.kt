package com.matchscores.commons.component.widget

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.matchscores.R


class CustomToolbar : Toolbar {
    constructor(context: Context) : super(context) { initialize(context, null, 0) }
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) { initialize(
        context,
        attrs,
        0
    ) }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) { initialize(context, attrs, defStyleAttr) }

    lateinit var container:RelativeLayout
    private lateinit var leftBarContainer:LinearLayout
    private lateinit var rightBarContainer:CardView
    private lateinit var titleTextView:AppCompatTextView
    lateinit var clickListener: OnClickListener

    var dropDownActive = false
    private val dropDownImageTag = "TAG"

    private fun initialize(context: Context, attrs: AttributeSet?, defStyleAttr: Int){

        container = RelativeLayout(context)
        container.layoutParams = ViewGroup.LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.MATCH_PARENT
        )

        leftBarContainer = LinearLayout(context)
        leftBarContainer.orientation = LinearLayout.HORIZONTAL
        val leftBarLayoutParams = RelativeLayout.LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.MATCH_PARENT
        )
        leftBarLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_START)
        leftBarLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP)
        leftBarLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
        leftBarContainer.layoutParams = leftBarLayoutParams

        titleTextView = AppCompatTextView(context)
        titleTextView.isSingleLine = true
        titleTextView.ellipsize = TextUtils.TruncateAt.END
        titleTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20F)
        titleTextView.setTextColor(
            ResourcesCompat.getColor(
                resources,
                R.color.colorSoftWhite,
                context.theme
            )
        )
        val titleLayoutParams = LinearLayout.LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT
        )
        titleLayoutParams.gravity = Gravity.START or Gravity.CENTER_VERTICAL
        titleTextView.layoutParams = titleLayoutParams

        leftBarContainer.addView(titleTextView)
        container.addView(leftBarContainer)

        rightBarContainer = CardView(context)
        val rightBarLayoutParams = RelativeLayout.LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.MATCH_PARENT
        )
        rightBarLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_END)
        rightBarLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP)
        rightBarLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
        rightBarContainer.layoutParams = rightBarLayoutParams

        container.addView(rightBarContainer)

        addView(container)
    }

    override fun setTitle(title: CharSequence?) { titleTextView.text = title }
    fun showDropDownMenuIcon(isShow : Int) { rightBarContainer.visibility = isShow }
    fun closeDropDownIcon(){
        val startColor = ContextCompat.getColor(context, R.color.mainBg)
        val iconDown = ResourcesCompat.getDrawable(resources, R.drawable.ic_arrow_down, context.theme)
        iconDown?.setTint(startColor)
        rightBarContainer.findViewWithTag<ImageView>(dropDownImageTag).background = iconDown
        rightBarContainer.setBackgroundColor(ContextCompat.getColor(context, R.color.transparent))
        dropDownActive = false
    }
    private fun openDropDownIcon(){
        val startColor = ContextCompat.getColor(context, R.color.mainBg)
        val iconUp = ResourcesCompat.getDrawable(resources, R.drawable.ic_arrow_up, context.theme)
        iconUp?.setTint(startColor)
        rightBarContainer.findViewWithTag<ImageView>(dropDownImageTag).background = iconUp
        rightBarContainer.setBackgroundResource(R.drawable.card_view_background)
        dropDownActive = true
    }
    fun addDropDownMenuIcon(){

        val imageView = ImageView(context)
        imageView.tag = dropDownImageTag

        val imageViewLayoutParams = FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        imageViewLayoutParams.gravity = Gravity.CENTER
        imageViewLayoutParams.marginEnd = resources.getDimensionPixelSize(R.dimen.right_bar_icon_margin_end)
        imageViewLayoutParams.marginStart = resources.getDimensionPixelSize(R.dimen.right_bar_icon_margin_end)
        imageView.layoutParams = imageViewLayoutParams
        imageView.foregroundGravity = Gravity.CENTER

        imageView.setOnClickListener {
            if(!dropDownActive){
                openDropDownIcon()
            }else{
                closeDropDownIcon()
            }
            clickListener?.onClick(it)
        }

        rightBarContainer.addView(imageView)
        closeDropDownIcon()
    }
}