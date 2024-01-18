package Igrica;

public class Hunter extends Player {

	public Hunter() {

	}

	public Hunter(String name) {
		super(name);
	}

	public Hunter(String username, Map mapa) {
		super(username, mapa);
	}

	public Hunter(String username, Map mapa, Boss boss, int maxLevel) {
		super(username);
		super.setHealth(100);
		super.setMaxHealth(100);
		Weapon weapon = new Weapon(3);
		super.setWeapon(weapon);
		super.setMaxLevel(maxLevel);
		if (mapa.getName() == "Desert") {
			weapon.setWeaponDamage(19);

		}
	}

	public void reset() {

		super.setEnergy(10);
		super.setHealth(100);
		if (super.getArmor().isHeadArmor()) {
			super.setHealth(110);
			if (super.getArmor().isChestArmor()) {
				super.setHealth(120);
			}
			if (super.getArmor().isLegArmor()) {
				super.setHealth(130);
			}
			if (super.getArmor().isFeetArmor()) {
				super.setHealth(140);
			}
		}
	}
}
