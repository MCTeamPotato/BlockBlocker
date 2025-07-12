package me.kall.blockblocker.common.mixin;

import me.kall.blockblocker.BlockBlocker;
import me.kall.blockblocker.common.api.IBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunkSection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(LevelChunkSection.class)
public abstract class LevelChunkSectionMixin {
    @ModifyVariable(method = "setBlockState(IIILnet/minecraft/world/level/block/state/BlockState;Z)Lnet/minecraft/world/level/block/state/BlockState;", at = @At("HEAD"), argsOnly = true)
    private BlockState onSetBlock(BlockState state) {
        if (((IBlock)state.getBlock()).blockblocker$isBlocked()) state = BlockBlocker.AIR;
        return state;
    }
}
