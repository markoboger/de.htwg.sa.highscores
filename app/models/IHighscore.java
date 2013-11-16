package models;

import com.fasterxml.jackson.databind.JsonNode;

public interface IHighscore {

	void setGame(String game);
	String getGame();
	
	void setPlayer(String player);
	String getPlayer();
	
	void setScore(long score);
	long getScore();
	
	void deserializeJson(JsonNode jsonRoot) throws HighscoreException;
}
