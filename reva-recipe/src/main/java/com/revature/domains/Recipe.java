package com.revature.domains;
import java.util.Objects;

/**
 * 
 * @author 
 * @version
 * @since
 *
 */
public class Recipe {

	private int id;

	private String name;
	
	private String instructions;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public Recipe() {
		super();
	}

	public Recipe(String name) {
		super();
		this.name = name;
	}

	public Recipe(String name, String instructions) {
		super();
		this.name = name;
		this.instructions = instructions;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Recipe)) {
			return false;
		}
		Recipe recipe = (Recipe) o;
		return Objects.equals(name, recipe.name) && Objects.equals(instructions, recipe.instructions);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, instructions);
	}
	
}
