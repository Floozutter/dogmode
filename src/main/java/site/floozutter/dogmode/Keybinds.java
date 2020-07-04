package site.floozutter.dogmode;
import site.floozutter.dogmode.DogMode;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.glfw.GLFW;


public final class Keybinds {
	private static KeyBinding keyuwu;
	
	public static void setup() {
		keyuwu = new KeyBinding("owo", GLFW.GLFW_KEY_Z, "OWO");
		ClientRegistry.registerKeyBinding(keyuwu);
	}
	
	@SubscribeEvent
	public static void uwu(TickEvent event) {
		if (keyuwu.isPressed()) {
			DogMode.LOGGER.info("press me harder uwu");
		}
	}
}