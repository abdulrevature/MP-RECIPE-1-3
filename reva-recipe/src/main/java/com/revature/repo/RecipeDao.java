package com.revature.repo;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.domains.Recipe;
import com.revature.util.ConnectionUtil;
import com.revature.util.Page;
import com.revature.util.PageOptions;

public class RecipeDao {
    private ConnectionUtil connectionUtil;

    public RecipeDao(ConnectionUtil connectionUtil) {
        this.connectionUtil = connectionUtil;
    }

    public List<Recipe> getAllRecipes() {
        try(Connection connection = connectionUtil.getConnection(); Statement statement = connection.createStatement()) {
            String sql = "SELECT * FROM recipe ORDER BY id";
            ResultSet resultSet = statement.executeQuery(sql);
            return mapRows(resultSet);
            
        } catch(SQLException e) {
            throw new RuntimeException("Unable to retrieve all recipes", e);
        }
    }

    public Page<Recipe> getAllRecipes(PageOptions pageOptions) {
        String sql = String.format("SELECT * FROM recipe ORDER BY %s %s", pageOptions.getSortBy(), pageOptions.getSortDirection());
        try(Connection connection = connectionUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            return pageResults(resultSet, pageOptions);
        } catch(SQLException e) {
            throw new RuntimeException("Unable to retrieve all recipes", e);
        }
    }

    public List<Recipe> searchRecipesByTerm(String term) {
        String sql = "SELECT * FROM recipe WHERE name LIKE ?";
        try(Connection connection = connectionUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + term + "%");
            ResultSet resultSet = statement.executeQuery();
            return mapRows(resultSet);
        } catch(SQLException e) {
            throw new RuntimeException("Unable to search recipes by term", e);
        }
    }

    public Page<Recipe> searchRecipesByTerm(String term, PageOptions pageOptions) {
        String sql = String.format("SELECT * FROM recipe WHERE name LIKE ? ORDER BY %s %s", pageOptions.getSortBy(), pageOptions.getSortDirection());
        try(Connection connection = connectionUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + term + "%");
            ResultSet resultSet = statement.executeQuery();
            return pageResults(resultSet, pageOptions);
        } catch(SQLException e) {
            throw new RuntimeException("Unable to search recipes by term", e);
        }
    }

    public Recipe getRecipeById(int id) {
        String sql = "SELECT * FROM recipe WHERE id = ?";
        try(Connection connection = connectionUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return mapSingleRow(resultSet);
        } catch(SQLException e) {
            throw new RuntimeException("Unable to retrieve recipe by id", e);
        }
    }

    public int createRecipe(Recipe recipe) {
        String sql = "INSERT INTO recipe (name, instructions) VALUES (?, ?)";
        try(Connection connection = connectionUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, recipe.getName());
            statement.setString(2, recipe.getInstructions());
            int affectedRows = statement.executeUpdate();

            if(affectedRows < 1) {
                throw new SQLException("Creating recipe failed, no rows affected");
            }
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if(generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating recipe failed, no ID obtained");
            }
            
        } catch(SQLException e) {
            throw new RuntimeException("Unable to create recipe", e);
        }
    }

    public void updateRecipe(Recipe recipe) {
        String sql = "UPDATE recipe SET name = ?, instructions = ? WHERE id = ?";
        try(Connection connection = connectionUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, recipe.getName());
            statement.setString(2, recipe.getInstructions());
            statement.setInt(3, recipe.getId());
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new RuntimeException("Unable to update recipe", e);
        }
    }

    public void deleteRecipe(Recipe recipe) {
        String sql = "DELETE FROM recipe WHERE id = ?";
        try(Connection connection = connectionUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, recipe.getId());
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new RuntimeException("Unable to delete recipe", e);
        }
    }

    private Recipe mapSingleRow(ResultSet set) throws SQLException {
        int id = set.getInt("id");
        String name = set.getString("name");
        String instructions = set.getString("instructions");
        return new Recipe(id, name, instructions);
    }


    private List<Recipe> mapRows(ResultSet set) throws SQLException {
        List<Recipe> recipes = new ArrayList<>();
        while(set.next()) {
            recipes.add(mapSingleRow(set));
        }
        return recipes;
    }

    private Page<Recipe> pageResults(ResultSet set, PageOptions pageOptions) throws SQLException {
        List<Recipe> recipes = mapRows(set);
        int offset = (pageOptions.getPageNumber() - 1) * pageOptions.getPageSize();
        int limit = offset + pageOptions.getPageSize();
        List<Recipe> slicedList = sliceList(recipes, offset, limit);
        return new Page<>(pageOptions.getPageNumber(), pageOptions.getPageSize(), recipes.size() / pageOptions.getPageSize(), recipes.size(), slicedList);
    }

    private List<Recipe> sliceList(List<Recipe> list, int start, int end) {
        List<Recipe> sliced = new ArrayList<>();
        for(int i = start; i < end; i++) {
            sliced.add(list.get(i));
        }
        return sliced;
    }
}
