package Igrica;

public class Armor {

	private boolean headArmor = false;
	private boolean chestArmor = false;
	private boolean legArmor = false;
	private boolean feetArmor = false;
	private Player player;

	public Armor() {

	}

	public Armor(Player player) {
		this.player = player;
	}

	public void setArmor(int i) {
		if (i == 1) {
			this.headArmor = true;
			player.setHealth(player.getHealth() + 10);
		} else if (i == 2) {
			this.headArmor = true;
			this.chestArmor = true;
			player.setHealth(player.getHealth() + 20);
			player.getBoss().setMissChance(30);
		} else if (i == 3) {
			this.headArmor = true;
			this.chestArmor = true;
			this.legArmor = true;
			player.setHealth(player.getHealth() + 30);
		} else if (i == 4) {
			this.headArmor = true;
			this.chestArmor = true;
			this.legArmor = true;
			this.feetArmor = true;
			player.setHealth(player.getHealth() + 40);
			player.getBoss().setMissChance(40);
		}
	}

	public boolean isFeetArmor() {
		return feetArmor;
	}

	public void setFeetArmor(boolean feetArmor) {
		this.feetArmor = feetArmor;
		if (feetArmor == true) {
			player.setHealth(player.getHealth() + 10);
			player.setMaxHealth((player.getHealth() + 10));
			player.getBoss().setMissChance(40);
		}
	}

	public boolean isLegArmor() {
		return legArmor;
	}

	public void setLegArmor(boolean legArmor) {

		this.legArmor = legArmor;
		if (legArmor == true) {
			player.setHealth(player.getHealth() + 10);
			player.setMaxHealth((player.getHealth() + 10));
		}
	}

	public boolean isChestArmor() {
		return chestArmor;
	}

	public void setChestArmor(boolean chestArmor) {
		this.chestArmor = chestArmor;
		if (chestArmor == true) {
			player.setHealth(player.getHealth() + 10);
			player.setMaxHealth((player.getHealth() + 10));
			player.getBoss().setMissChance(30);
		}
	}

	public boolean isHeadArmor() {
		return headArmor;
	}

	public void setHeadArmor(boolean headArmor) {
		this.headArmor = headArmor;
		if (headArmor == true) {
			player.setHealth(player.getHealth() + 10);
			player.setMaxHealth((player.getHealth() + 10));
		}

	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}
