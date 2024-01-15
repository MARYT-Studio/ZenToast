package world.maryt.CommandToast;

import world.maryt.CommandToast.command.CommandToast;
import world.maryt.CommandToast.command.ToastType;
import world.maryt.CommandToast.toast.SimpleToast;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import world.maryt.zen_toast.Tags;

/**
 * @Project CommandToast
 * @Author Hileb
 * @Date 2023/12/30 20:01
 **/
@Mod(modid = CommandToastMod.MOD_ID,version = CommandToastMod.VERSION, name = CommandToastMod.NAME)
public class CommandToastMod {
    public static final String MOD_ID = Tags.MOD_ID;
    public static final String NAME = "ZenToast";
    public static final String VERSION = Tags.VERSION;
    public static final Logger LOGGER= LogManager.getLogger(NAME);
    @Mod.Instance
    public static CommandToastMod INSTANCE;

    @Mod.EventHandler
    public void onServerStarting(FMLServerStartingEvent event){
        event.registerServerCommand(new CommandToast());
    }

    @Mod.EventBusSubscriber
    public static class ForgeEventHandler{
        @SubscribeEvent
        public static void onRegister(ToastType.RegisterEvent event){
            event.register("simple",new SimpleToast.Factory());
        }
    }

}
