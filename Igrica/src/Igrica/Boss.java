package Igrica;

import java.util.Random;

public class Boss  {

	private String name;
	private int health = 120;
	private int damage = 12;
	private Player player;
	private int level = 1;
	private int missChance=20;

	public Boss(String name) {
		this.name = name;
	}

	public void levelUp(int level) {
		this.health = 100 + level * 20;
		this.damage = 10 + level * 2;
	}


	public String Atack() {
		
		Random r = new Random();
		if(r.nextInt(100)<missChance)
		{
			return "BOSS MISSED AN ATACK! DAMAGE DONE 0";
		}
		else 
		{
			player.setHealth(player.getHealth() - this.damage);
			if (player.getHealth() < 0) {
				player.setHealth(0);
			}
			return "Boss damage done " + this.damage + " to : " + player.getUserName() + "  health remaining: "
					+ player.getHealth();
		}
		
	}

	public void Heal() {
		this.setHealth(this.getHealth() + 20);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMissChance() {
		return missChance;
	}

	public void setMissChance(int missChance) {
		this.missChance = missChance;
	}
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
}
