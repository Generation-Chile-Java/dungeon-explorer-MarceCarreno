package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Player {
    private final String name;
    private int health;
    private final List<GameObject> inventory;

    public Player(String name, int health) {
        this.name = name;
        this.health = health;
        this.inventory = new ArrayList<>();
    }

    public void takeDamage(int damage) {
        this.health -= damage;
        System.out.println(name + " recibe " + damage + " puntos de daño.");
    }

    public void addToInventory(GameObject item) {
        this.inventory.add(item);
        System.out.println(item.getName() + " ha sido añadido a tu inventario.");
    }

    public void showStatus() {
        System.out.println("\n--- Estado del jugador ---");
        System.out.println("Nombre: " + name);
        System.out.println("Salud: " + health + "\u2665");
        String objetos = inventory.isEmpty() ? "Vacío" :
                inventory.stream().map(GameObject::getName).collect(Collectors.joining(", "));
        System.out.println("Inventario: " + objetos);
        System.out.println("--------------------------\n");
    }

    public boolean isAlive() {
        return this.health > 0;
    }

    public void mostrarInventarioYLeer() {
        if (inventory.isEmpty()) {
            System.out.println("Tu inventario está vacío.");
            return;
        }

        System.out.println("\n--- Inventario ---");
        for (int i = 0; i < inventory.size(); i++) {
            System.out.println((i + 1) + ". " + inventory.get(i).getName());
        }

        System.out.print("¿Quieres leer algún objeto? Escribe su número o 'no': ");
        Scanner scanner = new Scanner(System.in);
        String entrada = scanner.nextLine().trim();

        try {
            int opcion = Integer.parseInt(entrada);
            if (opcion >= 1 && opcion <= inventory.size()) {
                GameObject objeto = inventory.get(opcion - 1);
                if (objeto instanceof DiarioHelena diario) {
                    System.out.println("\n📖 Leyendo el Diario de Helena:\n");
                    System.out.println(diario.leerContenido());
                } else {
                    System.out.println("Ese objeto no se puede leer.");
                }
            } else {
                System.out.println("Número fuera de rango.");
            }
        } catch (NumberFormatException e) {
            System.out.println("No lees nada.");
        }
    }
    public boolean tieneObjeto(String nombreObjeto) {
        return inventory.stream().anyMatch(obj -> obj.getName().equalsIgnoreCase(nombreObjeto));
    }


}
