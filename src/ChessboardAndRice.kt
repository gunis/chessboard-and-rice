import java.math.BigInteger
import kotlin.test.assertEquals

val SQUARES = 1..64

fun getRiceGrainsCountForSquare(square: Int): BigInteger {
    require(square in SQUARES) {
        "Chessboard contains just squares from ${SQUARES.start} to ${SQUARES.endInclusive}"
    }

    var riceGrainsCount = BigInteger.ONE
    if (square == 1) return riceGrainsCount

    (2..square).forEach { riceGrainsCount = riceGrainsCount.multiply(BigInteger.TWO) }

    return riceGrainsCount
}

fun getTotalRiceGrainsCount(): BigInteger {
    var totalRiceGrainsCount = BigInteger.ZERO

    SQUARES.forEach { totalRiceGrainsCount += getRiceGrainsCountForSquare(it) }

    return totalRiceGrainsCount
}

fun main() {
    SQUARES.forEach {
        assertEquals(
            expected = BigInteger.TWO.pow(it - 1),
            actual = getRiceGrainsCountForSquare(it))
    }

    assertEquals(
        expected = BigInteger.TWO.pow(SQUARES.endInclusive) - BigInteger.ONE,
        actual = getTotalRiceGrainsCount()
    )
}