package Igrica;

public class Warlock extends Player {

	public Warlock() {

	}

	public Warlock(String name) {
		super(name);
	}

	public Warlock(String username, Map mapa) {
		super(username, mapa);
	}

	public Warlock(String username, Map mapa, Boss boss, int maxLevel) {
		super(username, mapa, boss);
		super.setHealth(80);
		super.setMaxHealth(80);
		Weapon weapon = new Weapon(2);
		super.setWeapon(weapon);
		super.setMaxLevel(maxLevel);
		if (mapa.getName() == "Swamp") {
			weapon.setWeaponDamage(22);
		}
	}

	public void reset() {

		super.setEnergy(10);
		super.setHealth(80);
		if (super.getArmor().isHeadArmor()) {
			super.setHealth(90);
			if (super.getArmor().isChestArmor()) {
				super.setHealth(100);
			}
			if (super.getArmor().isLegArmor()) {
				super.setHealth(120);
			}
			if (super.getArmor().isFeetArmor()) {
				super.setHealth(130);
			}
		}
	}

}
