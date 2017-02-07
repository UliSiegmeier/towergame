package com.example.uli.mygammelgame;

import com.example.uli.mygammelgame.model.GameEntity;
import com.example.uli.mygammelgame.model.GameIdGenerator;
import com.example.uli.mygammelgame.model.Player;
import com.example.uli.mygammelgame.model.deprecated.QuantityEntity;
import com.example.uli.mygammelgame.model.deprecated.QuantityEntityInventory;
import com.example.uli.mygammelgame.model.inventory.Item;
import com.example.uli.mygammelgame.model.inventory.ItemMap;
import com.example.uli.mygammelgame.model.inventory.ResourceMap;
import com.example.uli.mygammelgame.model.inventory.ResourceType;
import com.example.uli.mygammelgame.model.tower.Level;
import com.example.uli.mygammelgame.model.tower.Tower;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void something_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);

        GameIdGenerator generator = GameIdGenerator.getInstance();
        System.out.println(generator.generateId("HOUSE"));
        System.out.println(generator.generateId("HOUSE"));
        System.out.println(generator.generateId("HUND"));
        System.out.println(generator.generateId("LOL"));
        System.out.println(generator.generateId("HOUSE"));

        GameEntity house_1 = new GameEntity("HOUSE_L1", "Wooden House");
        GameEntity house_2 = new GameEntity("HOUSE_L2", "Wooden House");
        System.out.println(house_1);
        System.out.println(house_2);

        QuantityEntity gold = new QuantityEntity("GOLD","Gold Pieces", 123);
        QuantityEntity wood = new QuantityEntity("WOOD","Wood");
        System.out.println(gold);
        System.out.println(wood.changeQuantity(100));
        System.out.println(wood.changeQuantity(-200));
        System.out.println(wood.setQuantity(456));

        QuantityEntityInventory resources = new QuantityEntityInventory();
        System.out.println(resources);
        resources.addEntity(gold);
        resources.addEntity(wood);
        System.out.println(resources);

        QuantityEntityInventory price1 = new QuantityEntityInventory();
        price1.addEntity(gold.copy());
        price1.addEntity(wood.copy());
        System.out.println(resources.containsAmountsOf(price1));

        QuantityEntity stone = new QuantityEntity("STONE","Stone",100);
        price1.addEntity(stone);
        System.out.println(resources.containsAmountsOf(price1));

        QuantityEntity aBitOfStone = new QuantityEntity("STONE","Stone",10);
        resources.addEntity(aBitOfStone);
        System.out.println(resources);
        System.out.println(resources.containsAmountsOf(price1));

        resources.addEntity(stone);
        System.out.println(resources);
        System.out.println(resources.containsAmountsOf(price1));

        ResourceMap resourceMap = new ResourceMap();
        resourceMap.add(ResourceType.GOLD, 100);
        resourceMap.add(ResourceType.GOLD, 100);
        System.out.println("resources: "+resourceMap);

        ResourceMap priceMap1 = new ResourceMap();
        priceMap1.add(ResourceType.STONE, 10);
        System.out.println("price 1: "+priceMap1);

        ResourceMap priceMap2 = new ResourceMap();
        priceMap2.add(ResourceType.GOLD, 10);
        System.out.println("price 2: "+priceMap2);

        System.out.println("resources enough for price 1? "+resourceMap.containsRequiredResources(priceMap1));
        System.out.println("resources enough for price 2? "+resourceMap.containsRequiredResources(priceMap2));

        System.out.println("resources enough for 20 GOLD? "+resourceMap.containsRequiredResource(ResourceType.GOLD,20));
        System.out.println("resources enough for 200 GOLD? "+resourceMap.containsRequiredResource(ResourceType.GOLD,200));
        System.out.println("resources enough for 500 GOLD? "+resourceMap.containsRequiredResource(ResourceType.GOLD,500));
        System.out.println("resources enough for 20 WOOD? "+resourceMap.containsRequiredResource(ResourceType.WOOD,20));

        ResourceMap house_price = new ResourceMap(ResourceType.WOOD, 10);
        ResourceMap house_input = new ResourceMap();
        ResourceMap house_output = new ResourceMap(ResourceType.GOLD, 1);
        Item house1 = new Item("HOUSE", "Wooden House", house_price, house_input, house_output );
        Item house2 = new Item("HOUSE", "Wooden House", house_price, house_input, house_output );
        System.out.println("created house 1: "+house1.toString());
        System.out.println("        - price: "+house1.getPrice());
        System.out.println("        - input: "+house1.getInputPerTurn());
        System.out.println("        - output: "+house1.getOutputPerTurn());

        ItemMap itemMap = new ItemMap();
        itemMap.put(house1);
        itemMap.put(house1);
        itemMap.put(house2);
        System.out.println("Created item map: "+itemMap);
        System.out.println("        - total input: "+itemMap.getResourceInput());
        System.out.println("        - total output: "+itemMap.getResourceOutput());


        // TEST RULES
        ItemMap startingBuildings = new ItemMap();

        house_price     = new ResourceMap(ResourceType.FOOD, -3);
        house_input     = new ResourceMap(ResourceType.FOOD, -1);
        house_output    = new ResourceMap(ResourceType.POPULATION, 1);
        Item startingHouse = new Item("HOUSE", "Huts", house_price, house_input, house_output);
        startingBuildings.put(startingHouse);

        ResourceMap field_price     = new ResourceMap(ResourceType.POPULATION, -1);
        ResourceMap field_input     = new ResourceMap();
        ResourceMap field_output    = new ResourceMap(ResourceType.FOOD, 2);
        Item startingField = new Item("FIELD", "Rice Field", field_price, field_input, field_output);
        startingBuildings.put(startingField);

        // PLAYER
        Player player1 = new Player();
        player1.setStartingBuildings(startingBuildings);
        System.out.println("Created Player...");
        System.out.println(player1);

        player1.nextTurn();
        System.out.println("Turn 1 - "+player1);

        player1.nextTurn();
        System.out.println("Turn 2 - "+player1);

        player1.nextTurn();
        System.out.println("Turn 3 - "+player1);

        for (int i=0; i<17; i++) {
            player1.nextTurn();
        }
        System.out.println("Turn 20 - "+player1);

        // TOWER
        int towerWidth = 3;
        Tower tower = new Tower(towerWidth);
        System.out.println("Created Tower... ");
        System.out.println("    - currentLevel:"+tower.getCurrentLevel());
        System.out.println("    - finishedLevels:"+tower.getNumberOfFinishedLevels());

        tower.buildBuilding(0, startingHouse);
        tower.buildBuilding(0, startingHouse); // this should be ignore by the tower
        tower.buildBuilding(1, startingField);
        tower.buildBuilding(2, startingField); // should also not be possible
        System.out.println("    - buildings: "+tower.getBuildings());

        // building more stuff
        Item house_1_2 = new Item("HOUSE", "Huts", house_price, house_input, house_output);
        tower.buildBuilding(2, house_1_2);

        Item house_2_0 = new Item("HOUSE", "Huts", house_price, house_input, house_output);
        tower.buildBuilding(0, house_2_0);

        Item house_2_1 = new Item("HOUSE", "Huts", house_price, house_input, house_output);
        tower.buildBuilding(1, house_2_1);

        Item house_2_2 = new Item("HOUSE", "Huts", house_price, house_input, house_output);
        tower.buildBuilding(2, house_2_2);

        Item house_3_0 = new Item("HOUSE", "Huts", house_price, house_input, house_output);
        tower.buildBuilding(0, house_3_0);

        System.out.println("built 5 more houses  ");
        System.out.println("    - currentLevel:"+tower.getCurrentLevel());
        System.out.println("    - finishedLevels:"+tower.getNumberOfFinishedLevels());
        System.out.println("    - buildings: "+tower.getBuildings());
        System.out.println("    - building output: "+tower.getBuildings().getResourceOutput());

    }
}
