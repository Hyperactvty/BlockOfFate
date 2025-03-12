package com.hyperactvty.blockoffate;

import com.hyperactvty.blockoffate.blocks.BlockOfFate_Block;
import com.hyperactvty.blockoffate.registry.*;
import com.hyperactvty.blockoffate.utilities.Fate;
import com.hyperactvty.blockoffate.utilities.Utils;
import com.mojang.logging.LogUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Tuple;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MainMod.MODID)
public class MainMod {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "blockoffate";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "blockoffate" namespace
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    // Create a Deferred Register to hold Items which will all be registered under the "blockoffate" namespace
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "blockoffate" namespace
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static Fate fateObject;
    public static JSONObject cfg;
    public JSONObject custom_dropsArray;
    public static JSONObject userBOF_Config;
    public static JSONArray fatePools = new JSONArray();
    public static JSONArray rates = new JSONArray();

    private static final SetupCheck setupCheck = new SetupCheck();


    /**
     *  DEVELOPMENT CODE
     * */

    // #region Development
//    public static final RegistryObject<Block> BoF_Generic_BLOCK = BLOCKS.register("bof_generic",
//            () -> new BlockOfFate_Block(BlockBehaviour.Properties.of()
//                    .setId(BLOCKS.key("bof_generic"))
//                    .mapColor(MapColor.QUARTZ)
//                    .destroyTime(0.5f)
//                    .overrideLootTable(Optional.ofNullable(LootTables.FIRST_JOIN_WORLD1))
//            )
//    );
//
//    // Creates a new BlockItem with the id "blockoffate:example_block", combining the namespace and path
//    public static final RegistryObject<Item> BoF_Generic_ITEM = ITEMS.register("bof_generic",
//            () -> new BlockItem(BoF_Generic_BLOCK.get(), new Item.Properties()
//                    .setId(ITEMS.key("bof_generic")) //block_of_fate
//                    .rarity(Rarity.RARE)
//                    .stacksTo(64))
//    );

    // #endregion Development


//    // Creates a new Block with the id "blockoffate:example_block", combining the namespace and path
//    public static final RegistryObject<Block> EXAMPLE_BLOCK = BLOCKS.register("example_block",
//        () -> new Block(BlockBehaviour.Properties.of()
//            .setId(BLOCKS.key("example_block"))
//            .mapColor(MapColor.STONE)
//                .strength(1.0f)
//        )
//    );
//    // Creates a new BlockItem with the id "blockoffate:example_block", combining the namespace and path
//    public static final RegistryObject<Item> EXAMPLE_BLOCK_ITEM = ITEMS.register("example_block",
//        () -> new BlockItem(EXAMPLE_BLOCK.get(), new Item.Properties().setId(ITEMS.key("example_block")))
//    );

    // Creates a new food item with the id "blockoffate:example_id", nutrition 1 and saturation 2
//    public static final RegistryObject<Item> BoF_Lucky_Ham_ITEM = ITEMS.register("bof_lucky_ham",
//        () -> new Item(new Item.Properties()
//            .setId(ITEMS.key("bof_lucky_ham"))
//            .stacksTo(16)
//            .food(new FoodProperties.Builder()
//                .alwaysEdible()
//                .nutrition(1)
//                .saturationModifier(2f)
//                .build()
//            )
//        )
//    );

    // Creates a creative tab with the id "blockoffate:example_tab" for the example item, that is placed after the combat tab
//    public static final RegistryObject<CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("bof_tab", () -> CreativeModeTab.builder()
//            .withTabsBefore(CreativeModeTabs.COMBAT)
//            .icon(() -> BoF_Lucky_Ham_ITEM.get().getDefaultInstance())
//            .title(Component.nullToEmpty("Blocks of Fate"))
//            .withLabelColor(4)
//            .displayItems((parameters, output) -> {
//                output.accept(BoF_Lucky_Ham_ITEM.get()); // Add the example item to the tab. For your own tabs, this method is preferred over the event
//                output.accept(BoF_Generic_ITEM.get());
//            }).build());

    public MainMod(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register's the `BlockItems.register(modEventBus)`
        BlockItems.register(modEventBus);
        EnchantmentRegistry.register(modEventBus);

        // Register the Deferred Register to the mod event bus so blocks get registered
//        BLOCKS.register(modEventBus);
//        // Register the Deferred Register to the mod event bus so items get registered
//        ITEMS.register(modEventBus);
//        // Register the Deferred Register to the mod event bus so tabs get registered
//        CREATIVE_MODE_TABS.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new KarmaEventHandler());
        MinecraftForge.EVENT_BUS.register(new ModClientEvents());


        // Register the item to a creative tab
//        modEventBus.addListener(this::addCreative);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");

        if (Config.logDirtBlock)
            LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));

        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);

        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));
    }

    // Add the example block item to the building blocks tab
//    private void addCreative(BuildCreativeModeTabContentsEvent event) {
//        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
////            event.accept(EXAMPLE_BLOCK_ITEM);
//            event.accept(BoF_Generic_ITEM);
//        }
//    }

    public static JSONObject loadConfig() {
        try {
            String configPath = "./config/blockoffate_config.json";
            String content = new String(Files.readAllBytes(Paths.get(configPath)));

            // Convert string to JSONObject
            JSONObject jsonObject = new JSONObject(content);

            // Print the entire JSON object
            System.out.println(jsonObject.toString(4)); // Pretty print with indentation

            setupCheck.setPrimaryConfigLoaded(true);
            System.err.println("Loaded the default config.");

            return new JSONObject(content);
        } catch (Exception e) {
            e.printStackTrace();
            return new JSONObject();
        }
    }

    public static JSONObject loadDefaultDrops() {
        try {
            String configPath = "./config/blockoffate_loot_pools.json";
            String content = new String(Files.readAllBytes(Paths.get(configPath)));

            // Convert string to JSONObject
            JSONObject jsonObject = new JSONObject(content);

            // Print the entire JSON object
            System.out.println(jsonObject.toString(4)); // Pretty print with indentation

            // Access specific fields
            rates = (JSONArray) jsonObject.get("rates");
            fateObject = new Fate(rates);
            fatePools = (JSONArray) jsonObject.get("fates");
            if(cfg.getBoolean("disableCurse")) {
                System.err.println("\tRemoving Tier `Cursed` from pools");
                List<JSONObject> filteredList = fateObject.getRates().toList().stream()
                        .map(obj -> new JSONObject((java.util.Map<?, ?>) obj)) // Converts Map to JSONObject
                        .filter(json -> !json.optString("tier").equals("CURSE")) // Removes objects with "tier": "CURSE"
                        .collect(Collectors.toList());

                fateObject.setRates(new JSONArray(filteredList));
//                fateObject.setRates(fateObject.getRates().remove(fateObject.getRates()));
            }

            setupCheck.setDefaultDropsLoaded(true);
//            System.out.println(fatePools.length()+" pools loaded.");
            System.err.println("Loaded the default drops.");
            fateObject.onStartUp(); // OR fateObject.onStartUp();

//            CustomFateRegistry.generateFateList();

            return new JSONObject(content);
        } catch (Exception e) {
            e.printStackTrace();
            return new JSONObject();
        }
    }

    public static JSONObject checkLoadCustomConfig() {
        try {
            String configPath = "./bof_config.json";
            String content = new String(Files.readAllBytes(Paths.get(configPath)));

            // Convert string to JSONObject
            JSONObject jsonObject = new JSONObject(content);

            System.out.println("===== LOAD CUSTOM CONFIG =====");
            // Print the entire JSON object
            System.out.println(jsonObject.toString(4)); // Pretty print with indentation

//            CustomFateRegistry.generateFateList();

            setupCheck.setCustomConfigLoaded(true);
            System.err.println("Loaded the user's custom config.");

            return new JSONObject(content);
        } catch (Exception e) {
            e.printStackTrace();
            return new JSONObject();
        }
    }

    public static JSONObject checkLoadCustomDrops() {
        try {
            String configPath = "./custom_drops.json";
            String content = new String(Files.readAllBytes(Paths.get(configPath)));

            // Convert string to JSONObject
            JSONObject jsonObject = new JSONObject(content);

            System.out.println("===== LOAD CUSTOM DROPS =====");
            // Print the entire JSON object
            System.out.println(jsonObject.toString(4)); // Pretty print with indentation

//            CustomFateRegistry.generateFateList();
            fatePools.putAll(jsonObject.get("custom_drops"));

            setupCheck.setCustomDropsLoaded(true);
            System.err.println("Loaded the user's custom drops.");


            return new JSONObject(content);
        } catch (Exception e) {
            e.printStackTrace();
            return new JSONObject();
        }
    }

    public static void onWorldLoad(String[] args) {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        try {
            cfg = loadConfig();
            loadDefaultDrops();
            // Check to see if there is a .txt or a .json file named `custom_drops` for user-added drops for a modpack
            custom_dropsArray = checkLoadCustomDrops();
            // Loads the config for the user to modify if they deem so.
            userBOF_Config = checkLoadCustomConfig();


            if (setupCheck.setupComplete() == false) {
                throw new LoadsIncomplete();
            }
            onWorldLoad(null);
        } catch (Exception e) {
            System.err.println("ERROR IN [onServerStarting] > "+e);
            throw new RuntimeException(e);
        }
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());

            // #region KARMA METER
//            ModClientEvents.subscribe(FMLJavaModLoadingContext.get().getModEventBus());
////            ModClientEvents.subscribe(FMLJavaModLoadingContext.get().getModEventBus());
//
////            IEventBus modEventBus = context.getModEventBus();
//            IEventBus modEventBus = event..getModEventBus();
////            MinecraftForge.EVENT_BUS.register(new ModClientEvents());
//            MinecraftForge.EVENT_BUS.register(new ModClientEvents(FMLJavaModLoadingContext));


            // #endregion KARMA METER
        }


//        @SubscribeEvent
//        public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
////            ServerPlayer player = (ServerPlayer) event.getPlayer();
//            if (event.getEntity() instanceof ServerPlayer player) { // Ensure it's a ServerPlayer
////                event.getEntity().awardStat(ResourceLocation.parse(MODID+":first_join_world"));
//                // Check if the player has already entered a world (i.e., check if it's their first time)
////                if (!player.getStats().hasStat(MODID+":first_join_world")) {
////                    // You can now trigger your advancement logic here
////                    player.awardAdvancement(AdvancementRewards.FIRST_ENTRY);
//////                    event.getEntity().awardStat(ResourceLocation.parse(MODID+":first_join_world"));
////                }
////                if (isNewPlayer(player)) {
////                    player.sendSystemMessage(Component.literal("Welcome to the server for the first time!"));
////                }
//            }
//
//
//        }

    }

    @SubscribeEvent
    public void onLootTableLoad(LootTableLoadEvent event) {
//        if (event.getName().equals(new ResourceLocation(MODID, "chests/my_custom_loot_table"))) {
        if (event.getName().equals(ResourceLocation.fromNamespaceAndPath(MODID, "chests/first_join_world"))) {
            LootTable table = event.getTable();
            // Modify or add to the loot table here if needed
            System.err.println("onLootTableLoad > "+table);
        }
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class PlayerJoinEventHandler {
        @SubscribeEvent
        public static void onPlayerJoin(net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent event) {
            if (event.getEntity() instanceof ServerPlayer player) { // Ensure it's a ServerPlayer
                if (isNewPlayer(player)) {
                    player.sendSystemMessage(Component.literal("Welcome to the server for the first time!"));

                    MinecraftServer server = player.server;
                    AdvancementHolder serverAdvancement = server.getAdvancements().get(Advancements.FIRST_JOIN_WORLD);
                    player.getAdvancements().award(serverAdvancement,"blockoffate:first_join_world");
                    player.getAdvancements().save();
                    System.err.println("serverAdvancement > " + serverAdvancement.value());
                    System.err.println("Awarded > " + player.getAdvancements());
                }
            }
        }

        // TODO: Maybe have this in the other `Subscribe`
        @SubscribeEvent
        public static void onModInitialization(final FMLCommonSetupEvent event) {
            // Register advancement provider
            IEventBus modEventBus = MinecraftForge.EVENT_BUS;
            modEventBus.register(AdvancementsProvider.class);
        }

        // #CHECKPOINT 0
//        @SubscribeEvent
//        public static void gatherData(GatherDataEvent event) {
//            System.err.println("[MainMod.java] C: `gatherData`");
//            DataGenerator generator = event.getGenerator();
//            PackOutput output = generator.getPackOutput();
//            ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
//            CompletableFuture<HolderLookup.Provider> registries = event.getLookupProvider();
//
//            // Add our custom advancements provider
//            generator.addProvider(event.includeServer(), new AdvancementsProvider(output, registries, existingFileHelper));
//        }

        private static boolean isNewPlayer(ServerPlayer player) {
            MinecraftServer server = player.server;

//            Advancement advancement = player.getAdvancements().getOrStartProgress(ResourceLocation.fromNamespaceAndPath("minecraft:story/root", "/"));

//            ResourceLocation storyRoot = ResourceLocation.parse("blockoffate:first_join_world"); // Correct way to create ResourceLocation
//            ResourceLocation storyRoot = ResourceLocation.parse("minecraft:story/root"); // Correct way to create ResourceLocation

//            Advancement advancement = player.getAdvancements().getOrStartProgress(new AdvancementHolder(storyRoot,storyRoot));
            AdvancementHolder serverAdvancement = server.getAdvancements().get(Advancements.FIRST_JOIN_WORLD);
//            System.err.println("serverAdvancement progress > " + serverAdvancement);
            if (serverAdvancement != null) {
                AdvancementProgress progress = player.getAdvancements().getOrStartProgress(serverAdvancement);
//                progress.grantProgress("blockoffate:first_join_world");
//                System.err.println("Player progress > " + progress);
//                System.err.println("Player > " + player.getName());
//                player.getAdvancements().award(serverAdvancement,"blockoffate:first_join_world");
//                player.getAdvancements().save();
//                System.err.println("Awarded > " + player.getAdvancements());
//                player.awardStat(Advancements.FIRST_JOIN_WORLD);
//                player.awardStat(storyRoot);
//                player.awardStat(ResourceLocation.parse(MODID+":first_join_world"));
                return !progress.isDone(); // If they haven't completed "story/root", they are new
            }


//            if (advancement != null) {
//                AdvancementProgress progress = player.getAdvancements().getOrStartProgress(advancement);
//                System.err.println("Player Advancements > " + progress);
//            } else {
//                System.err.println("Advancement not found!");
//            }


//            System.err.println("storyRoot > "+storyRoot);

            System.err.println("Player Advancements > "+player.getAdvancements());
//            System.err.println("Player Advancements > "+player.getAdvancements().getOrStartProgress(new AdvancementHolder(new ResourceLocation("minecraft:story/root"))));
//            Advancement advancement = server.getAdvancements().getAdvancement(new ResourceLocation("minecraft:story/root"));
//
//            if (advancement != null) {
//                AdvancementProgress progress = player.getAdvancements().getOrStartProgress(advancement);
//                return !progress.isDone(); // If they haven't completed "story/root", they are new
//            }

            return false; // Fallback in case advancement is missing
        }
    }

}


