package fr.viteducode.kafkaclient.producer.custom;

public class Champion implements CustomData {

    private Long id;

    private String name;

    public Champion(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
