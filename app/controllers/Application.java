package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import models.Highscore;
import models.HighscoreException;
import models.IHighscore;

import com.fasterxml.jackson.databind.JsonNode;

import play.*;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {

	private static List<IHighscore> scoreList = new ArrayList<IHighscore>();

	public static Result index() {
//		String[] games = { "Pixelwars", "CoD", "VierGewinnt", "CoD",
//				"VierGewinnt" };
//		String[] player = { "Ich", "Du", "Er", "Sie", "Es" };
//		Long[] score = { 1L, 0L, 500L, 100L, 1000L };
//
//		for (int i = 0; i < games.length; i++) {
//			IHighscore s = new Highscore();
//			s.setGame(games[i]);
//			s.setPlayer(player[i]);
//			s.setScore(score[i]);
//			scoreList.add(s);
//		}
//		sortList();

		return ok(index.render(scoreList));
	}

	public static Result addHighscore() {
		IHighscore s = new Highscore();
		try {
			System.out.println(request().body().asJson());
			s.deserializeJson(request().body().asJson());
		} catch (HighscoreException e) {
			return badRequest(e.getMessage() + "\n" + e.getStackTrace());
		}

//		for (int i = 0; i < scoreList.size(); i++) {
//			if (scoreList.get(i).getScore() < s.getScore()) {
				scoreList.add(i, s);
//				break;
//			}
//		}
		sortList();
		return ok(index.render(scoreList));
	}

	public static Result addHighscore(String game, String player, Long score) {
		IHighscore s = new Highscore();
		s.setGame(game);
		s.setPlayer(player);
		s.setScore(score);
		scoreList.add(s);
		sortList();
		return ok(index.render(scoreList));
	}
	
	private static void sortList() {
		Comparator<IHighscore> c = new Comparator<IHighscore>() {

			@Override
			public int compare(IHighscore arg0, IHighscore arg1) {
				long s1 = arg0.getScore();
				long s2 = arg1.getScore();
				
				if(s1 == s2) {
					return 0;
				} else if (s1 < s2) {
					return 1;
				} else {
					return -1;
				}
			}
			
		};
		Collections.sort(scoreList, c);
	}
}
