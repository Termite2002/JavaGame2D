package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {

	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];
	
	public TileManager(GamePanel gp) {
		
		this.gp = gp;
		
		tile = new Tile[100];
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		
		getTileImage();
		loadMap("/maps/maptest.txt");
	}
	
	public void getTileImage() {
		try {
			
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tile096.png"));
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tile088.png"));
//			tile[1].collision = true;
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tile089.png"));
			tile[2].collision = true;
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void loadMap(String filePath) {
				
		try {
			
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
				
				String line = br.readLine();
				
				while(col < gp.maxWorldCol) {
					
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[col][row] = num;
					col++;
				}
				if(col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			br.close();
			
			
		}catch(IOException e) {
			
		}
	}
	
	public void draw(Graphics2D g2) {
		
		int worldCol = 0;
		int worldRow = 0;

		
		while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			
			int tileNum = mapTileNum[worldCol][worldRow];
			
			int worldX = worldCol * gp.titleSize;	
			int worldY = worldRow * gp.titleSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			if(worldX + gp.titleSize > gp.player.worldX - gp.player.screenX &&
			   worldX - gp.titleSize < gp.player.worldX + gp.player.screenX &&
			   worldY + gp.titleSize > gp.player.worldY - gp.player.screenY &&
			   worldY - gp.titleSize < gp.player.worldY + gp.player.screenY) {
				g2.drawImage(tile[tileNum].image, screenX, screenY, gp.titleSize, gp.titleSize, null);
			}
			worldCol++;	

			
			if(worldCol == gp.maxScreenCol) {
				worldCol = 0;
				worldRow++;



			}
		}
	}
}