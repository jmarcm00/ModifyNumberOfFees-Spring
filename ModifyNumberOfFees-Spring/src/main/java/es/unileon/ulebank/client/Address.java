/* Application developed for AW subject, belonging to passive operations
 group.*/

package es.unileon.ulebank.client;

/**
 * Class that implements an address
 * 
 * @author Gonzalo Nicolas Barreales
 */
public class Address {

	/**
	 * Street
	 */
	private String street;

	/**
	 * Block in the street
	 */
	private int blockNumber;

	/**
	 * Floor in the block
	 */
	private int floor;

	/**
	 * Door in the floor
	 */
	private char door;

	/**
	 * Locality
	 */
	private String locality;

	/**
	 * Province
	 */
	private String province;

	/**
     * 
     */
	private int zipCode;

	/**
	 * Creates a new address with all the atributes
	 * 
	 * @param street
	 * @param blockNumber
	 * @param floor
	 * @param door
	 * @param locality
	 * @param province
	 * @param zipCode
	 */
	public Address(String street, int blockNumber, int floor, char door,
			String locality, String province, int zipCode) {
		this.street = street;
		this.blockNumber = blockNumber;
		this.floor = floor;
		this.door = door;
		this.locality = locality;
		this.province = province;
		this.zipCode = zipCode;
	}

	/**
	 * 
	 * @return street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * Sets the street
	 * 
	 * @param street
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * 
	 * @return the block number
	 */
	public int getBlockNumber() {
		return blockNumber;
	}

	/**
	 * Sets the block number
	 * 
	 * @param blockNumber
	 */
	public void setBlockNumber(int blockNumber) {
		this.blockNumber = blockNumber;
	}

	/**
	 * 
	 * @return the floor
	 */
	public int getFloor() {
		return floor;
	}

	/**
	 * Sets the floor
	 * 
	 * @param floor
	 */
	public void setFloor(int floor) {
		this.floor = floor;
	}

	/**
	 * 
	 * @return the door
	 */
	public char getDoor() {
		return door;
	}

	/**
	 * Sets the door
	 * 
	 * @param door
	 */
	public void setDoor(char door) {
		this.door = door;
	}

	/**
	 * 
	 * @return the locality
	 */
	public String getLocality() {
		return locality;
	}

	/**
	 * Sets the locality
	 * 
	 * @param locality
	 */
	public void setLocality(String locality) {
		this.locality = locality;
	}

	/**
	 * 
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * Sets the province
	 * 
	 * @param province
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * 
	 * @return the zip code
	 */
	public int getZipCode() {
		return zipCode;
	}

	/**
	 * Sets the zip code
	 * 
	 * @param zipCode
	 */
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

}
