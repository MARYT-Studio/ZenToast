package world.maryt.CommandToast.network;

import world.maryt.CommandToast.CommandToastMod;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

/**
 * @Project CommandToast
 * @Author Hileb
 * @Date 2023/12/30 20:36
 **/
public class NetworkHandler {
    public static final SimpleNetworkWrapper CHANNEL= NetworkRegistry.INSTANCE.newSimpleChannel(CommandToastMod.MOD_ID);
    static {
        CHANNEL.registerMessage(S2CToastPack.Handler.class,S2CToastPack.class,1, Side.CLIENT);
    }
}
