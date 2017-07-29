package io.github.mosadie.ExponentialPower.Blocks;

import io.github.mosadie.ExponentialPower.ExponentialPower;
import io.github.mosadie.ExponentialPower.Items.ItemManager;
import io.github.mosadie.ExponentialPower.TileEntitys.EnderGeneratorMk2TE;
import io.github.mosadie.ExponentialPower.TileEntitys.EnderGeneratorTE;
import io.github.mosadie.ExponentialPower.network.GUIHandler;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EnderGeneratorMk2 extends Block implements ITileEntityProvider {
	public EnderGeneratorMk2() {
		super(Material.PISTON);
		this.setUnlocalizedName("endergeneratormk2");
		this.setCreativeTab(ItemManager.CreativeTab);
		this.setHardness(2.5F);
		this.setResistance(15f);
		this.isBlockContainer = true;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new EnderGeneratorMk2TE();
    }
    
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
	        playerIn.openGui(ExponentialPower.instance, GUIHandler.MOD_TILE_ENTITY_GUI_MK2, worldIn, pos.getX(), pos.getY(), pos.getZ());
	    }
	    return true;
	}
    
    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        if (stack.hasDisplayName()) {
            ((EnderGeneratorMk2TE) worldIn.getTileEntity(pos)).setCustomName(stack.getDisplayName());
        }
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
    	EnderGeneratorTE te = (EnderGeneratorTE) world.getTileEntity(pos);
        InventoryHelper.dropInventoryItems(world, pos, te);
        super.breakBlock(world, pos, state);
    }
}