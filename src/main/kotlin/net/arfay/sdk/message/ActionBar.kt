/*
Copyright (C) 2022 Arfay

You may not use this file except in compliance with the Team Agreement.

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*/

package net.arfay.sdk.message

import net.minecraft.server.v1_8_R3.IChatBaseComponent
import net.minecraft.server.v1_8_R3.Packet
import net.minecraft.server.v1_8_R3.PacketListener
import net.minecraft.server.v1_8_R3.PacketPlayOutChat
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer
import org.bukkit.entity.Player

/**
 * Sends a packet to this player.
 */
fun Player.sendPacket(packet: Packet<out PacketListener>) {
    (this as CraftPlayer).handle.playerConnection.sendPacket(packet)
}

/**
 * Sends an action bar to this player.
 */
fun Player.actionBar(message: String) {
    sendPacket(PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a("{\"text\":\"$message\"}"), 2.toByte()))
}