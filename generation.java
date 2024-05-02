
import java.io.FileWriter;
import java.util.*;

public class generation {
    public static Random random = new Random();
    public static char[] itemName1 = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    public static char[] itemName2 = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    public static char[] itemName3 = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    public static char[] itemNum1 = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    public static char[] itemNum2 = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    public static char[] itemNum3 = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    public static String[] tagNames = {"Tool", "Food", "Block", "Ore", "Weapon", "Armour", "Potions", "Redstone", "Building Materials", "Decorations"};
    public static String[] itemSourced = {"Wood", "Stone", "Iron", "Gold", "Diamond", "Emerald", "Redstone", "Obsidian", "Netherite", "Quartz", "Glowstone", "Clay", "Sand", "Gravel", "Dirt", "Cobblestone", "Brick", "Glass", "Ice", "Snow", "Bone", "Leather", "Feather", "String", "Gunpowder"};
    public static String[] recipeTypes = {"Crafting", "Smelting"};
    public static String[] biomes = {"Plains", "Forest", "Taiga", "Snowy Tundra", "Jungle", "Swamp", "Desert", "Mesa", "Savanna", "Birch Forest", "Dark Forest", "Ice Spikes", "Mountains", "Mushroom Fields", "The End", "Nether Wastes", "Crimson Forest", "Warped Forest", "Basalt Deltas", "Ocean", "Beach", "River", "Frozen River", "Lukewarm Ocean", "Deep Ocean", "Frozen Ocean", "Tundra", "Badlands", "Warm Ocean", "Frozen Ocean"};

    public static void main(String[] args) {

        int length = 500000;
        List<dataGenerator.Item> items = new ArrayList<>();
        List<dataGenerator.Tag> tags = new ArrayList<>();
        List<dataGenerator.ItemSource> itemSources = new ArrayList<>();
        List<dataGenerator.Recipe> recipes = new ArrayList<>();
        List<dataGenerator.Mining> minings = new ArrayList<>();
        List<dataGenerator.Chest> chests = new ArrayList<>();
        List<dataGenerator.Location> locations = new ArrayList<>();
        List<dataGenerator.ItemsToTag> taggedItems = new ArrayList<>();
        List<dataGenerator.Input> inputs = new ArrayList<>();
        List<dataGenerator.BlockToMine> blockstomine = new ArrayList<>();

        //Generate Items
        for(int i = 0; i < length; i++) {
            int r = random.nextInt(64);
            dataGenerator.Item item = generateItems(r);
            checkItemName(item, items); //This ensures no duplicate item names
            items.add(item); 
        }
       
        //Generate Tags
        for(int i = 0; i < tagNames.length; i++) {
            dataGenerator.Tag tag = generateTags(i);
            tags.add(tag);
        }

        //Generate ItemSource
        for(int i = 0; i < length; i++) {
            dataGenerator.ItemSource source = generateItemSources(items.get(i));
            itemSources.add(source);
        }
        
        //Generate Recipe
        for(int i = 0; i < length/4; i++) {
            int r = random.nextInt(length);
            dataGenerator.Recipe recipe = generateRecipe(itemSources.get(r));
            recipes.add(recipe);
        }

        //Generate Mining
        for(int i = 0; i < length/8; i++) {
            int r = random.nextInt(length);
            dataGenerator.Mining mining = generateMining(itemSources.get(r));
            minings.add(mining);
        }

        //Generate Chest
        for(int i = 0; i < length/8; i++) {
            int r = random.nextInt(length);
            dataGenerator.Chest chest = generateChest(itemSources.get(r));
            chests.add(chest);
        }

        //Generate Location
        for(int i = 0; i < length; i++) {
            dataGenerator.Location location = generateLocation(itemSources.get(i));
            locations.add(location);
        }

        //Generate ItemToTag
        for(int i = 0; i < length; i++) {
            int r = random.nextInt(tags.size());
            dataGenerator.ItemsToTag itemsToTag = generateItemsToTag(items.get(i), tags.get(r));
            taggedItems.add(itemsToTag);
        }

        //Generate Input
        for(int i = 0; i < recipes.size()/2; i++) {
            int r = random.nextInt(recipes.size());
            int s = random.nextInt(items.size());
            dataGenerator.Input input = generateInput(recipes.get(r), items.get(s));
            inputs.add(input);
        }

        //Generate Block to Mine
        for(int i = 0; i < minings.size(); i++) {
            int r = random.nextInt(items.size())  ;
            dataGenerator.BlockToMine blockToMine = generateBlockToMine(minings.get(i), items.get(r));
            blockstomine.add(blockToMine);
        }
        
        //Write this information to SQL Format
        try (FileWriter w = new FileWriter("projects.sql")){
            for(dataGenerator.Item i : items) {
                String insertStatement = String.format("INSERT INTO Item (itemName, stackLimit) VALUES ('%s', %d);\n", i.getName(), i.getLimit());
                w.write(insertStatement);
            }
            for(dataGenerator.Tag t : tags) {
                String insertStatement = String.format("INSERT INTO Tag (tagName) VALUES ('%s');\n", t.getName());
                w.write(insertStatement);
            }
            for(dataGenerator.ItemSource s : itemSources) {
                String insertStatement = String.format("INSERT INTO ItemSource (sourceName, itemName, chance, outputQuantity) VALUES ('%s', '%s', %d, %d);\n", 
                    s.getSourceName(), s.getName(), s.getChance(), s.getQuantity());
                w.write(insertStatement);
            }
            for(dataGenerator.Recipe r : recipes) {
                String insertStatement = String.format("INSERT INTO Recipe (sourceName, itemName, recipeType) VALUES ('%s', '%s', '%s');\n", r.getSourceName(), r.getName(), r.getRecipe());
                w.write(insertStatement);
            }
            for(dataGenerator.Mining m : minings) {
                String insertStatement = String.format("INSERT INTO Mining (sourceName, itemName) VALUES ('%s', '%s');\n", m.getSourceName(), m.getName());
                w.write(insertStatement);
            }
            for(dataGenerator.Chest c : chests) {
                String insertStatement = String.format("INSERT INTO Chest (sourceName, itemName, structure) VALUES ('%s', '%s', '%s');\n", c.getSourceName(), c.getName(), c.getStructure());
                w.write(insertStatement);
            }
            for(dataGenerator.Location l : locations) {
                String insertStatement = String.format("INSERT INTO Location (sourceName, itemName, depth, biome) VALUES ('%s', '%s', %d, '%s');\n", 
                    l.getSourceName(), l.getName(), l.getDepth(), l.getBiome());
                w.write(insertStatement);
            }
            for(dataGenerator.ItemsToTag itt : taggedItems) {
                String insertStatement = String.format("INSERT INTO ItemToTag (itemName, tagName) VALUES ('%s', '%s');\n", itt.getName(), itt.getTagName());
                w.write(insertStatement);
            }
            for(dataGenerator.Input i : inputs) {
                String insertStatement = String.format("INSERT INTO Input (sourceName, outputItemName, inputItemName, recipeType) VALUES ('%s', '%s', '%s', '%s');\n",
                     i.getSourceName(), i.getInputName(), i.getOutputName(), i.getRecipe());
                w.write(insertStatement);
            }
            for(dataGenerator.BlockToMine btm : blockstomine) {
                String insertStatement = String.format("INSERT INTO BlockToMine (sourceName, outputItemName, inputItemName) VALUES ('%s', '%s', '%s');\n",
                     btm.getSourceName(), btm.getInputName(), btm.getOutputName());
                w.write(insertStatement);
            }

        } catch (Exception e) {
            System.out.println("Error writing to file");
        }
    }

    //Generates an item
    public static dataGenerator.Item generateItems(int stack) {
        //Uses 3 alphabetical and 3 numerical codes to generate name
        String itemName;
        char[] nameSections = {'Z', 'Z', 'Z', '0', '0', '0'};
        int index[] = {0,0,0,0,0,0};
        for(int i = 0; i < 3; i++) {
            int r = random.nextInt(26);
            int s = random.nextInt(10);
            index[i] = r;
            index[i + 3] = s;
        }
        nameSections[0] = itemName1[index[0]];
        nameSections[1] = itemName2[index[1]];
        nameSections[2] = itemName3[index[2]];
        nameSections[3] = itemNum1[index[3]];
        nameSections[4] = itemNum1[index[4]];
        nameSections[5] = itemNum1[index[5]];

        itemName = String.copyValueOf(nameSections);
        dataGenerator.Item i = new dataGenerator.Item(itemName, stack);
        return i;
    }

    //Generates tags
    public static dataGenerator.Tag generateTags(int i) {
        dataGenerator.Tag t = new dataGenerator.Tag(tagNames[i]);
        return t;
    }

    //Generates an itemSource for each item
    public static dataGenerator.ItemSource generateItemSources(dataGenerator.Item item) {
        String itemSource = itemSourced[random.nextInt(itemSourced.length)];
        int chance = random.nextInt(100);
        int outputQuantity = random.nextInt(8);
        dataGenerator.ItemSource s = new dataGenerator.ItemSource(itemSource, item.getName(), chance, outputQuantity);
        return s;
    }

    //Generates a recipe based on the itemSource
    public static dataGenerator.Recipe generateRecipe(dataGenerator.ItemSource itemSource) {
        String recipeType = recipeTypes[random.nextInt(2)];
        String itemSourceName = itemSource.getSourceName();
        String itemName = itemSource.getName();
        dataGenerator.Recipe r = new dataGenerator.Recipe(itemSourceName, itemName, recipeType);
        return r;
    }

    //Generates a mining based on the itemSource
    public static dataGenerator.Mining generateMining(dataGenerator.ItemSource itemSource) {
        String itemSourceName = itemSource.getSourceName();
        String itemName = itemSource.getName();
        dataGenerator.Mining m = new dataGenerator.Mining(itemSourceName, itemName);
        return m;
    }

    //Generates a chest based on the itemSource
    public static dataGenerator.Chest generateChest(dataGenerator.ItemSource itemSource) {
        String itemSourceName = itemSource.getSourceName();
        String itemName = itemSource.getName();
        String structure = "Wooden Chest";
        dataGenerator.Chest m = new dataGenerator.Chest(itemSourceName, itemName, structure);
        return m;
    }

    //Generates a location based on the itemSource
    public static dataGenerator.Location generateLocation(dataGenerator.ItemSource itemSource) {
        String itemSourceName = itemSource.getSourceName();
        String itemName = itemSource.getName();
        int depth = random.nextInt(200);
        String biome = biomes[random.nextInt(biomes.length)];
        dataGenerator.Location l = new dataGenerator.Location(itemSourceName, itemName, depth, biome);
        return l;
    }
    //Generates a tagged Item based on the item and tag
    public static dataGenerator.ItemsToTag generateItemsToTag(dataGenerator.Item item, dataGenerator.Tag tag) {
        String itemName = item.getName();
        String tagName = tag.getName();
        dataGenerator.ItemsToTag itt = new dataGenerator.ItemsToTag(tagName, itemName);
        return itt;
    }

    //Generates an input based on the recipe and output item
    public static dataGenerator.Input generateInput(dataGenerator.Recipe recipe, dataGenerator.Item output) {
        String sourceName = recipe.getSourceName();
        String itemName = recipe.getName();
        String recipeType = recipe.getRecipe();
        String outputItemName = output.getName();
        dataGenerator.Input i = new dataGenerator.Input(sourceName, itemName, outputItemName, recipeType);
        return i;
    }

    //Generates a Block to mine based on the mining and output item
    public static dataGenerator.BlockToMine generateBlockToMine(dataGenerator.Mining mining, dataGenerator.Item output) {
        String sourceName = mining.getSourceName();
        String itemName = mining.getName();
        String outputItemName = output.getName();
        dataGenerator.BlockToMine btm = new dataGenerator.BlockToMine(sourceName, itemName, outputItemName);
        return btm;
    }

    //Checks that the item name of an item is not duplicated
    public static void checkItemName(dataGenerator.Item item, List<dataGenerator.Item> items) {
        String name = item.getName();
        for(dataGenerator.Item i: items) {
            String existingName = i.getName();
            if(existingName.equals(name)) {
                name += "0";
                item.setName(name);
            }
        }
    }
}
