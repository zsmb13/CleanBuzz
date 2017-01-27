package co.zsmb.example.cleanbuzz.presentation.base

import io.reactivex.schedulers.Schedulers
import org.junit.Assert.assertEquals
import org.junit.Test

class ReplayOneSubjectTest {

    val subject = ReplayOneSubject<Int>(Schedulers.trampoline())

    val testItems = (1..50).toList()
    val subscriberItems = mutableListOf<Int>()

    @Test
    fun subscriberAddedBeforeOnNext_receivesAllItems() {
        // Add subscriber
        subject.subscribe { subscriberItems.add(it) }
        // Call onNext with all items
        testItems.forEach { subject.onNext(it) }

        assertEquals(testItems, subscriberItems)
    }

    @Test
    fun subscriberAddedAfterOnNext_receivesLastItem() {
        // Call onNext with all items
        testItems.forEach { subject.onNext(it) }
        // Add subscriber
        subject.subscribe { subscriberItems.add(it) }

        assertEquals(testItems.last(), subscriberItems.first())
    }

    @Test
    fun subscriberAddedAfterOnNext_receivesLastItemOnly() {
        // Call onNext with all items
        testItems.forEach { subject.onNext(it) }
        // Add subscriber
        subject.subscribe { subscriberItems.add(it) }

        assertEquals(1, subscriberItems.size)
    }

    @Test
    fun subscriberAddedAfterThirdOnNext_receivesThirdToLastItems() {
        // Call onNext with first 3 list items
        testItems.take(3).forEach { subject.onNext(it) }
        // Add subscriber
        subject.subscribe { subscriberItems.add(it) }
        // Call onNext with remaining items
        testItems.subList(3, testItems.lastIndex).forEach { subject.onNext(it) }

        assertEquals(testItems.subList(2, testItems.lastIndex), subscriberItems)
    }

    @Test
    fun subscriberAddedAfterTenthOnNext_receivesTenthToLastItems() {
        // Call onNext with first 10 list items
        testItems.take(10).forEach { subject.onNext(it) }
        // Add subscriber
        subject.subscribe { subscriberItems.add(it) }
        // Call onNext with remaining items
        testItems.subList(10, testItems.lastIndex).forEach { subject.onNext(it) }

        assertEquals(testItems.subList(9, testItems.lastIndex), subscriberItems)
    }

    @Test
    fun immediateUnsubscribe_sendsNoItemsToSubscriber() {
        // Add subscriber
        subject.subscribe { subscriberItems.add(it) }
        // Remove subscriber
        subject.unsubscribe()
        // Call onNext with all items
        testItems.forEach { subject.onNext(it) }

        assertEquals(0, subscriberItems.size)
    }

    @Test
    fun unsubscribeAfterTenthItem_stopsSendingItemsToSubscriber() {
        // Add subscriber
        subject.subscribe { subscriberItems.add(it) }
        // Call onNext with all items
        testItems.take(10).forEach { subject.onNext(it) }
        // Remove subscriber
        subject.unsubscribe()
        // Call onNext with remaining items
        testItems.subList(10, testItems.lastIndex).forEach { subject.onNext(it) }

        assertEquals(10, subscriberItems.size)
    }

}
