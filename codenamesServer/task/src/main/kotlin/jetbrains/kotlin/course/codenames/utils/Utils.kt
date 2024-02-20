package jetbrains.kotlin.course.codenames.utils

import jetbrains.kotlin.course.codenames.keyCard.KeyCardCell
import jetbrains.kotlin.course.codenames.keyCard.KeyCardType

object Utils {
    private const val N = 5

    const val TOTAL_NUMBER = N * N
    const val PINK_CARDS_NUMBER = 8
    const val VIOLET_CARDS_NUMBER = 9
    const val GRAY_CARDS_NUMBER = 7
    const val BLACK_CARDS_NUMBER = 1

    private val previousAttempts: MutableList<List<KeyCardCell>> = mutableListOf()

    val uniqueKeyCardGenerator: KeyCardGenerator = KeyCardGenerator {
        var keyCard = generateKeyCard()
        while (previousAttempts.contains(keyCard)) {
            keyCard = generateKeyCard()
        }
        previousAttempts.add(keyCard)
        keyCard
    }

    private fun generateKeyCard(): List<KeyCardCell> {
        val keyCard = mutableListOf<KeyCardCell>()
        keyCard.addAll(generateKeyCardCells(PINK_CARDS_NUMBER, KeyCardCell(KeyCardType.Pink)))
        keyCard.addAll(generateKeyCardCells(VIOLET_CARDS_NUMBER, KeyCardCell(KeyCardType.Violet)))
        keyCard.addAll(generateKeyCardCells(GRAY_CARDS_NUMBER, KeyCardCell(KeyCardType.Gray)))
        keyCard.addAll(generateKeyCardCells(BLACK_CARDS_NUMBER, KeyCardCell(KeyCardType.Black)))
        keyCard.shuffle()
        return keyCard
    }

    private fun generateKeyCardCells(pinkCardsNumber: Int, keyCardCell: KeyCardCell): Collection<KeyCardCell> =
        List(pinkCardsNumber) { keyCardCell }

    init {
        require(TOTAL_NUMBER == PINK_CARDS_NUMBER + VIOLET_CARDS_NUMBER + GRAY_CARDS_NUMBER + BLACK_CARDS_NUMBER) {
            "The sum of all cards should be equal to $TOTAL_NUMBER"
        }
    }
}

fun interface KeyCardGenerator {
    fun generateData(): List<KeyCardCell>
}
