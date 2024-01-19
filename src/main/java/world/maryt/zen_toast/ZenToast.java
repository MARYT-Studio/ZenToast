package world.maryt.zen_toast;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import world.maryt.zen_toast.network.NetworkManager;

@Mod(modid = ZenToast.MOD_ID,version = ZenToast.VERSION, name = ZenToast.NAME)
public class ZenToast {
    public static final String MOD_ID = Tags.MOD_ID;
    public static final String NAME = "zen_toast";
    public static final String VERSION = Tags.VERSION;
    public static final Logger LOGGER = LogManager.getLogger(NAME);

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        NetworkManager.init();
    }
}
