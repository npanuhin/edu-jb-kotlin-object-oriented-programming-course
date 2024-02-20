package jetbrains.kotlin.course.alias.card

import jetbrains.kotlin.course.alias.util.IdentifierFactory
import jetbrains.kotlin.course.alias.util.words
import org.springframework.stereotype.Service

@Service
class CardService {
    private val identifierFactory: IdentifierFactory = IdentifierFactory()

    private val cards: List<Card> = generateCards()

    companion object {
        private const val WORDS_IN_CARD: Int = 4
        val cardsAmount: Int = words.size / WORDS_IN_CARD
    }

    private fun generateCards(): List<Card> {
        val words = words
            .shuffled()
            .chunked(WORDS_IN_CARD)
            .take(cardsAmount)
        return words.map { Card(identifierFactory.uniqueIdentifier(), it.toWords()) }
    }

    private fun List<String>.toWords(): List<Word> =
        map { Word(it) }

    fun getCardByIndex(index: Int): Card =
        cards.getOrNull(index) ?: throw IllegalStateException("Card not found")
}
