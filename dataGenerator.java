/**
 * This class represents a data generator for creating various datasets.
 */
public class dataGenerator {

    /** Item Class for dataset */
    public static class Item {
        private String itemName; // Name of the item
        private int stackLimit; // Maximum stack limit for the item

        // Constructor
        public Item(String itemName, int stackLimit) {
            this.itemName = itemName;
            this.stackLimit = stackLimit;
        }

        // Getter for item name
        public String getName() {
            return itemName;
        }

        // Setter for item name
        public void setName(String itemName) {
            this.itemName = itemName;
        }

        // Getter for stack limit
        public int getLimit() {
            return stackLimit;
        }

        // Setter for stack limit
        public void setLimit(int stackLimit) {
            this.stackLimit = stackLimit;
        }
    }

    /** Tag Class for dataset */
    public static class Tag {
        private String tagName; // Name of the tag

        // Constructor
        public Tag(String tagName) {
            this.tagName = tagName;
        }

        // Getter for tag name
        public String getName() {
            return tagName;
        }

        // Setter for tag name
        public void setName(String tagName) {
            this.tagName = tagName;
        }
    }

    /** ItemSource Class for dataset */
    public static class ItemSource {
        private String sourceName; // Name of the source
        private String itemName; // Name of the item
        private int chance; // Chance of obtaining the item from the source
        private int outputQuantity; // Output quantity of the item

        // Constructor
        public ItemSource(String sourceName, String itemName, int chance, int outputQuantity) {
            this.sourceName = sourceName;
            this.itemName = itemName;
            this.chance = chance;
            this.outputQuantity = outputQuantity;
        }

        // Getter for source name
        public String getSourceName() {
            return sourceName;
        }

        // Setter for source name
        public void setSourceName(String sourceName) {
            this.sourceName = sourceName;
        }

        // Getter for item name
        public String getName() {
            return itemName;
        }

        // Setter for item name
        public void setName(String itemName) {
            this.itemName = itemName;
        }

        // Getter for chance
        public int getChance() {
            return chance;
        }

        // Setter for chance
        public void setChance(int chance) {
            this.chance = chance;
        }

        // Getter for output quantity
        public int getQuantity() {
            return outputQuantity;
        }

        // Setter for output quantity
        public void setQuantity(int outputQuantity) {
            this.outputQuantity = outputQuantity;
        }
    }

    /** Recipe Class for dataset */
    public static class Recipe {
        private String sourceName; // Name of the source
        private String itemName; // Name of the item
        private String recipeType; // Type of the recipe

        // Constructor
        public Recipe(String sourceName, String itemName, String recipeType) {
            this.sourceName = sourceName;
            this.itemName = itemName;
            this.recipeType = recipeType;
        }

        // Getter for source name
        public String getSourceName() {
            return sourceName;
        }

        // Setter for source name
        public void setSourceName(String sourceName) {
            this.sourceName = sourceName;
        }

        // Getter for item name
        public String getName() {
            return itemName;
        }

        // Setter for item name
        public void setName(String itemName) {
            this.itemName = itemName;
        }

        // Getter for recipe type
        public String getRecipe() {
            return recipeType;
        }

        // Setter for recipe type
        public void setRecipe(String recipeType) {
            this.recipeType = recipeType;
        }
    }

    /** Mining Class for dataset */
    public static class Mining {
        private String itemName; // Name of the item
        private String sourceName; // Name of the source

        // Constructor
        public Mining(String sourceName, String itemName) {
            this.itemName = itemName;
            this.sourceName = sourceName;
        }

        // Getter for source name
        public String getSourceName() {
            return sourceName;
        }

        // Setter for source name
        public void setSourceName(String sourceName) {
            this.sourceName = sourceName;
        }

        // Getter for item name
        public String getName() {
            return itemName;
        }

        // Setter for item name
        public void setName(String itemName) {
            this.itemName = itemName;
        }
    }

    /** Chest Class for dataset */
    public static class Chest {
        private String itemName; // Name of the item
        private String sourceName; // Name of the source
        private String structure; // Type of structure

        // Constructor
        public Chest(String sourceName, String itemName, String structure) {
            this.itemName = itemName;
            this.sourceName = sourceName;
            this.structure = structure;
        }

        // Getter for source name
        public String getSourceName() {
            return sourceName;
        }

        // Setter for source name
        public void setSourceName(String sourceName) {
            this.sourceName = sourceName;
        }

        // Getter for item name
        public String getName() {
            return itemName;
        }

        // Setter for item name
        public void setName(String itemName) {
            this.itemName = itemName;
        }

        // Getter for structure
        public String getStructure() {
            return structure;
        }

        // Setter for structure
        public void setStructure(String structure) {
            this.structure = structure;
        }
    }

    /** Location Class for dataset */
    public static class Location {
        private String sourceName; // Name of the source
        private String itemName; // Name of the item
        private int depth; // Depth of the location
        private String biome; // Biome of the location

        // Constructor
        public Location(String sourceName, String itemName, int depth, String biome) {
            this.sourceName = sourceName;
            this.itemName = itemName;
            this.depth = depth;
            this.biome = biome;
        }

        // Getter for source name
        public String getSourceName() {
            return sourceName;
        }

        // Setter for source name
        public void setSourceName(String sourceName) {
            this.sourceName = sourceName;
        }

        // Getter for item name
        public String getName() {
            return itemName;
        }

        // Setter for item name
        public void setName(String itemName) {
            this.itemName = itemName;
        }

        // Getter for depth
        public int getDepth() {
            return depth;
        }

        // Setter for depth
        public void setDepth(int depth) {
            this.depth = depth;
        }

        // Getter for biome
        public String getBiome() {
            return biome;
        }

        // Setter for biome
        public void setBiome(String biome) {
            this.biome = biome;
        }
    }

    /** ItemsToTag Class for dataset */
    public static class ItemsToTag {
        private String itemName; // Name of the item
        private String tagName; // Name of the tag

        // Constructor
        public ItemsToTag(String tagName, String itemName) {
            this.itemName = itemName;
            this.tagName = tagName;
        }

        // Getter for tag name
        public String getTagName() {
            return tagName;
        }

        // Setter for tag name
        public void setTagName(String tagName) {
            this.tagName = tagName;
        }

        // Getter for item name
        public String getName() {
            return itemName;
        }

        // Setter for item name
        public void setName(String itemName) {
            this.itemName = itemName;
        }
    }

    /** Input Class for dataset */
    public static class Input {
        private String sourceName; // Name of the source
        private String itemInputName; // Name of the input item
        private String itemOutputName; // Name of the output item
        private String recipeType; // Type of the recipe

        // Constructor
        public Input(String sourceName, String itemInputName, String itemOutputName, String recipeType) {
            this.sourceName = sourceName;
            this.itemInputName = itemInputName;
            this.itemOutputName = itemOutputName;
            this.recipeType = recipeType;
        }

        // Getter for source name
        public String getSourceName() {
            return sourceName;
        }

        // Setter for source name
        public void setSourceName(String sourceName) {
            this.sourceName = sourceName;
        }

        // Getter for input item name
        public String getInputName() {
            return itemInputName;
        }

        // Setter for input item name
        public void setInputName(String itemInputName) {
            this.itemInputName = itemInputName;
        }

        // Getter for output item name
        public String getOutputName() {
            return itemOutputName;
        }

        // Setter for output item name
        public void setOutputName(String itemOutputName) {
            this.itemOutputName = itemOutputName;
        }

        // Getter for recipe type
        public String getRecipe() {
            return recipeType;
        }

        // Setter for recipe type
        public void setRecipe(String recipeType) {
            this.recipeType = recipeType;
        }
    }

    /** BlockToMine Class for dataset */
    public static class BlockToMine {
        private String sourceName; // Name of the source
        private String itemInputName; // Name of the input item
        private String itemOutputName; // Name of the output item

        // Constructor
        public BlockToMine(String sourceName, String itemInputName, String itemOutputName) {
            this.sourceName = sourceName;
            this.itemInputName = itemInputName;
            this.itemOutputName = itemOutputName;
        }

        // Getter for source name
        public String getSourceName() {
            return sourceName;
        }

        // Setter for source name
        public void setSourceName(String sourceName) {
            this.sourceName = sourceName;
        }

        // Getter for input item name
        public String getInputName() {
            return itemInputName;
        }

        // Setter for input item name
        public void setInputName(String itemInputName) {
            this.itemInputName = itemInputName;
        }

        // Getter for output item name
        public String getOutputName() {
            return itemOutputName;
        }

        // Setter for output item name
        public void setOutputName(String itemOutputName) {
            this.itemOutputName = itemOutputName;
        }
    }
}
