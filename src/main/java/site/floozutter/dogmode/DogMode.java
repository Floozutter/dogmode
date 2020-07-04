package site.floozutter.dogmode;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.event.TickEvent;
import org.lwjgl.glfw.GLFW;


@Mod(DogMode.MODID)
@OnlyIn(Dist.CLIENT)
public final class DogMode {
	public static final String MODID = "dogmode";
	private static final Logger LOGGER = LogManager.getLogger();
	
	static KeyBinding keyuwu;

	public DogMode() {
		// Register modloading methods.
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

		// Register ourselves for server and other game events we are interested in
		MinecraftForge.EVENT_BUS.register(this);

		// Test logging.
		LOGGER.info("AAAAA!!!!!");
	}

	// Examples.
	private void setup(final FMLCommonSetupEvent event) {
		LOGGER.info("HELLO FROM PREINIT");
		LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
	}
	private void doClientStuff(final FMLClientSetupEvent event) {
		// Client only.
		LOGGER.info(
			"Got game settings {}",
			event.getMinecraftSupplier().get().gameSettings
		);
		
		keyuwu = new KeyBinding("owo", GLFW.GLFW_KEY_Z, "OWO");
		ClientRegistry.registerKeyBinding(keyuwu);
	}
	private void enqueueIMC(final InterModEnqueueEvent event) {
		// Dispatch InterModComm to another mod.
		InterModComms.sendTo(
			"dogmode",
			"helloworld",
			() -> {
				LOGGER.info("Hello world from the MDK");
				return "Hello world";
			}
		);
	}
	private void processIMC(final InterModProcessEvent event) {
		// Receive and process InterModComms from other mods.
		LOGGER.info(
			"Got IMC {}",
			event.getIMCStream().
				map(m->m.getMessageSupplier().get()).
				collect(Collectors.toList())
		);
	}

	// SubsribeEvent example to let the Event Bus discover methods to call.
	@SubscribeEvent
	public void onServerStarting(FMLServerStartingEvent event) {
		// do something when the server starts
		LOGGER.info("HELLO from server starting");
	}

	// EventBusSubscriber example to automatically subscribe to events on the contained class.
	// (This is subscribing to the MOD Event Bus for receiving Registry Events.)
	@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents {
		@SubscribeEvent
		public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
			// (Register a new block here.)
			LOGGER.info("HELLO from Register Block");
		}
	}
	
	@SubscribeEvent
	public void uwu(TickEvent event) {
		if (keyuwu.isPressed()) {
			LOGGER.info("press me harder uwu");
		}
	}
}
