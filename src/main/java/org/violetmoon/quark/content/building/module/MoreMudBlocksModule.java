package org.violetmoon.quark.content.building.module;

import org.violetmoon.quark.base.block.QuarkBlock;
import org.violetmoon.quark.base.block.QuarkPillarBlock;
import org.violetmoon.quark.content.building.block.MudBrickLatticeBlock;
import org.violetmoon.zeta.event.bus.LoadEvent;
import org.violetmoon.zeta.event.load.ZRegister;
import org.violetmoon.zeta.module.ZetaLoadModule;
import org.violetmoon.zeta.module.ZetaModule;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

@ZetaLoadModule(category = "building")
public class MoreMudBlocksModule extends ZetaModule {

	@LoadEvent
	public final void register(ZRegister event) {
		BlockBehaviour.Properties props = Properties.copy(Blocks.MUD_BRICKS);
		
		new QuarkBlock("carved_mud_bricks", this, CreativeModeTab.TAB_BUILDING_BLOCKS, props);
		new QuarkPillarBlock("mud_pillar", this, CreativeModeTab.TAB_BUILDING_BLOCKS, props);
		new MudBrickLatticeBlock(this, props);
	}
	
}