package game;

import java.util.Scanner;

public class TreasureRoom implements Room {
    private final GameObject treasure;
    private final Scanner scanner;

    public TreasureRoom(GameObject treasure, Scanner scanner) {
        this.treasure = treasure;
        this.scanner = scanner;
    }

    @Override
    public void enter(Player player) {
        System.out.println("Encuentras algo: " + treasure.getName());
        System.out.print("¿Deseas recogerlo? (si/no): ");
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("si")) {
            if (player.tieneObjeto(treasure.getName())) {
                System.out.println("Ya tienes " + treasure.getName() + " en tu inventario.");
            } else {
                player.addToInventory(treasure);

                if (treasure instanceof DiarioHelena diario) {
                    System.out.println("\n🕯 Has abierto el diario de Helena...\n");
                    System.out.println(diario.leerContenido());
                }
            }

        } else {
            System.out.println("Decides dejar el objeto donde estaba...");
        }
    }

    @Override
    public String getDescription() {
        return "Un resplandor tenue ilumina un objeto entre las sombras.";
    }
}
