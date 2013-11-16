package models;

import com.fasterxml.jackson.databind.JsonNode;

public class Highscore implements IHighscore {

	String player;
	String game;
	long score;

	@Override
	public void setGame(String game) {
		this.game = game;
	}

	@Override
	public String getGame() {
		return game;
	}

	@Override
	public void setPlayer(String player) {
		this.player = player;
	}

	@Override
	public String getPlayer() {
		return player;
	}

	@Override
	public void setScore(long score) {
		this.score = score;
	}

	@Override
	public long getScore() {
		return score;
	}

	@Override
	public void deserializeJson(JsonNode jsonRoot) throws HighscoreException {
		if(jsonRoot == null) {
			throw new HighscoreException("Expecting JSON data.");
		}
		
		String game = jsonRoot.findPath("game").textValue();
		String player = jsonRoot.findPath("player").textValue();
		Long score = jsonRoot.findPath("score").asLong();

		if (player == null || game == null || score == null) {
			throw new HighscoreException(
					"Some parameters cannot be parsed. Please ensure that"
							+ "all parameters are given in request (game, player, score)");
		}

		this.game = game;
		this.player = player;
		this.score = score;
	}
}
