package world.maryt.ZenToast.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MessageToast implements IMessage {

    public String titleJson;
    public String textJson;
    public ItemStack iconStack;
    public MessageToast() {}
    public MessageToast(String titleJson, String textJson, ItemStack iconStack) {
        this.titleJson = titleJson;
        this.textJson = textJson;
        this.iconStack = iconStack;
    }
    @Override
    public void fromBytes(ByteBuf buf) {
        this.titleJson = ByteBufUtils.readUTF8String(buf);
        this.textJson = ByteBufUtils.readUTF8String(buf);
        this.iconStack = ByteBufUtils.readItemStack(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, this.titleJson);
        ByteBufUtils.writeUTF8String(buf, this.textJson);
        ByteBufUtils.writeItemStack(buf, this.iconStack);
    }
}
