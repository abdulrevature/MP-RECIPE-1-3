package com.revature.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.revature.domains.Recipe;

class RecipeTest {

	@Test
	void testCreationOfRecipe() {
		Recipe Recipe = new Recipe();
		assertNotNull(Recipe, "Recipe created should not be null");
	}
	
	@Test
	void testCreationOfRecipeWithName() {
		Recipe Recipe = new Recipe("carrot soup");
		assertNotNull(Recipe, "Recipe created should not be null");
	}
	
	@Test
	void testSetRecipeName() {
		Recipe Recipe = new Recipe();
		Recipe.setName("carrot soup");
	}
	
	@Test
	void testGetRecipeName() {
		Recipe Recipe = new Recipe("carrot soup");
		assertEquals("carrot soup", Recipe.getName(), ".getName should return name carrot");
	}

	@Test
	void testSetRecipeInstructions() {
		Recipe recipe = new Recipe();
		recipe.setInstructions("Put carrot in water.  Boil.  Maybe salt.");
	}
	
	@Test
	void testGetRecipeInstructions() {
		Recipe Recipe = new Recipe("carrot soup", "Put carrot in water.  Boil.  Maybe salt.");
		assertEquals("Put carrot in water.  Boil.  Maybe salt.", Recipe.getInstructions(), ".setInstructions should return given instructions");
	}
	
}
