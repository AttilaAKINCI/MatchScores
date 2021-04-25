package com.akinci.matchscores.features.scores.data.output

import com.akinci.matchscores.R
import com.akinci.matchscores.features.scores.adapter.viewholder.ViewHolderTypeFactory
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Score (
    val id : Int,                       //used for unique identifier
    val headerTitleAndTime : String,    //used for header
    val type : Int,                     //0: Header / 1:Score
    val fts_A : Int,                    //score a
    val fts_B : Int,                    //score b
    val teamNameA : String,             //team name a
    val teamNameB : String              //team name b
) {
    // constructor for headers
    /** TODO Score header Id should be fetched from remote or calculate same value for each time
     *  Directly given fixed header id is not a good solution for recycler view diff util.
     * **/
    constructor(
        title: String
    ) : this (1, title, 0, 0,0,"","")

    // constructor for scores
    constructor(
        id: Int,
        fts_A: Int,
        fts_B: Int,
        teamNameA: String,
        teamNameB: String
    ) : this (id , "", 1, fts_A, fts_B, teamNameA, teamNameB)

    /**
     *  This object is used for message list view. The functions below are part of Visitor Pattern
     *  of Multiple View Recycler View
     * **/

    fun type(viewHolderTypeFactory: ViewHolderTypeFactory): Int {
        return viewHolderTypeFactory.type(this)
    }

    companion object {
        const val HEADER_VIEW = R.layout.row_score_header
        const val SCORE_VIEW = R.layout.row_score
    }
}