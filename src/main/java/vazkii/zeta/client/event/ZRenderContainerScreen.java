package vazkii.zeta.client.event;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import vazkii.zeta.event.bus.IZetaPlayEvent;

public interface ZRenderContainerScreen extends IZetaPlayEvent {
	AbstractContainerScreen<?> getContainerScreen();
	PoseStack getPoseStack();
	int getMouseX();
	int getMouseY();

	interface Foreground extends ZRenderContainerScreen { }
	interface Background extends ZRenderContainerScreen { }
}