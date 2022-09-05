package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Boot extends SuperObject{
	public OBJ_Boot() {
		name = "boot";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/normalboot.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
