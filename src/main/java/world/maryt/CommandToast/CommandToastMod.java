package world.maryt.CommandToast;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import world.maryt.CommandToast.network.NetworkManager;
import world.maryt.zen_toast.Tags;

@Mod(modid = CommandToastMod.MOD_ID,version = CommandToastMod.VERSION, name = CommandToastMod.NAME)
public class CommandToastMod {
    public static final String MOD_ID = Tags.MOD_ID;
    public static final String NAME = "ZenToast";
    public static final String VERSION = Tags.VERSION;
    public static final Logger LOGGER = LogManager.getLogger(NAME);

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        NetworkManager.init();
    }
}
