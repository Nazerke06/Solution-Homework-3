import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Interface for building a dungeon step by step
public interface IDungeonBuilder {
    IDungeonBuilder setDungeonName(String name); // Sets the dungeon's name
    IDungeonBuilder addRoom(Room room); // Adds a room to the dungeon
    IDungeonBuilder addNPC(NPC npc); // Adds an NPC to the dungeon
    IDungeonBuilder addItem(Item item); // Adds an item to the dungeon
    IDungeonBuilder setPlayer(Player player); // Sets the player character
    IDungeonBuilder addTrap(String trap); // Adds a trap to the dungeon
    IDungeonBuilder addTreasureRoom(Room treasureRoom); // Sets the treasure room
    IDungeonBuilder addSecretPassage(String passage); // Adds a secret passage
    Dungeon build(); // Builds and returns the final dungeon
}

// Implementation of the Dungeon Builder
public class SimpleDungeonBuilder implements IDungeonBuilder {
    private String name;
    private final List<Room> rooms = new ArrayList<>();
    private final List<NPC> npcs = new ArrayList<>();
    private final List<Item> items = new ArrayList<>();
    private Player player;
    private final List<String> traps = new ArrayList<>();
    private Room treasureRoom;
    private final List<String> secretPassages = new ArrayList<>();

    @Override
    public IDungeonBuilder setDungeonName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public IDungeonBuilder addRoom(Room room) {
        rooms.add(room);
        return this;
    }

    @Override
    public IDungeonBuilder addNPC(NPC npc) {
        npcs.add(npc);
        return this;
    }

    @Override
    public IDungeonBuilder addItem(Item item) {
        items.add(item);
        return this;
    }

    @Override
    public IDungeonBuilder setPlayer(Player player) {
        this.player = player;
        return this;
    }

    @Override
    public IDungeonBuilder addTrap(String trap) {
        traps.add(trap);
        return this;
    }

    @Override
    public IDungeonBuilder addTreasureRoom(Room treasureRoom) {
        this.treasureRoom = treasureRoom;
        return this;
    }

    @Override
    public IDungeonBuilder addSecretPassage(String passage) {
        secretPassages.add(passage);
        return this;
    }

    @Override
    public Dungeon build() {
        if (name == null || name.isEmpty()) {
            throw new IllegalStateException("Dungeon must have a name.");
        }
        if (player == null) {
            throw new IllegalStateException("Dungeon must have a player.");
        }
        return new Dungeon(name, rooms, npcs, items, player, traps, treasureRoom, secretPassages);
    }
}

// Dungeon class representing the final constructed dungeon
public class Dungeon {
    private final String name;
    private final List<Room> rooms;
    private final List<NPC> npcs;
    private final List<Item> items;
    private final Player player;
    private final List<String> traps;
    private final Room treasureRoom;
    private final List<String> secretPassages;

    public Dungeon(String name, List<Room> rooms, List<NPC> npcs, List<Item> items, Player player, List<String> traps, Room treasureRoom, List<String> secretPassages) {
        this.name = name;
        this.rooms = Collections.unmodifiableList(new ArrayList<>(rooms)); // Immutable list of rooms
        this.npcs = Collections.unmodifiableList(new ArrayList<>(npcs)); // Immutable list of NPCs
        this.items = Collections.unmodifiableList(new ArrayList<>(items)); // Immutable list of items
        this.player = player;
        this.traps = Collections.unmodifiableList(new ArrayList<>(traps)); // Immutable list of traps
        this.treasureRoom = treasureRoom;
        this.secretPassages = Collections.unmodifiableList(new ArrayList<>(secretPassages)); // Immutable list of secret passages
    }

    @Override
    public String toString() {
        return "Dungeon: " + name + "\nRooms: " + rooms + "\nNPCs: " + npcs + "\nItems: " + items + "\nPlayer: " + player +
                "\nTraps: " + traps + "\nTreasure Room: " + (treasureRoom != null ? treasureRoom.getName() : "None") +
                "\nSecret Passages: " + secretPassages;
    }
}

// Demo class to test the Dungeon Builder
public class MUDBuilderDemo {
    public static void main(String[] args) {
        // Creating rooms, NPCs, items, and player
        Room entrance = new Room("Entrance Hall", "A dimly lit hall with stone walls.");
        Room throneRoom = new Room("Throne Room", "A grand room with a golden throne.");
        Room hiddenChamber = new Room("Hidden Chamber", "A mysterious room full of treasures.");
        NPC boss = new NPC("Dark Overlord");
        Item sword = new Item("Enchanted Sword");
        Player hero = new Player("Hero");

        // Constructing the dungeon using the builder pattern
        Dungeon dungeon = new SimpleDungeonBuilder()
                .setDungeonName("The Dark Keep")
                .addRoom(entrance)
                .addRoom(throneRoom)
                .addNPC(boss)
                .addItem(sword)
                .setPlayer(hero)
                .addTrap("Pitfall Trap")
                .addTreasureRoom(hiddenChamber)
                .addSecretPassage("Hidden tunnel behind the throne")
                .build();

        // Printing the dungeon details
        System.out.println(dungeon);
    }
}
