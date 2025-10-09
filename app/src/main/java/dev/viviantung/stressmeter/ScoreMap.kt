package dev.viviantung.stressmeter

object ScoreMap {
    val scores = listOf(
        6, 8, 14, 16,
        5, 7, 13, 15,
        2, 4, 10, 12,
        1, 3, 9, 11
    )

    fun getScore(position: Int): Int {
        return scores[position]
    }
}