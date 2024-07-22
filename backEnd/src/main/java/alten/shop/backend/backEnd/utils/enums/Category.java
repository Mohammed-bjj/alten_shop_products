package alten.shop.backend.backEnd.utils.enums;

public enum Category {

    ACCESSORIES("Accessories"),
    FITNESS("Fitness"),
    CLOTHING("Clothing"),
    ELECTRONICS("Electronics");


    private final String value;


    Category(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
