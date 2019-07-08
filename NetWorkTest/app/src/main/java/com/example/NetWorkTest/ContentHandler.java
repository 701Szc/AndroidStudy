package com.example.NetWorkTest;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ContentHandler extends DefaultHandler {

    private String nodeName;
    private StringBuilder id;
    private StringBuilder name;
    private StringBuilder version;
    private final static String TAG = "ContentHandler";

    @Override
    public void startDocument() throws SAXException {
//       初始化
        id = new StringBuilder();
        name = new StringBuilder();
        version = new StringBuilder();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
//        记录当前节点名
        nodeName =localName;

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
//        根据当前的节点名判断将内容添加到哪一个StringBuilder对象中
        if("id".equals(nodeName)){
            id.append(ch,start,length);
        }else if("name".equals(nodeName)){
            name.append(ch,start,length);
        }else if("version".equals(nodeName)){
            version.append(ch,start,length);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
//        结束解析 打印结果
        if("app".equals(nodeName)){
            Log.d(TAG, "id is" + id.toString().trim());
            Log.d(TAG, "name is " + name.toString().trim());
            Log.d(TAG, "version is "+ version.toString().trim());
//            最后将StringBuilder清空掉
            id.setLength(0);
            name.setLength(0);
            version.setLength(0);
        }

    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }
}
