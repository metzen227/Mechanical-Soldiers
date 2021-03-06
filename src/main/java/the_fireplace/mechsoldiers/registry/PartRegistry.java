package the_fireplace.mechsoldiers.registry;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;
import the_fireplace.mechsoldiers.util.EnumPartType;
import the_fireplace.mechsoldiers.util.ICPU;
import the_fireplace.mechsoldiers.util.IComponentDamageBehavior;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author The_Fireplace
 */
@ParametersAreNonnullByDefault
public final class PartRegistry {
	private static PartRegistry instance;
	private HashMap<ItemStack, EnumPartType> partTypes = Maps.newHashMap();
	private HashMap<ItemStack, IComponentDamageBehavior> partBehaviors = Maps.newHashMap();
	private HashMap<ItemStack, String> partMaterials = Maps.newHashMap();
	private HashMap<ItemStack, ResourceLocation> partTextures = Maps.newHashMap();
	private HashMap<ItemStack, ICPU> itemAIHandlers = Maps.newHashMap();

	public PartRegistry() {
		if (instance == null)
			instance = this;
	}

	/**
	 * Register an item as a skeleton for Mechanical Skeletons
	 *
	 * @param item
	 * 		The item to be registered
	 * @param behavior
	 * 		The behavior instance defining how the item takes damage
	 * @param material
	 * 		The material the item is made of. Used in determining how much damage an item takes.
	 * @param texture
	 * 		The texture to render the part with.
	 */
	public static void registerSkeleton(Item item, IComponentDamageBehavior behavior, String material, ResourceLocation texture) {
		registerPart(item, OreDictionary.WILDCARD_VALUE, EnumPartType.SKELETON, behavior, material, texture);
	}

	/**
	 * Register an item as joints for a Mechanical Skeleton
	 *
	 * @param item
	 * 		The item to be registered
	 * @param behavior
	 * 		The behavior instance defining how the item takes damage
	 * @param material
	 * 		The material the item is made of. Used in determining how much damage an item takes.
	 * @param texture
	 * 		The texture to render the part with.
	 */
	public static void registerJoints(Item item, IComponentDamageBehavior behavior, String material, ResourceLocation texture) {
		registerPart(item, OreDictionary.WILDCARD_VALUE, EnumPartType.JOINTS, behavior, material, texture);
	}

	/**
	 * Register an item as a Mechanical Skeleton CPU
	 *
	 * @param item
	 * 		The item to be registered
	 * @param logicHandler
	 * 		The logic handler to be used with the CPU. If you don't want one, use registerPotatoCPU()
	 * @param behavior
	 * 		The behavior instance defining how the item takes damage
	 * @param material
	 * 		The material the item is made of. Used in determining how much damage an item takes.
	 */
	public static void registerCPU(Item item, ICPU logicHandler, IComponentDamageBehavior behavior, String material) {
		registerPart(item, OreDictionary.WILDCARD_VALUE, EnumPartType.CPU, behavior, material, null);
		instance.itemAIHandlers.put(new ItemStack(item, 1, OreDictionary.WILDCARD_VALUE), logicHandler);
	}

	/**
	 * Register an item as a CPU without AI.
	 *
	 * @param item
	 * 		The item to be registered
	 * @param meta
	 * 		The stack metadata. Accepts OreDictionary.WILDCARD_VALUE
	 * @param behavior
	 * 		The behavior instance defining how the item takes damage
	 * @param material
	 * 		The material the item is made of. Used in determining how much damage an item takes.
	 */
	public static void registerPotatoCPU(Item item, int meta, IComponentDamageBehavior behavior, String material) {
		registerPart(item, meta, EnumPartType.CPU, behavior, material, null);
	}

	private static void registerPart(Item item, int meta, EnumPartType type, IComponentDamageBehavior behavior, String material, @Nullable ResourceLocation texture) {
		instance.partTypes.put(new ItemStack(item, 1, meta), type);
		instance.partBehaviors.put(new ItemStack(item, 1, meta), behavior);
		instance.partMaterials.put(new ItemStack(item, 1, meta), material);
		if (texture != null)
			instance.partTextures.put(new ItemStack(item, 1, meta), texture);
	}

	public static boolean isPart(@Nullable ItemStack part) {
		if (part == null)
			return false;
		for (ItemStack stack : instance.partTypes.keySet())
			if (stack.getItem() == part.getItem() && (stack.getMetadata() == OreDictionary.WILDCARD_VALUE || stack.getMetadata() == part.getMetadata()))
				return true;
		return false;
	}

	public static boolean isPartOfType(@Nullable ItemStack part, EnumPartType slotType) {
		if (part == null || slotType == null || !isPart(part))
			return false;
		for (ItemStack stack : instance.partTypes.keySet())
			if (stack.getItem() == part.getItem() && (stack.getMetadata() == OreDictionary.WILDCARD_VALUE || stack.getMetadata() == part.getMetadata()))
				return instance.partTypes.get(stack) == slotType;
		return false;
	}

	public static ArrayList<ItemStack> getPartsOfType(EnumPartType type){
		ArrayList<ItemStack> out = Lists.newArrayList();
		for (ItemStack stack : instance.partTypes.keySet())
			if(instance.partTypes.get(stack) == type)
				out.add(stack);
		return out;
	}

	public static ItemStack damagePart(ItemStack part, DamageSource source, float amount, EntityLivingBase entity) {
		if (!isPart(part) || !part.isItemStackDamageable())
			return part;
		for (ItemStack stack : instance.partBehaviors.keySet())
			if (stack.getItem() == part.getItem() && (stack.getMetadata() == OreDictionary.WILDCARD_VALUE || stack.getMetadata() == part.getMetadata()))
				for (ItemStack mat : instance.partMaterials.keySet()) {
					if (mat.getItem() == part.getItem() && (mat.getMetadata() == OreDictionary.WILDCARD_VALUE || mat.getMetadata() == part.getMetadata()))
						return instance.partBehaviors.get(stack).getDamagedItemStack(part, source, amount, instance.partMaterials.get(mat), entity);
				}
		return part;
	}

	@Nullable
	public static ResourceLocation getTexLocation(ItemStack part) {
		if (!isPart(part))
			return null;
		for (ItemStack stack : instance.partTextures.keySet())
			if (stack.getItem() == part.getItem() && (stack.getMetadata() == OreDictionary.WILDCARD_VALUE || stack.getMetadata() == part.getMetadata()))
				return instance.partTextures.get(stack);
		return null;
	}

	@Nullable
	public static ICPU getCPU(ItemStack part) {
		if (!isPart(part))
			return null;
		for (ItemStack stack : instance.itemAIHandlers.keySet())
			if (stack.getItem() == part.getItem() && (stack.getMetadata() == OreDictionary.WILDCARD_VALUE || stack.getMetadata() == part.getMetadata()))
				return instance.itemAIHandlers.get(stack);
		return null;
	}
}
