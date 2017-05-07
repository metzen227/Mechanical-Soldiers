package the_fireplace.mechsoldiers.util;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

/**
 * @author The_Fireplace
 */
public abstract class ComponentDamageBehavior {
    public abstract ItemStack getDamagedItemStack(ItemStack itemToDamage, DamageSource source, float amount, String material, EntityLivingBase entityIn);
}
