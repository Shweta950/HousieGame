package housie_ticket;

public class Ticket {
	//declare the variables
	private int noOfRows=3, noOfCols=9, ticketId;
	private String[][] numbers;

	//declare the constructor to display rows and cols 
	public Ticket() {
		ticketId = -1;
		numbers = new String[noOfRows][noOfCols];

		for( int i=0; i<noOfRows; i++ ) {
			for( int j=0; j<noOfCols; j++ ) {
				numbers[i][j] = null;
			}
		}
	}


	public int getNoOfRows() {
		return noOfRows;
	}


	public void setNoOfRows(int noOfRows) {
		this.noOfRows = noOfRows;
	}


	public int getNoOfCols() {
		return noOfCols;
	}


	public void setNoOfCols(int noOfCols) {
		this.noOfCols = noOfCols;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	// to fill the values of rows and columns according to condition
	public void setNumber( int row, int col, int number ){
		if( number == -1 ) {
			numbers[row][col] = null;
		}
		else {
			numbers[row][col] = "" + number;
		}
	}

	public int getTicketId() {
		return ticketId;
	}

	public String[][] getNumbers() {
		return numbers;
	}

	//set the numbers according to length of rows and columns
	public void setNumbers(String[][] numbers) {
		if(numbers.length==3 && numbers.length==9) {
			this.numbers = numbers;
		}	
	}

	//get the numbers of rows and columns
	public int getNumber( int row, int col ){
		if( numbers[row][col] != null ) {
			return Integer.parseInt( numbers[row][col] );}
		return -1;
	}

	//get the no of rows
	public String[] getRowNumbers( int row ){
		if( row < 0 || row >=3 ) {
			return null;}
		return numbers[row];
	}

	//get no of columns
	public String[] getColumnNumbers( int col ){
		if( col < 0 || col >= 9 ) {
			return null;}
		String cols[] = new String[3];
		cols[0] = numbers[0][col];
		cols[1] = numbers[1][col];
		cols[2] = numbers[2][col];

		return cols;
	}

	public int getRowCount( int row ){
		String[] rowNumbers = getRowNumbers( row );
		int count = 0;
		for( int j=0; j<rowNumbers.length; j++ ) {
			if( rowNumbers[j] != null ) {
				count++;}
		}
		return count;
	}

	@Override
	/*
	 * public String toString() { return "Ticket [noOfRows=" + noOfRows +
	 * ", noOfCols=" + noOfCols + ", ticketId=" + ticketId + ", numbers=" +
	 * Arrays.toString(numbers) + "]"; }
	 */
	public String toString() {
		String result = " --- --- --- --- --- --- --- --- ---\n";
		result += " Ticket ID: " + this.ticketId + "\n\n";
		result += " --- --- --- --- --- --- --- --- ---\n";

		for( int i=0; i<noOfRows; i++ ){
			for( int j=0; j<noOfCols; j++ ){

				//to print empty spaces
				if( numbers[i][j] == null ) { 
					result += "|   ";}
				else if( numbers[i][j].length() == 1 ) {
					result += "|  " + numbers[i][j];} 
				else {
					result += "| " + numbers[i][j];}
			}
			result += "|\n --- --- --- --- --- --- --- --- ---\n";
		}
		return result;
	}

	//check whether the entered number is valid or not
	public boolean isNumberValid( int col, int number ){
		for( int i=0; i<noOfRows; i++ ) {
			if( numbers[i][col] == null ) {
				continue;}
			else if( numbers[i][col].equals( "" + number ) ) { // to avoid the repeated numbers
				return false;}
		}
		return true;

	}

	public void finalizeTicket(){
		for( int i=0; i<noOfCols; i++ ){
			String[] cols = getColumnNumbers( i );
			for( int m=0; m<cols.length-1; m++ ){
				for( int n=m+1; n<cols.length; n++ ){
					if( cols[m] == null || cols[n] == null ){
						continue;
					}
					else{
						if( Integer.parseInt( cols[m] ) > Integer.parseInt( cols[n] ) ){
							String temp = cols[m];
							setNumber( m, i, Integer.parseInt( cols[n] ) );
							setNumber( n, i, Integer.parseInt( cols[m] ) );
							cols[m] = cols[n];
							cols[n] = temp;
						}
					}
				}
			}
		}
	}

	//create a method to get empty columns values
	public int[] getEmptyColumns(){
		int emptyCols[] = new int[9];
		int index = 0;
		for( int i=0; i<noOfCols; i++ ){
			String cols[] = getColumnNumbers( i );
			if( cols[0] == null && cols[1] == null && cols[2] == null ) {
				emptyCols[index++] = i;
			}
		}
		int res[] = new int[index];
		for( int k = 0; k<index; k++ ) {
			res[k] = emptyCols[k];
		}
		return res;
	}

	//create a method to get filled columns
	public int[] getFullColumns(){
		int fullCols[] = new int[9];
		int inDex = 0;
		for( int i=0; i<9; i++ ){
			String cols[] = getColumnNumbers( i );
			if( cols[0] != null && cols[1] != null && cols[2] != null ) {
				fullCols[inDex++] = i;
			}
		}
		int res[] = new int[inDex];
		for( int k = 0; k<inDex; k++ ) {
			res[k] = fullCols[k];}
		return res;
	}

}

