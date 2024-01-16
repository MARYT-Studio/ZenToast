package world.maryt.ZenToast.network;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkManager {
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("zen_toast");
    public static void init() {
        INSTANCE.registerMessage(MessageToastHandler.class, MessageToast.class, 1, Side.CLIENT);
    }
}
