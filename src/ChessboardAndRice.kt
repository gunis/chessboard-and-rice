import java.math.BigInteger
import kotlin.test.assertEquals

val SQUARES = 1..64

fun riceGrainsForSquares(): Sequence<BigInteger> = sequence {
    var squares = Pair(BigInteger.ONE, BigInteger.TWO)
    while (true) {
        yield(squares.first)
        squares = Pair(squares.second, squares.second.multiply(BigInteger.TWO))
    }
}

fun getRiceGrainsCountForSquare(square: Int): BigInteger {
    require(square in SQUARES) {
        "Chessboard contains just squares from ${SQUARES.first} to ${SQUARES.last}"
    }

    return riceGrainsForSquares()
        .take(square)
        .last()
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
            actual = getRiceGrainsCountForSquare(it),
            message = "Rice grains count in $it. square is matching"
        )
    }

    assertEquals(
        expected = BigInteger.TWO.pow(SQUARES.last) - BigInteger.ONE,
        actual = getTotalRiceGrainsCount(),
        message = "Total rice grains count on chessboard is matching"
    )
}