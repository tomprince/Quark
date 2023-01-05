package vazkii.quark.content.building.block;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import vazkii.quark.base.block.QuarkBlock;
import vazkii.quark.base.block.QuarkGlassBlock;
import vazkii.quark.base.block.QuarkPillarBlock;
import vazkii.quark.base.module.LoadModule;
import vazkii.quark.base.module.ModuleCategory;
import vazkii.quark.base.module.QuarkModule;

@LoadModule(category = ModuleCategory.BUILDING)
public class MoreMudBlocks extends QuarkModule {

	@Override
	public void register() {
		BlockBehaviour.Properties props = Properties.copy(Blocks.MUD_BRICKS);
		
		new QuarkBlock("carved_mud_bricks", this, CreativeModeTab.TAB_BUILDING_BLOCKS, props);
		new QuarkPillarBlock("mud_pillar", this, CreativeModeTab.TAB_BUILDING_BLOCKS, props);
		new QuarkGlassBlock("mud_brick_lattice", this, CreativeModeTab.TAB_BUILDING_BLOCKS, false, props);
	}
	
}
