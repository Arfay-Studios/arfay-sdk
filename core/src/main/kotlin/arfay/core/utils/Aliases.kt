package arfay.core.utils

import com.mojang.authlib.*
import it.unimi.dsi.fastutil.bytes.*
import it.unimi.dsi.fastutil.doubles.*
import it.unimi.dsi.fastutil.floats.*
import it.unimi.dsi.fastutil.ints.*
import it.unimi.dsi.fastutil.longs.*
import it.unimi.dsi.fastutil.objects.*
import it.unimi.dsi.fastutil.shorts.*
import net.minecraft.server.v1_8_R3.Block
import net.minecraft.server.v1_8_R3.Entity
import net.minecraft.server.v1_8_R3.ItemStack
import net.minecraft.server.v1_8_R3.NBTTagCompound
import net.minecraft.server.v1_8_R3.World

typealias Array2D<T> = Array<Array<T>>
typealias Array3D<T> = Array<Array<Array<T>>>
typealias Array4D<T> = Array<Array<Array<Array<T>>>>

typealias ByteObjectMap<T> = Byte2ObjectOpenHashMap<T>
typealias ShortObjectMap<T> = Short2ObjectOpenHashMap<T>
typealias IntObjectMap<T> = Int2ObjectOpenHashMap<T>
typealias LongObjectMap<T> = Long2ObjectOpenHashMap<T>
typealias FloatObjectMap<T> = Float2ObjectOpenHashMap<T>
typealias DoubleObjectMap<T> = Double2ObjectOpenHashMap<T>

typealias ObjectByteMap<T> = Object2ByteOpenHashMap<T>
typealias ObjectShortMap<T> = Object2ShortOpenHashMap<T>
typealias ObjectIntMap<T> = Object2IntOpenHashMap<T>
typealias ObjectLongMap<T> = Object2LongOpenHashMap<T>
typealias ObjectFloatMap<T> = Object2FloatOpenHashMap<T>
typealias ObjectDoubleMap<T> = Object2DoubleOpenHashMap<T>

typealias ByteObjectTreeMap<T> = Byte2ObjectRBTreeMap<T>
typealias ShortObjectTreeMap<T> = Short2ObjectRBTreeMap<T>
typealias IntObjectTreeMap<T> = Int2ObjectRBTreeMap<T>
typealias LongObjectTreeMap<T> = Long2ObjectRBTreeMap<T>
typealias FloatObjectTreeMap<T> = Float2ObjectRBTreeMap<T>
typealias DoubleObjectTreeMap<T> = Double2ObjectRBTreeMap<T>

typealias ByteSet = ByteOpenHashSet
typealias ShortSet = ShortOpenHashSet
typealias IntSet = IntOpenHashSet
typealias LongSet = LongOpenHashSet
typealias FloatSet = FloatOpenHashSet
typealias DoubleSet = DoubleOpenHashSet

typealias Profile = GameProfile

typealias FloatRange = ClosedFloatingPointRange<Float>
typealias DoubleRange = ClosedFloatingPointRange<Double>

typealias NMSEntity = Entity
typealias NBTCompound = NBTTagCompound
typealias NMSItem = ItemStack
typealias NMSBlock = Block
typealias NMSWorld = World
