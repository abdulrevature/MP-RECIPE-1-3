# RevaRecipe
## Step 3: Business Service

### Review:

Let's begin by reviewing the work that was completed in `step 2`. The first task in the previous step was to implement a `DDL` script to create a database table. Navigate to `reva-recipe\src\main\resources\ddl.sql`. There you will see the configuration of the table including the required columns `id`, `name`, and `instructions` with their respective constraints. At this point however, there is a slight inconsistency between the database table and Java class. Navigate to `reva-recipe\src\main\java\com\revature\domains\Recipe.java`, there you will observe that an `id` field and a new constructor was added to the Recipe class to close the inconsistency.

The second task was to establish a JDBC connection to the database. The way this is acheived, is to encapsulate that logic in a class. The class in this application is named `ConnectionUtil` located at `reva-recipe\src\main\java\com\revature\util\ConnectionUtil.java`. There you will see that this class follows a `singleton` pattern. It encapsulates a `static` reference to a `ConnectionUtil` object, and the `instance` variables for `username`, `password`, and `url`. The no-args constructor is `private` to prevent outside construction of any `ConnectionUtil` objects outside of the class. Then you should see a `static` `getInstance` method for constructing or returning a `ConnectionUtil` reference object, an `instance` method `configure` for providing the instance with connection values. It is important to note that this will only need to be called once and call might look something like this:

```Java
ConnectionUtil connectionUtil = ConnectionUtil.getInstance().configure('user', 'password', 'jdbc:postgresql://localhost:5432/db', new PostgreSQLDriver());
```

There is now one remaing task, create the `DAO`. This task however has a sub-requirement, to support pagination. Let's start by looking at the `PageOptions` class which will help us create result pages. Navigate to `reva-recipe\src\main\java\com\revature\util\PageOptions.java`. As you can see this class is really just a PoJo that encapsulates the information needed to determine how pages are created. This class was implemented to package all of these options in a nice package rather than passing each one of these values individually to any method that wants to support paging.

After the `PageOptions` class, navigate to the `Page` class at `reva-recipe\src\main\java\com\revature\util\Page.java`. This class is a bit more advanced than the `PageOptions` class it encapsulates the list of items on the page and also some metadata that the client can utilize to create a fluid paged experience for the user. You will also observe the implementation of the `equals` and `hascode` methods. Where the application may be unlikely to store `PageOptions` in a `Collection` or compare `PageOptions` objects, it is likely to need to store `Page` objects in a `Collection` or compare `Pages`. It also support generics.

Finally navigate to the `RecipeDao` class at `reva-recipe\src\main\java\com\revature\repo\RecipeDao.java`. This class abstracts the CRUD operations for `Recipe` objects. It utilizes all of the classes that were created previously. This is mostly a pure functional class as it doesn't store any state rather than a `ConnectionUtil` reference for connecting to the database. The implementation of this complete structure may seem like a lot of work to achieve a limited result, but the infrastructure is more testable, maintainable, and extensible. With little effort we could add more entities and functionality without adverse effects on the rest of the application (in theory). You will also see some methods `mapSingleRow`, `mapRows`, `pageResults`, and `sliceList`, which weren't part of the requirements, but were added for utility and convenience in other methods. Since the use case for this project is a single entity we localized the implementation of those methods to the `DAO` object, but it may be wise to think about how that logic could be abstracted away to create a structure that fits the S.O.L.I.D. principals and OOP design.

### Tasks:

- [ ] Create Recipe Service Class

### Business Requirements:

Services classes implement the business logic of an application. Business logic can be as complex or simple as the solution requires. In this case we will keep it simple and add a layer of abstraction that separates data access from request handling. Create a `RecipeService` class that will act as that layer of abstraction.

#### RecipeService

##### Fields

- RecipeDao recipeDao

##### Constructors

- RecipeService(RecipeDao recipeDao)

##### Methods

- Optional\<Recipe> findRecipe(int id)
- void saveRecipe(Recipe recipe)
- Page\<Recipe> searchRecipes(String term, int page, int pageSize, String sortBy, String sortDirection)
- List\<Recipe> searchRecipes(String term)
- void deleteRecipe(int id)
