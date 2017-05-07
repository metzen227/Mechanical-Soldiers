package the_fireplace.mechsoldiers;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import the_fireplace.mechsoldiers.entity.EntityMechSkeleton;
import the_fireplace.mechsoldiers.items.ItemBrain;
import the_fireplace.mechsoldiers.items.ItemJoints;
import the_fireplace.mechsoldiers.items.ItemSkeleton;
import the_fireplace.mechsoldiers.network.CommonProxy;
import the_fireplace.mechsoldiers.registry.MechCraftingRecipes;
import the_fireplace.mechsoldiers.registry.PartRegistry;
import the_fireplace.mechsoldiers.util.ComponentDamageGeneric;
import the_fireplace.mechsoldiers.util.EnumPartType;
import the_fireplace.overlord.Overlord;

/**
 * @author The_Fireplace
 */
@Mod(modid=MechSoldiers.MODID, name=MechSoldiers.MODNAME, dependencies = "required-after:overlord@[2.3.*,)")
public class MechSoldiers {
    public static final String MODID = "mechsoldiers";
    public static final String MODNAME = "Mechanical Soldiers";

    @Mod.Instance(MODID)
    public static MechSoldiers instance;

    @SidedProxy(clientSide = "the_fireplace."+MODID+".client.ClientProxy", serverSide = "the_fireplace."+MODID+".network.CommonProxy")
    public static CommonProxy proxy;

    public static final Item skeleton_iron = new ItemSkeleton("iron", 250);
    public static final Item skeleton_gold = new ItemSkeleton("gold", 32);
    public static final Item skeleton_wood = new ItemSkeleton("wood", 59);
    public static final Item joints_iron = new ItemJoints("iron", 250);
    public static final Item joints_gold = new ItemJoints("gold", 32);
    public static final Item brain_copper_redstone = new ItemBrain("copper_redstone", 16);
    public static final Item brain_gold_redstone = new ItemBrain("gold_redstone", 64);

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        new PartRegistry();
        new ComponentDamageGeneric();
        GameRegistry.register(skeleton_iron);
        GameRegistry.register(skeleton_gold);
        GameRegistry.register(skeleton_wood);
        GameRegistry.register(joints_iron);
        GameRegistry.register(joints_gold);
        GameRegistry.register(brain_copper_redstone);
        GameRegistry.register(brain_gold_redstone);
        PartRegistry.registerPart(skeleton_iron, EnumPartType.SKELETON, ComponentDamageGeneric.instance, "iron", new ResourceLocation(Overlord.MODID, "textures/entity/iron_skeleton"));
        PartRegistry.registerPart(skeleton_gold, EnumPartType.SKELETON, ComponentDamageGeneric.instance, "gold", new ResourceLocation(MODID, "textures/entity/gold_skeleton"));
        PartRegistry.registerPart(skeleton_wood, EnumPartType.SKELETON, ComponentDamageGeneric.instance, "wood", new ResourceLocation(MODID, "textures/entity/wood_skeleton"));
        PartRegistry.registerPart(joints_iron, EnumPartType.JOINTS, ComponentDamageGeneric.instance, "iron", new ResourceLocation(MODID, "textures/entity/iron_joints"));
        PartRegistry.registerPart(joints_gold, EnumPartType.JOINTS, ComponentDamageGeneric.instance, "gold", new ResourceLocation(MODID, "textures/entity/gold_joints"));
        PartRegistry.registerPart(brain_copper_redstone, EnumPartType.BRAIN, ComponentDamageGeneric.instance, "copper_redstone", null);
        PartRegistry.registerPart(brain_gold_redstone, EnumPartType.BRAIN, ComponentDamageGeneric.instance, "gold_redstone", null);

        int eid=-1;
        EntityRegistry.registerModEntity(/*new ResourceLocation(MODID+":mechanical_skeleton"), */EntityMechSkeleton.class, "mechanical_skeleton", ++eid, instance, 128, 2, false);

        proxy.registerClient();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event){
        MechCraftingRecipes.register();
    }

    @SideOnly(Side.CLIENT)
    public void registerItemRenders(){
        rmm(skeleton_gold);
        rmm(skeleton_iron);
        rmm(skeleton_wood);
        rmm(joints_gold);
        rmm(joints_iron);
        rmm(brain_copper_redstone);
        rmm(brain_gold_redstone);
    }

    @SideOnly(Side.CLIENT)
    private void rmm(Block b) {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(b), 0, new ModelResourceLocation(MODID+":" + b.getUnlocalizedName().substring(5), "inventory"));
    }

    @SideOnly(Side.CLIENT)
    private void rmm(Item i) {
        ModelLoader.setCustomModelResourceLocation(i, 0, new ModelResourceLocation(MODID+":" + i.getUnlocalizedName().substring(5), "inventory"));
    }
}
