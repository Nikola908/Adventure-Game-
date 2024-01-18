package Igrica;

enum Wpn {
	Axe, Staff, BowAndArrow
}

public class Weapon {

	private String name;
	private int weaponDamage;
	public Map map;
	private int weaponEnchant;

	public Weapon() {

	}

	public Weapon(int i) {

		if (i == 1) {
			this.name = Wpn.Axe.toString();
			this.weaponDamage = 18;
		} else if (i == 2) {
			this.name = Wpn.Staff.toString();
			this.weaponDamage = 20;
		} else if (i == 3) {
			this.name = Wpn.BowAndArrow.toString();
			this.weaponDamage = 17;
		}
	}

	public String getName() {
		return name;
	}

	public void SetWeaponEnchantDamage(int i) {
		this.weaponEnchant=i;
		this.weaponDamage = this.weaponDamage + i * 3;
	}



	public int getWeaponDamage() {
		return this.weaponDamage;
	}

	public void setWeaponDamage(int weaponDamage) {
		this.weaponDamage = weaponDamage;
	}

	public int getWeaponEnchant() {
		return weaponEnchant;
	}

	public void setWeaponEnchant(int weaponEnchant) {
		this.weaponEnchant = weaponEnchant;
	}
}
