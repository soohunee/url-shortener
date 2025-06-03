package com.cold.urlshortener.support

object Base62Encoder {
    private val CHARACTERS = listOf(
        "0","1","2","3","4","5","6","7","8","9",
        "a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z",
        "A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"
    )

    fun encode(id: Long): String {
        var quotient: Long
        var remainder: Int
        val result = ArrayDeque<String>()

        do {
            quotient = id / 62
            remainder = (id % 62).toInt()

            result.addFirst(CHARACTERS[remainder])
        } while(quotient != 0L)

        return result.joinToString("")
    }
}