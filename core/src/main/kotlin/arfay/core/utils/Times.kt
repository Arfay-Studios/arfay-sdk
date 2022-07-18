@file:Suppress("NOTHING_TO_INLINE")

package arfay.core.utils

import com.soywiz.klock.*
import kotlin.time.Duration
import java.text.*
import java.text.DateFormat
import java.time.*
import java.util.Date

/**
 * The default date format used.
 */
val DEFAULT_DATE_FORMAT = SimpleDateFormat("HH:mm:ss dd/MM/yyyy")

/**
 * Returns the current time millis.
 */
inline val currentMillis get() = System.currentTimeMillis()

/**
 * Gets the actual date, this is, the current date.
 */
inline fun now(): DateTime = DateTime.now()

/**
 * Converts this date to a date time.
 */
inline fun Date.toDateTime(): DateTime = DateTime(time)

/**
 * Converts this date to a local date.
 */
inline fun Date.toLocalDateTime(): LocalDateTime =
	LocalDateTime.of(year, month, day, hours, minutes, seconds, 0)

/**
 * Formats this date by the specifed date format.
 */
inline fun Date.format(format: DateFormat = DEFAULT_DATE_FORMAT): String = format.format(this)

/**
 * Converts this string to a date by the specified date format.
 */
inline fun String.toDate(format: DateFormat = DEFAULT_DATE_FORMAT): Date = format.parse(this)

/**
 * Parses this string to a duration.
 */
inline fun String.toDuration(): Duration = Duration.parse(this)

/**
 * Adds the given [duration] to this date time.
 */
operator fun DateTime.plus(duration: Duration): DateTime {
	return this + duration.inWholeMilliseconds.milliseconds
}

/**
 * Creates a date progression by steping with the specified [step].
 */
infix fun ClosedRange<DateTime>.step(step: TimeSpan): DateProgression {
	return DateProgression(this.start, this.endInclusive, step)
}

/**
 * Creates a date progression by steping with the specified [step].
 */
infix fun ClosedRange<DateTime>.step(step: MonthSpan): DateMonthProgression {
	return DateMonthProgression(this.start, this.endInclusive, step)
}

/**
 * A date time progression range.
 */
class DateProgression(
	override val start: DateTime,
	override val endInclusive: DateTime,
	val step: TimeSpan,
) : ClosedRange<DateTime>, Iterable<DateTime> {
	override fun iterator(): Iterator<DateTime> {
		return DateProgressionIterator(start, endInclusive, step)
	}
}

/**
 * A date time progression range with month span.
 */
class DateMonthProgression(
	override val start: DateTime,
	override val endInclusive: DateTime,
	val step: MonthSpan,
) : ClosedRange<DateTime>, Iterable<DateTime> {
	override fun iterator(): Iterator<DateTime> {
		return DateProgressionMonthIterator(start, endInclusive, step)
	}
}

class DateProgressionIterator(
	var first: DateTime,
	var last: DateTime,
	val step: TimeSpan,
) : Iterator<DateTime> {
	val finalElement = last
	var hasNext = first <= last
	var next = if (hasNext()) first else finalElement
	
	override fun hasNext(): Boolean = hasNext
	
	override fun next(): DateTime {
		val value = next
		val to = value + step
		if (to >= finalElement) {
			if (!hasNext) throw kotlin.NoSuchElementException()
			hasNext = false
		} else {
			next = to
		}
		return value
	}
}

class DateProgressionMonthIterator(
	var first: DateTime,
	var last: DateTime,
	val step: MonthSpan,
) : Iterator<DateTime> {
	val finalElement = last
	var hasNext = first <= last
	var next = if (hasNext()) first else finalElement
	
	override fun hasNext(): Boolean = hasNext
	
	override fun next(): DateTime {
		val value = next
		val to = value + step
		if (to >= finalElement) {
			if (!hasNext) throw kotlin.NoSuchElementException()
			hasNext = false
		} else {
			next = to
		}
		return value
	}
}
