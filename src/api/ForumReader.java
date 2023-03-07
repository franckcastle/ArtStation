package api;

import java.net.URL;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import entities.Commentaire;
import entities.Statut;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

    public class ForumReader {

        public static void main(String[] args) throws Exception {
            // URL du flux RSS du forum
            URL url = new URL("http://example.com/forum/feed.rss");

            // Création d'un constructeur de documents XML
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            // Analyse du flux RSS en tant que document XML
            Document doc = db.parse(url.openStream());

            // Récupération des derniers éléments de statut
            NodeList statusItems = doc.getElementsByTagName("status");
            for (int i = 0; i < statusItems.getLength(); i++) {
                Element item = (Element) statusItems.item(i);
                Integer id_s = Integer.valueOf(item.getElementsByTagName("ids").item(0).getTextContent());
                String statusLink = item.getElementsByTagName("link").item(0).getTextContent();
                String statusDescription = item.getElementsByTagName("description").item(0).getTextContent();
                // Création d'un nouvel objet Statut à partir des données récupérées
                Statut status = new Statut(id_s, statusLink, statusDescription);
                // Ajout du nouvel objet Statut à une liste de statuts
                // ou à une base de données, etc.
            }

            // Récupération des derniers éléments de commentaire
            NodeList commentItems = doc.getElementsByTagName("comment");
            for (int i = 0; i < commentItems.getLength(); i++) {
                Element item = (Element) commentItems.item(i);
                Integer id_c = Integer.valueOf(item.getElementsByTagName("idc").item(0).getTextContent());

                String commentDescription = item.getElementsByTagName("description").item(0).getTextContent();
                // Création d'un nouvel objet Commentaire à partir des données récupérées
                Commentaire comment = new Commentaire(id_c,commentDescription);
                // Ajout du nouvel objet Commentaire à une liste de commentaires
                // ou à une base de données, etc.
            }
        }

       
    }

