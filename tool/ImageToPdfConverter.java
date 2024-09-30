//package tool;
// cant use ,use ts tool ok ,bcs lib cant find PDImageXImage
//
//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.pdmodel.PDPage;
////import org.apache.pdfbox.pdmodel.graphics.image.PDImageXImage;
////import org.apache.pdfbox.pdmodel.graphics.image.PDImageXImage;
////import org.apache.pdfbox.pdmodel.graphics.image.PDImageXImage;
////import org.apache.pdfbox.pdmodel.graphics.image.PDImageXImage;
//import java.io.File;
//import java.io.IOException;
//
//public class ImageToPdfConverter {
//
//    public static void main(String[] args) {
//        String folderPath = "c://pic"; // 替换为您的文件夹路径
//        String outputPdfPath = "byz_xaAer.pdf"; // 输出 PDF 文件路径
//
//        try {
//            convertImagesToPdf(folderPath, outputPdfPath);
//            System.out.println("转换完成，PDF 文件已保存至: " + outputPdfPath);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void convertImagesToPdf(String folderPath, String outputPdfPath) throws IOException {
//        File folder = new File(folderPath);
//        File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".jpg"));
//
//        if (files == null || files.length == 0) {
//            System.out.println("未找到 JPG 文件。");
//            return;
//        }
//
//        try (PDDocument document = new PDDocument()) {
//            for (File file : files) {
//                PDImageXImage image = PDImageXImage.createFromFile(file.getAbsolutePath());
//                PDPage page = new PDPage();
//                document.addPage(page);
//
//                // 获取图像宽高
//                float width = image.getWidth();
//                float height = image.getHeight();
//
//                // 将图像绘制到页面
//                PDImageXImage.addImage(page.getContents(), image, 0, 0, width, height);
//            }
//            document.save(outputPdfPath);
//        }
//    }
//}
