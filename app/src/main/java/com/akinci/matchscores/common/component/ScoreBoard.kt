package com.akinci.matchscores.common.component

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import com.akinci.matchscores.R
import com.akinci.matchscores.features.scores.data.output.Score
import timber.log.Timber

@BindingAdapter("scoreBoardData")
fun insertData (scoreBoard: ScoreBoard, data : MutableLiveData<List<Score>>){
    scoreBoard.insertScoreBoardData(data)
}

class ScoreBoard : LinearLayout {
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

    private fun initialize(context: Context, attrs: AttributeSet?, defStyleAttr: Int){
        orientation = VERTICAL
        setBackgroundColor(ContextCompat.getColor(context, R.color.mainBg))
    }

    fun insertScoreBoardData(data : MutableLiveData<List<Score>>) {
        removeAllViews()
        data.value?.let {
            for(score in it) {
                if(score.type == ScoreType.HEADER.ordinal){
                    addView(createHeader(score.headerTitleAndTime))
                } else {
                    addView(createScoreRow(score))
                }
            }
        }
        Timber.d("ScoreBoard Component Updated with new data size:${data.value?.size}")
    }

    private fun createHeader(title : String) : LinearLayout {
        val margin10 = resources.getDimensionPixelSize(R.dimen.margin10)

        val header = LinearLayout(context)
        header.orientation = VERTICAL
        val headerLayoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        header.layoutParams = headerLayoutParams
        header.setBackgroundColor(ContextCompat.getColor(context, R.color.grayDark))

        val headerTitle = TextView(context)
        val headerTitleLayoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        headerTitleLayoutParams.setMargins(margin10, margin10, margin10, margin10)
        headerTitle.layoutParams = headerTitleLayoutParams
        headerTitle.setTextColor(ContextCompat.getColor(context, R.color.white))
        headerTitle.typeface = ResourcesCompat.getFont(context, R.font.ubuntu_medium)
        headerTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, resources.getDimension(R.dimen.textSize))
        headerTitle.text = title

        header.addView(headerTitle)

        return header
    }

    private fun createScoreRow(score : Score) : LinearLayout {
        val margin10 = resources.getDimensionPixelSize(R.dimen.margin10)

        val scoreContainer = LinearLayout(context)
        scoreContainer.orientation = HORIZONTAL
        val scoreContainerLayoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        scoreContainer.layoutParams = scoreContainerLayoutParams
        scoreContainer.isBaselineAligned = false

        /** LEFT SCORE PANEL **/
        val leftScoreContainer = LinearLayout(context)
        leftScoreContainer.orientation = HORIZONTAL
        val leftScoreContainerLayoutParams = LayoutParams(0, LayoutParams.WRAP_CONTENT)
        leftScoreContainerLayoutParams.weight = 0.4f
        leftScoreContainer.layoutParams = leftScoreContainerLayoutParams
        leftScoreContainer.setBackgroundColor(ContextCompat.getColor(context, R.color.shimmerColorSoft))

        val leftScoreMemberTitle = TextView(context)
        val leftScoreMemberTitleLayoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        leftScoreMemberTitleLayoutParams.setMargins(margin10, margin10, margin10, margin10)
        leftScoreMemberTitle.layoutParams = leftScoreMemberTitleLayoutParams
        leftScoreMemberTitle.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary))
        leftScoreMemberTitle.typeface = ResourcesCompat.getFont(context, R.font.ubuntu)
        leftScoreMemberTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, resources.getDimension(R.dimen.textSize))
        leftScoreMemberTitle.text = score.teamNameA

        leftScoreContainer.addView(leftScoreMemberTitle)
        scoreContainer.addView(leftScoreContainer)
        /** LEFT SCORE PANEL END **/

        /** MIDDLE PANEL SCORES **/
        val middleScoreContainer = LinearLayout(context)
        middleScoreContainer.orientation = HORIZONTAL
        val middleScoreContainerLayoutParams = LayoutParams(0, LayoutParams.WRAP_CONTENT)
        middleScoreContainerLayoutParams.weight = 0.2f
        middleScoreContainer.layoutParams = middleScoreContainerLayoutParams
        middleScoreContainer.setBackgroundColor(ContextCompat.getColor(context, R.color.shimmerColor))

        val middleScoreMemberTitle = TextView(context)
        val middleScoreMemberTitleLayoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        middleScoreMemberTitleLayoutParams.setMargins(margin10, margin10, margin10, margin10)
        middleScoreMemberTitle.layoutParams = middleScoreMemberTitleLayoutParams
        middleScoreMemberTitle.setTextColor(ContextCompat.getColor(context, R.color.white))
        middleScoreMemberTitle.typeface = ResourcesCompat.getFont(context, R.font.ubuntu)
        middleScoreMemberTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, resources.getDimension(R.dimen.textSize))
        middleScoreMemberTitle.textAlignment = TEXT_ALIGNMENT_CENTER
        val text = "${score.fts_A}     -     ${score.fts_B}"
        middleScoreMemberTitle.text = text

        middleScoreContainer.addView(middleScoreMemberTitle)
        scoreContainer.addView(middleScoreContainer)
        /** MIDDLE PANEL SCORES END**/

        /** RIGHT SCORE PANEL **/
        val rightScoreContainer = LinearLayout(context)
        rightScoreContainer.orientation = HORIZONTAL
        val rightScoreContainerLayoutParams = LayoutParams(0, LayoutParams.WRAP_CONTENT)
        rightScoreContainerLayoutParams.weight = 0.4f
        rightScoreContainer.layoutParams = rightScoreContainerLayoutParams
        rightScoreContainer.setBackgroundColor(ContextCompat.getColor(context, R.color.shimmerColorSoft))

        val rightScoreMemberTitle = TextView(context)
        val rightScoreMemberTitleLayoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        rightScoreMemberTitleLayoutParams.setMargins(margin10, margin10, margin10, margin10)
        rightScoreMemberTitle.layoutParams = rightScoreMemberTitleLayoutParams
        rightScoreMemberTitle.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary))
        rightScoreMemberTitle.typeface = ResourcesCompat.getFont(context, R.font.ubuntu)
        rightScoreMemberTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, resources.getDimension(R.dimen.textSize))
        rightScoreMemberTitle.textAlignment = TEXT_ALIGNMENT_TEXT_END
        rightScoreMemberTitle.text = score.teamNameB

        rightScoreContainer.addView(rightScoreMemberTitle)
        scoreContainer.addView(rightScoreContainer)
        /** RIGHT SCORE PANEL END **/

        return scoreContainer
    }
}