package sef.module7.activity;

import java.util.Calendar;

/**
 * Represents an implementation of the Currency interface.  The equality test for
 * this currency implementation requires that the value and the name of the denomination
 * must be true in order to be considered equal.
 * 
 * @author John Doe
 *
 */
public class CurrencyImpl implements Currency {
	
	private float value;
	private Denomination denomination;
	private Calendar createDate;


	/**
	 * Creates an instance of the of the Currency class with the specified
	 * value and denomination
	 * 
	 * @param value the value of the currency 
	 * @param denomination the denomination of the currency
	 */
	public CurrencyImpl(float value, Denomination denomination) {
		this.value = value;
		this.denomination = new DenominationImpl(denomination.getName(), denomination.getDescription(), denomination.getSymbol());
		this.createDate = Calendar.getInstance();
	}

	/* (non-Javadoc)
	 * @see sef.module6.activity.Currency#getValue()
	 */
	public float getValue() {
		return value;
	}

	/* (non-Javadoc)
	 * @see sef.module6.activity.Currency#getDenomination()
	 */
	public Denomination getDenomination() {
		return denomination;
	}

	/* (non-Javadoc)
	 * @see sef.module6.activity.Currency#getCreateDate()
	 */
	public Calendar getCreateDate() {
		return (Calendar) createDate.clone();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "";
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		if (!(o instanceof Currency))
		return false;
		Currency currency = (Currency) o;
		if (value!=currency.getValue())
			return false;
		if (!denomination.equals(currency.getDenomination()))
			return false;
		if (!createDate.equals(currency.getCreateDate()))
			return false;
		return true;
	}

}
