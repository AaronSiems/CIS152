package helpers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import classes.Coin;
import classes.Penny;

public class XMLHelper {
	
	public XMLHelper() {
		
	}
	
	public Node newCoinNode(Document doc, Coin c, String id) {
		Element coin = doc.createElement("coin");
		
		coin.setAttribute("id", id);
		
		//int year, char mint, String design, boolean steel, boolean copper
		if(c instanceof Penny) {
			Element type = doc.createElement("type");
			type.appendChild(doc.createTextNode("penny"));
			Element design = doc.createElement("design");
			design.appendChild(doc.createTextNode(c.getDesign()));
			Element year = doc.createElement("year");
			year.appendChild(doc.createTextNode(String.valueOf(c.getYear())));
			Element mint = doc.createElement("mint");
			mint.appendChild(doc.createTextNode(String.valueOf(c.getMint())));
			Element steel = doc.createElement("steel");
			steel.appendChild(doc.createTextNode(String.valueOf(((Penny) c).isSteel())));
			Element copper = doc.createElement("copper");
			copper.appendChild(doc.createTextNode(String.valueOf(((Penny) c).isCopper())));
			Element error = doc.createElement("error");
			if(c.getError() == null) {
				error.appendChild(doc.createTextNode(""));
			} else {
				error.appendChild(doc.createTextNode(c.getError()));
			}
			
			coin.appendChild(type);
			coin.appendChild(design);
			coin.appendChild(year);
			coin.appendChild(mint);
			coin.appendChild(steel);
			coin.appendChild(copper);
			coin.appendChild(error);
		} else {
			String typeString = c.getClass().getCanonicalName().replace("classes.", "");
			Element type = doc.createElement("type");
			type.appendChild(doc.createTextNode(typeString.toLowerCase()));
			Element design = doc.createElement("design");
			design.appendChild(doc.createTextNode(c.getDesign()));
			Element year = doc.createElement("year");
			year.appendChild(doc.createTextNode(String.valueOf(c.getYear())));
			Element mint = doc.createElement("mint");
			mint.appendChild(doc.createTextNode(String.valueOf(c.getMint())));
			Element silver = doc.createElement("silver");
			silver.appendChild(doc.createTextNode(String.valueOf(c.isSilver())));
			Element error = doc.createElement("error");
			if(c.getError() == null) {
				error.appendChild(doc.createTextNode(""));
			} else {
				error.appendChild(doc.createTextNode(c.getError()));
			}
			
			coin.appendChild(type);
			coin.appendChild(design);
			coin.appendChild(year);
			coin.appendChild(mint);
			coin.appendChild(silver);
			coin.appendChild(error);
		}
		
		return coin;
	}
	
	public void resetFile(String fileLoc) {
		File file = new File(fileLoc);
		String fileStart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		try{
			if(file.exists()) {
				file.delete();
			}
			file.createNewFile();
			FileWriter fw = new FileWriter(fileLoc);
			PrintWriter pw = new PrintWriter(fw);
			pw.print(fileStart);
			pw.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public void backup(String fileLoc, String backupLoc) {
		File file = new File(fileLoc);
		File backup = new File(backupLoc);
		try {
			if (backup.exists()) {
				backup.delete();
			}
			Files.copy(file.toPath(), backup.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
