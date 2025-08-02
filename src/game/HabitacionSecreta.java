package game;

import java.util.Scanner;

public class HabitacionSecreta implements Room {
    private boolean activaFinalAlternativo = false;

    public boolean activaFinalAlternativo() {
        return activaFinalAlternativo;
    }

    @Override
    public void enter(Player player) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("La llave encaja en la cerradura... La puerta se abre con un chirrido que te hiela la sangre.");
        System.out.println("La habitación está oscura, pero ves un espejo cubierto con un velo raído.");
        System.out.println("Al acercarte, escuchas una voz tenue susurrando tu nombre...");

        System.out.print("¿Deseas quitar el velo y mirar el espejo? (si/no): ");
        String decision = scanner.nextLine().trim().toLowerCase();

        if (decision.equals("si")) {
            System.out.println("\nSientes un tirón en el alma mientras el velo cae al suelo.");
            System.out.println("El espejo refleja una figura detrás de ti... pero al voltearte, no hay nadie.");
            System.out.println("La voz de Helena grita dentro de tu mente: \"¡AHORA ESTÁS CONMIGO!\"");
            activaFinalAlternativo = true;
        } else {
            System.out.println("Decides no mirar. El silencio vuelve, pero algo sigue acechando desde la oscuridad.");
        }
    }

    @Override
    public String getDescription() {
        return "Una puerta antigua, cubierta de óxido y marcas de uñas. Parece haber sido cerrada por siglos.";
    }
}
