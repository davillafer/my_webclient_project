package client;

import webService.IOrderWS;
import webService.Order;
import webService.OrderWSService;

public class OrdersClient {
	
	static OrderWSService service = new OrderWSService();
	static IOrderWS ordersService = service.getOrderWSPort();
	
	public static boolean create(Order order)
	{		
		return ordersService.createOrder(order);
	}
	
	public static int orderId() {
		return ordersService.orderId();
	}
	
}
