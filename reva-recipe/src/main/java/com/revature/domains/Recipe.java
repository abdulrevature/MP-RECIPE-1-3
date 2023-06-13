package com.revature.domains;

/**
 * 
 * @author 
 * @version
 * @since
 *
 */
public class Recipe {

	private String name;
	
	private String instructions;

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
	
}
