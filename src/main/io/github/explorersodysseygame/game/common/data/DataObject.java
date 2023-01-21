package io.github.explorersodysseygame.game.common.data;

public class DataObject {
    protected String name;
    protected String value;

    public DataObject(String nam, String val) {
        name = nam;
        value = val;
    }

    public String getName() {
        return name;
    }
    public String getValue() {
        return value;
    }

    public String setName(String nam) {
        name = nam;
        return name;
    }
    public String setValue(String val) {
        value = val;
        return value;
    }


}
