package alten.shop.backend.backEnd.utils.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum StatusStock {

    INSTOCK("INSTOCK"),
    LOWSTOCK("LOWSTOCK"),
    OUTOFSTOCK("OUTOFSTOCK");

    private final String value;


    StatusStock(String value) {
        this.value = value;
    }




    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static StatusStock fromValue(String value) {
        for (StatusStock statusStock : StatusStock.values()) {
            if (statusStock.value.equalsIgnoreCase(value)) {
                return statusStock;
            }
        }
        throw new IllegalArgumentException("Invalid value for StatusStock: " + value);
    }
}

