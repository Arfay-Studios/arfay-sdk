package arfay.core.interfaces

/**
 * Represents an object that's can start something.
 */
interface Stoppable {
   
   /**
    * Returns if this stoppable object has stopped.
    */
   var isStopped: Boolean
   
   /**
    * Stops/finishs this stoppable object.
    */
   fun stop() {
      isStopped = true
   }
}
