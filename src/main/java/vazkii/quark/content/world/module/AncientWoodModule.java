package vazkii.quark.content.world.module;

import com.google.common.base.Functions;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import vazkii.arl.util.RegistryHelper;
import vazkii.quark.base.block.QuarkLeavesBlock;
import vazkii.quark.base.handler.MiscUtil;
import vazkii.quark.base.handler.VariantHandler;
import vazkii.quark.base.handler.WoodSetHandler;
import vazkii.quark.base.handler.WoodSetHandler.WoodSet;
import vazkii.quark.base.module.LoadModule;
import vazkii.quark.base.module.ModuleCategory;
import vazkii.quark.base.module.QuarkModule;
import vazkii.quark.base.module.config.Config;
import vazkii.quark.base.module.config.Config.Min;
import vazkii.quark.content.world.block.AncientSaplingBlock;
import vazkii.quark.content.world.item.AncientFruitItem;

@LoadModule(category = ModuleCategory.WORLD, hasSubscriptions = true)
public class AncientWoodModule extends QuarkModule {

	@Config(description = "Set to 0 to disable Ancient Fruit giving exp")
	@Min(0)
	public static int ancientFruitExpValue = 10;
	
	@Config(description = "Set to 0 to disable loot chest generation")
	@Min(0)
	public static int ancientCityLootWeight = 8;
	
	@Config
	@Min(0)
	public static int ancientCityLootQuality = 1;
	
	public static WoodSet woodSet;
	public static Block ancient_leaves;
	public static Block ancient_sapling;
	public static Item ancient_fruit;
	
	@Override
	public void register() {
		woodSet = WoodSetHandler.addWoodSet(this, "ancient", MaterialColor.TERRACOTTA_WHITE, MaterialColor.TERRACOTTA_WHITE);
		ancient_leaves = new QuarkLeavesBlock(woodSet.name, this, MaterialColor.PLANT);
		ancient_sapling = new AncientSaplingBlock(this);
		ancient_fruit = new AncientFruitItem(this);

		VariantHandler.addFlowerPot(ancient_sapling, RegistryHelper.getInternalName(ancient_sapling).getPath(), Functions.identity());
	}
	
	@SubscribeEvent
	public void onLootTableLoad(LootTableLoadEvent event) {
		int weight = 0;

		if(event.getName().equals(BuiltInLootTables.ANCIENT_CITY))
			weight = ancientCityLootWeight;

		if(weight > 0) {
			LootPoolEntryContainer entry = LootItem.lootTableItem(ancient_sapling)
					.setWeight(weight)
					.setQuality(ancientCityLootQuality)
					.build();
			MiscUtil.addToLootTable(event.getTable(), entry);
		}
	}
	
}
