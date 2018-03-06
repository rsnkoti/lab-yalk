package com.xebia.yalk.util;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import com.xebia.yalk.vo.LabYak;
import com.xebia.yalk.vo.YakSex;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class HerdInputXMLParser {
    private File herdXml;
    private Set<LabYak> labYakSet;
    public HerdInputXMLParser(File herdXml){
        this.herdXml = herdXml;
    }
    
    public Set<LabYak> parse(){
    	labYakSet = new HashSet<>();

        try {
        	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(herdXml);
            document.getDocumentElement().normalize();


            NodeList list = document.getElementsByTagName("labyak");
            for (int i = 0; i < list.getLength(); i++) {

                Node node = list.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;
                    String name =  element.getAttribute("name");
                    double age =  Double.parseDouble(element.getAttribute("age"));
                    YakSex sex = "f".equalsIgnoreCase(element.getAttribute("sex")) ? YakSex.FEMALE : YakSex.MALE;
                    
                    //yak builder
                    labYakSet.add(new LabYak.Builder().name(name).age(age).sex(sex).build());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return labYakSet;
    }
}

