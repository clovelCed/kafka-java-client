package fr.viteducode.kafkaclient.producer.custom;

public class Player implements CustomData {

    private Long id;

    private String name;

    private boolean isPlaying;

    public Player(Long id, String name, boolean isPlaying) {
        this.id = id;
        this.name = name;
        this.isPlaying = isPlaying;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isPlaying() {
        return isPlaying;
    }
}
