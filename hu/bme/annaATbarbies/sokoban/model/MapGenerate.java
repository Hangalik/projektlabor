package hu.bme.annaATbarbies.sokoban.model;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class MapGenerate {
	public static void MapGenerate(int mapx,int mapy, int switch_trap,int holes, int box_target,int player) {
		try {
			BufferedWriter writer=new BufferedWriter(new FileWriter("palya.txt"));
			
			//Item Identifier
			int ItemID=1;
			
			//Random koordináta randomizáló
			Random r=new Random();
			int rx;
			int ry;
			
			if(player<2 || player>4) {
				System.out.print("Nem megfelelő játékos méret!! Beállítva 4");
				player=4;
			}
			if(mapx<10 || mapx>30 || mapy<10 || mapy>30) {
				System.out.println("Nem megfelelő map méret!!! Beállítva 20*20");
				mapx=20;
				mapy=20;
			}
			
			//A falak miatt kettővel szélesebb lesz a betöltött rész
			mapx+=2;
			mapy+=2;
			
			//Map mérete File ba írás
			writer.write(mapx+" "+mapy);
			writer.newLine();
			
			//Seged 2D tömb inicializálása
			int[][] map = new int[mapx][mapy];
			for(int i=0;i<mapx;i++) {
				for(int j=0;j<mapy;j++) {
					map[i][j]=0;
				}
			}
			
			//Koordinátaként viszont már csak 0- x-1 vagy 0- y-1 lesz szükség így 
			mapx--;mapy--;
			//Falak körbe (külső fal ItemId=1)
			for(int i=0;i<mapx;i++) {
				map[i][0]=ItemID;
				map[i][mapy]=ItemID;
			}
			for(int j=0;j<mapy;j++) {
				map[0][j]=ItemID;
				map[mapy][j]=ItemID;
			}
			map[mapx][mapy]=ItemID;
			
			//Playerek elhelyezése (player/worker ItemID=2)
			ItemID++;
			switch(player) {
				case 2:
					map[1][1]=ItemID;
					map[mapx-1][mapy-1]=ItemID;
					break;
				case 3:
					map[1][1]=ItemID;
					map[mapx-1][mapy-1]=ItemID;
					map[1][mapy-1]=ItemID;
					break;
				case 4:
					map[1][1]=ItemID;
					map[mapx-1][mapy-1]=ItemID;
					map[1][mapy-1]=ItemID;
					map[mapx-1][1]=ItemID;
					break;
				default:
					break;
			}
			
			
			//Belső falak (belsp fal ItemID=3)
			ItemID++;
			int belsof=((mapy-1)*(mapx-1))/10;
			
			while(belsof!=0) {
				rx=r.nextInt(mapx-2)+1;
				ry=r.nextInt(mapy-2)+1;
				if(map[rx][ry]==0) {
					if(szomszed_byID(map,rx,ry,1)) {
						map[rx][ry]=ItemID;
						belsof--;
					}
				}
			}
			
			//Hole elhelyezés (hole ItemID=4)
			ItemID++;
			while(holes!=0) {
				rx=r.nextInt(mapx-2)+1;
				ry=r.nextInt(mapy-2)+1;
				if(map[rx][ry]==0) {
					if(szomszed_byID(map,rx,ry,4)&& szomszed_byID(map,rx,ry,2)) {
						map[rx][ry]=ItemID;
						holes--;
					}
				}
			}
			
			//Targetek (target ItemID=5)
			ItemID++;
			int target=box_target;
			while(target!=0) {
				rx=r.nextInt(mapx-2)+1;
				ry=r.nextInt(mapy-2)+1;
				if(map[rx][ry]==0) {
					if(szomszed_byCount(map,rx,ry,3,2)) {
						map[rx][ry]=ItemID;
						target--;
					}
				}
			}
			
			//Boxok (box ItemID=6)
			ItemID++;
			while(box_target!=0) {
				rx=r.nextInt(mapx-2)+1;
				ry=r.nextInt(mapy-2)+1;
				if(map[rx][ry]==0) {
					if(szomszed_byCount(map,rx,ry,3,1)&&szomszed_byID(map,rx,ry,1)) {
						map[rx][ry]=ItemID;
						box_target--;
					}
				}
			}
			
			//Switch_trap ItemId=6+ párosával (7-8,9-10.....)
			int st=switch_trap*2;
			while(st!=0) {
				rx=r.nextInt(mapx-2)+1;
				ry=r.nextInt(mapy-2)+1;
				if(map[rx][ry]==0) {
						ItemID++;
						map[rx][ry]=ItemID;
						st--;
					
				}
			}
			
			
			//A File ba kiírás a csapdák kivételével, mapx,mapy eredeti visszaállítása
			//mapx++;mapy++;
			int playerID=1;
			for(int i=0;i<mapx;i++) {
				for(int j=0;j<mapy;j++) {
					switch(map[i][j]) {
					case 0:
						writer.write("field "+i+" "+j);
						writer.newLine();
						break;
					case 1:
						writer.write("block "+i+" "+j);
						writer.newLine();
						break;
					case 2:
						writer.write("worker "+i+" "+j+" "+playerID);
						playerID++;
						writer.newLine();
						break;
					case 3: 
						writer.write("block "+i+" "+j);
						writer.newLine();
						break;
					case 4:
						writer.write("hole "+i+" "+j);
						writer.newLine();
						break;
					case 5: 
						writer.write("target "+i+" "+j);
						writer.newLine();
						break;
					case 6:
						writer.write("box "+i+" "+j);
						writer.newLine();
						break;
					default:
						break;
					
					}
				}
			}
			
			//Tapek és Switchek kiírása file ba
			int s=7;
			int t=8;
			while(switch_trap!=0) {
				for(int i=0;i<mapx;i++) {
					for(int j=0;j<mapy;j++) {
						if(map[i][j]==s)writer.write("switch "+i+" "+j+" ");
					}
				}
				s+=2;
				for(int i=0;i<mapx;i++) {
					for(int j=0;j<mapy;j++) {
						if(map[i][j]==t)writer.write("trap "+i+" "+j);
					}
				}
				t+=2;
				switch_trap--;
			}
			
			//tombkiiras(map,mapx,mapy);
			writer.close();
			
		}
		catch(IOException e){
			System.out.print("Nem sikerült a filet kiírni");
		}
				
	}
	public static void tombkiiras(int[][] v,int x,int y) {
		for(int i=0;i<x+1;i++) {
			for(int j=0;j<y+1;j++) {
				System.out.print(v[i][j]+" ");
			}
			System.out.println();
		}
	}
	public static boolean szomszed_byID(int [][] v,int x, int y, int ItemID) {
		if(v[x-1][y+1]!=ItemID && v[x][y+1]!=ItemID && v[x+1][y+1]!=ItemID && v[x+1][y]!=ItemID && v[x+1][y-1]!=ItemID && v[x][y-1]!=ItemID && v[x-1][y-1]!=ItemID && v[x-1][y]!=ItemID) return true;
		else return false;
	}
	public static boolean szomszed_byCount(int [][] v,int x, int y, int ItemID,int Count) {
		int counter=0;
		if(v[x-1][y+1]==ItemID)counter++;
		if(v[x][y+1]==ItemID)counter++;
		if(v[x+1][y+1]==ItemID)counter++;
		if(v[x+1][y]==ItemID)counter++;
		if(v[x+1][y-1]==ItemID)counter++;
		if(v[x][y-1]==ItemID)counter++;
		if(v[x-1][y-1]==ItemID)counter++;
		if(v[x-1][y]==ItemID)counter++;
		if(counter<=Count)return true;
		else return false;
	}
	
}
