package arfay.core.utils

import com.mojang.authlib.properties.Property
import org.bukkit.inventory.meta.SkullMeta
import java.lang.reflect.Field

/**
 * Gets the texture property of this profile.
 */
val Profile.texture: MutableCollection<Property>?
   get() = properties["textures"]

/**
 * Gets the profile of this skull meta.
 */
var SkullMeta.profile: Profile
   get() = profileField.get(this) as Profile
   set(value) {
      profileField.set(this, value)
   }

/**
 * Gets the head data of this skull meta.
 */
var SkullMeta.head: String
   get() = profile.texture?.find { it.name == "textures" }?.value ?: ""
   set(value) {
      val prof = Profile(randomUUID(), null)
      prof.properties.put("textures", Property("textures", value, null))
      profile = prof
   }


internal val profileField: Field by lazy {
   SkullMeta::class.java.getDeclaredField("profile").apply {
      isAccessible = true
   }
}
