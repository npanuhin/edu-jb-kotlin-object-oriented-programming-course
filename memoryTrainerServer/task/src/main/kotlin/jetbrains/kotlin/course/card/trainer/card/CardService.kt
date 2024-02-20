package jetbrains.kotlin.course.card.trainer.card

import org.springframework.stereotype.Service
import jetbrains.kotlin.course.card.trainer.util.countries

@Service
class CardService {
    companion object {
        private val randomCardGenerator = CardSequenceGenerator {
            countries.map {
                (capital, country) -> Card(Front(capital), Back(country))
            }.shuffled()
        }

        private fun generateNewCardsSequence() =
            randomCardGenerator.generateCards().toMutableList()

        private var cards = generateNewCardsSequence()
    }

    fun getNextCard(): Card {
        return cards.also {
            require(it.isNotEmpty()) { "No cards left" }
        }.removeAt(0)
    }

    fun startNewGame(): Card {
        cards = generateNewCardsSequence()
        return getNextCard()
    }
}
