
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod("apathyutils")
public class ApathyUtilsMod {
    public ApathyUtilsMod() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.DEDICATED_SERVER)
    public static class EventHandler {
        @SubscribeEvent
        public static void onItemUse(PlayerInteractEvent.RightClickItem event) {
            if (!event.getSide().isServer()) return;

            ItemStack mainHand = event.getEntity().getMainHandItem();
            ItemStack offHand = event.getEntity().getOffhandItem();

            if (mainHand.isEdible() && !offHand.isEmpty()) {
                event.setCanceled(true);
                event.setCancellationResult(InteractionResult.FAIL);
            }
        }
    }
}