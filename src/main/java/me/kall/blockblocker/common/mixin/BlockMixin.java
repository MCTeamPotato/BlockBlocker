package me.kall.blockblocker.common.mixin;

import me.kall.blockblocker.common.api.IBlock;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(Block.class)
public class BlockMixin implements IBlock {
    @Unique
    private boolean blockblocker$blocked;

    @Override
    public boolean blockblocker$isBlocked() {
        return this.blockblocker$blocked;
    }

    @Override
    public void blockblocker$setBlocked() {
        this.blockblocker$blocked = true;
    }
}
