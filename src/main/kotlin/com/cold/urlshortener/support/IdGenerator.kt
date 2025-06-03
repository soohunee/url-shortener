package com.cold.urlshortener.support

object IdGenerator {
    /**
     * 1(sign) 41(timestamp) 10(workerId) 12(sequence)
     */
    private const val WORKER_ID = 0L

    private const val WORKER_ID_BITS = 10
    private const val SEQUENCE_ID_BITS = 12

    private const val MAX_SEQUENCE_ID = (1L shl SEQUENCE_ID_BITS) - 1 // 4095

    private const val TIMESTAMP_SHIFT = SEQUENCE_ID_BITS + WORKER_ID_BITS // 22
    private const val WORKER_ID_SHIFT = SEQUENCE_ID_BITS // 12

    private var lastTimestamp = -1L
    private var sequence = 0L

    fun generateId(): Long {
        val currentTimestamp = decideSequenceId(System.currentTimeMillis())

        lastTimestamp = currentTimestamp

        return (currentTimestamp shl TIMESTAMP_SHIFT) or
                (WORKER_ID shl WORKER_ID_SHIFT) or
                sequence
    }

    @Synchronized
    private fun decideSequenceId(timestamp: Long): Long {
        var currentTimestamp = timestamp
        if (lastTimestamp == currentTimestamp) {
            sequence = (sequence + 1) and MAX_SEQUENCE_ID
            if (sequence == 0L) { // 초과
                currentTimestamp = timestamp + 1
            }
        } else {
            sequence = 0L
        }
        return currentTimestamp
    }
}
