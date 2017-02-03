package com.example.uli.mygammelgame;

import com.example.uli.mygammelgame.model.GameEntity;
import com.example.uli.mygammelgame.model.GameIdGenerator;
import com.example.uli.mygammelgame.model.deprecated.QuantityEntity;
import com.example.uli.mygammelgame.model.deprecated.QuantityEntityInventory;
import com.example.uli.mygammelgame.model.inventory.ResourceMap;
import com.example.uli.mygammelgame.model.inventory.ResourceType;

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
    }
}