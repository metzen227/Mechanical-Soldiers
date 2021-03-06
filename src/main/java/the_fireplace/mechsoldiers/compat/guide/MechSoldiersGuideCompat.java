package the_fireplace.mechsoldiers.compat.guide;

import amerifrance.guideapi.api.GuideBook;
import amerifrance.guideapi.api.IGuideBook;
import amerifrance.guideapi.api.IPage;
import amerifrance.guideapi.api.impl.Book;
import amerifrance.guideapi.api.impl.abstraction.CategoryAbstract;
import amerifrance.guideapi.api.impl.abstraction.EntryAbstract;
import amerifrance.guideapi.category.CategoryItemStack;
import amerifrance.guideapi.entry.EntryItemStack;
import amerifrance.guideapi.page.PageIRecipe;
import amerifrance.guideapi.page.PageText;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import the_fireplace.mechsoldiers.MechSoldiers;
import the_fireplace.mechsoldiers.registry.MechCraftingRecipes;
import the_fireplace.overlord.Overlord;
import the_fireplace.overlord.compat.guide.OverlordGuide;

import java.util.List;
import java.util.Map;

import static the_fireplace.overlord.Overlord.proxy;

@GuideBook(priority = EventPriority.LOW)
public class MechSoldiersGuideCompat implements IGuideBook {
	@Override
	public Book buildBook() {
		if (OverlordGuide.myGuide == null) {
			Overlord.logError("Mechanical Soldiers guide book modifier loaded before the Overlord guide book. THIS SHOULD NOT HAPPEN. USERS, REPORT THIS.");
			return null;
		}
		Map<ResourceLocation, EntryAbstract> entries = Maps.newLinkedHashMap();

		List<IPage> pages = Lists.newArrayList();
		pages.add(new PageText(proxy.translateToLocal("mechsoldiers.guide.1.1.1")));
		//pages.add(new PageJsonRecipe(new ResourceLocation(MechSoldiers.MODID, "robot_constructor")));
		pages.add(new PageText(proxy.translateToLocal("mechsoldiers.guide.1.1.2")));
		pages.add(new PageText(proxy.translateToLocal("mechsoldiers.guide.1.1.3")));
		pages.add(new PageText(proxy.translateToLocal("mechsoldiers.guide.1.1.4")));
		entries.put(new ResourceLocation(MechSoldiers.MODID, "1.1"), new EntryItemStack(pages, proxy.translateToLocal("mechsoldiers.guide.1.1"), new ItemStack(MechSoldiers.robot_constructor)));

		pages = Lists.newArrayList();
		pages.add(new PageText(proxy.translateToLocal("mechsoldiers.guide.1.2.1")));
		//pages.add(new PageJsonRecipe(new ResourceLocation(MechSoldiers.MODID, "metal_part_constructor")));
		pages.add(new PageText(proxy.translateToLocal("mechsoldiers.guide.1.2.2")));
		pages.add(new PageText(proxy.translateToLocal("mechsoldiers.guide.1.2.3")));
		pages.add(new PageText(proxy.translateToLocal("mechsoldiers.guide.1.2.4")));
		//pages.add(new PageJsonRecipe(new ResourceLocation(MechSoldiers.MODID, "skeleton_wood")));
		entries.put(new ResourceLocation(MechSoldiers.MODID, "1.2"), new EntryItemStack(pages, proxy.translateToLocal("mechsoldiers.guide.1.2"), new ItemStack(MechSoldiers.metal_part_constructor)));

		pages = Lists.newArrayList();
		pages.add(new PageText(proxy.translateToLocal("mechsoldiers.guide.1.3.1")));
		//pages.add(new PageJsonRecipe(new ResourceLocation(MechSoldiers.MODID, "cpu_melter")));
		pages.add(new PageText(proxy.translateToLocal("mechsoldiers.guide.1.3.2")));
		entries.put(new ResourceLocation(MechSoldiers.MODID, "1.3"), new EntryItemStack(pages, proxy.translateToLocal("mechsoldiers.guide.1.3"), new ItemStack(MechSoldiers.cpu_melter)));

		pages = Lists.newArrayList();
		pages.add(new PageText(proxy.translateToLocal("mechsoldiers.guide.1.4.1")));
		//pages.add(new PageJsonRecipe(new ResourceLocation(MechSoldiers.MODID, "mini_tank")));
		pages.add(new PageText(proxy.translateToLocal("mechsoldiers.guide.1.4.2")));
		//pages.add(new PageJsonRecipe(new ResourceLocation(MechSoldiers.MODID, "blueprint")));
		entries.put(new ResourceLocation(MechSoldiers.MODID, "1.4"), new EntryItemStack(pages, proxy.translateToLocal("mechsoldiers.guide.1.4"), new ItemStack(MechSoldiers.blueprint)));

		pages = Lists.newArrayList();
		pages.add(new PageText(proxy.translateToLocal("mechsoldiers.guide.1.5.1")));
		//pages.add(new PageJsonRecipe(new ResourceLocation(MechSoldiers.MODID, "part_stainer")));
		pages.add(new PageText(proxy.translateToLocal("mechsoldiers.guide.1.5.2")));
		pages.add(new PageText(proxy.translateToLocal("mechsoldiers.guide.1.5.3")));
		pages.add(new PageText(proxy.translateToLocal("mechsoldiers.guide.1.5.4")));
		entries.put(new ResourceLocation(MechSoldiers.MODID, "1.5"), new EntryItemStack(pages, proxy.translateToLocal("mechsoldiers.guide.1.5"), new ItemStack(MechSoldiers.part_stainer)));

		List<CategoryAbstract> categories = OverlordGuide.myGuide.getCategoryList();
		categories.add(new CategoryItemStack(entries, proxy.translateToLocal("mechsoldiers.guide.1"), new ItemStack(MechSoldiers.robot_constructor)));

		for (CategoryAbstract cat : categories) {
			if (cat.name.equals(proxy.translateToLocal("overlord.guide.2"))) {
				pages = Lists.newArrayList();
				pages.add(new PageText(proxy.translateToLocal("mechsoldiers.guide.2.1.1")));
				pages.add(new PageText(proxy.translateToLocal("mechsoldiers.guide.2.1.2")));
				pages.add(new PageText(proxy.translateToLocal("mechsoldiers.guide.2.1.3")));
				cat.entries.put(new ResourceLocation(MechSoldiers.MODID, "2.1"), new EntryItemStack(pages, proxy.translateToLocal("mechsoldiers.guide.2.1"), new ItemStack(MechSoldiers.skeleton_term)));
				break;
			}
		}

		OverlordGuide.myGuide.setCategoryList(categories);
		return null;
	}

	@Override
	public void handleModel(ItemStack bookStack) {

	}

	@Override
	public void handlePost(ItemStack bookStack) {

	}
}
