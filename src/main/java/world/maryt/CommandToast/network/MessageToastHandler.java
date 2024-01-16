package world.maryt.CommandToast.network;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.toasts.IToast;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import world.maryt.CommandToast.CommandToastMod;
import world.maryt.CommandToast.toast.SimpleToast;

public class MessageToastHandler implements IMessageHandler<MessageToast, IMessage> {
    @Override
    public IMessage onMessage(MessageToast message, MessageContext ctx) {
        Minecraft.getMinecraft().addScheduledTask(() -> {
            IToast toast = new SimpleToast(message.titleJson, message.textJson, message.iconStack);
            CommandToastMod.LOGGER.debug("about to toast");
            Minecraft.getMinecraft().getToastGui().add(toast);
        });
        return null;
    }
}