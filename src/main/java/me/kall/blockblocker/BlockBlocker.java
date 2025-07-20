package me.kall.blockblocker;

import me.kall.blockblocker.common.api.IBlock;
import me.kall.blockblocker.common.config.Config;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;

@Mod(BlockBlocker.MOD_ID)
public final class BlockBlocker {
    public static final String MOD_ID = "blockblocker";
    public static BlockState AIR = null;

    public BlockBlocker() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.INSTANCE);
        FMLJavaModLoadingContext.get().getModEventBus().addListener((FMLCommonSetupEvent event) -> {
            initConfig();
            AIR = Blocks.AIR.defaultBlockState();
        });
        MinecraftForge.EVENT_BUS.addListener(this::onLeftClick);
        MinecraftForge.EVENT_BUS.addListener(this::onRightClick);
    }

    public void onRightClick(PlayerInteractEvent.RightClickBlock event) {
        Level level = event.getWorld();
        BlockPos pos = event.getPos();
        BlockState state = level.getBlockState(pos);
        if (((IBlock)state.getBlock()).blockBlocker$isBlocked()) {
            level.setBlockAndUpdate(pos, AIR);
            event.setCanceled(true);
        }
    }

    public void onLeftClick(PlayerInteractEvent.LeftClickBlock event) {
        Level level = event.getWorld();
        BlockPos pos = event.getPos();
        BlockState state = level.getBlockState(pos);
        if (((IBlock)state.getBlock()).blockBlocker$isBlocked()) {
            level.setBlockAndUpdate(pos, AIR);
            event.setCanceled(true);
        }
    }

    private static void initConfig() {
        Config.BLOCKED_BLOCKS.get().forEach(name -> {
            Block block = ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse(name));
            if (block != null) ((IBlock)block).blockBlocker$setBlocked();
        });
    }
}
