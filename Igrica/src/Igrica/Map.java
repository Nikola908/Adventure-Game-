package Igrica;

enum MapType {
	Swamp, Jungle, Desert
}

public class Map {

	private String name;

	public Map() {

	}

	public Map(int i) {

		if (i == 1) {
			this.name = MapType.Swamp.toString();
		} else if (i == 2) {
			this.name = MapType.Jungle.toString();
		} else if (i == 3) {
			this.name = MapType.Desert.toString();
		} else
			System.out.println("greska");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
