package Test;

import housie_ticket.TicketGenerator;

public class Main {

	public static void main(String[] args) {

		//generate the instance of TicketGenerator to call the method
		TicketGenerator ticket = new TicketGenerator();
		System.out.println(ticket.getTicket()); //call method to print the ticket
		//System.out.println(ticket.getTicket());

	}
}
