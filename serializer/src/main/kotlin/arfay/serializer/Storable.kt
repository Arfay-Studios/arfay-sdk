package arfay.serializer

import arfay.serializer.tag.types.CompoundTag

/**
 * Represents an object that's can be stored.
 */
interface Storable {

    /**
     * Saves this storable object in the given [tag].
     *
     * @return the same [tag].
     */
    fun save(tag: CompoundTag): CompoundTag

    /**
     * Loads this storable in the given [tag].
     *
     * @return the same [tag].
     */
    fun load(tag: CompoundTag): CompoundTag
}

/**
 * Saves the given [storable] object with the given key in this compound tag.
 */
fun CompoundTag.save(key: String, storable: Storable) = put(key, storable.save(CompoundTag()))
