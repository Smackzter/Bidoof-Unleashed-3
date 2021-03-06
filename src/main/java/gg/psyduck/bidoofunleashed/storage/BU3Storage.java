package gg.psyduck.bidoofunleashed.storage;

import com.google.common.collect.Multimap;
import com.nickimpact.impactor.api.storage.Storage;
import gg.psyduck.bidoofunleashed.api.battlables.Category;
import gg.psyduck.bidoofunleashed.e4.EliteFour;
import gg.psyduck.bidoofunleashed.gyms.Gym;
import gg.psyduck.bidoofunleashed.players.PlayerData;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface BU3Storage extends Storage {

	CompletableFuture<Void> addPlayerData(PlayerData data);

	CompletableFuture<Void> updatePlayerData(PlayerData data);

	/**
	 * Attempts to locate the {@link PlayerData} based on the specified UUID. If the data does not exist, this method
	 * will return an empty Optional. Otherwise, the data will be wrapped inside an Optional holder.
	 *
	 * @param uuid The uuid of the player
	 * @return A {@link CompletableFuture}, wrapped with an Optional value pertaining to
	 * the {@link PlayerData} of the user.
	 */
	CompletableFuture<Optional<PlayerData>> getPlayerData(UUID uuid);

	CompletableFuture<Void> addGym(Gym gym);

	CompletableFuture<Void> updateGym(Gym gym);

	/**
	 * Fetches all saved gyms, and returns them as a List for easy operation. We realistically should have all of them
	 * preserved in memory rather than just loading player data as it comes in. Having all gyms in memory allows us
	 * to easily reference them at any time.
	 *
	 * @return A {@link CompletableFuture}, composed of a List of gyms upon successful completion
	 */
	CompletableFuture<Multimap<Category, Gym>> fetchGyms();

    /**
     * Deletes the specified gym from the registered storage. If the gym has already been deleted,
     * this will do nothing.
     *
     * @param gym The gym to be deleted
     * @return A {@link CompletableFuture} with a void value
     */

	CompletableFuture<Void> removeGym(Gym gym);

	CompletableFuture<Void> addE4(EliteFour e4);

	CompletableFuture<Void> updateE4(EliteFour e4);

	CompletableFuture<Map<Category, EliteFour>> fetchE4();

	CompletableFuture<Void> removeE4(EliteFour e4);
}
