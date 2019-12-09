package helpers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import classes.Coin;

public class CoinListHelper {
	
	private String fileLoc;

	public CoinListHelper(String fileLoc) {
		super();
		this.fileLoc = fileLoc;
	}
	
	public List<Coin> loadList() {
		List<Coin> c = new ArrayList<Coin>();
		try {
			//Load in file
			File xmlFile = new File(fileLoc);
			DocumentBuilderFactory DBF = DocumentBuilderFactory.newInstance();
			DocumentBuilder DB = DBF.newDocumentBuilder();
			Document doc = DB.parse(xmlFile);
			doc.getDocumentElement().normalize();
			
			//Load in coins to list c
			NewCoinHelper NCH = new NewCoinHelper();
			
			
			NodeList cList = doc.getElementsByTagName("coin");
			
			for(int i = 0; i < cList.getLength(); i++) {
				Node cN = cList.item(i);
				Element newCoin = (Element) cN;
				String type = newCoin.getElementsByTagName("type").item(0).getTextContent();
				
				
				if(type.equals("quarter")) {
					String design = newCoin.getElementsByTagName("design").item(0).getTextContent();
					char mint = newCoin.getElementsByTagName("mint").item(0).getTextContent().charAt(0);
					int year = Integer.parseInt(newCoin.getElementsByTagName("year").item(0).getTextContent());
					String error = newCoin.getElementsByTagName("error").item(0).getTextContent();
					String sTemp = newCoin.getElementsByTagName("silver").item(0).getTextContent();
					boolean silver;
					if (sTemp == "true") {
						silver = true;
					} else {
						silver = false;
					}
					c.add(NCH.newCoin(type, year, mint, design, silver, false, error));
				} else if (type.equals("dime")) {
					String design = newCoin.getElementsByTagName("design").item(0).getTextContent();
					char mint = newCoin.getElementsByTagName("mint").item(0).getTextContent().charAt(0);
					int year = Integer.parseInt(newCoin.getElementsByTagName("year").item(0).getTextContent());
					String error = newCoin.getElementsByTagName("error").item(0).getTextContent();
					String sTemp = newCoin.getElementsByTagName("silver").item(0).getTextContent();
					boolean silver;
					if (sTemp == "true") {
						silver = true;
					} else {
						silver = false;
					}
					c.add(NCH.newCoin(type, year, mint, design, silver, false, error));					
				} else if (type.equals("nickel")) {
					String design = newCoin.getElementsByTagName("design").item(0).getTextContent();
					char mint = newCoin.getElementsByTagName("mint").item(0).getTextContent().charAt(0);
					int year = Integer.parseInt(newCoin.getElementsByTagName("year").item(0).getTextContent());
					String error = newCoin.getElementsByTagName("error").item(0).getTextContent();
					String sTemp = newCoin.getElementsByTagName("silver").item(0).getTextContent();
					boolean silver;
					if (sTemp == "true") {
						silver = true;
					} else {
						silver = false;
					}
					c.add(NCH.newCoin(type, year, mint, design, silver, false, error));
				} else if (type.equals("penny")) {
					String design = newCoin.getElementsByTagName("design").item(0).getTextContent();
					char mint = newCoin.getElementsByTagName("mint").item(0).getTextContent().charAt(0);
					int year = Integer.parseInt(newCoin.getElementsByTagName("year").item(0).getTextContent());
					String error = newCoin.getElementsByTagName("error").item(0).getTextContent();
					String sTemp = newCoin.getElementsByTagName("steel").item(0).getTextContent();
					String cTemp = newCoin.getElementsByTagName("copper").item(0).getTextContent();
					boolean steel;
					boolean copper;
					if (sTemp == "true") {
						steel = true;
					} else {
						steel = false;
					}
					
					if (cTemp == "true") {
						copper = true;
					} else {
						copper = false;
					}
					
					c.add(NCH.newCoin(type, year, mint, design, steel, copper, error));
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return c;
	}
	
	public void saveList(List<Coin> c) {
		try {
			XMLHelper XMLH = new XMLHelper();
			
			//Clear file
			XMLH.resetFile(fileLoc);
			
			
			//Load in file
			File xmlFile = new File(fileLoc);
			DocumentBuilderFactory DBF = DocumentBuilderFactory.newInstance();
			DocumentBuilder DB = DBF.newDocumentBuilder();
			Document doc = DB.newDocument();
			//doc.getDocumentElement().normalize();
			
			
			//Write file
			Element rootElement = doc.createElement("CoinCollection");
			doc.appendChild(rootElement);
			
			for(int i = 0; i < c.size(); i++) {
				rootElement.appendChild(XMLH.newCoinNode(doc, c.get(i), String.valueOf(i)));
			}
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            
            DOMSource source = new DOMSource(doc);
            StreamResult file = new StreamResult(xmlFile);
            
            transformer.transform(source, file); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getFileLoc() {
		return fileLoc;
	}

	public void setFileLoc(String fileLoc) {
		this.fileLoc = fileLoc;
	}
	
	
}
