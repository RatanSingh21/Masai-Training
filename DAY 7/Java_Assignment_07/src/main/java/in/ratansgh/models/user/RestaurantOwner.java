package in.ratansgh.models.user;

import in.ratansgh.models.enums.UserRole;
import in.ratansgh.models.restaurant.Restaurant;

public class RestaurantOwner extends User {
    private Restaurant restaurant;

    public RestaurantOwner(String id, String name, Restaurant restaurant) {
        super(id, name, UserRole.RESTAURANT_OWNER);
        this.restaurant = restaurant;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }
}


