package com.example.uli.mygammelgame.model.inventory;

import android.util.Log;

import java.util.HashMap;

/**
 * Created by uli on 2/3/17.
 */

public class ResourceMap extends HashMap<ResourceType,Integer> {

    public ResourceMap() {
        initAllResourcesWithZero();
    }

    public ResourceMap(ResourceType resourceType, int amount) {
        initAllResourcesWithZero();
        add(resourceType, amount);
    }

    private void initAllResourcesWithZero() {
        for (ResourceType r : ResourceType.values()) {
            this.add(r,0);
        }
    }


    public ResourceMap add(ResourceType resourceType, int amount) {
        if (this.containsKey(resourceType)) {
            int newValue = this.get(resourceType).intValue() + amount;
            put(resourceType, newValue);
            return this;
        }
        else {
            put(resourceType, amount);
            return this;
        }
    }

    public boolean containsRequiredResources(ResourceMap requiredResources) {
        for (ResourceType r : requiredResources.keySet()) {
            if (this.containsKey(r)) {
                Integer availableAmount = this.get(r);
                Integer requiredAmount = requiredResources.get(r);
                if (availableAmount < requiredAmount)
                    return false;
            }
            else
                return false;
        }
        return true;
    }

    public boolean containsRequiredResource(ResourceType resourceType, int amount) {
        ResourceMap requirements = new ResourceMap();
        requirements.add(resourceType, amount);
        return containsRequiredResources(requirements);
    }

    public ResourceMap merge(ResourceMap resources) {
        for (Entry<ResourceType, Integer> entry : resources.entrySet()) {
            add(entry.getKey(), entry.getValue() );
        }
        return this;
    }


}
