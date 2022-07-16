package arfay.sdk.utils

import com.google.common.collect.*
import net.arfay.sdk.extensions.*
import org.bukkit.*
import org.bukkit.inventory.*
import org.bukkit.material.*
import java.util.*

typealias MaterialPair = Pair<Int, Int>

/**
 * Represents a more useful material enum type.
 */
enum class Materials(val type: Material, val subdata: Int = 0, val category: Category = Category.BLOCK) {
   AIR(Material.AIR),
   STONE(Material.STONE),
   GRANITE(Material.STONE, 1),
   POLISHED_GRANITE(Material.STONE, 2),
   DIORITE(Material.STONE, 3),
   POLISHED_DIORITE(Material.STONE, 4),
   ANDESITE(Material.STONE, 5),
   POLISHED_ANDESITE(Material.STONE, 6),
   GRASS(Material.GRASS),
   DIRT(Material.DIRT),
   COARSE_DIRT(Material.DIRT, 1),
   PODZOL(Material.DIRT, 2),
   COBBLESTONE(Material.COBBLESTONE),
   OAK_PLANK(Material.WOOD),
   SPRUCE_PLANK(Material.WOOD, 1),
   BIRCH_PLANK(Material.WOOD, 2),
   JUNGLE_PLANK(Material.WOOD, 3),
   ACACIA_PLANK(Material.WOOD, 4),
   DARK_OAK_PLANK(Material.WOOD, 5),
   OAK_SAPLING(Material.SAPLING),
   SPRUCE_SAPLING(Material.SAPLING, 1),
   BIRCH_SAPLING(Material.SAPLING, 2),
   JUNGLE_SAPLING(Material.SAPLING, 3),
   ACACIA_SAPLING(Material.SAPLING, 4),
   DARK_OAK_SAPLING(Material.SAPLING, 5),
   BEDROCK(Material.BEDROCK),
   WATER(Material.WATER, category = Category.LIQUID),
   STATIONARY_WATER(Material.STATIONARY_WATER, category = Category.LIQUID),
   LAVA(Material.LAVA, category = Category.LIQUID),
   STATIONARY_LAVA(Material.STATIONARY_LAVA, category = Category.LIQUID),
   SAND(Material.SAND),
   RED_SAND(Material.SAND, 1),
   GRAVEL(Material.GRAVEL),
   GOLD_ORE(Material.GOLD_ORE),
   IRON_ORE(Material.IRON_ORE),
   COAL_ORE(Material.COAL_ORE),
   OAK_WOOD(Material.LOG),
   SPRUCE_WOOD(Material.LOG, 1),
   BIRCH_WOOD(Material.LOG, 2),
   JUNGLE_WOOD(Material.LOG, 3),
   OAK_BARK(Material.LOG, 12),
   SPRUCE_BARK(Material.LOG, 13),
   BIRCH_BARK(Material.LOG, 14),
   JUNGLE_BARK(Material.LOG, 15),
   OAK_LEAVES(Material.LEAVES),
   SPRUCE_LEAVES(Material.LEAVES, 1),
   BIRCH_LEAVES(Material.LEAVES, 2),
   JUNGLE_LEAVES(Material.LEAVES, 3),
   OAK_BARK_LEAVES(Material.LEAVES, 12),
   SPRUCE_BARK_LEAVES(Material.LEAVES, 13),
   BIRCH_BARK_LEAVES(Material.LEAVES, 14),
   JUNGLE_BARK_LEAVES(Material.LEAVES, 15),
   SPONGE(Material.SPONGE),
   WET_SPONGE(Material.SPONGE, 1),
   GLASS(Material.GLASS),
   LAPIS_ORE(Material.LAPIS_ORE),
   LAPIS_BLOCK(Material.LAPIS_BLOCK),
   DISPENSER(Material.DISPENSER),
   SANDSTONE(Material.SANDSTONE),
   CHISELED_SANDSTONE(Material.SANDSTONE, 1),
   SMOOTH_SANDSTONE(Material.SANDSTONE, 2),
   NOTE_BLOCK(Material.NOTE_BLOCK, category = Category.TILE_ENTITY),
   BED_BLOCK(Material.BED_BLOCK),
   POWERED_RAIL(Material.POWERED_RAIL),
   DETECTOR_RAIL(Material.DETECTOR_RAIL),
   PISTON_STICKY_BASE(Material.PISTON_STICKY_BASE, category = Category.TILE_ENTITY),
   WEB(Material.WEB),
   DEAD_GRASS(Material.LONG_GRASS),
   TALL_GRASS(Material.LONG_GRASS, 1),
   FERN(Material.LONG_GRASS, 2),
   DEAD_BUSH(Material.DEAD_BUSH),
   
   // piston section
   PISTON(Material.PISTON_BASE, category = Category.TILE_ENTITY),
   PISTON_UP(Material.PISTON_BASE, 1, category = Category.TILE_ENTITY),
   PISTON_NORTH(Material.PISTON_BASE, 2, category = Category.TILE_ENTITY),
   PISTON_SOUTH(Material.PISTON_BASE, 3, category = Category.TILE_ENTITY),
   PISTON_WEST(Material.PISTON_BASE, 4, category = Category.TILE_ENTITY),
   PISTON_EAST(Material.PISTON_BASE, 5, category = Category.TILE_ENTITY),
   PISTON_BASE(Material.PISTON_BASE, 8, category = Category.TILE_ENTITY),
   PISTON_BASE_UP(Material.PISTON_BASE, 9, category = Category.TILE_ENTITY),
   PISTON_BASE_NORTH(Material.PISTON_BASE, 10, category = Category.TILE_ENTITY),
   PISTON_BASE_SOUTH(Material.PISTON_BASE, 11, category = Category.TILE_ENTITY),
   PISTON_BASE_WEST(Material.PISTON_BASE, 12, category = Category.TILE_ENTITY),
   PISTON_BASE_EAST(Material.PISTON_BASE, 13, category = Category.TILE_ENTITY),
   PISTON_EXTENSION(Material.PISTON_EXTENSION, category = Category.TILE_ENTITY),
   PISTON_EXTENSION_UP(Material.PISTON_EXTENSION, 1, category = Category.TILE_ENTITY),
   PISTON_EXTENSION_NORTH(Material.PISTON_EXTENSION, 2, category = Category.TILE_ENTITY),
   PISTON_EXTENSION_SOUTH(Material.PISTON_EXTENSION, 3, category = Category.TILE_ENTITY),
   PISTON_EXTENSION_WEST(Material.PISTON_EXTENSION, 4, category = Category.TILE_ENTITY),
   PISTON_EXTENSION_EAST(Material.PISTON_EXTENSION, 5, category = Category.TILE_ENTITY),
   STICKY_PISTON_EXTENSION(Material.PISTON_EXTENSION, 8, category = Category.TILE_ENTITY),
   STICKY_PISTON_EXTENSION_UP(Material.PISTON_EXTENSION, 9, category = Category.TILE_ENTITY),
   STICKY_PISTON_EXTENSION_NORTH(Material.PISTON_EXTENSION, 10, category = Category.TILE_ENTITY),
   STICKY_PISTON_EXTENSION_SOUTH(Material.PISTON_EXTENSION, 11, category = Category.TILE_ENTITY),
   STICKY_PISTON_EXTENSION_WEST(Material.PISTON_EXTENSION, 12, category = Category.TILE_ENTITY),
   STICKY_PISTON_EXTENSION_EAST(Material.PISTON_EXTENSION, 13, category = Category.TILE_ENTITY),
   
   WHITE_WOOL(Material.WOOL, 0, Category.COLORED_BLOCK),
   ORANGE_WOOL(Material.WOOL, 1, Category.COLORED_BLOCK),
   MAGENTA_WOOL(Material.WOOL, 2, Category.COLORED_BLOCK),
   LIGHT_BLUE_WOOL(Material.WOOL, 3, Category.COLORED_BLOCK),
   YELLOW_WOOL(Material.WOOL, 4, Category.COLORED_BLOCK),
   LIME_WOOL(Material.WOOL, 5, Category.COLORED_BLOCK),
   PINK_WOOL(Material.WOOL, 6, Category.COLORED_BLOCK),
   GRAY_WOOL(Material.WOOL, 7, Category.COLORED_BLOCK),
   LIGHT_GRAY_WOOL(Material.WOOL, 8, Category.COLORED_BLOCK),
   CYAN_WOOL(Material.WOOL, 9, Category.COLORED_BLOCK),
   PURPLE_WOOL(Material.WOOL, 10, Category.COLORED_BLOCK),
   BLUE_WOOL(Material.WOOL, 11, Category.COLORED_BLOCK),
   BROWN_WOOL(Material.WOOL, 12, Category.COLORED_BLOCK),
   GREEN_WOOL(Material.WOOL, 13, Category.COLORED_BLOCK),
   RED_WOOL(Material.WOOL, 14, Category.COLORED_BLOCK),
   BLACK_WOOL(Material.WOOL, 15, Category.COLORED_BLOCK),
   YELLOW_FLOWER(Material.YELLOW_FLOWER),
   RED_ROSE(Material.RED_ROSE),
   BLUE_ORCHID(Material.RED_ROSE, 1),
   ALLIUM(Material.RED_ROSE, 2),
   AZURE_BLUET(Material.RED_ROSE, 3),
   RED_TULIP(Material.RED_ROSE, 4),
   ORANGE_TULIP(Material.RED_ROSE, 5),
   WHITE_TULIP(Material.RED_ROSE, 6),
   PINK_TULIP(Material.RED_ROSE, 7),
   OXEYE_DAISY(Material.RED_ROSE, 8),
   BROWN_MUSHROOM(Material.BROWN_MUSHROOM),
   RED_MUSHROOM(Material.RED_MUSHROOM),
   GOLD_BLOCK(Material.GOLD_BLOCK),
   IRON_BLOCK(Material.IRON_BLOCK),
   DOUBLE_STONE_SLAB(Material.DOUBLE_STEP),
   DOUBLE_SANDSTONE_SLAB(Material.DOUBLE_STEP, 1),
   DOUBLE_PLANKS_SLAB(Material.DOUBLE_STEP, 2),
   DOUBLE_COBBLESTONE_SLAB(Material.DOUBLE_STEP, 3),
   DOUBLE_BRICKS_SLAB(Material.DOUBLE_STEP, 4),
   DOUBLE_STONE_BRICKS_SLAB(Material.DOUBLE_STEP, 5),
   DOUBLE_NETHER_BRICKS_SLAB(Material.DOUBLE_STEP, 6),
   DOUBLE_QUARTZ_SLAB(Material.DOUBLE_STEP, 7),
   STONE_SLAB(Material.STEP),
   SANDSTONE_SLAB(Material.STEP, 1),
   PLANKS_SLAB(Material.STEP, 2),
   COBBLESTONE_SLAB(Material.STEP, 3),
   BRICKS_SLAB(Material.STEP, 4),
   STONE_BRICKS_SLAB(Material.STEP, 5),
   NETHER_BRICKS_SLAB(Material.STEP, 6),
   QUARTZ_SLAB(Material.STEP, 7),
   BRICKS(Material.BRICK),
   TNT(Material.TNT),
   BOOKSHELF(Material.BOOKSHELF),
   MOSSY_COBBLESTONE(Material.MOSSY_COBBLESTONE),
   OBSIDIAN(Material.OBSIDIAN),
   TORCH(Material.TORCH),
   FIRE(Material.FIRE),
   MOB_SPAWNER(Material.MOB_SPAWNER, category = Category.TILE_ENTITY),
   OAK_WOOD_STAIRS(Material.WOOD_STAIRS),
   CHEST(Material.CHEST, category = Category.TILE_ENTITY),
   REDSTONE_WIRE(Material.REDSTONE_WIRE),
   DIAMOND_ORE(Material.DIAMOND_ORE),
   DIAMOND_BLOCK(Material.DIAMOND_BLOCK),
   CRAFTING_TABLE(Material.WORKBENCH, category = Category.TILE_ENTITY),
   WHEAT_CROPS(Material.CROPS, category = Category.PLANTATION),
   FARMLAND(Material.SOIL),
   FURNACE(Material.FURNACE, category = Category.TILE_ENTITY),
   BURNING_FURNACE(Material.BURNING_FURNACE, category = Category.TILE_ENTITY),
   SIGN_BLOCK(Material.SIGN_POST),
   OAK_DOOR(Material.WOODEN_DOOR),
   LADDER(Material.LADDER),
   RAIL(Material.RAILS),
   COBBLESTONE_STAIRS(Material.COBBLESTONE_STAIRS),
   WALL_SIGN(Material.WALL_SIGN),
   LEVER(Material.LEVER),
   STONE_PLATE(Material.STONE_PLATE),
   IRON_DOOR_BLOCK(Material.IRON_DOOR_BLOCK),
   WOOD_PLATE(Material.WOOD_PLATE),
   REDSTONE_ORE(Material.REDSTONE_ORE),
   GLOWING_REDSTONE_ORE(Material.GLOWING_REDSTONE_ORE),
   REDSTONE_TORCH_OFF(Material.REDSTONE_TORCH_OFF),
   REDSTONE_TORCH_ON(Material.REDSTONE_TORCH_ON),
   STONE_BUTTON(Material.STONE_BUTTON),
   SNOW(Material.SNOW),
   ICE(Material.ICE),
   SNOW_BLOCK(Material.SNOW_BLOCK),
   CACTUS(Material.CACTUS),
   CLAY_BLOCK(Material.CLAY),
   SUGAR_CANE_BLOCK(Material.SUGAR_CANE_BLOCK),
   JUKEBOX(Material.JUKEBOX, category = Category.TILE_ENTITY),
   OAK_FENCE(Material.FENCE),
   PUMPKIN(Material.PUMPKIN),
   NETHERRACK(Material.NETHERRACK),
   SOUL_SAND(Material.SOUL_SAND),
   GLOWSTONE(Material.GLOWSTONE),
   NETHER_PORTAL(Material.PORTAL),
   JACK_O_LANTERN(Material.JACK_O_LANTERN),
   CAKE_BLOCK(Material.CAKE_BLOCK),
   REPEATER_BLOCK_OFF(Material.DIODE_BLOCK_OFF),
   REPEATER_BLOCK_ON(Material.DIODE_BLOCK_ON),
   WHITE_GLASS(Material.STAINED_GLASS, 0, Category.COLORED_BLOCK),
   ORANGE_GLASS(Material.STAINED_GLASS, 1, Category.COLORED_BLOCK),
   MAGENTA_GLASS(Material.STAINED_GLASS, 2, Category.COLORED_BLOCK),
   LIGHT_BLUE_GLASS(Material.STAINED_GLASS, 3, Category.COLORED_BLOCK),
   YELLOW_GLASS(Material.STAINED_GLASS, 4, Category.COLORED_BLOCK),
   LIME_GLASS(Material.STAINED_GLASS, 5, Category.COLORED_BLOCK),
   PINK_GLASS(Material.STAINED_GLASS, 6, Category.COLORED_BLOCK),
   GRAY_GLASS(Material.STAINED_GLASS, 7, Category.COLORED_BLOCK),
   LIGHT_GRAY_GLASS(Material.STAINED_GLASS, 8, Category.COLORED_BLOCK),
   CYAN_GLASS(Material.STAINED_GLASS, 9, Category.COLORED_BLOCK),
   PURPLE_GLASS(Material.STAINED_GLASS, 10, Category.COLORED_BLOCK),
   BLUE_GLASS(Material.STAINED_GLASS, 11, Category.COLORED_BLOCK),
   BROWN_GLASS(Material.STAINED_GLASS, 12, Category.COLORED_BLOCK),
   GREEN_GLASS(Material.STAINED_GLASS, 13, Category.COLORED_BLOCK),
   RED_GLASS(Material.STAINED_GLASS, 14, Category.COLORED_BLOCK),
   BLACK_GLASS(Material.STAINED_GLASS, 15, Category.COLORED_BLOCK),
   TRAPDOOR(Material.TRAP_DOOR),
   STONE_MONSTER_EGG(Material.MONSTER_EGGS),
   COBBLESTONE_MONSTER_EGG(Material.MONSTER_EGGS, 1),
   STONE_BRICKS_MONSTER_EGG(Material.MONSTER_EGGS, 2),
   MOSSY_STONE_MONSTER_EGG(Material.MONSTER_EGGS, 3),
   CRACKED_STONE_MONSTER_EGG(Material.MONSTER_EGGS, 4),
   CHISELED_STONE_MONSTER_EGG(Material.MONSTER_EGGS, 5),
   STONE_BRICKS(Material.SMOOTH_BRICK),
   MOSSY_STONE_BRICKS(Material.SMOOTH_BRICK, 1),
   CRACKED_STONE_BRICKS(Material.SMOOTH_BRICK, 2),
   CHISELED_STONE_BRICKS(Material.SMOOTH_BRICK, 3),
   BROWN_MUSHROOM_BLOCK(Material.HUGE_MUSHROOM_1),
   BROWN_MUSHROOM_FULL(Material.HUGE_MUSHROOM_1, 14),
   RED_MUSHROOM_BLOCK(Material.HUGE_MUSHROOM_2),
   RED_MUSHROOM_FULL(Material.HUGE_MUSHROOM_2, 14),
   MUSHROOM_STEM_FULL(Material.HUGE_MUSHROOM_1, 15),
   MUSHROOM_STEM_PILLAR(Material.HUGE_MUSHROOM_1, 10),
   IRON_BAR(Material.IRON_FENCE),
   GLASS_PANE(Material.THIN_GLASS),
   MELON_BLOCK(Material.MELON_BLOCK),
   PUMPKIN_CROPS(Material.PUMPKIN_STEM, category = Category.PLANTATION),
   MELON_CROPS(Material.MELON_STEM, category = Category.PLANTATION),
   VINES(Material.VINE),
   OAK_FENCE_GATE(Material.FENCE_GATE),
   BRICK_STAIRS(Material.BRICK_STAIRS),
   STONE_BRICK_STAIRS(Material.SMOOTH_STAIRS),
   MYCELIUM(Material.MYCEL),
   LILYPAD(Material.WATER_LILY),
   NETHER_BRICK(Material.NETHER_BRICK),
   NETHER_BRICK_FENCE(Material.NETHER_FENCE),
   NETHER_BRICK_STAIRS(Material.NETHER_BRICK_STAIRS),
   NETHER_WART_CROPS(Material.NETHER_WARTS),
   ENCHANTMENT_TABLE(Material.ENCHANTMENT_TABLE, category = Category.TILE_ENTITY),
   BREWING_STAND(Material.BREWING_STAND, category = Category.TILE_ENTITY),
   CAULDRON(Material.CAULDRON),
   ENDER_PORTAL(Material.ENDER_PORTAL),
   ENDER_PORTAL_FRAME(Material.ENDER_PORTAL_FRAME),
   END_STONE(Material.ENDER_STONE),
   DRAGON_EGG(Material.DRAGON_EGG),
   REDSTONE_LAMP_OFF(Material.REDSTONE_LAMP_OFF),
   REDSTONE_LAMP_ON(Material.REDSTONE_LAMP_ON),
   DOUBLE_OAK_SLAB(Material.WOOD_DOUBLE_STEP),
   DOUBLE_SPRUCE_SLAB(Material.WOOD_DOUBLE_STEP, 1),
   DOUBLE_BIRCH_SLAB(Material.WOOD_DOUBLE_STEP, 2),
   DOUBLE_JUNGLE_SLAB(Material.WOOD_DOUBLE_STEP, 3),
   DOUBLE_ACACIA_SLAB(Material.WOOD_DOUBLE_STEP, 4),
   DOUBLE_DARK_OAK_SLAB(Material.WOOD_DOUBLE_STEP, 5),
   OAK_SLAB(Material.WOOD_STEP),
   SPRUCE_SLAB(Material.WOOD_STEP, 1),
   BIRCH_SLAB(Material.WOOD_STEP, 2),
   JUNGLE_SLAB(Material.WOOD_STEP, 3),
   ACACIA_SLAB(Material.WOOD_STEP, 4),
   DARK_OAK_SLAB(Material.WOOD_STEP, 5),
   COCOA_BLOCK(Material.COCOA),
   SANDSTONE_STAIRS(Material.SANDSTONE_STAIRS),
   EMERALD_ORE(Material.EMERALD_ORE),
   ENDER_CHEST(Material.ENDER_CHEST, category = Category.TILE_ENTITY),
   TRIPWIRE_HOOK(Material.TRIPWIRE_HOOK),
   TRIPWIRE(Material.TRIPWIRE),
   EMERALD_BLOCK(Material.EMERALD_BLOCK),
   SPRUCE_STAIRS(Material.SPRUCE_WOOD_STAIRS),
   BIRCH_STAIRS(Material.BIRCH_WOOD_STAIRS),
   JUNGLE_STAIRS(Material.JUNGLE_WOOD_STAIRS),
   COMMAND_BLOCK(Material.COMMAND),
   BEACON(Material.BEACON, category = Category.TILE_ENTITY),
   COBBLESTONE_WALL(Material.COBBLE_WALL),
   FLOWER_POT(Material.FLOWER_POT),
   CARROT_CROPS(Material.CARROT, category = Category.PLANTATION),
   POTATO_CROPS(Material.POTATO, category = Category.PLANTATION),
   WOODEN_BUTTON(Material.WOOD_BUTTON),
   SKULL(Material.SKULL),
   ANVIL(Material.ANVIL),
   TRAPPED_CHEST(Material.TRAPPED_CHEST, category = Category.TILE_ENTITY),
   GOLD_PLATE(Material.GOLD_PLATE),
   IRON_PLATE(Material.IRON_PLATE),
   COMPARATOR_BLOCK_OFF(Material.REDSTONE_COMPARATOR_OFF),
   COMPARATOR_BLOCK_ON(Material.REDSTONE_COMPARATOR_ON),
   DAYLIGHT_SENSOR(Material.DAYLIGHT_DETECTOR, category = Category.TILE_ENTITY),
   REDSTONE_BLOCK(Material.REDSTONE_BLOCK),
   QUARTZ_ORE(Material.QUARTZ_ORE),
   HOPPER(Material.HOPPER, category = Category.TILE_ENTITY),
   QUARTZ_BLOCK(Material.QUARTZ_BLOCK),
   CHISELED_QUARTZ_BLOCK(Material.QUARTZ_BLOCK, 1),
   PILLAR_QUARTZ_BLOCK(Material.QUARTZ_BLOCK, 2),
   QUARTZ_STAIRS(Material.QUARTZ_STAIRS),
   ACTIVATOR_RAIL(Material.ACTIVATOR_RAIL),
   DROPPER(Material.DROPPER, category = Category.TILE_ENTITY),
   WHITE_CLAY(Material.STAINED_CLAY, 0, Category.COLORED_BLOCK),
   ORANGE_CLAY(Material.STAINED_CLAY, 1, Category.COLORED_BLOCK),
   MAGENTA_CLAY(Material.STAINED_CLAY, 2, Category.COLORED_BLOCK),
   LIGHT_BLUE_CLAY(Material.STAINED_CLAY, 3, Category.COLORED_BLOCK),
   YELLOW_CLAY(Material.STAINED_CLAY, 4, Category.COLORED_BLOCK),
   LIME_CLAY(Material.STAINED_CLAY, 5, Category.COLORED_BLOCK),
   PINK_CLAY(Material.STAINED_CLAY, 6, Category.COLORED_BLOCK),
   GRAY_CLAY(Material.STAINED_CLAY, 7, Category.COLORED_BLOCK),
   LIGHT_GRAY_CLAY(Material.STAINED_CLAY, 8, Category.COLORED_BLOCK),
   CYAN_CLAY(Material.STAINED_CLAY, 9, Category.COLORED_BLOCK),
   PURPLE_CLAY(Material.STAINED_CLAY, 10, Category.COLORED_BLOCK),
   BLUE_CLAY(Material.STAINED_CLAY, 11, Category.COLORED_BLOCK),
   BROWN_CLAY(Material.STAINED_CLAY, 12, Category.COLORED_BLOCK),
   GREEN_CLAY(Material.STAINED_CLAY, 13, Category.COLORED_BLOCK),
   RED_CLAY(Material.STAINED_CLAY, 14, Category.COLORED_BLOCK),
   BLACK_CLAY(Material.STAINED_CLAY, 15, Category.COLORED_BLOCK),
   WHITE_GLASS_PANE(Material.STAINED_GLASS_PANE, 0, Category.COLORED_BLOCK),
   ORANGE_GLASS_PANE(Material.STAINED_GLASS_PANE, 1, Category.COLORED_BLOCK),
   MAGENTA_GLASS_PANE(Material.STAINED_GLASS_PANE, 2, Category.COLORED_BLOCK),
   LIGHT_BLUE_GLASS_PANE(Material.STAINED_GLASS_PANE, 3, Category.COLORED_BLOCK),
   YELLOW_GLASS_PANE(Material.STAINED_GLASS_PANE, 4, Category.COLORED_BLOCK),
   LIME_GLASS_PANE(Material.STAINED_GLASS_PANE, 5, Category.COLORED_BLOCK),
   PINK_GLASS_PANE(Material.STAINED_GLASS_PANE, 6, Category.COLORED_BLOCK),
   GRAY_GLASS_PANE(Material.STAINED_GLASS_PANE, 7, Category.COLORED_BLOCK),
   LIGHT_GRAY_GLASS_PANE(Material.STAINED_GLASS_PANE, 8, Category.COLORED_BLOCK),
   CYAN_GLASS_PANE(Material.STAINED_GLASS_PANE, 9, Category.COLORED_BLOCK),
   PURPLE_GLASS_PANE(Material.STAINED_GLASS_PANE, 10, Category.COLORED_BLOCK),
   BLUE_GLASS_PANE(Material.STAINED_GLASS_PANE, 11, Category.COLORED_BLOCK),
   BROWN_GLASS_PANE(Material.STAINED_GLASS_PANE, 12, Category.COLORED_BLOCK),
   GREEN_GLASS_PANE(Material.STAINED_GLASS_PANE, 13, Category.COLORED_BLOCK),
   RED_GLASS_PANE(Material.STAINED_GLASS_PANE, 14, Category.COLORED_BLOCK),
   BLACK_GLASS_PANE(Material.STAINED_GLASS_PANE, 15, Category.COLORED_BLOCK),
   ACACIA_LEAVES(Material.LEAVES_2),
   DARK_OAK_LEAVES(Material.LEAVES_2, 1),
   ACACIA_WOOD(Material.LOG_2),
   DARK_OAK_WOOD(Material.LOG_2, 1),
   ACACIA_BARK(Material.LOG_2, 12),
   DARK_OAK_BARK(Material.LOG_2, 13),
   ACACIA_STAIRS(Material.ACACIA_STAIRS),
   DARK_OAK_STAIRS(Material.DARK_OAK_STAIRS),
   SLIME_BLOCK(Material.SLIME_BLOCK),
   BARRIER(Material.BARRIER),
   IRON_TRAPDOOR(Material.IRON_TRAPDOOR),
   PRISMARINE(Material.PRISMARINE),
   PRISMARINE_BRICKS(Material.PRISMARINE, 1),
   DARK_PRISMARINE(Material.PRISMARINE, 2),
   SEA_LANTERN(Material.SEA_LANTERN),
   HAY_BLOCK(Material.HAY_BLOCK),
   WHITE_CARPET(Material.CARPET, 0, Category.COLORED_BLOCK),
   ORANGE_CARPET(Material.CARPET, 1, Category.COLORED_BLOCK),
   MAGENTA_CARPET(Material.CARPET, 2, Category.COLORED_BLOCK),
   LIGHT_BLUE_CARPET(Material.CARPET, 3, Category.COLORED_BLOCK),
   YELLOW_CARPET(Material.CARPET, 4, Category.COLORED_BLOCK),
   LIME_CARPET(Material.CARPET, 5, Category.COLORED_BLOCK),
   PINK_CARPET(Material.CARPET, 6, Category.COLORED_BLOCK),
   GRAY_CARPET(Material.CARPET, 7, Category.COLORED_BLOCK),
   LIGHT_GRAY_CARPET(Material.CARPET, 8, Category.COLORED_BLOCK),
   CYAN_CARPET(Material.CARPET, 9, Category.COLORED_BLOCK),
   PURPLE_CARPET(Material.CARPET, 10, Category.COLORED_BLOCK),
   BLUE_CARPET(Material.CARPET, 11, Category.COLORED_BLOCK),
   BROWN_CARPET(Material.CARPET, 12, Category.COLORED_BLOCK),
   GREEN_CARPET(Material.CARPET, 13, Category.COLORED_BLOCK),
   RED_CARPET(Material.CARPET, 14, Category.COLORED_BLOCK),
   BLACK_CARPET(Material.CARPET, 15, Category.COLORED_BLOCK),
   HARDENED_CLAY(Material.HARD_CLAY),
   COAL_BLOCK(Material.COAL_BLOCK),
   SUNFLOWER(Material.DOUBLE_PLANT),
   LILAC(Material.DOUBLE_PLANT, 1),
   LARGE_GRASS(Material.DOUBLE_PLANT, 2),
   LARGE_FERN(Material.DOUBLE_PLANT, 3),
   ROSE_BUSH(Material.DOUBLE_PLANT, 4),
   PEONY(Material.DOUBLE_PLANT, 5),
   STANDING_BANNER(Material.STANDING_BANNER),
   WALL_BANNER(Material.WALL_BANNER),
   NIGHTLIGHT_SENSOR(Material.DAYLIGHT_DETECTOR_INVERTED),
   RED_SANDSTONE(Material.RED_SANDSTONE),
   CHISELED_RED_SANDSTONE(Material.RED_SANDSTONE, 1),
   SMOOTH_RED_SANDSTONE(Material.RED_SANDSTONE, 2),
   RED_SANDSTONE_STAIRS(Material.RED_SANDSTONE_STAIRS),
   DOUBLE_RED_SANDSTONE_SLAB(Material.DOUBLE_STONE_SLAB2),
   RED_SANDSTONE_SLAB(Material.STONE_SLAB2),
   SPRUCE_FENCE_GATE(Material.SPRUCE_FENCE_GATE),
   BIRCH_FENCE_GATE(Material.BIRCH_FENCE_GATE),
   JUNGLE_FENCE_GATE(Material.JUNGLE_FENCE_GATE),
   DARK_OAK_FENCE_GATE(Material.DARK_OAK_FENCE_GATE),
   ACACIA_FENCE_GATE(Material.ACACIA_FENCE_GATE),
   SPRUCE_FENCE(Material.SPRUCE_FENCE),
   BIRCH_FENCE(Material.BIRCH_FENCE),
   JUNGLE_FENCE(Material.JUNGLE_FENCE),
   DARK_OAK_FENCE(Material.DARK_OAK_FENCE),
   ACACIA_FENCE(Material.ACACIA_FENCE),
   SPRUCE_DOOR(Material.SPRUCE_DOOR),
   BIRCH_DOOR(Material.BIRCH_DOOR),
   JUNGLE_DOOR(Material.JUNGLE_DOOR),
   ACACIA_DOOR(Material.ACACIA_DOOR),
   DARK_OAK_DOOR(Material.DARK_OAK_DOOR),
   
   // Item section
   
   IRON_SHOVEL(Material.IRON_SPADE, category = Category.EQUIPMENT),
   IRON_PICKAXE(Material.IRON_PICKAXE, category = Category.EQUIPMENT),
   IRON_AXE(Material.IRON_AXE, category = Category.EQUIPMENT),
   FLINT_AND_STEEL(Material.FLINT_AND_STEEL, category = Category.ITEM),
   APPLE(Material.APPLE, category = Category.FOOD),
   BOW(Material.BOW, category = Category.EQUIPMENT),
   ARROW(Material.ARROW, category = Category.EQUIPMENT),
   COAL(Material.COAL, category = Category.ORE),
   CHARCOAL(Material.COAL, 1, category = Category.ORE),
   DIAMOND(Material.DIAMOND, category = Category.ORE),
   IRON_INGOT(Material.IRON_INGOT, category = Category.ORE),
   GOLD_INGOT(Material.GOLD_INGOT, category = Category.ORE),
   IRON_SWORD(Material.IRON_SWORD, category = Category.EQUIPMENT),
   WOOD_SWORD(Material.WOOD_SWORD, category = Category.EQUIPMENT),
   WOOD_SHOVEL(Material.WOOD_SPADE, category = Category.EQUIPMENT),
   WOOD_PICKAXE(Material.WOOD_PICKAXE, category = Category.EQUIPMENT),
   WOOD_AXE(Material.WOOD_AXE, category = Category.EQUIPMENT),
   STONE_SWORD(Material.STONE_SWORD, category = Category.EQUIPMENT),
   STONE_SHOVEL(Material.STONE_SPADE, category = Category.EQUIPMENT),
   STONE_PICKAXE(Material.STONE_PICKAXE, category = Category.EQUIPMENT),
   STONE_AXE(Material.STONE_AXE, category = Category.EQUIPMENT),
   DIAMOND_SWORD(Material.DIAMOND_SWORD, category = Category.EQUIPMENT),
   DIAMOND_SHOVEL(Material.DIAMOND_SPADE, category = Category.EQUIPMENT),
   DIAMOND_PICKAXE(Material.DIAMOND_PICKAXE, category = Category.EQUIPMENT),
   DIAMOND_AXE(Material.DIAMOND_AXE, category = Category.EQUIPMENT),
   STICK(Material.STICK, category = Category.ITEM),
   BOWL(Material.BOWL, category = Category.ITEM),
   MUSHROOM_SOUP(Material.MUSHROOM_SOUP, category = Category.FOOD),
   GOLD_SWORD(Material.GOLD_SWORD, category = Category.EQUIPMENT),
   GOLD_SHOVEL(Material.GOLD_SPADE, category = Category.EQUIPMENT),
   GOLD_PICKAXE(Material.GOLD_PICKAXE, category = Category.EQUIPMENT),
   GOLD_AXE(Material.GOLD_AXE, category = Category.EQUIPMENT),
   STRING(Material.STRING, category = Category.ITEM),
   FEATHER(Material.FEATHER, category = Category.ITEM),
   GUNPOWDER(Material.SULPHUR, category = Category.ITEM),
   WOOD_HOE(Material.WOOD_HOE, category = Category.EQUIPMENT),
   STONE_HOE(Material.STONE_HOE, category = Category.EQUIPMENT),
   IRON_HOE(Material.IRON_HOE, category = Category.EQUIPMENT),
   DIAMOND_HOE(Material.DIAMOND_HOE, category = Category.EQUIPMENT),
   GOLD_HOE(Material.GOLD_HOE, category = Category.EQUIPMENT),
   WHEAT_SEEDS(Material.SEEDS, category = Category.PLANTATION),
   WHEAT(Material.WHEAT, category = Category.PLANTATION),
   BREAD(Material.BREAD, category = Category.FOOD),
   LEATHER_HELMET(Material.LEATHER_HELMET, category = Category.ARMOR),
   LEATHER_CHESTPLATE(Material.LEATHER_CHESTPLATE, category = Category.ARMOR),
   LEATHER_LEGGINGS(Material.LEATHER_LEGGINGS, category = Category.ARMOR),
   LEATHER_BOOTS(Material.LEATHER_BOOTS, category = Category.ARMOR),
   CHAINMAIL_HELMET(Material.CHAINMAIL_HELMET, category = Category.ARMOR),
   CHAINMAIL_CHESTPLATE(Material.CHAINMAIL_CHESTPLATE, category = Category.ARMOR),
   CHAINMAIL_LEGGINGS(Material.CHAINMAIL_LEGGINGS, category = Category.ARMOR),
   CHAINMAIL_BOOTS(Material.CHAINMAIL_BOOTS, category = Category.ARMOR),
   IRON_HELMET(Material.IRON_HELMET, category = Category.ARMOR),
   IRON_CHESTPLATE(Material.IRON_CHESTPLATE, category = Category.ARMOR),
   IRON_LEGGINGS(Material.IRON_LEGGINGS, category = Category.ARMOR),
   IRON_BOOTS(Material.IRON_BOOTS, category = Category.ARMOR),
   DIAMOND_HELMET(Material.DIAMOND_HELMET, category = Category.ARMOR),
   DIAMOND_CHESTPLATE(Material.DIAMOND_CHESTPLATE, category = Category.ARMOR),
   DIAMOND_LEGGINGS(Material.DIAMOND_LEGGINGS, category = Category.ARMOR),
   DIAMOND_BOOTS(Material.DIAMOND_BOOTS, category = Category.ARMOR),
   GOLD_HELMET(Material.GOLD_HELMET, category = Category.ARMOR),
   GOLD_CHESTPLATE(Material.GOLD_CHESTPLATE, category = Category.ARMOR),
   GOLD_LEGGINGS(Material.GOLD_LEGGINGS, category = Category.ARMOR),
   GOLD_BOOTS(Material.GOLD_BOOTS, category = Category.ARMOR),
   FLINT(Material.FLINT, category = Category.ITEM),
   RAW_PORK(Material.PORK, category = Category.FOOD),
   COOKED_PORK(Material.GRILLED_PORK, category = Category.FOOD),
   PAINTING(Material.PAINTING),
   GOLDEN_APPLE(Material.GOLDEN_APPLE, category = Category.FOOD),
   ENCHANTED_GOLDEN_APPLE(Material.GOLDEN_APPLE, 1, category = Category.FOOD),
   SIGN(Material.SIGN, category = Category.ITEM),
   WOOD_DOOR(Material.WOOD_DOOR, category = Category.ITEM),
   BUCKET(Material.BUCKET, category = Category.ITEM),
   WATER_BUCKET(Material.WATER_BUCKET, category = Category.ITEM),
   LAVA_BUCKET(Material.LAVA_BUCKET, category = Category.ITEM),
   MINECART(Material.MINECART, category = Category.TILE_ENTITY),
   SADDLE(Material.SADDLE, category = Category.ITEM),
   IRON_DOOR(Material.IRON_DOOR, category = Category.ITEM),
   REDSTONE(Material.REDSTONE, category = Category.ORE),
   SNOW_BALL(Material.SNOW_BALL, category = Category.ITEM),
   BOAT(Material.BOAT, category = Category.ITEM),
   LEATHER(Material.LEATHER, category = Category.ITEM),
   MILK_BUCKET(Material.MILK_BUCKET, category = Category.FOOD),
   CLAY_BRICK(Material.CLAY_BRICK, category = Category.ITEM),
   CLAY_BALL(Material.CLAY_BALL, category = Category.ITEM),
   SUGAR_CANE(Material.SUGAR_CANE, category = Category.PLANTATION),
   PAPER(Material.PAPER, category = Category.ITEM),
   BOOK(Material.BOOK, category = Category.ITEM),
   SLIME_BALL(Material.SLIME_BALL, category = Category.ITEM),
   CHEST_MINECART(Material.STORAGE_MINECART, category = Category.TILE_ENTITY),
   FURNACE_MINECART(Material.POWERED_MINECART, category = Category.TILE_ENTITY),
   EGG(Material.EGG, category = Category.ITEM),
   COMPASS(Material.COMPASS, category = Category.ITEM),
   FISHING_ROD(Material.FISHING_ROD, category = Category.ITEM),
   CLOCK(Material.WATCH, category = Category.ITEM),
   GLOWSTONE_DUST(Material.GLOWSTONE_DUST, category = Category.ITEM),
   RAW_FISH(Material.RAW_FISH, category = Category.FISH),
   RAW_SALMON(Material.RAW_FISH, 1, category = Category.FISH),
   CLOWNFISH(Material.RAW_FISH, 2, category = Category.FISH),
   PUFFERFISH(Material.RAW_FISH, 3, category = Category.FISH),
   COOKED_FISH(Material.COOKED_FISH, category = Category.FISH),
   COOKED_SALMON(Material.COOKED_FISH, 1, category = Category.FISH),
   INK_SACK(Material.INK_SACK, category = Category.COLORED_ITEM),
   RED_DYE(Material.INK_SACK, 1, category = Category.COLORED_ITEM),
   GREEN_DYE(Material.INK_SACK, 2, category = Category.COLORED_ITEM),
   COCOA_BEANS(Material.INK_SACK, 3, category = Category.PLANTATION), // exception
   LAPIS_LAZULLI(Material.INK_SACK, 4, category = Category.COLORED_ITEM),
   PURPLE_DYE(Material.INK_SACK, 5, category = Category.COLORED_ITEM),
   CYAN_DYE(Material.INK_SACK, 6, category = Category.COLORED_ITEM),
   LIGHT_GRAY_DYE(Material.INK_SACK, 7, category = Category.COLORED_ITEM),
   GRAY_DYE(Material.INK_SACK, 8, category = Category.COLORED_ITEM),
   PINK_DYE(Material.INK_SACK, 9, category = Category.COLORED_ITEM),
   LIME_DYE(Material.INK_SACK, 10, category = Category.COLORED_ITEM),
   YELLOW_DYE(Material.INK_SACK, 11, category = Category.COLORED_ITEM),
   LIGHT_BLUE_DYE(Material.INK_SACK, 12, category = Category.COLORED_ITEM),
   MAGENTA_DYE(Material.INK_SACK, 13, category = Category.COLORED_ITEM),
   ORANGE_DYE(Material.INK_SACK, 14, category = Category.COLORED_ITEM),
   BONE_MEAL(Material.INK_SACK, 15, category = Category.COLORED_ITEM),
   BONE(Material.BONE, category = Category.ITEM),
   SUGAR(Material.SUGAR, category = Category.ITEM),
   CAKE(Material.CAKE, category = Category.FOOD),
   BED(Material.BED, category = Category.ITEM),
   REPEATER(Material.DIODE, category = Category.ITEM),
   COOKIE(Material.COOKIE, category = Category.FOOD),
   MAP(Material.MAP, category = Category.ITEM),
   SHEARS(Material.SHEARS, category = Category.ITEM),
   MELON(Material.MELON, category = Category.FOOD),
   PUMPKIN_SEEDS(Material.PUMPKIN_SEEDS, category = Category.PLANTATION),
   MELON_SEEDS(Material.MELON_SEEDS, category = Category.PLANTATION),
   RAW_BEEF(Material.RAW_BEEF, category = Category.FOOD),
   COOKED_BEEF(Material.COOKED_BEEF, category = Category.FOOD),
   RAW_CHICKEN(Material.RAW_CHICKEN, category = Category.FOOD),
   COOKED_CHICKEN(Material.COOKED_CHICKEN, category = Category.FOOD),
   ROTTEN_FLESH(Material.ROTTEN_FLESH, category = Category.FOOD),
   ENDER_PEARL(Material.ENDER_PEARL, category = Category.ITEM),
   BLAZE_ROD(Material.BLAZE_ROD, category = Category.ITEM),
   GHAST_TEAR(Material.GHAST_TEAR, category = Category.ITEM),
   GOLD_NUGGET(Material.GOLD_NUGGET, category = Category.ORE),
   NETHER_WART(Material.NETHER_STALK, category = Category.PLANTATION),
   POTION(Material.POTION, category = Category.ITEM),
   GLASS_BOTTLE(Material.GLASS_BOTTLE, category = Category.ITEM),
   SPIDER_EYE(Material.SPIDER_EYE, category = Category.ITEM),
   FERMENTED_SPIDER_EYE(Material.FERMENTED_SPIDER_EYE, category = Category.ITEM),
   BLAZE_POWDER(Material.BLAZE_POWDER, category = Category.ITEM),
   MAGMA_CREAM(Material.MAGMA_CREAM, category = Category.ITEM),
   BREWING_STAND_ITEM(Material.BREWING_STAND_ITEM, category = Category.ITEM),
   CAULDRON_ITEM(Material.CAULDRON_ITEM, category = Category.ITEM),
   ENDER_EYE(Material.EYE_OF_ENDER, category = Category.ITEM),
   SHINY_MELON(Material.SPECKLED_MELON, category = Category.ITEM),
   
   // Monster eggs section
   
   MONSTER_EGG(Material.MONSTER_EGG, category = Category.ITEM),
   CREEPER_EGG(Material.MONSTER_EGG, 50, category = Category.ITEM),
   SKELETON_EGG(Material.MONSTER_EGG, 51, category = Category.ITEM),
   SPIDER_EGG(Material.MONSTER_EGG, 52, category = Category.ITEM),
   ZOMBIE_EGG(Material.MONSTER_EGG, 54, category = Category.ITEM),
   SLIME_EGG(Material.MONSTER_EGG, 55, category = Category.ITEM),
   GHAST_EGG(Material.MONSTER_EGG, 56, category = Category.ITEM),
   PIGMAN_EGG(Material.MONSTER_EGG, 57, category = Category.ITEM),
   ENDERMAN_EGG(Material.MONSTER_EGG, 58, category = Category.ITEM),
   CAVE_SPIDER_EGG(Material.MONSTER_EGG, 59, category = Category.ITEM),
   SILVERFISH_EGG(Material.MONSTER_EGG, 60, category = Category.ITEM),
   BLAZE_EGG(Material.MONSTER_EGG, 61, category = Category.ITEM),
   MAGMA_CUBE_EGG(Material.MONSTER_EGG, 62, category = Category.ITEM),
   BAT_EGG(Material.MONSTER_EGG, 65, category = Category.ITEM),
   WITCH_EGG(Material.MONSTER_EGG, 66, category = Category.ITEM),
   ENDERMITE_EGG(Material.MONSTER_EGG, 67, category = Category.ITEM),
   GUARDIAN_EGG(Material.MONSTER_EGG, 68, category = Category.ITEM),
   PIG_EGG(Material.MONSTER_EGG, 90, category = Category.ITEM),
   SHEEP_EGG(Material.MONSTER_EGG, 91, category = Category.ITEM),
   COW_EGG(Material.MONSTER_EGG, 92, category = Category.ITEM),
   CHICKEN_EGG(Material.MONSTER_EGG, 93, category = Category.ITEM),
   SQUID_EGG(Material.MONSTER_EGG, 94, category = Category.ITEM),
   WOLF_EGG(Material.MONSTER_EGG, 95, category = Category.ITEM),
   MUSHROOM_COW_EGG(Material.MONSTER_EGG, 96, category = Category.ITEM),
   OCELOT_EGG(Material.MONSTER_EGG, 98, category = Category.ITEM),
   HORSE_EGG(Material.MONSTER_EGG, 100, category = Category.ITEM),
   RABBIT_EGG(Material.MONSTER_EGG, 101, category = Category.ITEM),
   VILLAGER_EGG(Material.MONSTER_EGG, 120, category = Category.ITEM),
   
   EXP_BOTTLE(Material.EXP_BOTTLE, category = Category.ITEM),
   FIREBALL(Material.FIREBALL, category = Category.ITEM),
   BOOK_AND_QUILL(Material.BOOK_AND_QUILL, category = Category.ITEM),
   WRITTEN_BOOK(Material.WRITTEN_BOOK, category = Category.ITEM),
   EMERALD(Material.EMERALD, category = Category.ORE),
   ITEM_FRAME(Material.ITEM_FRAME, category = Category.TILE_ENTITY),
   FLOWER_POT_ITEM(Material.FLOWER_POT_ITEM, category = Category.TILE_ENTITY),
   CARROT(Material.CARROT_ITEM, category = Category.FOOD),
   POTATO(Material.POTATO_ITEM, category = Category.FOOD),
   BAKED_POTATO(Material.BAKED_POTATO, category = Category.FOOD),
   POISONOUS_POTATO(Material.POISONOUS_POTATO, category = Category.FOOD),
   EMPTY_MAP(Material.EMPTY_MAP, category = Category.ITEM),
   GOLDEN_CARROT(Material.GOLDEN_CARROT, category = Category.FOOD),
   SKELETON_SKULL(Material.SKULL_ITEM, category = Category.TILE_ENTITY),
   WITHER_SKELETON_SKULL(Material.SKULL_ITEM, 1, category = Category.TILE_ENTITY),
   ZOMBIE_SKULL(Material.SKULL_ITEM, 2, category = Category.TILE_ENTITY),
   PLAYER_SKULL(Material.SKULL_ITEM, 3, category = Category.TILE_ENTITY),
   CREEPER_SKULL(Material.SKULL_ITEM, 4, category = Category.TILE_ENTITY),
   CARROT_ON_STICK(Material.CARROT_STICK, category = Category.ITEM),
   NETHER_STAR(Material.NETHER_STAR, category = Category.ITEM),
   PUMPKIN_PIE(Material.PUMPKIN_PIE, category = Category.FOOD),
   FIREWORK(Material.FIREWORK, category = Category.ITEM),
   FIREWORK_CHARGE(Material.FIREWORK_CHARGE, category = Category.ITEM),
   ENCHANTED_BOOK(Material.ENCHANTED_BOOK, category = Category.ITEM),
   COMPARATOR(Material.REDSTONE_COMPARATOR, category = Category.TILE_ENTITY),
   NETHER_BRICK_ITEM(Material.NETHER_BRICK_ITEM, category = Category.ITEM),
   QUARTZ(Material.QUARTZ, category = Category.ORE),
   TNT_MINECART(Material.EXPLOSIVE_MINECART, category = Category.TILE_ENTITY),
   HOPPER_MINECART(Material.HOPPER_MINECART, category = Category.TILE_ENTITY),
   PRISMARINE_SHARD(Material.PRISMARINE_SHARD, category = Category.ITEM),
   PRISMARINE_CRYSTALS(Material.PRISMARINE_CRYSTALS, category = Category.ITEM),
   RAW_RABBIT(Material.RABBIT, category = Category.FOOD),
   COOKED_RABBIT(Material.COOKED_RABBIT, category = Category.FOOD),
   RABBIT_STEW(Material.RABBIT_STEW, category = Category.FOOD),
   RABBIT_FOOT(Material.RABBIT_FOOT, category = Category.ITEM),
   RABBIT_HIDE(Material.RABBIT_HIDE, category = Category.ITEM),
   ARMOR_STAND(Material.ARMOR_STAND, category = Category.TILE_ENTITY),
   IRON_HORSE_ARMOR(Material.IRON_BARDING, category = Category.ARMOR),
   GOLD_HORSE_ARMOR(Material.GOLD_BARDING, category = Category.ARMOR),
   DIAMOND_HORSE_ARMOR(Material.DIAMOND_BARDING, category = Category.ARMOR),
   LEASH(Material.LEASH, category = Category.ITEM),
   NAMETAG(Material.NAME_TAG, category = Category.ITEM),
   COMMAND_BLOCK_MINECART(Material.COMMAND_MINECART, category = Category.TILE_ENTITY),
   RAW_MUTTON(Material.MUTTON, category = Category.FOOD),
   COOKED_MUTTON(Material.COOKED_MUTTON, category = Category.FOOD),
   WHITE_BANNER(Material.BANNER, 15, category = Category.COLORED_BLOCK),
   ORANGE_BANNER(Material.BANNER, 14, category = Category.COLORED_BLOCK),
   MAGENTA_BANNER(Material.BANNER, 13, category = Category.COLORED_BLOCK),
   LIGHT_BLUE_BANNER(Material.BANNER, 12, category = Category.COLORED_BLOCK),
   YELLOW_BANNER(Material.BANNER, 11, category = Category.COLORED_BLOCK),
   LIME_BANNER(Material.BANNER, 10, category = Category.COLORED_BLOCK),
   PINK_BANNER(Material.BANNER, 9, category = Category.COLORED_BLOCK),
   GRAY_BANNER(Material.BANNER, 8, category = Category.COLORED_BLOCK),
   LIGHT_GRAY_BANNER(Material.BANNER, 7, category = Category.COLORED_BLOCK),
   CYAN_BANNER(Material.BANNER, 6, category = Category.COLORED_BLOCK),
   PURPLE_BANNER(Material.BANNER, 5, category = Category.COLORED_BLOCK),
   BLUE_BANNER(Material.BANNER, 4, category = Category.COLORED_BLOCK),
   BROWN_BANNER(Material.BANNER, 3, category = Category.COLORED_BLOCK),
   GREEN_BANNER(Material.BANNER, 2, category = Category.COLORED_BLOCK),
   RED_BANNER(Material.BANNER, 1, category = Category.COLORED_BLOCK),
   BLACK_BANNER(Material.BANNER, 0, category = Category.COLORED_BLOCK),
   SPRUCE_DOOR_ITEM(Material.SPRUCE_DOOR_ITEM, category = Category.ITEM),
   BIRCH_DOOR_ITEM(Material.BIRCH_DOOR_ITEM, category = Category.ITEM),
   JUNGLE_DOOR_ITEM(Material.JUNGLE_DOOR_ITEM, category = Category.ITEM),
   ACACIA_DOOR_ITEM(Material.ACACIA_DOOR_ITEM, category = Category.ITEM),
   DARK_OAK_DOOR_ITEM(Material.DARK_OAK_DOOR_ITEM, category = Category.ITEM),
   RECORD_13(Material.GOLD_RECORD, category = Category.ITEM),
   CAT_RECORD(Material.GREEN_RECORD, category = Category.ITEM),
   BLOCKS_RECORD(Material.RECORD_3, category = Category.ITEM),
   CHIRP_RECORD(Material.RECORD_4, category = Category.ITEM),
   FAR_RECORD(Material.RECORD_5, category = Category.ITEM),
   MALL_RECORD(Material.RECORD_7, category = Category.ITEM),
   MELLOHI_RECORD(Material.RECORD_8, category = Category.ITEM),
   STAL_RECORD(Material.RECORD_9, category = Category.ITEM),
   STRAD_RECORD(Material.RECORD_10, category = Category.ITEM),
   WARD_RECORD(Material.RECORD_11, category = Category.ITEM),
   RECORD_11(Material.RECORD_12, category = Category.ITEM);
   
   /**
    * Returns the id value of this material object.
    */
   val id = type.id
   
   /**
    * Returns the material data of this material object.
    */
   val data = MaterialData(type, subdata.toByte())
   
   /**
    * Returns the max stack size of this material object.
    */
   val maxStack get() = type.maxStackSize
   
   /**
    * Returns the max durability of this material object.
    */
   val durability = type.maxDurability.toInt()
   
   /**
    * Returns if this material object contains subdata.
    */
   val hasSubdata get() = subdata != 0
   
   /**
    * Verify if this material object is a plantation
    */
   val isPlantation get() = name.endsWith("CROPS")
   
   /**
    * Verify if this material object is a monster egg
    */
   val isMonsterEgg get() = id == 383
   
   /**
    * Verify if this material object is solid glass.
    */
   val isSolidGlass get() = id == 95
   
   /**
    * Verify if this material object is pane glass.
    */
   val isPaneGlass get() = id == 160
   
   /**
    * Verify if this material object is any type of glass, including pane glass.
    */
   val isGlass get() = id == 160 || id == 95
   
   /**
    * Verify if this material object has a variety of colors.
    */
   val hasColorVariety get() = when (id) {
      35, 95, 159, 160, 171, 425 -> true
      else -> false
   }
   
   /**
    * Verify if this material object is a block
    */
   val isBlock get() = type.isBlock
   
   /**
    * Verify if this material object is an item
    */
   val isItem get() = !isBlock
   
   /**
    * Verify if this material object is burnable
    */
   val isBurnable get() = type.isBurnable
   
   /**
    * Verify if this material object is edible
    */
   val isEdible get() = type.isEdible
   
   /**
    * Verify if this material object is flammable
    */
   val isFlammable get() = type.isFlammable
   
   /**
    * Verify if this material object is a record
    */
   val isRecord get() = type.isRecord
   
   /**
    * Verify if this material object is solid
    */
   val isSolid get() = type.isSolid
   
   /**
    * Verify if this material object is occluding
    */
   val isOccluding get() = type.isOccluding
   
   /**
    * Verify if this material object is transparent
    */
   val isTransparent get() = type.isTransparent
   
   /**
    * Verify if this material object has gravity
    */
   val hasGravity get() = type.hasGravity()
   
   /**
    * Returns if this material is showable in an inventory.
    */
   val isShowable get() = when (id) {
      8, 9, 10, 11, 26, 34, 43, 51, 55, 59, 60, 63, 64, 68, 71, 83, 90, 115, 127, 132, 141, 142 -> false
      else -> true
   }
   
   /**
    * Converts this material object to an item stack with the specified [amount].
    */
   fun toItem(amount: Int = 1): ItemStack = data.toItemStack(amount)
   
   /**
    * Converts this material object to an item stack with the specified [amount].
    */
   fun toItem(name: String, amount: Int = 1) = item(data, amount) {
      displayName = name
   }
   
   /**
    * Converts this material object to an item stack with the specified [amount].
    */
   fun toItem(name: String, lore: List<String>, amount: Int = 1) = item(data, amount) {
      displayName = name
      this.lore = lore
   }
   
   override fun toString(): String = "($name) $id:$subdata"
   
   companion object : Set<Materials> by EnumSet.allOf(Materials::class.java) {
      internal val BY_DATA: BiMap<Materials, MaterialPair> = EnumHashBiMap.create(Materials::class.java)
      internal val BY_ID: BiMap<Materials, Int> = EnumHashBiMap.create(Materials::class.java)
      
      init {
         for (value in values()) {
            BY_DATA[value] = value.id to value.subdata
            BY_ID.inverse().putIfAbsent(value.id, value)
         }
      }
      
      /**
       * Gets a material object from the specified [data].
       */
      @JvmStatic
      fun from(data: MaterialData): Materials = from(data.itemTypeId, data.data.toInt())
      
      /**
       * Gets a material object from the specified [material] and [data].
       */
      @JvmStatic
      fun from(material: Material, data: Int): Materials = from(material.id, data)
      
      /**
       * Gets a material object from the specified [id] and [data].
       */
      @JvmStatic
      fun from(id: Int, data: Int): Materials = BY_DATA.inverse()[id to data] ?: BY_ID.inverse()[id] ?: AIR
      
      /**
       * Parses the given string to a material object or [AIR] if no material has found with the given string.
       */
      fun parse(value: String): Materials {
         if (value.isBlank())
            return AIR
         
         return runCatching {
            valueOf(value)
         }.recoverCatching {
            val split = value.split(':', limit = 2)
            from(split[0].toInt(), split[1].toInt())
         }.getOrDefault(AIR)
      }
      
      /**
       * Parses the given string to a material object or nulls if no material has found with the given string.
       */
      fun parseOrNull(value: String): Materials? {
         if (value.isBlank())
            return null
         
         return runCatching {
            valueOf(value)
         }.recoverCatching {
            val split = value.split(':', limit = 2)
            from(split[0].toInt(), split[1].toInt())
         }.getOrNull()
      }
   }
}

/**
 * Material category type.
 */
enum class Category(val id: Int) {
   BLOCK(0),
   ITEM(1),
   FOOD(2),
   FISH(3),
   EQUIPMENT(4),
   ARMOR(5),
   COLORED_BLOCK(6),
   COLORED_ITEM(7),
   TILE_ENTITY(8),
   LIQUID(9),
   ORE(10),
   PLANTATION(11);
   
   /**
    * Verify if this category is a part of block.
    */
   val isBlock get() = id == 0 || id == 8 || id == 9 || id == 11
   
   /**
    * Verify if this category is a part of item.
    */
   val isItem get() = id <= 5 || id == 10
   
   /**
    * Verify if this category has variety of color.
    */
   val isColorable get() = id == 6 || id == 7
}
