package client;

import java.util.List;

import webService.BoardGame;
import webService.BoardGameWSService;
import webService.IBoardGameWS;

public class BoardGamesClient {
	
	static BoardGameWSService service = new BoardGameWSService();
	static IBoardGameWS boardGamesService = service.getBoardGameWSPort();
	
	public static List<BoardGame> getBoardGames()
	{
		
		return boardGamesService.readAll();
	}
	
	public static List<BoardGame> getBoardGamesByContainsName(String name)
	{
		
		return boardGamesService.findByName(name);
	}

	public static BoardGame findById(String id) {
		
		return boardGamesService.read(id);
	}
	
	public static boolean modify(BoardGame boardGame) {

		
		return boardGamesService.update(boardGame);
	}
	
	public static boolean create(BoardGame boardGame) {

		
		return boardGamesService.create(boardGame);
	}
	
	public static boolean delete(String id) {

		
		return boardGamesService.delete(id);
	}
}
