package Igrica;

import java.io.BufferedReader;
import java.util.Map;

import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JComboBox;

import MVC.Model;

public class Main {

	public static void main(String[] args) throws SQLException {

		HashMap<Integer,Player> map = new HashMap<>();
		Player player = new Player("Nix");
		Player player2 = new Player("Rokva");
		Player player3 = new Player("Rokva");
		Player player4 = new Player("Rokva");
		int capacity =10;
		map.put(1, player);
		for (int i = 1; i<capacity;i++)
		{
			if(!map.containsKey(i))
			{	
				map.put(i, player);
				break;
			}
	   
		}
//		map.put(1, player);
//		map.put(2, player2);
//		System.out.println(map.values());
//		map.remove(1);
		System.out.println(map.values());
		
//		DatabaseConnection dbConnection1 = new DatabaseConnection();
//		Connection connection = dbConnection1.createConnection();
//
//		Statement st = connection.createStatement();
//		ResultSet rs1 = st.executeQuery("select count(*) from player");
//		int rowCount = 0;
//		while (rs1.next()) {
//			rowCount = rs1.getInt(1);
//		}
//
//		String sqlSt = "select * from Player p join Weapon w on (p.ID_weapon=w.ID_weapon) join Map m on (m.ID_map=p.ID_map)";
//		ResultSet rs = st.executeQuery(sqlSt);
//
//		Model model = new Model();
//		model.add(new Player("kurac"));
//		model.add(new Player("Kurac2"));
//		while (rs.next()) {
//			System.out.println(rs.getRow() + " :  " + model.getPlayers().size());
//			if (model.getPlayers().size() >= rs.getRow()) {
//				System.out.println("Preskoci row");
//			} else {
//				model.add(new Player(rs.getString(2)));
//
//			}
//			System.out.println(model.get(rs.getRow() - 1).getUserName());
//			System.out.println(model.getPlayers().size());
//
//		}

//		JComboBox<Integer> jcb = new JComboBox<Integer>();
//		System.out.println(jcb);
//		jcb.addItem(1);
//		jcb.addItem(2);
//		jcb.addItem(3);
//		jcb.addItem(4);
//		jcb.addItem(5);
//		jcb.removeItemAt(0);
//		System.out.println(jcb.getItemCount());
//		System.out.println(jcb.getItemAt(0));
//		 for( int i= jcb.getItemCount();i>1;i--)
//         {
//         	jcb.removeItemAt(i-1);
//         }

//		    
//		DatabaseConnection dbCon = new DatabaseConnection();
//		connection = dbCon.createConnection();
//		int s = 1;
//		 PreparedStatement ps ;
//   		 String sqlSt ="select * from Player where ID_Player = ?";
//   		 ps = connection.prepareStatement(sqlSt);
//		 ps.setInt(1,s);
//   		  ps.executeQuery();
//   		ResultSet rs= ps.getResultSet();
//   	    while(rs.next())
//   	    {
//   	     System.out.println(rs.getString(2));
//   	    }
//         ps.execute();
//         
//        
//		 connection.close();

//
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		try {
//			
//			DatabaseConnection dbConnection = new DatabaseConnection();
//		    dbConnection.createConnection(); 
//		    
//			    Boss _boss = new Boss("Rokva");
//
//				System.out.println("\tUnesite Username:");
//				String _userName = br.readLine();
//				System.out.println("\tIzaberite Mapu za borbu:");
//				System.out.println("Opcija 1 : Swamp  +2 damage for Warlock");
//				System.out.println("Opcija 2 : Jungle +2 damage for Barbarian");
//				System.out.println("Opcija 3 : Desert +2 damage for Hunter");
//				System.out.println(":");
//				
//				int mapaInput = Map.getIntInput(br);
//				System.out.println("\tIzaberite Klasu od ponudjenih:");
//				System.out.println("Opcija 1 : Barbarian wields a big axe that does 18 damage and has 120 health");
//				System.out.println("Opcija 2 : Warlock who can cast Shadow bolts for 20 damage but has lower health=80");
//				System.out.println("Opcija 3 : Hunter who can shoot with a bow and arrow -> 17 damage and has health=100");
//				System.out.println(":");
//				int classPick =  Map.getIntInput(br);
//				
//				Map _mapa = new Map(mapaInput);
//				
//				
//				Player player =Player.createPlayer(classPick,_userName,_mapa,_boss,1);
//
//				dbConnection.insertPlayer(_userName, _mapa, _boss,player);
//				
//				_boss.setPlayer(player);
//				
//				
//				System.out.println("Uspesno ste napravili klasu: "+player.getClassName()+ " sa imenom: "+ player.getUserName()+" sa oruzjem:"+player.getWeapon().getName() 
//				+ "->"+player.getWeapon().getWeaponDamage()+" damage " + " i  hp-a:"+ player.getHealth()+" protiv boss-a -> "+ _boss.getName() 
//				+ " boss health:"+ _boss.getHealth()+ " i damage : "+ _boss.getDamage());
//				
//
//					
//				while(_boss.getLevel()<4)
//				{	
//				
//					System.out.println("\tIzaberite sledecu akciju:");
//					System.out.println("Ukucajte 1 za : Basic Atack! -1 energy");
//					System.out.println("Ukucajte 2 za : Heal + 20hp -1 energy");
//					System.out.println("Ukucajte 3 za : Preskoci potez + 3 energy");
//					if(player.getPotion()>0)
//					{
//						System.out.println("Ukucajte 4 za : Iskoristi Potion +20hp");
//					}
//					int input =  Map.getIntInput(br);
//				
//					player.Action(input);
//					_boss.Action(input);
//					if (player.getHealth()<=0)
//					{
//						
//						System.out.println("\tHealth reached 0 -> Game Over!");
//						break;
//					}
//					if (_boss.getHealth()<=0)
//					{
//						
//						System.out.println("\tBoss health reached 0 -> GG! LEVEL: "+ _boss.getLevel()+" DONE!");
//						System.out.println("");  
//						
//						_boss.setLevel(_boss.getLevel()+1);
//						_boss.levelUp(_boss.getLevel());
//						player.reset();
//						if(_boss.getLevel()==4)
//						{
//							
//						System.out.println("\t PRESLI STE CELU IGRICU !");
//						}
//						
//						else {
//
//							System.out.println("Prelazimo na sledeci nivo sa oruzjem:"+player.getWeapon().getName() 
//							+ "->"+player.getWeapon().getWeaponDamage()+" damage " + " i regenerisanih hp:" +player.getHealth()+  " protiv boss-a -> "+ _boss.getName() 
//							+ " boss health:"+ _boss.getHealth()+ " i damage : "+ _boss.getDamage());
//						}
//						
//						
//					
//						}
//					}
//				
//
//		} catch (IOException ioe) {
//			System.out.println(ioe);
//			
//		}
//		
//
//
	}
}
