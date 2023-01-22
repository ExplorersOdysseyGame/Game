package io.github.explorersodysseygame.game.common.data;

import io.github.explorersodysseygame.game.Main;

import java.util.ArrayList;
import java.util.Objects;

public class DataHolder extends ArrayList<DataObject> {

    public final String alias;

    public DataHolder(String name) {
        alias = name;
    }
    public DataObject getData(String name) {
        for (DataObject dataObject : this) {
            String s = dataObject.getName();
            //search the string
            if (name.equals(s)) {
                return dataObject;
            }
        }
        return null;
    }

    public ArrayList<DataObject> listData() {
        StringBuilder data = new StringBuilder();
        ArrayList<DataObject> ret = new ArrayList<>();
        for (DataObject dataObject : this) {
            data.append(String.format("[%s::%s] ", dataObject.getName(), dataObject.getValue()));
            ret.add(dataObject);
        }
        Main.log(String.format("Data in data-holder '%s': %s", alias, data));
        return ret;
    }

    public void addData(DataObject obj) {
        try {
            if (this.getData(obj.getName()) == null) {
                this.add(obj);
            } else {
                throw new Exception("Data already exists in data-holder '%s'!");
            }
        } catch (Exception exc) {
            Main.log(String.format("Unable to add data to data-holder '%s': %s", alias, exc.getMessage()));
        }
    }

    public void removeData(String name) {
        try {
            this.removeIf(dataObject -> Objects.equals(dataObject.getName(), name));
        } catch (Exception exc) {
            Main.log(String.format("Unable to remove data from data-holder '%s': %s", alias, exc.getMessage()));
        }
    }
}
