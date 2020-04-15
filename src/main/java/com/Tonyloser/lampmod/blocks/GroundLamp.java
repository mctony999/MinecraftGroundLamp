package com.Tonyloser.lampmod.blocks;

import javax.annotation.Nullable;

import com.Tonyloser.lampmod.Main;
import com.Tonyloser.lampmod.network.PacketRequestUpdateLantern;
import com.Tonyloser.lampmod.network.PacketUpdateLantern;
import com.Tonyloser.lampmod.tileentity.TileEntityHang;
import com.Tonyloser.lampmod.util.handlers.SoundsHandler;

import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;


public class GroundLamp extends BlockTileEntity<TileEntityHang>
{
	public static final AxisAlignedBB GROUND_LAMP_BLOCK_AABB = new AxisAlignedBB(0.0625 * 5, 0, 0.0625*5, 0.0625*11, 0.0625*9, 0.0625*11); //collision control, set to point in the space and the rectangle will be the collision space
	public static final AxisAlignedBB GROUND_LAMP_HANG_AABB = new AxisAlignedBB(0.0625 * 5, 0.0625*1, 0.0625*5, 0.0625*11, 0.0625*10, 0.0625*11); //collision control, set to point in the space and the rectangle will be the collision space
	public static final PropertyBool HANGING = PropertyBool.create("hanging");
	public static final SoundType LANTERN = new SoundType(1.0F, 1.0F, SoundsHandler.BLOCK_LANTERN_BREAK, SoundsHandler.BLOCK_LANTERN_STEP, SoundsHandler.BLOCK_LANTERN_PLACE, SoundsHandler.BLOCK_LANTERN_HIT, SoundsHandler.BLOCK_LANTERN_FALL);
	
	public GroundLamp(String name, Material material) //block property
	{
		super(name, material);
		GameRegistry.registerTileEntity(this.getTileEntityClass(), this.getRegistryName()); //registry TileEntity
		setSoundType(LANTERN);
		//setSoundType(SoundType.METAL); //The sound walk on it.
		setHardness(3.5F); //Hardness
		setResistance(3.5F); //Explosive Resistance
		setHarvestLevel("pickaxe", 0); //What tool can harvest the block
		setLightLevel(1.0F); //lightness only between 0~1
		//setLightOpacity(1); //1 for light can through the block
		//setBlockUnbreakable();
		this.setDefaultState(this.blockState.getBaseState().withProperty(HANGING, Boolean.valueOf(false)));
		
	}
	
	protected BlockStateContainer createBlockState() //Create Block Property
	{
		return new BlockStateContainer(this, new IProperty[] {HANGING});
	}
	
	
	@Override
	public boolean isOpaqueCube(IBlockState stat) //make the floor can see
	{
		return false;
	}
	
	public boolean isFullCube(IBlockState state) 
	{
		return false;
	}
	
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) //Get the correct collision box. It will keep firing when game is running.
	{

		if(state.getValue(HANGING) == true)
		{
			return GROUND_LAMP_HANG_AABB;
		}
		else
		{
			return GROUND_LAMP_BLOCK_AABB;
		}
	}
	
	public BlockRenderLayer getBlockLayer() //Make it's texture partial transparent
	{
		return BlockRenderLayer.CUTOUT_MIPPED;
	}
	
	public int getMetaFromState(IBlockState state) //Without this the game won't know the new property you created and game will crash.
	{
		return 0;
	}
	
	/**
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) //Trigger when nearby block update. It will set the correct block property. Abandon.
	 {
		TileEntityHang tile = getTileEntity(worldIn, pos);
		tile.onLoad(); //send packet"PacketRequestUpdateLantern" to get the right tile entity
//		Main.logger.info("getActualState start");
//		Main.logger.info(tile.getTileEntityHang());
		if(tile.getTileEntityHang() == 1)
		{
			return state.withProperty(HANGING, Boolean.valueOf(true));
		}
		else
		{
			return state.withProperty(HANGING, Boolean.valueOf(false));
		}
	 }*/
	
	public static void SetBlockState(World worldIn, BlockPos pos, boolean HangbooleanFromTES) //Set the model correctly. It is called by TESGroundLamp.
	{
		if(HangbooleanFromTES == true)
		{
			worldIn.setBlockState(pos, worldIn.getBlockState(pos).withProperty(HANGING, Boolean.valueOf(true)));
		}
		else
		{
			worldIn.setBlockState(pos, worldIn.getBlockState(pos).withProperty(HANGING, Boolean.valueOf(false)));
		}
	}
	
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) //trigger when block is placed
	{
		canPlaceBlockAt(worldIn,pos); //detect the place is valid or not
		TileEntityHang tile = getTileEntity(worldIn, pos);
		
		if(worldIn.getBlockState(pos.down()).isNormalCube() || IsFence(worldIn.getBlockState(pos.down()).getBlock().getRegistryName()))
		{
			if(!worldIn.isRemote) { //if it is sever than do these
				tile.SetTileEntityHangToFalse();
				Main.network.sendToAllAround(new PacketUpdateLantern(tile), new NetworkRegistry.TargetPoint(worldIn.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 64));
			}
//				Main.logger.info("onBlockPlacedBy UP");
		}
		else
		{
			if(!worldIn.isRemote) {//if it is sever than do these
				tile.SetTileEntityHangToTrue();
				Main.network.sendToAllAround(new PacketUpdateLantern(tile), new NetworkRegistry.TargetPoint(worldIn.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 64));
			}
//				Main.logger.info("onBlockPlacedBy DOWN");
		}
		
	}
	
	@SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        return true;
    }
	
	public void observedNeighborChange(IBlockState state, World world, BlockPos observerPos, Block changedBlock, BlockPos changedBlockPos) //Destroy the block when base gone
	{
		TileEntityHang tile = getTileEntity(world, observerPos);

		boolean observedNeighborChangeOriginalbool = false;
		if(tile.getTileEntityHang() == 1)
		{
			observedNeighborChangeOriginalbool = true;
		}
		else
		{
			observedNeighborChangeOriginalbool = false;
		}
		
		if(observedNeighborChangeOriginalbool == true)
		{
			if(world.getBlockState(observerPos.up()).getMaterial() == Material.AIR) 
			{
				world.destroyBlock(observerPos, true);
			}
		}
		else
		{
			if(world.getBlockState(observerPos.down()).getMaterial() == Material.AIR)
			{
				world.destroyBlock(observerPos, true);
			}
		}
	}

	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) //make sure the place is valid
    {
		boolean UpAir = false;
		boolean DwAir = false;
		boolean CanPlaceOrNot = true;
		ResourceLocation UpBlockRegistryName = worldIn.getBlockState(pos.up()).getBlock().getRegistryName();
		ResourceLocation DownBlockRegistryName = worldIn.getBlockState(pos.down()).getBlock().getRegistryName();	
		if(worldIn.getBlockState(pos.up()).getMaterial() == Material.AIR) //detect the upper block is valid or not
		{
			UpAir = true;
		}
		else if(!worldIn.getBlockState(pos.up()).isNormalCube()) 
		{
			UpAir = true;
		}
		if(IsFence(UpBlockRegistryName))
		{
			UpAir = false;
		}
		
		
		if(worldIn.getBlockState(pos.down()).getMaterial() == Material.AIR) //detect the down block is valid or not
		{
			DwAir = true;
		}
		else if(!worldIn.getBlockState(pos.down()).isNormalCube())  
		{
			DwAir = true;
		}
		if(IsFence(DownBlockRegistryName))
		{
			DwAir = false;
		}
		if (UpAir && DwAir) 
		{
			CanPlaceOrNot = false;
		}
		return CanPlaceOrNot;
    }
	
	private boolean IsFence(ResourceLocation name) //Detect Fence
	{
		ResourceLocation fences[] = 
			{
				Blocks.OAK_FENCE.getRegistryName(),
				Blocks.ACACIA_FENCE.getRegistryName(),
				Blocks.ACACIA_FENCE.getRegistryName(),
				Blocks.BIRCH_FENCE.getRegistryName(),
				Blocks.DARK_OAK_FENCE.getRegistryName(),
				Blocks.JUNGLE_FENCE.getRegistryName(),
				Blocks.SPRUCE_FENCE.getRegistryName(),
				Blocks.NETHER_BRICK_FENCE.getRegistryName(),
				Blocks.COBBLESTONE_WALL.getRegistryName()
			};
		
		for (ResourceLocation fence: fences)
		{
			if (name == fence)
				return true;
		}
		return false;
	}
	
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) //Stop fence connect to this block
    {
        return face != EnumFacing.UP && face != EnumFacing.DOWN ? BlockFaceShape.MIDDLE_POLE : BlockFaceShape.CENTER;
    }

	/**TileEntity part*/
	@Override
	public Class<TileEntityHang> getTileEntityClass() 
	{
		return TileEntityHang.class;
	}

	@Nullable
	@Override
	public TileEntityHang createTileEntity(World world, IBlockState state) 
	{
		return new TileEntityHang();
	}
}
