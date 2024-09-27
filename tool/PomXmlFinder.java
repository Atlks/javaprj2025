package tool;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.util.stream.Stream;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class PomXmlFinder {

    public static void main(String[] args) {
        String directoryPath = "C:\\0oprj\\digital-bet-service"; // 请替换为您的目录路径
        findPomFiles(directoryPath);
    }

    public static void findPomFiles(String directoryPath) {
        Path startPath = Paths.get(directoryPath);

        try (Stream<Path> paths = Files.walk(startPath)) {
            paths.filter(path -> path.toFile().isFile() && path.getFileName().toString().equals("pom.xml"))
                    .forEach(path ->
                            {


                            //    System.out.println("找到 pom.xml 文件: " + path.toAbsolutePath());


                                String pomFilePath = String.valueOf(path.toAbsolutePath()); // 替换为您的 POM 文件路径
                                try {
                                    PomData pomData = readPomFile(pomFilePath);
                                    System.out.println(" <dependency>");
                                    System.out.println("<groupId>" + pomData.getGroupId()+ "</groupId>");
                                    System.out.println(" <artifactId>" + pomData.getArtifactId()+"</artifactId>");
                                    System.out.println("<version>" + pomData.getVersion()+"</version>");

                                   //  <groupId>com.squareup.okhttp3</groupId>
                                    System.out.println(" </dependency>\n\n\n");

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }


                    );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static PomData readPomFile(String filePath) throws Exception {
        File xmlFile = new File(filePath);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(xmlFile);
        document.getDocumentElement().normalize();

        // 读取 <groupId>
        String groupId = getElementValue(document, "groupId");
        // 读取 <artifactId>
        String artifactId = getElementValue(document, "artifactId");
        // 读取 <version>
        String version = getElementValue(document, "version");

        return new PomData(groupId, artifactId, version);
    }

    private static String getElementValue(Document document, String tagName) {
        NodeList nodeList = document.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            Element element = (Element) nodeList.item(0);
            return element.getTextContent();
        }
        return null;
    }

    static class PomData {
        private String groupId;
        private String artifactId;
        private String version;

        public PomData(String groupId, String artifactId, String version) {
            this.groupId = groupId;
            this.artifactId = artifactId;
            this.version = version;
        }

        public String getGroupId() {
            return groupId;
        }

        public String getArtifactId() {
            return artifactId;
        }

        public String getVersion() {
            return version;
        }
    }
}
