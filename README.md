## ZenToast

Very simple CraftTweaker addon. It currently has only one simple feature:

Add a ZenMethod `sendToast()` for `IPlayer`:

```ZenScript
// String parameters should be in Raw JSON text format -> https://minecraft.wiki/w/Raw_JSON_text_format
// Don't forget to escape quotes (" -> \") in your string 
player.sendToast(titleJson as string, textJson as string, icon as IItemStack);

// Or simply use IData to represent Raw JSON text format instead (easier to coding, recommended)
player.sendToast(titleJson as IData, textJson as IData, icon as IItemStack);
```

### Example
 
ZenScript below will make a toast like this, on the upper-right corner

![](https://i.imgur.com/jtBzSiF.png)

```ZenScript
import crafttweaker.data.IData;

// any place you can get IPlayer object, events or something else 
// Use the second ZenMethod
// The third IItemStack will become the icon of the toast. We use apple here.
player.sendToast({"text": 111111, "color": "gold"} as IData, {"text": "Hello world"} as IData, <minecraft:apple>);
```

## License and Credits

This mod is built upon the foundation of the `1.12.2` branch of [Ecdcaeb/Command-Toast](https://github.com/Ecdcaeb/Command-Toast/tree/1.12.2).

Great thanks to the original mod author!

License of this repo will keep the same as [its upstream's license](https://github.com/Ecdcaeb/Command-Toast/blob/1.12.2/LICENSE).

(Chinese-localized README below)
________________

## 魔改面包机

一个拥有如下功能的简单 CraftTweaker 附属：

向 `IPlayer` 类型添加了 `sendToast()` 方法，用于向玩家发送右上角弹出的成就消息。

```ZenScript
// String 类型的参数必须按照原始 JSON 文本格式
// 不要忘记转义其中的引号 (" -> \")  
player.sendToast(titleJson as string, textJson as string, icon as IItemStack);

// 或者你可以直接用 IData 来表示原始 JSON 文本格式 (推荐)
player.sendToast(titleJson as IData, textJson as IData, icon as IItemStack);
```

原始 JSON 文本格式的具体写法参考 -> [Minecraft 中文 Wiki/原始 JSON 文本格式](https://zh.minecraft.wiki/w/%E6%95%99%E7%A8%8B/%E5%8E%9F%E5%A7%8BJSON%E6%96%87%E6%9C%AC)

### 示例

下面的脚本会在触发时发送下图所示的成就信息，从玩家的游戏画面右上角弹出。

![](https://i.imgur.com/jtBzSiF.png)

```ZenScript
import crafttweaker.data.IData;

// 假设这段脚本置于任何可以拿到 IPlayer 对象的地方，比如事件或者别的什么地方。 
// 我们使用第二种 ZenMethod 来写
// 第三个 IItemStack 参数就是成就信息的图标，这里我们用的是苹果物品。
player.sendToast({"text": 111111, "color": "gold"} as IData, {"text": "Hello world"} as IData, <minecraft:apple>);
```

## 开源许可 & 致谢

本模组的源代码是在 [Ecdcaeb/Command-Toast](https://github.com/Ecdcaeb/Command-Toast/tree/1.12.2) 项目的 `1.12.2` 分支基础上编写的。

向原模组作者致以衷心感谢！

本仓库的开源许可证将与 [上游项目的开源许可](https://github.com/Ecdcaeb/Command-Toast/blob/1.12.2/LICENSE) 保持一致。
