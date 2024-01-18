package Igrica;
import java.util.Random;

public class Player  {

	private String username;
	private Weapon weapon;
	private int health = 100;
	private int maxHealth;
	private Map mapa;
	private Boss boss;
	private Armor armor;
	private int energy = 10;
	private int Potion = 0;
	private int maxLevel;
	private int gold;

	public static Player createPlayer(String name, String className, int mapID, int maxLevel) {
		Player player = null;
		Map mapa = new Map(mapID);
		Boss boss = new Boss("Rokva");

		if (className.startsWith("B")) {
			player = new Barbarian(name, mapa, boss, maxLevel);
			Armor armor = new Armor(player);
			player.setArmor(armor);
			player.checkForArmor();
			boss.setPlayer(player);
		}
		if (className.startsWith("W")) {
			player = new Warlock(name, mapa, boss, maxLevel);
			Armor armor = new Armor(player);
			player.setArmor(armor);
			player.checkForArmor();
			boss.setPlayer(player);
		}
		if (className.startsWith("H")) {
			player = new Hunter(name, mapa, boss, maxLevel);
			Armor armor = new Armor(player);
			player.setArmor(armor);
			player.checkForArmor();
			boss.setPlayer(player);
		}

		return player;
	}

	public void checkForArmor() {
		if (armor.isHeadArmor() == true) {
			this.health = this.health + 10;
			if (armor.isChestArmor() == true) {
				this.health = this.health + 10;
				if (armor.isLegArmor() == true) {
					this.health = this.health + 10;
					if (armor.isFeetArmor() == true) {
						this.health = this.health + 10;
					}
				}
			}
		}
	}

	public Player() {

	}

	public Player(String name) {
		this.username = name;
	}

	public Player(String username, Map mapa) {
		this.setMapa(mapa);
		this.username = username;
	}

	public Player(String username, Map mapa, Boss boss) {
		this.setMapa(mapa);
		this.setBoss(boss);
		this.username = username;

	}
	public void setRemainingValues(int armor,int WeaponEnchant,int maxHealth,int potion, int gold)
	{
		getArmor().setArmor(armor);
		getWeapon().SetWeaponEnchantDamage(WeaponEnchant);
		setMaxHealth(maxHealth);
		setPotion(potion);
		setGold(gold);
	}

	public void reset() {
		this.health = 100;
		this.energy = 10;
	}

	public String Atack() {
		if (this.energy > 0) {
			Random rand = new Random();
			if (rand.nextInt(4) == 0) {
				boss.setHealth(boss.getHealth() - (weapon.getWeaponDamage() * 2));
				if (boss.getHealth() < 0) {
					boss.setHealth(0);
				}
				energy--;
				return " CRITICAL STRIKE ->" + weapon.getWeaponDamage() * 2 + " damage done " + " Boss Health:"
						+ boss.getHealth() + " energija:" + energy + "\n";
			} else {
				boss.setHealth(boss.getHealth() - weapon.getWeaponDamage());
				energy--;
				if (boss.getHealth() < 0) {
					boss.setHealth(0);
				}
				return "Successful Strike ->" + weapon.getWeaponDamage() + " damage done " + " Boss Health:"
						+ boss.getHealth() + " energija:" + energy + "\n";
			}

		} else {
			System.out.println("\tEnergy is 0 you cant do anything!");
			return "\tEnergy is 0 you cant do anything ! \n";
		}
	}

	public String Heal() {
		if (boss.getHealth() <= 0) {
			return "You killed the boss!";
		}
		if (this.energy > 0) {
			this.setHealth(this.getHealth() + 20);
			this.setEnergy(this.getEnergy() - 1);
			System.out.println("\t Successful heal +20 -> player health:" + this.getHealth() + " Boss Health:"
					+ boss.getHealth() + " energija:" + energy);
			return "\tSucc +20 -> player health:" + this.getHealth() + " Boss Health:" + boss.getHealth() + " energija:"
					+ energy + "\n";
		} else {
			System.out.println("\tEnergy is 0 you cant do anything !");
			return "\tEnergy is 0 you cant do anything ! \n";
		}
	}

	public String SkipTurn() {
		if (boss.getHealth() <= 0) {
			return "You killed the boss!";
		}
		this.setEnergy(this.getEnergy() + 3);
		if (this.energy > 10) {
			this.energy = 10;
			return "Max Energy is 10!";
		}
		System.out.println("\tEnergy +3 -> total energy:" + this.getEnergy() + " Boss Health:" + boss.getHealth());
		return "\tEnergy +3 -> total energy:" + this.getEnergy() + " Boss Health:" + boss.getHealth() + "\n";
	}

	public String UsePotion() {
		if (boss.getHealth() <= 0) {
			return "You killed the boss!";
		}
		this.Potion--;
		this.health = this.health + 20;
		System.out.println(
				"\t Potion Used! Health +20! Health: " + this.getHealth() + " Boss Health:" + boss.getHealth());
		return "\t Health +20! Health: " + this.getHealth() + " Boss Health:" + boss.getHealth() + "\n:";
	}

	public void setUserName(String username) {
		this.username = username;
	}

	public String getUserName() {
		return this.username;

	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public Map getMapa() {
		return mapa;
	}

	public void setMapa(Map mapa) {
		this.mapa = mapa;
	}

	public Boss getBoss() {
		return boss;
	}

	public void setBoss(Boss boss) {
		this.boss = boss;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public int getPotion() {
		return Potion;
	}

	public void setPotion(int potion) {
		Potion = potion;
	}

	public Weapon getWeapon() {
		return weapon;
	}


	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public int getMaxLevel() {
		return maxLevel;
	}

	public void setMaxLevel(int maxLevel) {
		this.maxLevel = maxLevel;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public Armor getArmor() {
		return armor;
	}

	public void setArmor(Armor armor) {
		this.armor = armor;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

}
