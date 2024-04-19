package inia.comp;

public enum TitleMode {
		
    MAIN("TITLE"),
    SUB("SUBTITLE");

    private String name;

    TitleMode(String name) {
        this.setName(name);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}