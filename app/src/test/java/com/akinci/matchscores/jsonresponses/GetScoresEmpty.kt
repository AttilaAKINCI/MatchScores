package com.akinci.matchscores.jsonresponses

class GetScoresEmpty {
    companion object {
        val scores = """
            {
                "name": "Avrupa Ligi",
                "format": "International cup",
                "date": "2019-10-03 14:50:00",
                "matches": [],
                "groups": [],
                "displayRound": true
            }           
        """
    }
}