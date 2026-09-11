package models;

/**
 * Model representing SauceDemo checkout information.
 *
 * Responsibilities: - Store checkout/customer information.
 *
 * This class does not perform: - Selenium operations - UI actions - Assertions
 */
public class CheckoutData {

	private String firstName;
	private String lastName;
	private String postalCode;

	/**
	 * Default constructor.
	 */
	public CheckoutData() {
	}

	/**
	 * Constructor with checkout information.
	 *
	 * @param firstName  customer first name
	 * @param lastName   customer last name
	 * @param postalCode postal/ZIP code
	 */
	public CheckoutData(String firstName, String lastName, String postalCode) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.postalCode = postalCode;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * Validates whether all checkout fields contain data.
	 *
	 * @return true when all required fields are populated
	 */
	public boolean isComplete() {

		return isNotBlank(firstName) && isNotBlank(lastName) && isNotBlank(postalCode);
	}

	/**
	 * Internal blank-value validation.
	 */
	private boolean isNotBlank(String value) {

		return value != null && !value.trim().isEmpty();
	}

	/**
	 * Returns a safe representation of checkout data.
	 */
	@Override
	public String toString() {

		return "CheckoutData{" + "firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", postalCode='"
				+ postalCode + '\'' + '}';
	}
}