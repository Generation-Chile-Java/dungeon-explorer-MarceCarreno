package game;

public class EnemyRoom implements Room {
    private final String enemyName;
    private final int damage;

    public EnemyRoom(String enemyName, int damage) {
        this.enemyName = enemyName;
        this.damage = damage;
    }

    @Override
    public void enter(Player player) {
        System.out.println("¡" + enemyName + " aparece y te ataca!");
        player.takeDamage(damage);
    }

    @Override
    public String getDescription() {
        return "El aire está helado... Algo maligno habita esta habitación.";
    }
}
