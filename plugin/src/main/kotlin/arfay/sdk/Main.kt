/*
Copyright (C) 2022 Arfay

You may not use this file except in compliance with the Team Agreement.

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*/

package arfay.sdk

import arfay.graphical.loadInterfaceService
import arfay.repository.*
import org.bukkit.plugin.java.*

class Main : JavaPlugin() {
   
   override fun onLoad() {
      LibService.loadSDK()
      LibService.loadAll()

   }

   override fun onEnable() {
      loadInterfaceService()
   }
   
   override fun onDisable() {
   }
}
