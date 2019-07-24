package tacos.data;

import tacos.Ingredient;

public interface IngredientRepository {
    Iterable<Ingredient> findAll();
    Ingredient save(Ingredient ingredient);
    Ingredient findById(String id);
}
