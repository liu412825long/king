//package cn.bj.king.util;
//
//import com.google.common.base.Strings;
//import me.ocheng.common.exception.XMLParseException;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//
///**
// * @author Eric.Li
// * @date 2018-05-27
// */
//public class XMLReader {
//
//    Document document = null;
//
//    public XMLReader(String xmlDocument) throws XMLParseException {
//        if (Strings.isNullOrEmpty(xmlDocument)) {
//            throw new XMLParseException();
//        }
//
//        document = Jsoup.parse(xmlDocument);
//
//        if (null == document) {
//            throw new XMLParseException();
//        }
//    }
//
//    public String getString(String cssQuery) {
//        Element element = document.selectFirst(cssQuery);
//
//        if(null == element)
//            return null;
//
//        return element.text();
//    }
//
//    public static XMLReader build(String xmlDocument) throws XMLParseException {
//        return new XMLReader(xmlDocument);
//    }
//}
