package com.Tonyloser.lampmod.network;

import com.Tonyloser.lampmod.tileentity.TileEntityHang;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketRequestUpdateLantern implements IMessage {
	
	private BlockPos pos;
	private int dimension;
	
	
	/**The PacketRequestUpdateLantern is to get the right TileEntity from sever.
	 * 
	 * @param pos It mean where the block is.
	 * @param dimension It tell server which dimension this block is.
	 */
	public PacketRequestUpdateLantern(BlockPos pos, int dimension) {
		this.pos = pos;
		this.dimension = dimension;
	}
	
	public PacketRequestUpdateLantern(TileEntityHang te) {
		this(te.getPos(), te.getWorld().provider.getDimension());
	}
	
	public PacketRequestUpdateLantern() { //This is for initial.
	}
	
	@Override
	public void toBytes(ByteBuf buf) { //Transform the data into serial data.
		buf.writeLong(pos.toLong());
		buf.writeInt(dimension);
	}
	
	@Override
	public void fromBytes(ByteBuf buf) { //Transform it back.
		pos = BlockPos.fromLong(buf.readLong());
		dimension = buf.readInt();
	}
	
	/**	The IMessageHandlerpart part mean ( the packet will be send to server, packet will be send back from server)
	 * 	If te is null than return null.
	 */
	public static class Handler implements IMessageHandler<PacketRequestUpdateLantern, PacketUpdateLantern> 
	{
		@Override
		public PacketUpdateLantern onMessage(PacketRequestUpdateLantern message, MessageContext ctx) {
			World world = FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(message.dimension);
			TileEntityHang te = (TileEntityHang)world.getTileEntity(message.pos);
			if (te != null) {
				return new PacketUpdateLantern(te);
			} else {
				return null;
			}
		}
	}
}
