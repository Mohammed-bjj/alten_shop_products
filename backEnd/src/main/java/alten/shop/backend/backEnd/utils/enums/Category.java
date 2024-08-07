package alten.shop.backend.backEnd.utils.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Category {

    ACCESSORIES("Accessories"),
    FITNESS("Fitness"),
    CLOTHING("Clothing"),
    ELECTRONICS("Electronics");

    private final String value;

    Category(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static Category fromValue(String value) {
        for (Category category : Category.values()) {
            if (category.value.equalsIgnoreCase(value)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Invalid value for Category: " + value);
    }
}

