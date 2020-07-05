package site.floozutter.dogmode;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import java.util.ArrayList;


public final class Keybinds {
	private static ArrayList<BoundRunnable> binds;
	
	public static void add(KeyBinding binding, Runnable proc) {
		binds.add(new BoundRunnable(binding, proc));
	}
	
	public static void setup() {
		binds.forEach(BoundRunnable::register);
	}
	
	@SubscribeEvent
	public static void onTick(TickEvent event) {
		binds.forEach(BoundRunnable::run);
	}
	
	private static class BoundRunnable {
		private final KeyBinding binding;
		private final Runnable proc;
		
		public BoundRunnable(KeyBinding binding, Runnable proc) {
			this.binding = binding;
			this.proc = proc;
		}

		public void register() {
			ClientRegistry.registerKeyBinding(binding);
		}
		
		public void run() {
			if (binding.isPressed()) {
				proc.run();
			}
		}
	}
}