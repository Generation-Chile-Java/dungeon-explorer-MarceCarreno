package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import game.DiarioHelena;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║   Bienvenide a la Mansión de Helena    ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("Tu misión: Encontrar el cuerpo de Helena y liberarla de su tormento eterno.");
        System.out.print("Ingresa tu nombre, visitante valiente: ");
        String nombreJugador = scanner.nextLine();

        Player player = new Player(nombreJugador, 100);

        List<Room> mansion = new ArrayList<>();
        mansion.add(new EmptyRoom()); // 0
        mansion.add(new TreasureRoom(() -> "Una llave oxidada", scanner)); // 1
        mansion.add(new EnemyRoom("Un espectro envuelto en sombras", 20)); // 2
        mansion.add(new TreasureRoom(new DiarioHelena(), scanner)); // 3
        mansion.add(new EnemyRoom("Una muñeca con ojos humanos", 30)); // 4
        mansion.add(new TreasureRoom(() -> "El cuerpo sin alma de Helena", scanner)); // 5

        int currentRoom = 0;
        boolean finalAlternativoDesbloqueado = false;

        while (player.isAlive() && currentRoom < mansion.size()) {
            System.out.println("\n══════════════════════════════════");
            System.out.println("Te encuentras en la habitación " + (currentRoom + 1) + "...");
            System.out.println(mansion.get(currentRoom).getDescription());
            mansion.get(currentRoom).enter(player);
            player.showStatus();

            // Habitacion secreta aparece justo después de la habitación 4
            if (currentRoom == 4) {
                if (player.tieneObjeto("Una llave oxidada")) {
                    System.out.print("Encuentras una puerta oxidada... ¿Quieres usar la llave para abrirla? (si/no): ");
                    String decisionLlave = scanner.nextLine().trim().toLowerCase();
                    if (decisionLlave.equals("si")) {
                        HabitacionSecreta habitacionSecreta = new HabitacionSecreta();
                        System.out.println(habitacionSecreta.getDescription());
                        habitacionSecreta.enter(player);

                        if (habitacionSecreta.activaFinalAlternativo()) {
                            finalAlternativoDesbloqueado = true;
                        }
                    } else {
                        System.out.println("Ignoras la puerta y sigues avanzando...");
                    }
                } else {
                    System.out.println("Ves una puerta antigua, pero no tienes ninguna llave que encaje.");
                }
            }

            if (!player.isAlive()) {
                System.out.println("Una oscuridad te envuelve... Has sido poseíde. Fin del juego.");
                break;
            }

            if (currentRoom == mansion.size() - 1) {
                if (finalAlternativoDesbloqueado) {
                    System.out.println("Has encontrado el cuerpo de Helena.");
                    System.out.println("Pero su alma ya no está allí... Está contigo.");
                    System.out.println("Mientras cierras los ojos, el espejo te llama una vez más.");
                    System.out.println("✨ Final Alternativo: Atado a Helena. La maldición continúa contigo.");
                } else {
                    System.out.println("Has encontrado el cuerpo de Helena. Su alma puede descansar en paz...");
                    System.out.println("🌙 Final Normal: Liberación. Has roto el ciclo del tormento.");
                }
                break;
            }

            // Menú de decisiones del jugador
            boolean decisionTomada = false;
            while (!decisionTomada) {
                System.out.print("¿Qué deseas hacer ahora? (avanzar / inventario / salir): ");
                String accion = scanner.nextLine().trim().toLowerCase();

                switch (accion) {
                    case "avanzar":
                        currentRoom++;
                        decisionTomada = true;
                        break;
                    case "inventario":
                        player.mostrarInventarioYLeer();
                        break;
                    case "salir":
                        System.out.println("Abandonaste la mansión... El misterio de Helena quedará sin resolver.");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Opción no válida. Intenta con: avanzar / inventario / salir");
                        break;
                }
            }
        }

        scanner.close();
    }
}
