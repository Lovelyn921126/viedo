package com.ultrapower.viedo.utils.viedo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DownloadTask {

	private  String charset;

	String fileName;

	private int threadNum;
	URL url;
	private long threadLength;
	String fileDir;
	private ChildThread[] childThreads;
	boolean statusError;
	public URL getUrl() {
		return url;
	}
	public void setUrl(URL url) {
		this.url = url;
	}
	//多线程下载
	  public String download(String urlStr, String charset) throws Exception {  
		  // 从url中获得下载的文件格式与名字 
		  this.charset = charset;  
	        long contentLength = 0;  
	       CountDownLatch latch = new CountDownLatch(threadNum);  
	        long[] startPos = new long[threadNum];  
	        long endPos = 0;  
	        this.fileName = urlStr.substring(urlStr.lastIndexOf("/") + 1, urlStr.lastIndexOf("?")>0 ? urlStr.lastIndexOf("?") : urlStr.length()); 
	        if("".equalsIgnoreCase(this.fileName)){  
                this.fileName = UUID.randomUUID().toString();  
            }  
	        this.url = new URL(urlStr); 
	        URLConnection con = url.openConnection();  
            setHeader(con); 
            // 得到content的长度  
            contentLength = con.getContentLength();  
            // 把context分为threadNum段的话，每段的长度。  
            this.threadLength = contentLength / threadNum;  
            // 第一步，分析已下载的临时文件，设置断点，如果是新的下载任务，则建立目标文件。在第4点中说明。  
            startPos = setThreadBreakpoint(fileDir, fileName, contentLength, startPos);  
            
            //第二步，分多个线程下载文件  
            ExecutorService exec = Executors.newCachedThreadPool();  
            for (int i = 0; i < threadNum; i++) {  
                // 创建子线程来负责下载数据，每段数据的起始位置为(threadLength * i + 已下载长度)  
                startPos[i] += threadLength * i;  
  
                /*设置子线程的终止位置，非最后一个线程即为(threadLength * (i + 1) - 1) 
                最后一个线程的终止位置即为下载内容的长度*/  
                if (i == threadNum - 1) {  
                    endPos = contentLength;  
                } else {  
                    endPos = threadLength * (i + 1) - 1;  
                }  
                // 开启子线程，并执行。  
                ChildThread thread = new ChildThread(this, latch, i, startPos[i], endPos);  
                childThreads[i] = thread;  
                exec.execute(thread);  
            }
			return charset;  
  
	  }
	  private void tempFileToTargetFile(ChildThread[] childThreads) {  
		    try {  
		        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(fileDir + fileName));  
		  
		        // 遍历所有子线程创建的临时文件，按顺序把下载内容写入目标文件中  
		        for (int i = 0; i < threadNum; i++) {  
		            if (statusError) {  
		                for (int k = 0; k < threadNum; k++) {  
		                    if (childThreads[k].getTempFile().length() == 0)  
		                        childThreads[k].getTempFile().delete();  
		                }  
		                System.out.println("本次下载任务不成功，请重新设置线程数。");  
		                break;  
		            }  
		  
		            BufferedInputStream inputStream = new BufferedInputStream(  
		                    new FileInputStream(childThreads[i].getTempFile()));  
		            System.out.println("Now is file " + childThreads[i].getId());  
		            int len = 0;  
		            long count = 0;  
		            byte[] b = new byte[1024];  
		            while ((len = inputStream.read(b)) != -1) {  
		                count += len;  
		                outputStream.write(b, 0, len);  
		                if ((count % 4096) == 0) {  
		                    outputStream.flush();  
		                }  
		  
		                // b = new byte[1024];  
		            }  
		  
		            inputStream.close();  
		            // 删除临时文件  
		            if (childThreads[i].status == ChildThread.STATUS_HAS_FINISHED) {  
		                childThreads[i].getTempFile().delete();  
		            }  
		        }  
		  
		        outputStream.flush();  
		        outputStream.close();  
		    } catch (FileNotFoundException e) {  
		        e.printStackTrace();  
		    } catch (IOException e) {  
		        e.printStackTrace();  
		    }  
		}  
	  private void setHeader(URLConnection con) {  
		    con.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9.0.3) Gecko/2008092510 Ubuntu/8.04 (hardy) Firefox/3.0.3");  
		    con.setRequestProperty("Accept-Language", "en-us,en;q=0.7,zh-cn;q=0.3");  
		    con.setRequestProperty("Accept-Encoding", "aa");  
		    con.setRequestProperty("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.7");  
		    con.setRequestProperty("Keep-Alive", "300");  
		    con.setRequestProperty("Connection", "keep-alive");  
		    con.setRequestProperty("If-Modified-Since", "Fri, 02 Jan 2009 17:00:05 GMT");  
		    con.setRequestProperty("If-None-Match", "\"1261d8-4290-df64d224\"");  
		    con.setRequestProperty("Cache-Control", "max-age=0");  
		    con.setRequestProperty("Referer", "http://www.dianping.com");  
		} 
	//第一步，分析已下载的临时文件，设置断点，如果是新的下载任务，则建立目标文件。  
	  private long[] setThreadBreakpoint(String fileDir2, String fileName2,  
	          long contentLength, long[] startPos) {  
	    
		File file = new File(fileDir + fileName);  
	      long localFileSize = file.length();  
	    
	      if (file.exists()) {  
	          System.out.println("file " + fileName + " has exists!");  
	          // 下载的目标文件已存在，判断目标文件是否完整  
	          if (localFileSize < contentLength) {  
	              System.out.println("Now download continue ... ");  
	    
	              // 遍历目标文件的所有临时文件，设置断点的位置，即每个临时文件的长度  
	              File tempFileDir = new File(fileDir);  
	              File[] files = tempFileDir.listFiles();  
	              for (int k = 0; k < files.length; k++) {  
	                  String tempFileName = files[k].getName();  
	                  // 临时文件的命名方式为：目标文件名+"_"+编号  
	                  if (tempFileName != null && files[k].length() > 0  
	                          && tempFileName.startsWith(fileName + "_")) {  
	                      int fileLongNum = Integer.parseInt(tempFileName  
	                              .substring(tempFileName.lastIndexOf("_") + 1,  
	                                      tempFileName.lastIndexOf("_") + 2));  
	                      // 为每个线程设置已下载的位置  
	                      startPos[fileLongNum] = files[k].length();  
	                  }  
	              }  
	          }  
	      } else {  
	          // 如果下载的目标文件不存在，则创建新文件  
	          try {  
	              file.createNewFile();  
	          } catch (IOException e) {  
	              e.printStackTrace();  
	          }  
	      }  
	    
	      return startPos;  
	  }  
		public String getCharset() {
			return charset;
		}
		public void setCharset(String charset) {
			this.charset = charset;
		}
		public String getFileName() {
			return fileName;
		}
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
		public int getThreadNum() {
			return threadNum;
		}
		public void setThreadNum(int threadNum) {
			this.threadNum = threadNum;
		}
		public long getThreadLength() {
			return threadLength;
		}
		public void setThreadLength(long threadLength) {
			this.threadLength = threadLength;
		}
		public String getFileDir() {
			return fileDir;
		}
		public void setFileDir(String fileDir) {
			this.fileDir = fileDir;
		}
		public ChildThread[] getChildThreads() {
			return childThreads;
		}
		public void setChildThreads(ChildThread[] childThreads) {
			this.childThreads = childThreads;
		}
		public boolean isStatusError() {
			return statusError;
		}
		public void setStatusError(boolean statusError) {
			this.statusError = statusError;
		}

}
