package liteloader.qianniancc.rxyz;

import java.io.File;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mumfrey.liteloader.ServerCommandProvider;
import com.mumfrey.liteloader.modconfig.ConfigStrategy;
import com.mumfrey.liteloader.modconfig.ExposableOptions;

import net.minecraft.command.ServerCommandManager;

/**
 * @author qianniancc
 */

@ExposableOptions(strategy = ConfigStrategy.Versioned, filename = "RandomXYZ.json")
public class LiteModRxyz  implements ServerCommandProvider
{
    @Expose
    @SerializedName("x")
    private int x = 300;
    
    @Expose
    @SerializedName("z")
    private int z = 300;
	
	@Expose
    @SerializedName("msg")
	private String msg="\u968f\u673a\u4f20\u9001\u6210\u529f\u0021";
	
	@Expose
    @SerializedName("cmd")
	private String cmd="rxyz";
		
	private CommandRxyz tpr=new CommandRxyz();

	public LiteModRxyz()
	{
	}
	

	@Override
	public String getName()
	{
		return "Tpr Mod";
	}
	

	@Override
	public String getVersion()
	{
		return "0.0.1";
	}
	
	@Override
	public void init(File configPath)
	{
		
	}
	

	@Override
	public void provideCommands(ServerCommandManager commandManager) {
		tpr.setMsg(msg);
		tpr.setCmd(cmd);
		tpr.setXZ(x, z);
		commandManager.registerCommand(tpr);
	}

	@Override
	public void upgradeSettings(String version, File configPath, File oldConfigPath) {
		
		
	}


}
