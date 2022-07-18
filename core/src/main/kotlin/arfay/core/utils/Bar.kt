package arfay.core.utils

import org.bukkit.*

typealias Color = ChatColor

/**
 * Create a new progress bar with the specified amount value, max, symbol,
 * amount, incomplete color, complete color and percentage color.
 */
tailrec fun createBar(
   value: Number,
   max: Number,
   symbol: String = "▮",
   amount: Int = 10,
   showPercentage: Boolean = true,
   showData: Boolean = false,
   incompleteColor: Color = Color.DARK_GRAY,
   completeColor: Color = Color.LIGHT_PURPLE,
   percentageColor: Color = Color.DARK_GRAY,
   dataColor: Color = ChatColor.DARK_GRAY
): String {
   val percentage = percentage(value, max)
   
   // evict +100% error
   if (percentage > 100)
      return createBar(max, max, symbol, amount, showPercentage, showData, incompleteColor, completeColor, percentageColor, dataColor)
   
   val completedAmount = amount * percentage.toInt() / 100
   val completed = "$completeColor" + symbol * completedAmount
   val uncompleted = "$incompleteColor" + symbol * (amount - completedAmount)
   
   return buildString {
      append(completed).append(uncompleted)
      
      if (showData)
         append(" ").append(dataColor).append("(${value.format()}/${max.format()})")
      
      if (showPercentage)
         append(" ").append(percentageColor).append("(${percentage.decimals(2)}%)")
   }
}
