package milos.sf.controllers;

import milos.sf.Domain.Recipe;
import milos.sf.exceptions.NotFoundException;
import milos.sf.repositories.RecipeRepository;
import milos.sf.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RecipeConrollerTest {

    @Mock
    RecipeService recipeService;
    @Mock
    RecipeRepository recipeRepository;

    MockMvc mockMvc;

    RecipeController controller;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        controller = new RecipeController(recipeService);

    }

    @Test
    public void getRecipeByIdTestNotFound() throws Exception {

        when(recipeRepository.findById(anyLong())).thenThrow(NotFoundException.class);

        mockMvc.perform(get("/recipe/1/show"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("404error"));

    }

    @Test
    public void getRecipeByIdTestNumberFormat() throws Exception {

//        when(recipeRepository.findById(anyLong())).thenThrow(NumberFormatException.class);

        mockMvc.perform(get("/recipe/dfsdds/show"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("400error"));

    }

    @Test
    public void testGetRecipe() throws Exception {

        Recipe recipe = new Recipe();
        recipe.setId(1L);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        when(recipeService.findById(anyLong())).thenReturn(recipe);

        mockMvc.perform(get("/recipe/show/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/show"))
                .andExpect(model().attributeExists("recipe"));
    }



}
