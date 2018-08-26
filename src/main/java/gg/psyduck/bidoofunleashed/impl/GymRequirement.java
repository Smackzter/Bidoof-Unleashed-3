package gg.psyduck.bidoofunleashed.impl;

import com.google.common.collect.Maps;
import com.nickimpact.impactor.json.Typing;
import gg.psyduck.bidoofunleashed.BidoofUnleashed;
import gg.psyduck.bidoofunleashed.api.gyms.Requirement;
import gg.psyduck.bidoofunleashed.config.MsgConfigKeys;
import gg.psyduck.bidoofunleashed.gyms.Gym;
import gg.psyduck.bidoofunleashed.utils.MessageUtils;
import lombok.RequiredArgsConstructor;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Typing("gym")
@RequiredArgsConstructor
public class GymRequirement implements Requirement {

	private final String type = "gym";
	private final String badgeRequirment;

	@Override
	public boolean passes(Gym gym, Player player) {
		return BidoofUnleashed.getInstance().getDataRegistry().getPlayerData(player.getUniqueId()).getBadges().stream().anyMatch(badge -> badge.getName().equalsIgnoreCase(badgeRequirment));
	}

	@Override
	public void onInvalid(Gym gym, Player player) {
		Map<String, Function<CommandSource, Optional<Text>>> tokens = Maps.newHashMap();
		tokens.put("bu3_badge", s -> Optional.of(Text.of(badgeRequirment)));
		Text parsed = MessageUtils.fetchAndParseMsg(player, MsgConfigKeys.REQUIREMENT_GYM, tokens, null);
		player.sendMessage(parsed);
	}
}
