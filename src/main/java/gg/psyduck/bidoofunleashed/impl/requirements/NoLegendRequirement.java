package gg.psyduck.bidoofunleashed.impl.requirements;

import com.nickimpact.impactor.json.Typing;
import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.enums.EnumSpecies;
import com.pixelmonmod.pixelmon.storage.PlayerPartyStorage;
import gg.psyduck.bidoofunleashed.api.battlables.BU3Battlable;
import gg.psyduck.bidoofunleashed.api.gyms.Requirement;
import gg.psyduck.bidoofunleashed.config.MsgConfigKeys;
import gg.psyduck.bidoofunleashed.utils.MessageUtils;
import org.spongepowered.api.entity.living.player.Player;

@Typing("no-legend")
public class NoLegendRequirement implements Requirement {

	private final String type = "no-legend";

	@Override
	public String id() {
		return this.type;
	}

	@Override
	public boolean passes(BU3Battlable battlable, Player player) throws Exception {
		PlayerPartyStorage storage = Pixelmon.storageManager.getParty(player.getUniqueId());
		return storage.getTeam().stream().noneMatch(pokemon -> pokemon == null || EnumSpecies.legendaries.contains(pokemon.getSpecies().getPokemonName()));
	}

	@Override
	public void onInvalid(BU3Battlable battlable, Player player) {
		player.sendMessage(MessageUtils.fetchAndParseMsg(player, MsgConfigKeys.REQUIREMENT_LEGENDS, null, null));
	}

	@Override
	public Requirement supply(String... args) throws Exception {
		return this;
	}
}
