package com.ruoyi.project.utils;

import com.ruoyi.project.monitor.operlog.domain.OperLog;
import jcifs.smb.SmbFile;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.*;
import java.nio.channels.FileChannel;

/**
 * 文件操作的实现类
 * @author 崔永辉
 * @since 2010年3月19日
 * @version 1.0
 */
public class FileOperateImpl implements IFileOperate {
	private OperLog log;

	public OperLog getLog() {
		return log;
	}

	public void setLog(OperLog log) {
		this.log = log;
	}

	/**
	 * 默认构造方法
	 */
	public FileOperateImpl() {}

	/**
	 * 传参构造方法
	 * @param log
	 */
	public FileOperateImpl(OperLog log) {
		this.log = log;
	}

	@Override
	public boolean clearDirectory(String directoryPath) {
		boolean result = false;
		File file = new File(directoryPath);
		if (file.exists() && file.isDirectory()) {
			File[] fileArr = file.listFiles();
			try {
				for (File f : fileArr) {
					if (f.delete()) {
						result = true;
					}else {
						result = false;
						throw new Exception("删除文件" + f.getPath() + "失败");
					}
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public boolean deleteDirectory(String directoryPath) {
		boolean result = false;
		File file = new File(directoryPath);
		if (file.exists() && file.isDirectory()) {
			result = file.delete();
			if (result) {
			}else {
			}
		}
		return result;
	}

	@Override
	public boolean deleteFile(String filePath) {
		boolean result = false;
		File file = new File(filePath);
		if (file.exists() && file.isFile()) {
			result = file.delete();
			if (result) {
			}else {
			}
		}
		return result;
	}

	@Override
	public boolean copyFile(String sourceFilePath, String targetFilePath) {
		boolean result = false;
		File sourceFile = new File(sourceFilePath);
		File targetFile = new File(targetFilePath);
		result = copyFile(sourceFile, targetFile);
		return result;
	}

	@Override
	public boolean copyFile(File sourceFile, File targetFile) {
		boolean result = false;
		int buffer = 20971520;
		if (sourceFile.exists()) {
			// 文件拷贝
			if (sourceFile.isFile()) {
				if (targetFile.exists() || createFile(targetFile.getPath())) {
					// 开始拷贝文件,采用方式：管道到管道传输
					try {
						int length = 0;
						FileInputStream in = new FileInputStream(sourceFile);
						FileOutputStream out = new FileOutputStream(targetFile);
						FileChannel inC = in.getChannel();
						FileChannel outC = out.getChannel();
						while (true) {
							if (inC.position() == inC.size()) {
								inC.close();
								outC.close();
								break;
							}
							if ((inC.size() - inC.position()) < buffer) {
								length = (int) (inC.size() - inC.position());
							}else {
								length = buffer;
							}
							inC.transferTo(inC.position(), length, outC);
							inC.position(inC.position() + length);
						}
						result = true;
					}catch (FileNotFoundException e) {
						e.printStackTrace();
					}catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return result;
	}

	@Override
	public boolean copyDirectory(String sourceDirPath, String targetDirPath) {
		boolean result = false;
		File sourceDir = new File(sourceDirPath);
		File targetDir = new File(targetDirPath);
		result = copyDirectory(sourceDir, targetDir);
		return result;
	}

	@Override
	public boolean copyDirectory(File sourceDir, File targetDir) {
		boolean result = false;
		if (sourceDir.exists()) {
			if (targetDir.exists() || createDirectory(targetDir.getPath())) {
				File[] sFile = sourceDir.listFiles();
				for (File f : sFile) {
					if (!this.copyFile(f, new File(targetDir, f.getName()))) {
						result = false;
						break;
					}
					result = true;
				}
			}
		}
		return result;
	}

	@Override
	public boolean createDirectory(String directoryPath) {
		boolean result = false;
		File dir = new File(directoryPath);
		if (dir.exists()) {
			result = true;
		}else {
			if (dir.mkdirs()) {
				result = true;
			}else {
			}
		}
		return result;
	}

	@Override
	public boolean createFile(String filePath) {
		boolean result = false;
		File file = new File(filePath);
		try {
			if (file.exists()) {
				throw new Exception("创建文件" + filePath + "失败，文件已经存在");
			}else {
				File dir = file.getParentFile();
				if (!dir.exists()) {
					dir.mkdirs();
				}
				result = file.createNewFile();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean fileExists(String filePath) {
		boolean result = false;
		File file = new File(filePath);
		if (file.exists()) {
			result = true;
		}
		return result;
	}

	@Override
	public String getFileContent(File textFile) {
		StringBuffer result = new StringBuffer();
		try {
			String fileEncode=guestFileEncoding(textFile);
			InputStreamReader insr = new InputStreamReader(new FileInputStream(textFile), fileEncode);
			int len=0;
			char[] buf=new char[512];
			while((len=insr.read(buf))!=-1){
				result.append(String.valueOf(buf, 0, len));
			}
			/*
			BufferedReader br = new BufferedReader(new FileReader(textFile));
			String line = "";
			while ((line = br.readLine()) != null) {
				result.append(line);
			}
			result=new StringBuffer(new String(result.toString().getBytes(),fileEncode));
			*/
			insr.close();
			buf=null;
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result.toString();
	}

	@Override
	public SmbFile listDirFromSmb(String smbMachine) {
		SmbFile rmifile = null;
		try {
			rmifile = new SmbFile(smbMachine);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return rmifile;
	}

	/**
	 * 生成图片的缩略图
	 * @param source
	 * @param targetW
	 * @param targetH
	 * @return
	 */
	public BufferedImage resizeImg(BufferedImage source, int targetW, int targetH) {
		// targetW，targetH分别表示目标长和宽
		int type = source.getType();
		BufferedImage target = null;
		double miniX = (double) targetW / source.getWidth();
		double miniY = (double) targetH / source.getHeight();
		// 这里想实现在targetW，targetH范围内实现等比缩放。如果不需要等比缩放
		// 则将下面的if else语句注释即可
		if (miniX > miniY) {
			miniX = miniY;
			targetW = (int) (miniX * source.getWidth());
		}else {
			miniY = miniX;
			targetH = (int) (miniY * source.getHeight());
		}
		if (type == BufferedImage.TYPE_CUSTOM) { // handmade
			ColorModel cm = source.getColorModel();
			WritableRaster raster = cm.createCompatibleWritableRaster(targetW, targetH);
			boolean alphaPremultiplied = cm.isAlphaPremultiplied();
			target = new BufferedImage(cm, raster, alphaPremultiplied, null);
		}else
			target = new BufferedImage(targetW, targetH, type);
		Graphics2D graph = target.createGraphics();
		// smoother than exlax:
		graph.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		graph.drawRenderedImage(source, AffineTransform.getScaleInstance(miniX, miniY));
		graph.dispose();
		return target;
	}

	/**
	 * 生产缩略图
	 * @param sourceFile 共享图片文件
	 * @param targetFilePath 生成图片缩略图的路径
	 * @param width
	 * @param hight
	 * @throws Exception
	 */
	public boolean saveImageAsJpg(SmbFile sourceFile, String targetFilePath, int width, int hight) throws Exception {
		BufferedImage srcImage;
		String imgType = "JPEG";
		if (sourceFile.getName().toLowerCase().endsWith(".png")) {
			imgType = "PNG";
		}
		File saveFile = new File(targetFilePath);
		srcImage = ImageIO.read(sourceFile.getInputStream());
		if (width > 0 || hight > 0) {
			srcImage = resizeImg(srcImage, width, hight);
		}
		return ImageIO.write(srcImage, imgType, saveFile);
	}

    @Override
    public String guestFileEncoding(File file) throws FileNotFoundException, IOException {
        return null;
    }

    /**
	 * 传入一个文件(File)对象，检查文件编码
	 * @param file
	 *        File对象实例
	 * @return 文件编码，若无，则返回null
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
//	public String guestFileEncoding(File file) throws FileNotFoundException, IOException {
//		return getFileEncoding(file, new nsDetector());
//	}

	/**
	 * 获取文件的编码
	 * @param path
	 *        文件路径
	 * @return 文件编码，eg：UTF-8,GBK,GB2312形式，若无，则返回null
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public String guestFileEncoding(String path) throws FileNotFoundException, IOException {
		return guestFileEncoding(new File(path));
	}

	private boolean found    = false;
	private String encoding = null;

	/**
	 * 获取文件的编码
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
//	private String getFileEncoding(File file, nsDetector det) throws FileNotFoundException, IOException {
//		// Set an observer...
//		// The Notify() will be called when a matching charset is found.
//		det.Init(new nsICharsetDetectionObserver() {
//			public void Notify(String charset) {
//				found = true;
//				encoding = charset;
//			}
//		});
//		BufferedInputStream imp = new BufferedInputStream(new FileInputStream(file));
//		byte[] buf = new byte[1024];
//		int len;
//		boolean done = false;
//		boolean isAscii = true;
//		while ((len = imp.read(buf, 0, buf.length)) != -1) {
//			// Check if the stream is only ascii.
//			if (isAscii)
//				isAscii = det.isAscii(buf, len);
//			// DoIt if non-ascii and not done yet.
//			if (!isAscii && !done)
//				done = det.DoIt(buf, len, false);
//		}
//		det.DataEnd();
//		if (isAscii) {
//			encoding = "ASCII";
//			found = true;
//		}
//		if (!found) {
//			String prob[] = det.getProbableCharsets();
//			if (prob.length > 0) {
//				// 在没有发现情况下，则取第一个可能的编码
//				encoding = prob[0];
//			}else {
//				return null;
//			}
//		}
//		return encoding;
//	}

	public static void main(String[] args) {
		// 测试文件是否存在
//		IFileOperate fileDao = new FileOperateImpl(new LogImpl());
//		SmbFile file = fileDao.listDirFromSmb("smb://Guest:ssdsms@172.21.138.204/2010/");
//		try {
//			if (file.exists()) {
//				if (file.isDirectory()) {
//					String[] dirArr = file.list();
//					for (String subFileName : dirArr) {
//						System.out.println(subFileName);
//					}
//				}
//			}
//		}catch (SmbException e) {
//			e.printStackTrace();
//		}
	}
}
