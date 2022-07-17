package arfay.sdk

import kotlinx.coroutines.*
import org.bukkit.plugin.java.JavaPlugin
import kotlin.coroutines.*

/**
 * Extendable [JavaPlugin].
 */
abstract class Plugin : JavaPlugin(), CoroutineScope {
   override val coroutineContext = EmptyCoroutineContext
}
