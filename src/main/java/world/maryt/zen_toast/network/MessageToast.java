package world.maryt.zen_toast.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MessageToast implements IMessage {

    public String title;
    public String titleArgs;
    public String text;
    public String textArgs;
    public ItemStack iconStack;
    public MessageToast() {}
    public MessageToast(String title, String titleArgs, String text, String textArgs, ItemStack iconStack) {
        this.title = title;
        this.titleArgs = titleArgs;
        this.text = text;
        this.textArgs = textArgs;
        this.iconStack = iconStack;
    }
    @Override
    public void fromBytes(ByteBuf buf) {
        this.title = ByteBufUtils.readUTF8String(buf);
        this.titleArgs = ByteBufUtils.readUTF8String(buf);
        this.text = ByteBufUtils.readUTF8String(buf);
        this.textArgs = ByteBufUtils.readUTF8String(buf);
        this.iconStack = ByteBufUtils.readItemStack(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, this.title);
        ByteBufUtils.writeUTF8String(buf, this.titleArgs);
        ByteBufUtils.writeUTF8String(buf, this.text);
        ByteBufUtils.writeUTF8String(buf, this.textArgs);
        ByteBufUtils.writeItemStack(buf, this.iconStack);
    }
}
