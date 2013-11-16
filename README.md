de.htwg.sa.highscores
=====================

RESTful highscore server:

Requests:

GET  -> show page
POST -> Add new highscore entry
		- JSON BODY:
			{
				"game": "MyGame",
				"player": "MyPlayer",
				"score": "56789"
			}
