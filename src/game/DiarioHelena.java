package game;

public class DiarioHelena implements GameObject {
    @Override
    public String getName() {
        return "Diario de Helena";
    }

    public String leerContenido() {
        return """
        📖 Páginas marchitas cubiertas de manchas de sangre...

        5 de octubre:
        Mamá ya no habla. Pasa los días mirando el espejo del pasillo.
        A veces creo que el espejo le responde...

        12 de octubre:
        Algo entró en la mansión. No camina. No respira. Pero está en todas partes.
        Se esconde en las paredes y en los susurros.

        18 de octubre:
        He escondido la llave del sótano detrás del retrato de la abuela.
        Si alguien encuentra esto... Por favor, no vengas.

        ✍︎ - Helena
        """;
    }
}
