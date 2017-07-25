package the_fireplace.mechsoldiers.registry;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import the_fireplace.mechsoldiers.MechSoldiers;
import the_fireplace.overlord.registry.CraftingRecipes;

/**
 * @author The_Fireplace
 */
public class MechCraftingRecipes {
	public static ItemStack robot_constructor = new ItemStack(MechSoldiers.robot_constructor);
	public static ItemStack wood_skeleton = new ItemStack(MechSoldiers.skeleton_wood);
	public static ItemStack iron_skeleton = new ItemStack(MechSoldiers.skeleton_iron);
	public static ItemStack gold_skeleton = new ItemStack(MechSoldiers.skeleton_gold);

	public static void register() {
		CraftingRecipes.addRecipe(robot_constructor, " d ", "drd", "ddd", 'd', "gemDiamond", 'r', CraftingRecipes.skeleton_maker);
		CraftingRecipes.addRecipe(wood_skeleton, "sls", "sss", "sss", 's', "stickWood", 'l', "logWood");

		MetalMeltRecipes.instance().addMeltingRecipe(iron_skeleton, "ingotIron", "blockIron");
		MetalMeltRecipes.instance().addMeltingRecipe(gold_skeleton, "ingotGold", "blockGold");
	}
}
