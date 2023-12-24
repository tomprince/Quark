package org.violetmoon.quark.content.building.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LadderBlock;
import net.minecraft.world.level.block.state.BlockState;

import org.violetmoon.zeta.block.IZetaBlock;
import org.violetmoon.zeta.module.ZetaModule;
import org.violetmoon.zeta.registry.CreativeTabManager;
import org.violetmoon.zeta.registry.RenderLayerRegistry;
import org.violetmoon.zeta.util.BooleanSuppliers;

import java.util.function.BooleanSupplier;

public class VariantLadderBlock extends LadderBlock implements IZetaBlock {

	private final ZetaModule module;
	private final boolean flammable;

	private BooleanSupplier condition = BooleanSuppliers.TRUE;

	public VariantLadderBlock(String type, ZetaModule module, Block.Properties props, boolean flammable) {
		super(props);

		this.module = module;
		module.zeta.registry.registerBlock(this, type + "_ladder", true);
		CreativeTabManager.addToCreativeTabNextTo(CreativeModeTabs.FUNCTIONAL_BLOCKS, this, Blocks.LADDER, false);
		module.zeta.renderLayerRegistry.put(this, RenderLayerRegistry.Layer.CUTOUT);

		this.flammable = flammable;
	}

	public VariantLadderBlock(String type, ZetaModule module, boolean flammable) {
		this(type, module,
				Block.Properties.copy(Blocks.LADDER),
				flammable);
	}

	@Override
	public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
		return flammable;
	}

	@Override
	public ZetaModule getModule() {
		return module;
	}

	@Override
	public VariantLadderBlock setCondition(BooleanSupplier condition) {
		this.condition = condition;
		return this;
	}

	@Override
	public boolean doesConditionApply() {
		return condition.getAsBoolean();
	}

}