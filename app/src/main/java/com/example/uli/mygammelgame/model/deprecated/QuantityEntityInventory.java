package com.example.uli.mygammelgame.model.deprecated;

import java.util.HashMap;

/**
 * Created by uli on 2/3/17.
 */

public class QuantityEntityInventory {

    private HashMap <String,QuantityEntity> entities;

    public QuantityEntityInventory() {
        entities = new HashMap<String,QuantityEntity>();
    }

    public void addEntity(QuantityEntity entity) {
        String k = entity.getType();
        if (entities.containsKey(k))
            entities.get(k).changeQuantity(entity.getQuantity());
        else
            entities.put(k,entity);
    }

    public void removeEntity(QuantityEntity entity) {
        String k = entity.getType();
        if (entities.containsKey(k))
            entities.remove(k);
    }

    public void changeAmount(QuantityEntity entity) {
        String k = entity.getType();
        if (entities.containsKey(k))
            entities.get(k).changeQuantity(entity.getQuantity());
        else {
            entities.put(entity.getType(), entity.copy());
        }
    }

    public int getAmount(String entityType) {
        if (entities.containsKey(entityType))
            return entities.get(entityType).getQuantity();
        else
            return 0;
    }

    public QuantityEntityInventory applyToInventory(QuantityEntityInventory targetInventory) {
        HashMap <String,QuantityEntity> targetEntities = targetInventory.getEntities();
        for (String k : targetEntities.keySet()) {
            if (this.entities.containsKey(k)) {
                QuantityEntity targetEntity = targetEntities.get(k);
                int amount = this.entities.get(k).getQuantity();
                targetEntity.changeQuantity(amount);
            }
        }
        return targetInventory;
    }

    public boolean containsAmountsOf(QuantityEntityInventory targetInventory) {
        HashMap <String,QuantityEntity> targetEntities = targetInventory.getEntities();
        for (String k : targetEntities.keySet()) {
            if (this.entities.containsKey(k)) {
                QuantityEntity targetEntity = targetEntities.get(k);
                int myAmount = this.entities.get(k).getQuantity();
                int amountNeeded = targetEntity.getQuantity();
                if (myAmount < amountNeeded)
                    return false;
            }
            else
                return false;
        }
        return true;
    }

    public HashMap<String,QuantityEntity> getEntities() {
        return entities;
    }

    public String toString() {
        return "QuantityInventory "+entities.toString();
    }
}
