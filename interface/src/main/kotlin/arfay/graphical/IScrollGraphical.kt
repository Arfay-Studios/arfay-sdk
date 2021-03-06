package arfay.graphical

import arfay.graphical.common.*
import arfay.graphical.engines.*
import arfay.graphical.interfaces.*
import arfay.graphical.mapper.*
import arfay.graphical.schema.*
import kotlin.math.*

typealias Scrollers = List<List<Engine>>
typealias ScrollRenderAction = Action<IScrollGraphical>

/**
 * Represents a scrollable graphical interface. This interface
 * can map all [source] with [schema] and [mapper].
 * This is equals to a paginated inventory. This not stores all pages
 * in a separed graphical interface in a list, this map in runtime
 * all [source] and the [schema] and [mapper] will decide
 * when showing an engine.
 */
interface IScrollGraphical : IGraphical, Scrollable {
   
   /**
    * Represents an engine used to scroll down
    * this scrollable graphical interface.
    */
   var scrollDownEngine: ScrollDownEngine
   
   /**
    * Represents an engine used to scroll up
    * this scrollable graphical interface.
    */
   var scrollUpEngine: ScrollUpEngine
   
   /**
    * The schematic used for mapping all scrollable engines
    * of this scrollable graphical interface.
    */
   var schema: Schema
   
   /**
    * All possible scrollable engines. This is used to map
    * all engines in this scrollable graphical interface.
    * ### Note:
    * This is different of [engineStack] because engineStack just
    * stores all current engines, so for example, if I have a scrollable
    * graphical interface with two pages and my current page is one, the
    * [engineStack] only will show the engines of my current page, while
    * [source] will show all pages.
    * This not includes [scrollUpEngine] and [scrollDownEngine] engine.
    * To not includes a scrollable engine in the mapper, just adds the
    * [isPersistent] metadata to an engine.
    */
   var source: Source
   
   /**
    * A mapper to order all [source].
    */
   var mapper: Mapper
   
   /**
    * All pages mapped from [mapper].
    */
   var pages: Scrollers
   
   /**
    * The current page of this [IScrollGraphical]
    */
   var page: Int
   
   /**
    * The scroll amount that's this graphical has maden.
    */
   var scrolleds: Int
   
   /**
    * Returns if this scroll graphical needs an update from [Mapper].
    */
   var isDirty: Boolean
   
   /**
    * Scrolls this [IScrollGraphical] to a certain page.
    * If a [to] is less than 0, equals than [page] or is greather
    * than [pageCount] nothing will be done.
    */
   fun scrollTo(to: Int = 1) {
      // fast non possible scroll
      if (to < 1 || hasScrolled && to > pageCount)
         return
      
      // evict out of index error
      if (source.isEmpty()) {
         uninstallAllNonPersistents()
         return
      }
      
      page = to
      
      // optimizes mapper call
      if (!hasScrolled || isDirty) {
         pages = mapper.map(this, source)
         isDirty = false
      }
      
      val engines = currentEngines
      
      // TODO: this is really necessary?
      if (hasScrolled) uninstallAllNonPersistents()
      
      var installed = 0
      for (i in 0 until size) {
         if (i !in schema) continue
         if (installed >= schema.size || installed >= engines.size) break
         install(i, engines[installed++])
      }
      
      if (!hasScrolled) initEngines()
      
      scrollAll()
   }
   
   override fun scroll() {
      scrolleds++
      for (scroll in scrollers) scroll(this)
   }
   
   /**
    * Initializes all default engines for this graphical.
    */
   fun initEngines() {
      install(scrollUpEngine)
      install(scrollDownEngine)
   }
}

/**
 * Edits the scroll up engine of this graphical.
 */
inline fun IScrollGraphical.editScrollUp(block: ScrollUpEngine.() -> Unit) {
   scrollUpEngine.apply(block).notifyChange()
}

/**
 * Edits the scroll up engine of this graphical.
 */
inline fun IScrollGraphical.editScrollDown(block: ScrollDownEngine.() -> Unit) {
   scrollDownEngine.apply(block).notifyChange()
}

/**
 * Scrolls up this scrollable graphical interface.
 */
fun IScrollGraphical.scrollUp() = scrollTo(next)

/**
 * Scrolls down this scrollable graphical interface.
 */
fun IScrollGraphical.scrollDown() = scrollTo(previous)

/**
 * Updates the current page of this scrollable graphical interface.
 */
fun IScrollGraphical.updatePage() = scrollTo(page)

/**
 * Triggers all scrollers handlers. Including
 * [IScrollGraphical] scrolls handlers and
 * engines scrolls handlers
 */
fun IScrollGraphical.scrollAll() {
   scroll()
   for (engine in engineStack.values) engine.scroll()
}

/**
 * Returns all current mappable engines of
 * this scrollable graphical interface
 */
inline val IScrollGraphical.currentEngines get() = pages[page - 1]

/**
 * Returns the amount of pages that's this
 * scrollable graphical interface contains.
 */
inline val IScrollGraphical.pageCount get() = pages.size

/**
 * Returns if this scrollable graphical interface
 * is in the first page.
 */
inline val IScrollGraphical.isFirstPage get() = page <= 1

/**
 * Returns the scroll page that is the previous of the current.
 */
inline val IScrollGraphical.previous get() = max(1, page - 1)

/**
 * Returns the scroll page that is the next of the current.
 */
inline val IScrollGraphical.next get() = min(pageCount, page + 1)

/**
 * Returns if this scrollable graphical interface
 * is in the last page.
 */
inline val IScrollGraphical.isLastPage get() = page >= pages.size

/**
 * Returns if this scrollable graphical interface never drewed.
 */
inline val IScrollGraphical.hasScrolled get() = scrolleds >= 1

/**
 * Returns the amount of engines that a scrollable
 * graphical interface can install per page.
 */
inline val IScrollGraphical.enginesPerPage: Int
   get() = schema.size

/**
 * Registers a handlers to scrollers and renders
 * of this [IScrollGraphical].
 */
fun IScrollGraphical.onScrollAndRender(action: ScrollRenderAction) {
   onScroll { action(this) }
   onRender { action(this@onScrollAndRender) }
}
