package models;

import java.util.Objects;

public class PizzaToppings {
	private int id;
	private String toppingName, referenceId;
	public PizzaToppings() {
		super();
	}
	public PizzaToppings(int id, String toppingName, String referenceId) {
		super();
		this.id = id;
		this.toppingName = toppingName;
		this.referenceId = referenceId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getToppingName() {
		return toppingName;
	}
	public void setToppingName(String toppingName) {
		this.toppingName = toppingName;
	}
	public String getReferenceId() {
		return referenceId;
	}
	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}
	@Override
	public String toString() {
		return toppingName;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, referenceId, toppingName);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PizzaToppings other = (PizzaToppings) obj;
		return id == other.id && Objects.equals(referenceId, other.referenceId)
				&& Objects.equals(toppingName, other.toppingName);
	}
	
}
