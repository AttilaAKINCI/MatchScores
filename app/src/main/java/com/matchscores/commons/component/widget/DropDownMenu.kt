package com.matchscores.commons.component.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.matchscores.R

class DropDownMenu : LinearLayout {
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

    enum class DropDownItems {
        NEWS,
        SCORES,
        STANDINGS
    }

    lateinit var dropDownClickListener: DropDownClickListener

    private fun initialize(context: Context, attrs: AttributeSet?, defStyleAttr: Int){

        orientation = VERTICAL

        val newsLayout = createContainer()
        newsLayout.setOnClickListener { dropDownClickListener?.dropDownClicked(DropDownItems.NEWS) }

        val scoresLayout = createContainer()
        scoresLayout.setOnClickListener {  dropDownClickListener?.dropDownClicked(DropDownItems.SCORES) }

        val standingsLayout = createContainer()
        standingsLayout.setOnClickListener {  dropDownClickListener?.dropDownClicked(DropDownItems.STANDINGS) }

        val newsTextView = createMenuText(resources.getString(R.string.news_title))
        newsLayout.addView(newsTextView)

        val scoresTextView = createMenuText(resources.getString(R.string.scores_title))
        scoresLayout.addView(scoresTextView)

        val standingsTextView = createMenuText(resources.getString(R.string.standings_title))
        standingsLayout.addView(standingsTextView)

        addView(newsLayout)
        addView(createBorder())
        addView(scoresLayout)
        addView(createBorder())
        addView(standingsLayout)
        addView(createBorder())
    }

    private fun createBorder() : View {
        val borderView = View(context)
        val borderParams = LayoutParams(LayoutParams.MATCH_PARENT,
            resources.getDimensionPixelSize(R.dimen.borderHeight))
        borderView.layoutParams = borderParams
        borderView.setBackgroundColor(ContextCompat.getColor(context, R.color.mainBg))
        return borderView
    }

    private fun createMenuText(title : String) : TextView {
        val margin10 = resources.getDimensionPixelSize(R.dimen.margin10)
        val titleTextView = TextView(context)
        val newsTextViewParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        newsTextViewParams.setMargins(margin10, margin10, margin10, margin10)
        titleTextView.layoutParams = newsTextViewParams
        titleTextView.text = title
        titleTextView.typeface = ResourcesCompat.getFont(context, R.font.ubuntu_medium)
        titleTextView.setTextColor(ContextCompat.getColor(context, R.color.mainBg))
        return titleTextView
    }

    private fun createContainer() : LinearLayout {
        val menuContainerLayout = LinearLayout(context)
        menuContainerLayout.orientation = VERTICAL
        val menuContainerLayoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        menuContainerLayout.layoutParams = menuContainerLayoutParams
        menuContainerLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
        return menuContainerLayout
    }
}