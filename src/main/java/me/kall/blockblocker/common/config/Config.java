package me.kall.blockblocker.common.config;

import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.List;

public class Config {
    public static final ForgeConfigSpec INSTANCE;
    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> BLOCKED_BLOCKS;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.push("BlockBlocker");
        BLOCKED_BLOCKS = builder.defineList("BlockedBlocks", Lists.newArrayList(), Predicates.alwaysTrue());
        builder.pop();
        INSTANCE = builder.build();
    }
}
