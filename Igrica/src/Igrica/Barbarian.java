package Igrica;

public class Barbarian extends Player {

	public Barbarian() {

	}

	public Barbarian(String name) {
		super(name);

	}

	public Barbarian(String username, Map mapa) {
		super(username, mapa);
	}

	public Barbarian(String username, Map mapa, Boss boss, int maxLevel) {
		super(username, mapa, boss);
		super.setHealth(120);
		super.setMaxHealth(120);
		Weapon weapon = new Weapon(1);
		super.setWeapon(weapon);
		super.setMaxLevel(maxLevel);
		if (mapa.getName() == "Jungle") {
			weapon.setWeaponDamage(20);
		}
	}

	public void reset() {
		super.setEnergy(10);
		super.setHealth(120);
		if (super.getArmor().isHeadArmor()) {
			super.setHealth(130);
			if (super.getArmor().isChestArmor()) {
				super.setHealth(140);
			}
			if (super.getArmor().isLegArmor()) {
				super.setHealth(150);
			}
			if (super.getArmor().isFeetArmor()) {
				super.setHealth(160);
			}
		}
	}
}
