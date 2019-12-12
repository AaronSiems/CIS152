package helpers;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
					if (sTemp.equals("true")) {
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
					if (sTemp.equals("true")) {
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
					if (sTemp.equals("true")) {
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
					if (sTemp.equals("true")) {
						steel = true;
					} else {
						steel = false;
					}
					
					if (cTemp.equals("true")) {
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
	
	
	public List<Coin> sortList(List<Coin> c, String sort){
		List<Coin> sortedList = new ArrayList<Coin>();
		if(sort.equals("Coin")) {
			int size = c.size();
			int[] remove = new int[c.size()];
			int count = 0;
			//Pull out the quarters
			for(int i = 0; i < size; i++) {
				if(c.get(i).getClass().getCanonicalName().replace("classes.", "").equals("Quarter")) {
					remove[count] = i;
					count++;
					sortedList.add(c.get(i));
				}
			}
			for(int i = 0; i < count; i++) { //This remove will make the function take less time
				c.remove(remove[i]-i);
			}
			//Pull out the dimes
			count = 0;
			size = c.size();
			for(int i = 0; i < size; i++) {
				if(c.get(i).getClass().getCanonicalName().replace("classes.", "").equals("Dime")) {
					remove[count] = i;
					count++;
					sortedList.add(c.get(i));
				}
			}
			for(int i = 0; i < count; i++) {
				c.remove(remove[i]);
			}
			//Pull out the nickels
			count = 0;
			size = c.size();
			for(int i = 0; i < size; i++) {
				if(c.get(i).getClass().getCanonicalName().replace("classes.", "").equals("Nickel")) {
					remove[count] = i;
					count++;
					sortedList.add(c.get(i));
				}
			}
			for(int i = 0; i < count; i++) {
				c.remove(remove[i]);
			}
			//Pull out the pennies
			count = 0;
			size = c.size();
			for(int i = 0; i < size; i++) {
				if(c.get(i).getClass().getCanonicalName().replace("classes.", "").equals("Penny")) {
					remove[count] = i;
					count++;
					sortedList.add(c.get(i));
				}
			}
			for(int i = 0; i < count; i++) {
				c.remove(remove[i]);
			}
		} else if(sort.equals("Year")) {
			Collections.sort(c, new Comparator<Coin>() 
			{
				@Override
			     public int compare(Coin left, Coin right) {
			    	 return Integer.valueOf(left.getYear()).compareTo(right.getYear());
			      }
			});
			for(int i = 0; i < c.size(); i++) {
				sortedList.add(c.get(i));
			}
		} else { //This runs when the forum is first loaded, just copies the list
			for(int i = 0; i < c.size(); i++) {
				sortedList.add(c.get(i));
			}
		}
		
		
		return sortedList;
	}

	public String getFileLoc() {
		return fileLoc;
	}

	public void setFileLoc(String fileLoc) {
		this.fileLoc = fileLoc;
	}
	
	
}
