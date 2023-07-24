package com.revature.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.h2.Driver;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.revature.domains.Recipe;
import com.revature.repo.RecipeDao;
import com.revature.util.ConnectionUtil;
import com.revature.util.Page;
import com.revature.util.PageOptions;

class RecipeDaoTest {

	private List<Recipe> recipeList = new ArrayList<Recipe>();
	private ConnectionUtil connectionUtil;
	private RecipeDao recipeDao;

	@BeforeEach
	void setUpTestsData() throws SQLException {
		DBTestSetUp.RUN_DDL();
		DBTestSetUp.RUN_DML();
		recipeList.clear();
		recipeList.addAll(Arrays.asList(new Recipe("carrot soup", "Put carrot in water.  Boil.  Maybe salt."),
				new Recipe("potato soup", "Put potato in water.  Boil.  Maybe salt."),
				new Recipe("tomato soup", "Put tomato in water.  Boil.  Maybe salt."),
				new Recipe("lemon rice soup", "Put lemon and rice in water.  Boil.  Maybe salt."),
				new Recipe("stone soup", "Put stone in water.  Boil.  Maybe salt.")));
		connectionUtil = ConnectionUtil.getInstance().configure("sa", "", "jdbc:h2:./h2/db", new Driver());
		recipeDao = new RecipeDao(connectionUtil);
	}

	@Test
	void readOneRecipe() {
		Recipe recipe = recipeDao.getRecipeById(1);
		assertEquals(recipe, recipeList.get(0), () -> "The returned recipe doesn't match the expected recipe. Expected: " + recipeList.get(0) + " Actual: " + recipe);
	}

	@Test
	void readAllRecipes() {
		List<Recipe> recipes = recipeDao.getAllRecipes();
		assertIterableEquals(recipeList, recipes, () -> "The returned list of recipes doesn't match the expected list of recipes.");
	}

	@Test
	void createRecipe() {
		Recipe recipeToInsert = new Recipe("test recipe", "test instructions");
		int id = recipeDao.createRecipe(recipeToInsert);
		Recipe recipe = recipeDao.getRecipeById(id);
		assertTrue(id > 0, () -> "The returned id is not greater than 0. Actual: " + id);
		assertEquals(recipe, recipeToInsert, () -> "The database doesn't include the expected recipe Expected: " + recipeToInsert + " Actual: " + recipe);
	}

	@Test
	void deleteRecipe() {
		int count = recipeDao.getAllRecipes().size();
		Recipe recipeToDelete = recipeDao.getRecipeById(1);
		recipeDao.deleteRecipe(recipeToDelete);
		assertEquals(count - 1, recipeDao.getAllRecipes().size(), () -> "The database doesn't have the expected number of recipes after deleting a recipe. Expected: " + (count - 1) + " Actual: " + recipeDao.getAllRecipes().size());
	}

	@Test
	void updateRecipe() {
		Recipe recipeToUpdate = recipeDao.getRecipeById(1);
		recipeToUpdate.setName("updated name");
		recipeToUpdate.setInstructions("updated instructions");
		recipeDao.updateRecipe(recipeToUpdate);
		Recipe recipe = recipeDao.getRecipeById(1);
		assertEquals(recipe, recipeToUpdate, () -> "The database doesn't include the expected recipe after updating. Expected: " + recipeToUpdate + " Actual: " + recipe);
	}

	@Test
	void searchRecipesByTerm() {
		List<Recipe> recipes = recipeDao.searchRecipesByTerm("soup");
		assertIterableEquals(recipes, recipeList, () -> "The returned list of recipes doesn't match the expected list of recipes.");
	}

	@Test
	void getAndPageAllRecipes() {
		PageOptions pageable = new PageOptions(1, 2);
		Page<Recipe> expectedPage = new Page<>(1, 2, recipeList.size() / 2, recipeList.size(), Arrays.asList(recipeList.get(0), recipeList.get(1)));
		Page<Recipe> recipes = recipeDao.getAllRecipes(pageable);
		assertIterableEquals(expectedPage.getItems(), recipes.getItems(), () -> "The returned page of recipes doesn't match the expected page of recipes.");
	}

	@Test
	void searchAndPageAllRecipesByTerm() {
		PageOptions pageable = new PageOptions(1, 2);
		Page<Recipe> expectedPage = new Page<>(1, 2, recipeList.size() / 2, recipeList.size(), Arrays.asList(recipeList.get(0), recipeList.get(1)));
		Page<Recipe> recipes = recipeDao.searchRecipesByTerm("soup", pageable);
		assertIterableEquals(expectedPage.getItems(), recipes.getItems(), () -> "The returned page of recipes doesn't match the expected page of recipes.");
		assertEquals(expectedPage, recipes, () -> "The returned page of recipes doesn't match the expected page of recipes.");
	}

	@Test
	void searchPageAndSortAllRecipesByTerm() {
		PageOptions pageable = new PageOptions(1, 2, "name", "desc");
		Page<Recipe> expectedPage = new Page<>(1, 2, recipeList.size() / 2, recipeList.size(), Arrays.asList(recipeList.get(2), recipeList.get(4)));
		Page<Recipe> recipes = recipeDao.searchRecipesByTerm("soup", pageable);
		assertIterableEquals(expectedPage.getItems(), recipes.getItems(), () -> "The returned page of recipes doesn't match the expected page of recipes.");
		assertEquals(expectedPage, recipes, () -> "The returned page of recipes doesn't match the expected page of recipes.");
	}

<<<<<<< HEAD
}
=======
}
>>>>>>> f42d010 (finish the step-2 test and code impl)
