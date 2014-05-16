package es.unileon.ulebank.assets.handler;

import java.util.Locale;

import es.unileon.ulebank.handler.MalformedHandlerException;

public class GenericLoanHandler implements Handler {
	
	private String codeCheckDigit;
	
	private String id;
	
	public GenericLoanHandler(final String id) throws MalformedHandlerException {
		
		String[] loanID = id.split("-");
		
		if(loanID.length == 6) {
			if(loanID[0].length() > 2)
				throw new MalformedHandlerException("Type length  is too long");
			
			if(loanID[0].equals("LN") || loanID[0].equals("MG")){
				try{
					int month = Integer.parseInt(String.valueOf(loanID[1]));
					if(month < 0 || month > 13)
						throw new MalformedHandlerException("The month is not valid");
					int year = Integer.parseInt(String.valueOf(loanID[2]));
				}catch(NumberFormatException ex){
					throw new MalformedHandlerException("The month or year is not valid");
				}
				
				boolean countryCodeExist = false;
				String[] countries = Locale.getISOCountries();
				for (String country : countries) {
					if (country.equals(loanID[3])) {
						countryCodeExist = true;
					}
				}
				
				if(!countryCodeExist){
					throw new MalformedHandlerException("Country code not exist.");
				}
				
				if(!(loanID[4].length() == 5)){
					throw new MalformedHandlerException("The length of the random characters must be 5.");
				}
				
				try{
					int checkDigit = Integer.parseInt(String.valueOf(loanID[5]));
					
					this.codeCheckDigit = loanID[0] + loanID[1] + loanID[2] +
												loanID[3] +  loanID[4];
					
					if(!(checkDigit == doCheckDigit(this.codeCheckDigit))){
						throw new MalformedHandlerException("The check digit is not valid.");
					}
					
				}catch(NumberFormatException ex){
					throw new MalformedHandlerException("The check digit is not valid, must be a number between 0 and 9.");
				}
				
				
				
			}else{
				throw new MalformedHandlerException("type must be LN or MG");
			}
		}else{
			throw new MalformedHandlerException("wrong number of fields");
		}
		
		
		this.id = id;
	}
	
	private int doCheckDigit(String code) {
		char[] characters = code.toCharArray();
		int sum = 0;
		
		for(char character : characters){
			try{
				int num = Integer.parseInt(String.valueOf(character));			
				sum = sum + num;
			}catch(NumberFormatException ex){
				int num = Character.valueOf(character);
				sum = sum + num;
			}
		}
		
		return sum%9;
	}
	
	@Override
	public int compareTo(Handler another) {
		return this.id.compareTo(another.toString());
	}
	
	@Override
	public String toString() {
		return this.id;
	}
	
	
}
