package controllers;

import java.util.ArrayList;
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
    	String[] games = {"Pixelwars", "CoD", "VierGewinnt", "CoD", "VierGewinnt"};
    	String[] player = {"Ich", "Du", "Er", "Sie", "Es"};
    	Long[] score = {1L, 0L, 500L, 100L, 1000L};
    	
    	for(int i = 0; i < games.length; i++) {
    		IHighscore s = new Highscore();
    		s.setGame(games[i]);
    		s.setPlayer(player[i]);
    		s.setScore(score[i]);
    		scoreList.add(s);
    	}
    	
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
    	scoreList.add(s);
    	return ok(index.render(scoreList));
    }
}
