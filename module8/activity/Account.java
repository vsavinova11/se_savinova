package sef.module8.activity;


/**
 * Thsi class represents a simple representation of an account encapsulating
 * a name 
 * 
 * @author John Doe
 *
 */
public class Account {



	/**
	 * Creates an Account object with the specified name.  If the accout name
	 * given violates the minimum requirements, then an AccountException is thrown
	 * 
	 * @param accountName
	 * @throws AccountException
	 */
	private String accountName;

	public  Account(String accountName) throws AccountException{
		this.accountName = accountName;
		if (accountName.length()<4)
			throw new AccountException(AccountException.NAME_TOO_SHORT, accountName);
		if (accountName.matches("[a-zA-Z]\\d"))
			throw new AccountException(AccountException.NAME_TOO_SIMPLE, accountName);
	}
	
	
	/**
	 * Returns the account name
	 * 
	 * @return the account name
	 */
	public String getName(){
		return accountName;
	}
}
