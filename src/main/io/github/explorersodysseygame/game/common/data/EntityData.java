package io.github.explorersodysseygame.game.common.data;

public class EntityData extends DataHolder {

    public EntityData(String name, Integer health, Integer maxHealth, Integer hunger, Integer maxHunger) {
        super(name);
        this.addData(new DataObject("health", String.valueOf(health)));
        this.addData(new DataObject("maxHealth", String.valueOf(maxHealth)));
        this.addData(new DataObject("hunger", String.valueOf(hunger)));
        this.addData(new DataObject("maxHunger", String.valueOf(maxHunger)));
    }
    public EntityData(String name, Integer health, Integer maxHealth, Integer hunger) {
        super(name);
        this.addData(new DataObject("health", String.valueOf(health)));
        this.addData(new DataObject("maxHealth", String.valueOf(maxHealth)));
        this.addData(new DataObject("hunger", String.valueOf(hunger)));
        this.addData(new DataObject("maxHunger", String.valueOf(25)));
    }
    public EntityData(String name, Integer health, Integer maxHealth) {
        super(name);
        this.addData(new DataObject("health", String.valueOf(health)));
        this.addData(new DataObject("maxHealth", String.valueOf(maxHealth)));
        this.addData(new DataObject("hunger", String.valueOf(25)));
        this.addData(new DataObject("maxHunger", String.valueOf(25)));
    }
    public EntityData(String name, Integer health) {
        super(name);
        this.addData(new DataObject("health", String.valueOf(health)));
        this.addData(new DataObject("maxHealth", String.valueOf(100)));
        this.addData(new DataObject("hunger", String.valueOf(25)));
        this.addData(new DataObject("maxHunger", String.valueOf(25)));
    }
    public EntityData(String name) {
        super(name);
        this.addData(new DataObject("health", String.valueOf(100)));
        this.addData(new DataObject("maxHealth", String.valueOf(100)));
        this.addData(new DataObject("hunger", String.valueOf(25)));
        this.addData(new DataObject("maxHunger", String.valueOf(25)));
    }
}
