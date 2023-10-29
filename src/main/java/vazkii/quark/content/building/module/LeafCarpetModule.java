package vazkii.quark.content.building.module;

import java.util.LinkedList;
import java.util.List;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;
import vazkii.quark.base.block.IQuarkBlock;
import vazkii.quark.base.module.ModuleLoader;
import vazkii.zeta.client.AlikeColorHandler;
import vazkii.zeta.client.event.ZAddBlockColorHandlers;
import vazkii.zeta.client.event.ZAddItemColorHandlers;
import vazkii.zeta.module.ZetaLoadModule;
import vazkii.zeta.module.ZetaModule;
import vazkii.quark.base.util.VanillaWoods;
import vazkii.quark.base.util.VanillaWoods.Wood;
import vazkii.quark.content.building.block.LeafCarpetBlock;
import vazkii.quark.content.world.block.BlossomLeavesBlock;
import vazkii.quark.content.world.module.AncientWoodModule;
import vazkii.quark.content.world.module.BlossomTreesModule;
import vazkii.zeta.event.ZLoadComplete;
import vazkii.zeta.event.ZRegister;
import vazkii.zeta.event.bus.LoadEvent;

@ZetaLoadModule(category = "building", antiOverlap = { "woodworks", "immersive_weathering" })
public class LeafCarpetModule extends ZetaModule {

	public static List<LeafCarpetBlock> carpets = new LinkedList<>();

	@LoadEvent
	public final void register(ZRegister event) {
		for(Wood wood : VanillaWoods.OVERWORLD)
			carpet(wood.leaf());
		
		carpet(Blocks.AZALEA_LEAVES);
		carpet(Blocks.FLOWERING_AZALEA_LEAVES);
	}

	@LoadEvent
	public void postRegister(ZRegister.Post e) {
		BlossomTreesModule.trees.keySet().stream().map(t -> (BlossomLeavesBlock) t.leaf.getBlock()).forEach(this::blossomCarpet);
		
		carpetBlock(AncientWoodModule.ancient_leaves).setCondition(() -> ModuleLoader.INSTANCE.isModuleEnabled(AncientWoodModule.class));
	}

	@LoadEvent
	public void loadComplete(ZLoadComplete event) {
		event.enqueueWork(() -> {
			for(LeafCarpetBlock c : carpets) {
				if(c.asItem() != null)
					ComposterBlock.COMPOSTABLES.put(c.asItem(), 0.2F);
			}
		});
	}

	private void carpet(Block base) {
		carpetBlock(base);
	}

	private void blossomCarpet(BlossomLeavesBlock base) {
		carpetBlock(base).setCondition(base::isEnabled);
	}

	private LeafCarpetBlock carpetBlock(Block base) {
		LeafCarpetBlock carpet = new LeafCarpetBlock(IQuarkBlock.inherit(base, s -> s.replaceAll("_leaves", "_leaf_carpet")), base, this);
		carpets.add(carpet);
		return carpet;
	}

	@ZetaLoadModule(clientReplacement = true)
	public static class Client extends LeafCarpetModule {

		@LoadEvent
		public void blockColorHandlers(ZAddBlockColorHandlers event) {
			event.registerNamed(b -> new AlikeColorHandler((LeafCarpetBlock) b, LeafCarpetBlock::getBaseState), "leaf_carpet");
		}

		@LoadEvent
		public void itemColorHandlers(ZAddItemColorHandlers event) {
			event.registerNamed(i -> new AlikeColorHandler(i, LeafCarpetBlock::getBaseState), "leaf_carpet");
		}

	}

}
