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
        return ok(index.render(scoreList));
    }
    
    public static Result addHighscore() {
    	System.out.println("REQUEST INCOMING");
    	IHighscore s = new Highscore();
    	try {
    		System.out.println(request().body().asJson());
			s.deserializeJson(request().body().asJson());
		} catch (HighscoreException e) {
			System.err.println(e);
    	      return badRequest(e.getMessage() + "\n" + e.getStackTrace());
		}
    	scoreList.add(s);
    	System.out.println("An entry was added: " + s.getGame() + " " + s.getPlayer() + " " + s.getScore());
    	return ok(index.render(scoreList));
    }
}
