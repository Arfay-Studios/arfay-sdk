package arfay.core.interfaces

/**
 * Represents an object that's can start something.
 */
interface Startable {
   
   /**
    * Returns if this startable object has started.
    */
   var isStarted: Boolean
   
   /**
    * Starts this startable object.
    */
   fun start() {
      isStarted = true
   }
}
