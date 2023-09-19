import java.util.Random;

public class Main {
    public static int bossHeals = 700;
    public static int bossDamage = 50;
    public static int[] heroHeals = {250, 230, 210, 200};
    public static int[] heroDamage = {25, 25, 20, 0};
    public static String[] heroAttackType = {"Warrior", "MagicK", "Telepat", "Medic"};
    public static String bossBarrier;
    public static int round = 0;

    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            round++;
            round();
            medic();
        }
    }

    public static void bossTypeBarrier() {
        Random random = new Random();
        int index = random.nextInt(heroAttackType.length);
        bossBarrier = heroAttackType[index];
        System.out.println("Boss barrier type for " + bossBarrier);
        bossHeals = bossHeals + heroDamage[index];
    }

    public static boolean isGameFinished() {
        boolean allHeroesDead = true;
        if (bossHeals <= 0) {
            System.out.println("HEROES WIN!!!");
            return true;
        }
        for (int i = 0; i < heroHeals.length; i++) {
            if (heroHeals[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("BOSS WIN!!!");
        }
        return allHeroesDead;
    }

    public static void printStatistics() {
        System.out.println("STATISTIC");
        System.out.println("Boss heals " + bossHeals + ";" + " damage " + bossDamage);
        for (int i = 0; i < heroAttackType.length; i++) {
            System.out.println("Hero heals " + heroAttackType[i]
                    + " " + heroHeals[i] + " damage " + heroDamage[i]);
        }
    }

    public static void bossHits() {
        for (int i = 0; i < heroAttackType.length; i++) {
            if (heroHeals[i] < bossDamage) {
                heroHeals[i] = 0;
            } else {
                heroHeals[i] = heroHeals[i] - bossDamage;
            }
        }
    }

    public static void heroHits() {
        for (int i = 0; i < heroAttackType.length; i++) {
            if (bossHeals < heroDamage[i]) {
                bossHeals = 0;
            } else {
                bossHeals = bossHeals - heroDamage[i];
            }
        }
    }

    public static void medic() {
        boolean medicAlive = false;
        medicAlive = false;
        int heal = 50;
        for (int i = 0; i < heroAttackType.length; i++) {
            if (heroAttackType[i].equals("Medic") && heroHeals[i] > 0) {
                medicAlive = true;
            }
        }
        for (int i = 0; i < heroAttackType.length; i++) {
            if (heroHeals[i] < 100 && medicAlive && heroHeals[i] > 0) {
                System.out.println("Medic heals" + heroAttackType[i]);
                heroHeals[i] = heroHeals[i] + heal;
                break;
            }
        }
    }


    public static void round() {
        System.out.println("ROUND - " + round);
        bossHits();
        heroHits();
        medic();
        bossTypeBarrier();
        printStatistics();
    }
}