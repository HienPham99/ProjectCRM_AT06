package helpers;

import drivers.DriverManager;
import drivers.DriverManager;
import org.monte.media.Format;
import org.monte.media.Registry;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.monte.media.FormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;


public class CaptureHelper extends ScreenRecorder {

    // Record with Monte Media library
    public static ScreenRecorder screenRecorder;
    public String name;

    //Hàm xây dựng
    public CaptureHelper(GraphicsConfiguration cfg, Rectangle captureArea, Format fileFormat, Format screenFormat, Format mouseFormat, Format audioFormat, File movieFolder, String name) throws IOException, AWTException {
        super(cfg, captureArea, fileFormat, screenFormat, mouseFormat, audioFormat, movieFolder);
        this.name = name;
        PropertiesHelper.loadAllFiles();
    }

    //Hàm này bắt buộc để ghi đè custom lại hàm trong thư viên viết sẵn
    @Override
    protected File createMovieFile(Format fileFormat) throws IOException {

        if (!movieFolder.exists()) {
            movieFolder.mkdirs();
        } else if (!movieFolder.isDirectory()) {
            throw new IOException("\"" + movieFolder + "\" is not a directory.");
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
        return new File(movieFolder, name + "-" + dateFormat.format(new Date()) + "." + Registry.getInstance().getExtension(fileFormat));
    }

    // Start record video
    public static void startRecord(String methodName) {
        //Tạo thư mục để lưu file video vào
        File file = new File(SystemHelper.getCurrentDir() + PropertiesHelper.getValue("Video_Record_Path"));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        Rectangle captureSize = new Rectangle(0, 0, width, height);

        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        try {
            screenRecorder = new CaptureHelper(gc, captureSize, new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI), new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey, 24, FrameRateKey, Rational.valueOf(15), QualityKey, 1.0f, KeyFrameIntervalKey, 15 * 60), new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(30)), null, file, methodName);
            screenRecorder.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    // Stop record video
    public static void stopRecord() {
        try {
            screenRecorder.stop();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");

    public static void captureScreenshot(String screenshotName) {
        try {
            // Tạo tham chiếu đối tượng của TakesScreenshot với dirver hiện tại
            TakesScreenshot ts = (TakesScreenshot) DriverManager.getDriver();
            // Gọi hàm getScreenshotAs để chuyển hóa hình ảnh về dạng FILE
            File source = ts.getScreenshotAs(OutputType.FILE);
            //Kiểm tra folder nếu không tồn tại thì tạo folder
            File theDir = new File(SystemHelper.getCurrentDir() + PropertiesHelper.getValue("Screenshot_Path"));
            if (!theDir.exists()) {
                theDir.mkdirs();
            }

            FileHandler.copy(source, new File(SystemHelper.getCurrentDir() + PropertiesHelper.getValue("Screenshot_Path") + File.separator + screenshotName + "_" + dateFormat.format(new Date()) + ".png"));
            System.out.println("Screenshot taken: " + screenshotName);
            System.out.println("Screenshot taken current URL: " + DriverManager.getDriver().getCurrentUrl());
        } catch (Exception e) {
            System.out.println("Exception while taking screenshot: " + e.getMessage());
        }
    }

    //Hàm chụp ảnh và tự tạo đường dẫn foder để lưu ảnh
    public static void takeScreenshot(String screenshotName) {
        TakesScreenshot ts = (TakesScreenshot) DriverManager.getDriver();
        //Gọi hàm để chụp ảnh màn hình_getScreenshotAs
        File source = ts.getScreenshotAs(OutputType.FILE);
        //Kiểm tra folder tồn tại. Nếu không thì tạo mới folder theo đường dẫn
        File thDir = new File("./screenshots");
        //File thDir = new File(SystemHelper.getCurrentDir() + PropertiesHelper.getValue("Screenshot_Path"));
        if (!thDir.exists()) {
            thDir.mkdir();//tạo mới thư mục
        }
        //Lưu ảnh với tên cụ thể vào đường dẫn.
        //Note: Tên pải trùng với tên đặt ở dòng 'File thDir = new File("./screenshots");'
        try {
            FileHandler.copy(source, new File("./screenshots/ " + screenshotName + ".png"));
            //FileHandler.copy(source, new File(SystemHelper.getCurrentDir() + PropertiesHelper.getValue("Screenshot_Path") + screenshotName + ".png"));
            System.out.println("____Take screenshot " + screenshotName + " successfully______");
        } catch (IOException e) {
            System.out.println("ERROR. Can not take screenshot " + screenshotName + ".");
            //e.printStackTrace(); //in ra lỗi nếu ko có đường dẫn
        }
    }
}
