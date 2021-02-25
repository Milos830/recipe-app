package milos.sf.bootstrap;

import lombok.extern.slf4j.Slf4j;
import milos.sf.Domain.*;
import milos.sf.repositories.CategoryRepository;
import milos.sf.repositories.RecipeRepository;
import milos.sf.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository,
                           RecipeRepository recipeRepository,
                           UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }
    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
        log.debug("Loading Bootstrap Data");
    }


    private List<Recipe> getRecipes(){

        List<Recipe> recipes = new ArrayList<>(2);

        // get UnitsOfMeasure
        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");

        if(!eachUomOptional.isPresent()) {
            throw  new RuntimeException("Expected Oum not found");
        }

        Optional<UnitOfMeasure> tableSpoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
        if(!tableSpoonUomOptional.isPresent()) {
            throw  new RuntimeException("Expected Oum not found");
        }

        Optional<UnitOfMeasure> teaSpoonOumOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
        if(!teaSpoonOumOptional.isPresent()) {
            throw  new RuntimeException("Expected Oum not found");
        }

        Optional<UnitOfMeasure> dashOumOptional = unitOfMeasureRepository.findByDescription("Dash");
        if(!dashOumOptional.isPresent()) {
            throw  new RuntimeException("Expected Oum not found");
        }

        Optional<UnitOfMeasure> pintOumOptional = unitOfMeasureRepository.findByDescription("Pint");
        if(!pintOumOptional.isPresent()) {
            throw  new RuntimeException("Expected Oum not found");
        }

        Optional<UnitOfMeasure> cupsOumOptional = unitOfMeasureRepository.findByDescription("Cup");
        if(!cupsOumOptional.isPresent()) {
            throw  new RuntimeException("Expected Oum not found");
        }

        // get Optional

        UnitOfMeasure eachUom = eachUomOptional.get();
        UnitOfMeasure tableSpoonUom = tableSpoonUomOptional.get();
        UnitOfMeasure teaSpoonUom = tableSpoonUomOptional.get();
        UnitOfMeasure dashUom = dashOumOptional.get();
        UnitOfMeasure pintUom = pintOumOptional.get();
        UnitOfMeasure cupsUom = cupsOumOptional.get();

        // Categories

        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");
        if(!mexicanCategoryOptional.isPresent()) {
            throw  new RuntimeException("Expected Category not found");
        }

        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");
        if(!americanCategoryOptional.isPresent()) {
            throw  new RuntimeException("Expected Oum not found");
        }

        Category mexicanCategory = mexicanCategoryOptional.get();
        Category americanCategory = americanCategoryOptional.get();


        // Yummy Guac
        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("Perfect Guacamole");
        guacRecipe.setPrepTime(10);
        guacRecipe.setCockTime(0);
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setDirections("1 Cut the avocado, remove flesh: Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl." +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving." +
                "4 Serve: Serve immediately, or if making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.");
        Notes guacNotes = new Notes();
        guacNotes.setRecipeNote("Elise Bauer is the founder of Simply Recipes. Elise launched Simply Recipes in 2003 as a way to keep track of her family's recipes, and along the way grew it into one of the most popular cooking websites in the world. Elise is dedicated to helping home cooks be successful in the kitchen. Elise is a graduate of Stanford University, and lives in Sacramento, California.");
        guacRecipe.setNotes(guacNotes);


        guacRecipe.addIngredient(new Ingredient("ripe avocado", new BigDecimal(2), tableSpoonUom));
        guacRecipe.addIngredient(new Ingredient("KosherSalt", new BigDecimal(".5"), teaSpoonUom));
        guacRecipe.addIngredient(new Ingredient("fresh lime juice or lemon juice", new BigDecimal(1), tableSpoonUom));
        guacRecipe.addIngredient(new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(2), tableSpoonUom));
        guacRecipe.addIngredient(new Ingredient("chiles, stems and seeds removed, minced", new BigDecimal(2), eachUom));
        guacRecipe.addIngredient(new Ingredient(" cilantro (leaves and tender stems), finely chopped", new BigDecimal(2), tableSpoonUom));
        guacRecipe.addIngredient(new Ingredient("freshly grated black pepper", new BigDecimal(1), dashUom));
        guacRecipe.addIngredient(new Ingredient(" tomato, seeds and pulp removed, chopped", new BigDecimal(".5"), dashUom));

        guacRecipe.getCategories().add(americanCategory);
        guacRecipe.getCategories().add(mexicanCategory);

        guacRecipe.setUrl("www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacRecipe.setServings(4);
        guacRecipe.setSource("Simply recipes");

        //add to return list

        recipes.add(guacRecipe);


        return recipes;
    }


}
