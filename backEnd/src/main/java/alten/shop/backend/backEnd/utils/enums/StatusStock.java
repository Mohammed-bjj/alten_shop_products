package alten.shop.backend.backEnd.utils.enums;

public enum StatusStock {
    INSTOCK("INSTOCK"),
    LOWSTOCK("LOWSTOCK"),
    OUTOFSTOCK("OUTOFSTOCK");

    private final String value;


    StatusStock(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
