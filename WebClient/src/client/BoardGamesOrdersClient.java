package client;

import webService.BoardGameOrder;
import webService.BoardGameOrderWSService;
import webService.IBoardGameOrderWS;

public class BoardGamesOrdersClient {
	
	static BoardGameOrderWSService service = new BoardGameOrderWSService();
	static IBoardGameOrderWS boardgamesordersService = service.getBoardGameOrderWSPort();
	
	public static boolean create(BoardGameOrder bgo)
	{		
		return boardgamesordersService.createBoardGameOrder(bgo);
	}
	
	
}
