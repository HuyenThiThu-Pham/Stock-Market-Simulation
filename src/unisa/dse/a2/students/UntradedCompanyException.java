package unisa.dse.a2.students;

public class UntradedCompanyException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UntradedCompanyException(String companyCode)
	{
		/**
		 * The UntradedCompanyException class represents an exception that is thrown 
		 * when an unknown company code is used in a trading operation. 
		 */

		super(companyCode + "is not a listed company on this exchange");
	}
}
