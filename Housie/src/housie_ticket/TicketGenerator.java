package housie_ticket;

import java.util.Random;

public class TicketGenerator {
	static int ticketCounter = 1;
	int noOfCols=9,noOfRows=3;
	public Ticket getTicket(){
		//create the instance of ticket to set the value of ticket id
		Ticket ticket = new Ticket();
		ticket.setTicketId( ticketCounter++ );

		Random rdNumber = new Random();

		int lastcolMax = -1;
		int colMax = rdNumber.nextInt( 3 );//set the size of columns

		for( int i=0; i<noOfCols; i++ ){
			while( lastcolMax == colMax ) {
				colMax = rdNumber.nextInt( 3 );

				lastcolMax = colMax;}

			for( int j=0; j<colMax; j++ ){
				int number = getRandomNumber( i, ticket );
				int row = rdNumber.nextInt(3);

				while( ticket.getNumber(row, i ) != -1 ) {
					row = rdNumber.nextInt(3);

					ticket.setNumber( row, i, number );}
			}			
		}
		if( ! isTicketFilled( ticket ) ) {
			fillTicket( ticket );
			adjustTicket( ticket );
			ticket.finalizeTicket();}
		return ticket;		
	}

	public void adjustTicket( Ticket ticket ){
		int emptyCols[] = ticket.getEmptyColumns();
		int fullCols[] = ticket.getFullColumns();
		if( emptyCols.length == 0 ) {
			return;}
		if( emptyCols.length <= fullCols.length ){
			for( int i=0; i<emptyCols.length; i++ ){
				int row = (new Random()).nextInt( 3 );
				int number = getRandomNumber( emptyCols[i], ticket );
				ticket.setNumber( row, fullCols[i], -1 );
				ticket.setNumber( row, emptyCols[i], number );
			}
		}
	}

	public void fillTicket( Ticket ticket ){
		Random rdNumber = new Random();
		int iterations1, iterations2;
		iterations1 = iterations2 = 0;
		for( int i=0; i<noOfRows; i++ ){
			int count;
			boolean flag = false;
			if( ticket.getRowCount(i) >= 6 ){
				while( ticket.getRowCount(i) != 5 ){
					int col = rdNumber.nextInt( 9 );
					if( ticket.getNumber( i, col ) != -1 )
						ticket.setNumber( i, col, -1 );
				}
				continue;
			}
			while( (count=ticket.getRowCount( i )) <= 4){ //to print the 5 numbers
				int col = rdNumber.nextInt( 9 );
				while( ticket.getNumber( i, col ) != -1 ){
					iterations2++;
					col = rdNumber.nextInt( 9 );
					if( iterations2 >= 100 ){
						//System.out.println( iterations2 + ". Here i m stuck at Fill Ticket, dont know why col = " + col );
						flag = true;
						break;
					}
				}
				if( flag ){
					col = -1;
					for( int col1=0; col<noOfCols; col1++ )
						if( ticket.getNumber( i, col1 ) == -1 ){
							col = col1;
							break;
						}
					if (col == -1)
					{
						i++;
						continue;
					}
				}
				int number = getRandomNumber( col, ticket );
				ticket.setNumber( i, col, number );
				iterations1++;
				if( iterations1 == 50 ){}
			}
		}
	}

	public int getRandomNumber( int col, Ticket ticket ){
		int number = 0;
		Random rdNumber = new Random();
		int iterations = 0;
		do{
			number = rdNumber.nextInt( (col*10)+11 );
			iterations++;
			if( iterations >= 50 ){}

		}while( (number <= col*10 || number >= (col*10)+11) || !(ticket.isNumberValid( col, number )) );
		return number;
	}

	boolean isTicketFilled( Ticket ticket ){
		boolean ticketFilled = true;
		for( int row=0; row<noOfRows; row++ ){
			int count = ticket.getRowCount( row );
			if( count == 5 ) {
				continue;}
			else {
				return false;}
		}
		return ticketFilled;
	}


}


