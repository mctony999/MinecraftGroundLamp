package com.Tonyloser.lampmod.network;

import com.Tonyloser.lampmod.tileentity.TileEntityHang;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketUpdateLantern implements IMessage{
	
	private BlockPos pos;
	private int TileEntityHang;
	
	/**The PacketUpdateLantern is to give client the block current tile Entity.
	 * 
	 * @param pos *It mean the block position.
	 * @param TileEntityHang *It mean the TileEntity belong to this block
	 */
	public PacketUpdateLantern (BlockPos pos, int TileEntityHang)
	{
		this.pos = pos;
		this.TileEntityHang = TileEntityHang;
	}
	
	public PacketUpdateLantern(TileEntityHang te) 
	{
		this(te.getPos(), te.getTileEntityHang());
	}
	
	public PacketUpdateLantern()  //This is for initial.
	{
	}

	@Override
	public void toBytes(ByteBuf buf) { //Transform the data into serial data.
		buf.writeLong(pos.toLong());
		buf.writeInt(TileEntityHang);
	}

	@Override
	public void fromBytes(ByteBuf buf) { //Transform it back.
		pos = BlockPos.fromLong(buf.readLong());
		TileEntityHang = buf.readInt();
	}
	
	/** The IMessageHandlerpart part mean ( the packet will be send to client, message will be send back from client)
	 *  If te is null than return null.
	 */
	public static class Handler implements IMessageHandler<PacketUpdateLantern, IMessage>
	{
		@Override
		public IMessage onMessage(PacketUpdateLantern message, MessageContext ctx) {
			Minecraft.getMinecraft().addScheduledTask(() -> {
				TileEntityHang te = (TileEntityHang)Minecraft.getMinecraft().world.getTileEntity(message.pos);
				te.TileEntityHang = message.TileEntityHang;
			});
			return null;
		}
	}
	

}
