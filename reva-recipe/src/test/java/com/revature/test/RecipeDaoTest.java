package com.revature.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.domains.Recipe;

class RecipeDaoTest {
	
	private List<Recipe> recipeList = new ArrayList<Recipe>();
	
	@BeforeAll
	void setUpDB() {
		DBTestSetUp.RUN_DDL();
	}
	
	@BeforeEach
	void setUpTestsData() {
		DBTestSetUp.RUN_DML();
		recipeList.clear();
		recipeList.addAll(List.of(new Recipe("carrot soup", "Put carrot in water.  Boil.  Maybe salt."), 
				new Recipe("potato soup", "Put potato in water.  Boil.  Maybe salt."), 
				new Recipe("tomato soup", "Put tomato in water.  Boil.  Maybe salt."), 
				new Recipe("lemon rice soup", "Put lemon and rice in water.  Boil.  Maybe salt."), 
				new Recipe("stone soup", "Put stone in water.  Boil.  Maybe salt.")));
	}
	
	@Test
	void readAllRecipes() {
		
	}

	@Test
	void readOneRecipe() {
		
	}
	
	@Test
	void createRecipe() {
		
	}
	
	@Test
	void deleteRecipe() {
		
	}
	
	@Test
	void updateRecipe() {
		
	}
	
}
