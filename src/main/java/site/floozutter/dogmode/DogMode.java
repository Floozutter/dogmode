package site.floozutter.dogmode;

import site.floozutter.dogmode.KeyBinds;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.glfw.GLFW;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(DogMode.MODID)
@OnlyIn(Dist.CLIENT)
public final class DogMode {
	public static final String MODID = "dogmode";
	public static final Logger LOGGER = LogManager.getLogger();

	public DogMode() {
		// Register modloading methods.
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setupClient);

		// Register to EVENT_BUS.
		MinecraftForge.EVENT_BUS.register(KeyBinds.class);

		// Test logging.
		LOGGER.info("DogMode constructed!");
	}

	private void setupClient(final FMLClientSetupEvent event) {
		KeyBinds.add(
			new KeyBinding("owo", GLFW.GLFW_KEY_Z, "DogMode"),
			() -> LOGGER.info("press me harder uwu")
		);
		KeyBinds.setup();
		
		LOGGER.info("DogMode activated!");
	}
}
