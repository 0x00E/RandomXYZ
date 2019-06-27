package liteloader.qianniancc.rxyz;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.material.Material;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.chunk.Chunk;



public class CommandRxyz extends CommandBase
{
	//CommandStop
	Random r=new Random();
	ITextComponent msgText;
	ITextComponent seaText=(new TextComponentString("\u003d\u0077\u003d\u6c34\u597d\u6df1\u554a\uff0c\u68c0\u6d4b\u5230\u6eba\u6c34\u7684\u98ce\u9669\uff0c\u5efa\u8bae\u91cd\u65b0\u4f20\u9001\uff01"));
	Style s=new Style();
	String cmd;
	int x;
	int z;
	
	public void setMsg(String msg){
		s.setBold(true);
		msgText=(new TextComponentString(msg));
		msgText.setStyle(s);
		seaText.setStyle(s);
	}
	
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
    public String getName()
    {
        return cmd;
    }
    public void setXZ(int x,int z){
    	this.x=x;
    	this.z=z;
    }


    public int getRequiredPermissionLevel()
    {
        return 2;
    }


    public String getUsage(ICommandSender sender)
    {
        return "commands.tpr.usage";
    }
    


    
    public BlockPos getTopSolidOrLiquidBlock(BlockPos pos,Chunk c)
    {
        Chunk chunk = c;
        BlockPos blockpos;
        BlockPos blockpos1;

        for (blockpos = new BlockPos(pos.getX(), chunk.getTopFilledSegment() + 16, pos.getZ()); blockpos.getY() >= 0; blockpos = blockpos1)
        {
            blockpos1 = blockpos.down();
            Material material = chunk.getBlockState(blockpos1).getMaterial();

            if (material.blocksMovement() || material.isLiquid())
            {
            	return blockpos;
            }
        }

        return blockpos;
    }
    
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {
    	
		int oldX=sender.getPosition().getX();
    	int oldZ=sender.getPosition().getZ();
    	int newX=oldX+r.nextInt(x);
    	int newZ=oldZ+r.nextInt(z);
    	BlockPos bp=new BlockPos(newX, 0, newZ);
    	Chunk c=sender.getEntityWorld().getChunkFromBlockCoords(bp);
    	int newY=this.getTopSolidOrLiquidBlock(bp,c).getY()+1;
    	bp=new BlockPos(newX, newY-3, newZ);
		((EntityPlayerMP)sender).connection.setPlayerLocation(
				newX, 
				newY, 
				newZ,
				0,
				0
		);
		
		if(sender.getEntityWorld().getBlockState(bp).getMaterial().isLiquid()){
			sender.sendMessage(seaText);
		}
		sender.sendMessage(msgText);
    }

    public boolean isUsernameIndex(String[] args, int index)
    {
        return index == 0;
    }
    
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos)
    {
        return args.length == 1 ? getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames()) : Collections.<String>emptyList();
    }
}