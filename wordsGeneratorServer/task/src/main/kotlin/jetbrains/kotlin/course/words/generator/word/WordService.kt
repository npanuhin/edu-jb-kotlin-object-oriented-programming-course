package jetbrains.kotlin.course.words.generator.word

import jetbrains.kotlin.course.words.generator.util.words
import org.springframework.stereotype.Service

@Service
class WordService {

    companion object {
        val numberOfWords: Int = words.size
        val previousWords: MutableMap<String, MutableList<Word>> = mutableMapOf()
    }

    fun generateNextWord(): Word {
        require(words.isNotEmpty()) { "No words available" }
        return Word(words.removeFirst())
    }

    fun isValidWord(keyWord: String, newWord: String): Boolean {
        if (newWord.isEmpty()) return false

        val keyWordGrouped = keyWord.groupingBy { it }.eachCount()
        val newWordGrouped = newWord.groupingBy { it }.eachCount()

        for ((key, value) in newWordGrouped) {
            if ((keyWordGrouped[key] ?: 0) < value) return false
        }
        return true
    }

    fun isNewWord(keyWord: String, newWord: String): Boolean {
        if (!previousWords.containsKey(keyWord)) {
            previousWords[keyWord] = mutableListOf(Word(newWord))
            return true
        }
        if (previousWords[keyWord]!!.contains(Word(newWord))) return false
        previousWords[keyWord]!!.add(Word(newWord))
        return true
    }
}
