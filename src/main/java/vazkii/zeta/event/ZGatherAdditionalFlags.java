package vazkii.zeta.event;

import vazkii.quark.base.module.config.ConfigFlagManager;
import vazkii.zeta.event.bus.IZetaPlayEvent;

public record ZGatherAdditionalFlags(ConfigFlagManager flagManager) implements IZetaPlayEvent { }