package net.arfay.sdk.utils

fun <K, V> Array<out Pair<K, V>>.toMutableMap(): MutableMap<K, V> = toMap(LinkedHashMap())
fun <K, V> Array<out Pair<K, V>>.toHashMap() = toMap(HashMap())
fun <K, V> Array<out Pair<K, V>>.toLinkedHashMap() = toMap(LinkedHashMap())

