package world.maryt.ZenToast;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import world.maryt.ZenToast.network.NetworkManager;
import world.maryt.zen_toast.Tags;

@Mod(modid = ZenToast.MOD_ID,version = ZenToast.VERSION, name = ZenToast.NAME)
public class ZenToast {
    public static final String MOD_ID = Tags.MOD_ID;
    public static final String NAME = "ZenToast";
    public static final String VERSION = Tags.VERSION;
    public static final Logger LOGGER = LogManager.getLogger(NAME);

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        NetworkManager.init();
    }
}
