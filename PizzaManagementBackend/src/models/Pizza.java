package models;

import java.util.Objects;

public class Pizza {
	private int id;
	private String pizzaName, pizzaToppings;
	public Pizza() {
		super();
	}
	
	public Pizza(int id, String pizzaName, String pizzaToppings) {
		super();
		this.id = id;
		this.pizzaName = pizzaName;
		this.pizzaToppings = pizzaToppings;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPizzaName() {
		return pizzaName;
	}
	public void setPizzaName(String pizzaName) {
		this.pizzaName = pizzaName;
	}
	public String getPizzaToppings() {
		return pizzaToppings;
	}
	public void setPizzaToppings(String pizzaToppings) {
		this.pizzaToppings = pizzaToppings;
	}

	@Override
	public String toString() {
		return "Pizza [id=" + id + ", pizzaName=" + pizzaName + ", pizzaToppings=" + pizzaToppings + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, pizzaName, pizzaToppings);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pizza other = (Pizza) obj;
		return id == other.id && Objects.equals(pizzaName, other.pizzaName)
				&& Objects.equals(pizzaToppings, other.pizzaToppings);
	}
	
}
