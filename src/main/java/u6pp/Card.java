package u6pp;

public class Card {

    public static String RED = "RED";
    public static String GREEN = "GREEN";
    public static String BLUE = "BLUE";
    public static String YELLOW = "YELLOW";

    public static String ZERO = "0";
    public static String ONE = "1";
    public static String TWO = "2";
    public static String THREE = "3";
    public static String FOUR = "4";
    public static String FIVE = "5";
    public static String SIX = "6";
    public static String SEVEN = "7";
    public static String EIGHT = "8";
    public static String NINE = "9";

    public static String DRAW_2 = "DRAW_2";
    public static String REVERSE = "REVERSE";
    public static String SKIP = "SKIP";
    public static String WILD = "WILD";
    public static String WILD_DRAW_4 = "WILD_DRAW_4";

    // Wild color is the default color for wilds, before they are played. 
    public static String[] COLORS = {RED, GREEN, BLUE, YELLOW, WILD, WILD_DRAW_4}; 
    public static String[] VALUES = {ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, 
        DRAW_2, REVERSE, SKIP, WILD, WILD_DRAW_4};

    // start you code here
    
    public static boolean isInArray(String str, String[] arr) {
        for (String s : arr) {
            if (s.equals(str)) {
                return true;
            }
        }
        return false;
    }
    
    
    private String color;
    private final String value;

    public Card(String color, String value) {
        if (color == null || value == null) {
            throw new IllegalArgumentException("Color and value cannot be null");
        }

        if (!value.equals(WILD) && color.equals(WILD) && !value.equals(WILD_DRAW_4)) {
            throw new IllegalArgumentException("Cannot set WILD color on non-wild card");
        }

        this.color = color;
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public String getValue() {
        return value;
    }
//checks if card if valid card
    public boolean canPlayOn(Card other) {
        if (other == null) {
            return false;
        }
    //another condition
        if (this.value.equals(WILD) || this.color.equals(WILD) || this.color.equals(WILD_DRAW_4)|| this.value.equals(WILD_DRAW_4)) {
            return true;
        }
        
        if (this.value.equals(WILD_DRAW_4)) {
            return true;
        }
    //checks if color and value are valid
        if (this.color.equals(other.color) || this.value.equals(other.value)) {
            return true;
        }
    
        if (other.color.equals(WILD) && other.getColor() != null && this.color.equals(other.getColor())) {
            return true;
        }
    
        return false;
    }

    public boolean trySetColor(String color) {
        boolean isVal = false;
        for(int i = 0; i < COLORS.length; i++){
            if(COLORS[i].equals(color)){
                isVal = true;
            }
        }
        if(!isVal){
            return false;
        }
        if (color == null || (!color.equals(WILD) && !this.color.equals(WILD) && !this.color.equals(WILD_DRAW_4))) {
            return false;
        }
        if (color.equals(this.color)) {
            return false;
        }
        if (!color.equals(WILD) && !this.value.equals(WILD)) {
            return false;
        }
        this.color = color;
        return true;
    }

    @Override
    public String toString() {
        return color + " " + value;
    }

	public String getRank() {
		return null;
	}
}



