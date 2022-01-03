package fr.viteducode.kafkaclient.consumer.customserializer;

public class Champion {

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