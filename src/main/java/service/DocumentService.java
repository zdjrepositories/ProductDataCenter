package service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import pojo.Document;
import pojo.DocumentFile;
import util.Conf;
import util.HttpClient;
import util.Log4j;
import util.SQLSession;

import java.io.UnsupportedEncodingException;

public class DocumentService extends DataCenterService {
    private Document document = new Document();
    private DocumentFile documentFile = new DocumentFile();

    /**
     * 获取数据流程
     *
     * @param id
     */
    public void run(String id) {
        System.out.println("开始获取Document:" + id);
        for (int i = 0; i < 2000; i++) {
            System.out.println("循环：" + i);
            getData(id, i * 50);
            if (data.contains("\"getDocumentListResponse\":null")) {
                break;
            }

            System.out.println(":::"+data);
            analyze();


        }
        System.out.println("成功获取Document:" + id + "  " + getTime());
    }

    /**
     * 获取数据
     */

    public void getData(String id, int num) {
        //从配置文件获取Category id url
        String url = Conf.getConf().getDocument();
        String body = "<getDocumentList>\n" +
                "\n" +
                "    <query>\n" +
                "\n" +
                "        <rangeIds>" + id + "</rangeIds>\n" +
                "\n" +
                "    </query>\n" +
                "\n" +
                "    <pagination firstResult=\"" + num + "\" maxResult=\"50\" />\n" +
                "\n" +
                "</getDocumentList>";
        document.setRange_id(Long.valueOf(id));

        try {
            data = HttpClient.doPost(url, null, body);
        } catch (UnsupportedEncodingException e) {
            Log4j.getLog4j().error("获取Document数据发生异常：" + e.toString());
            e.printStackTrace();
        }
    }

    /**
     * 解析数据
     */
    @Override
    public void analyze() {
        if (data != null && data.indexOf("getDocumentListResponse") > -1) {
            JSONObject object = JSONObject.parseObject(data);
            String str = object.getJSONObject("getDocumentListResponse").getString("return");
            analyzeDocument(str);
        }
    }

    int x = 0;

    public void analyzeDocument(String str) {
        if (isArray(str)) {
            JSONArray jsonArray = JSON.parseArray(str);
            for (int i = 0; i < jsonArray.size(); i++) {
                analyzeDocument(jsonArray.get(i).toString());
            }
        } else {
            if (str.indexOf("document") > -1 && str.indexOf("document") < 30) {
                JSONObject object = JSONObject.parseObject(str);
                // document.setDocumentDate(object.getString("documentDate"));
                document.setAttributeLists(object.getString("attributeLists"));
                document.setAuthor(object.getString("author"));
                //document.setLastModificationDate(object.getString("lastModificationDate"));
                document.setFlipFlopGenerated(object.getString("flipFlopGenerated").equals("true") ? 1 : 0);
                document.setVersion(object.getString("version"));
                document.setTitle(object.getString("title"));
                document.setReference(object.getString("reference"));
                document.setNumberOfPage(object.getLongValue("numberOfPage"));
                //document.setCreateDate(object.getString("creationDate"));
                document.setDocOwner(object.getString("docOwner"));
                document.setKeywords(object.getString("keywords"));
                document.setDocument_id(object.getLongValue("docId"));
                document.setAttributes(object.getJSONObject("attributes").getString("attribute"));
                document.setDescription(object.getString("description"));
                document.setDoctype_englishlabel(object.getJSONObject("documentType").getString("englishLabel"));
                document.setDoctype_name(object.getJSONObject("documentType").getString("name"));
                if (object.getString("files") != null && (!"".equals(object.getString("files")))) {
                    if (object.getJSONObject("files").getString("file") != null && (!"".equals(object.getJSONObject("files").getString("file")))) {
                        document.setFiles_id(1L);
                        documentFile.setDoc_id(document.getDocument_id());
                        analyzefile(object.getJSONObject("files").getString("file"));
                    }
                }

                document.setAudience_translation(object.getJSONObject("audience").getString("translation"));
                document.setAudience_id(object.getJSONObject("audience").getString("id"));

                SQLSession sqlSession = new SQLSession();
                sqlSession.getSqlsession().insert("DocumentMapper.insertDocument", document);
                sqlSession.getSqlsession().commit();
                sqlSession.closeSession();
                System.out.println("\tdocument成功添加：" + document.getDocument_id());
            }
        }
    }

    public void analyzefile(String str) {
        if (isArray(str)) {
            JSONArray jsonArray = JSON.parseArray(str);
            for (int i = 0; i < jsonArray.size(); i++) {
                analyzefile(jsonArray.get(i).toString());
            }
        } else {
            JSONObject object = JSONObject.parseObject(str);
            documentFile.setFilename(object.getString("filename"));
            documentFile.setSize(object.getLongValue("size"));
            documentFile.setExtension(object.getString("extension"));
            documentFile.setDocument_file_id(object.getLongValue("id"));
            SQLSession sqlSession = new SQLSession();
            System.out.println("\t\tfile添加成功：" + documentFile.getDocument_file_id());
            sqlSession.getSqlsession().insert("DocumentFileMapper.insertDocumentFile", documentFile);
            sqlSession.getSqlsession().commit();
            sqlSession.closeSession();
        }
    }
}



