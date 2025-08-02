package game;

public class EmptyRoom implements Room {
    @Override
    public void enter(Player player) {
        System.out.println("La habitación está vacía, pero sientes que alguien te observa...");
    }

    @Override
    public String getDescription() {
        return "Una habitación polvorienta con las paredes agrietadas. El silencio es sepulcral.";
    }
}
